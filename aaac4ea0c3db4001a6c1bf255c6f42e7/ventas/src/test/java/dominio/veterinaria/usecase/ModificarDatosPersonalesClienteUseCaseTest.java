package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.command.ModificarDatosPersonalesCliente;
import dominio.veterinaria.tienda.events.ClienteAdicionado;
import dominio.veterinaria.tienda.events.DatosPersonalesDelClienteModificados;
import dominio.veterinaria.tienda.events.TiendaCreada;
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
class ModificarDatosPersonalesClienteUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void ModificarDatosPersonalesClienteUseCaseTest(){
        var tiendaId = TiendaId.of("951");
        var clienteId = ClienteId.of("2648");
        var datosDePago = new DatosDePago("Bancolombia", 654789321L, MedioDePago.EFECTIVO, 1);
        var nombre = new Nombre("Shiro-sama");
        var telefono = new Telefono(4778905L);
        var command = new ModificarDatosPersonalesCliente(clienteId, tiendaId, datosDePago, nombre, telefono);

        var useCase = new ModificarDatosPersonalesClienteUseCase();
        useCase.addRepository(repository);

        when(repository.getEventsBy(Mockito.any())).thenReturn(eventStored());
        List<DomainEvent> events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(tiendaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        DatosPersonalesDelClienteModificados event = (DatosPersonalesDelClienteModificados) events.get(0);
        Mockito.verify(repository).getEventsBy(tiendaId.value());

        Assertions.assertEquals("951", event.aggregateRootId());
        Assertions.assertEquals("2648", event.getClienteId().value());
        Assertions.assertEquals(4778905L, event.getTelefono().value());
        Assertions.assertEquals("Shiro-sama", event.getNombre().value());

    }

    private List<DomainEvent> eventStored(){
        return List.of(new TiendaCreada(new Telefono(852369L), new Direccion("calle wollowick")),
                new ClienteAdicionado(ClienteId.of("2648"), new DatosDePago("Bancolombia", 654789321L, MedioDePago.EFECTIVO, 1), new Nombre("Shiro"), new Telefono(5098774L)));
    }

}