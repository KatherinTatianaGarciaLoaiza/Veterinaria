package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.command.AdicionarVendedor;
import dominio.veterinaria.tienda.events.TiendaCreada;
import dominio.veterinaria.tienda.events.VendedorAdicionado;
import dominio.veterinaria.tienda.objetosdevalor.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AdicionarVendedorUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void AdicionarVendedorUseCaseTest(){
        var tiendaId = TiendaId.of("123");
        var vendedorId = new VendedorId();
        var telefono = new Telefono(4778905L);
        var nombre = new Nombre("Shiro");
        var direccion = new Direccion("Casa de shiro");
        var correo = new Correo("shiro-sama@udea.edu.co");
        var command = new AdicionarVendedor(vendedorId, tiendaId, telefono, nombre, direccion, correo);

        var useCase = new AdicionarVendedorUseCase();
        useCase.addRepository(repository);

        when(repository.getEventsBy(Mockito.any())).thenReturn(eventStored());
        List<DomainEvent> events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(tiendaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        VendedorAdicionado event = (VendedorAdicionado) events.get(0);
        Mockito.verify(repository).getEventsBy(tiendaId.value());

        Assertions.assertEquals(4778905L, event.getTelefono().value());
        Assertions.assertEquals("Shiro", event.getNombre().value());
        Assertions.assertEquals("Casa de shiro", event.getDireccion().value());
        Assertions.assertEquals("shiro-sama@udea.edu.co", event.getCorreo().value());
    }

    private  List<DomainEvent> eventStored(){
        return List.of(new TiendaCreada(new Telefono(4778905L), new Direccion("Casa de kuro")));
    }
}