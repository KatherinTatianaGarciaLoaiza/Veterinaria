package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.command.ModificarDatosPersonalesVendedor;
import dominio.veterinaria.tienda.events.DatosPersonalesDelVendedorModificados;
import dominio.veterinaria.tienda.events.TiendaCreada;
import dominio.veterinaria.tienda.events.VendedorAdicionado;
import dominio.veterinaria.tienda.objetosdevalor.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModificarDatosPersonalesVendedorUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void ModificarDatosPersonalesVendedorUseCaseTest(){
        var tiendaId = TiendaId.of("258");
        var vendedorId = VendedorId.of("456");
        var telefono = new Telefono(5098774L);
        var nombre = new Nombre("Shiro");
        var direccion = new Direccion("Casa de Shiro");
        var correo = new Correo("shiro-sama@udea.edu.co");
        var command = new ModificarDatosPersonalesVendedor(vendedorId, tiendaId, telefono, nombre, direccion, correo);

        var useCase = new ModificarDatosPersonalesVendedorUseCase();
        useCase.addRepository(repository);

        when(repository.getEventsBy(Mockito.any())).thenReturn(eventStored());
        List<DomainEvent> events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(tiendaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        DatosPersonalesDelVendedorModificados event = (DatosPersonalesDelVendedorModificados) events.get(0);
        Mockito.verify(repository).getEventsBy(tiendaId.value());

        Assertions.assertEquals("258", event.aggregateRootId());
        Assertions.assertEquals("456", event.getVendedorId().value());
        Assertions.assertEquals(5098774L, event.getTelefono().value());
        Assertions.assertEquals("Shiro", event.getNombre().value());
        Assertions.assertEquals("Casa de Shiro", event.getDireccion().value());
        Assertions.assertEquals("shiro-sama@udea.edu.co", event.getCorreo().value());
    }

    private List<DomainEvent> eventStored(){
        return List.of(new TiendaCreada(new Telefono(852369L), new Direccion("calle wollowick")),
                new VendedorAdicionado(VendedorId.of("456"),new Telefono(4778905L), new Nombre("Shiro"), new Direccion("Casa de Shiro y Kuro"), new Correo("shiro-sama@udea.edu.co")));
    }
}