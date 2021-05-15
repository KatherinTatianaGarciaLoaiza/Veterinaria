package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.events.AlertaClienteAdicionado;
import dominio.veterinaria.tienda.events.ClienteAdicionado;
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
class AlertaClienteCreadoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void AlertaClienteCreadoUseCaseTest(){
        var tiendaId = TiendaId.of("852");
        var clienteId = ClienteId.of("741");
        var datosdepago = new DatosDePago("Bancolombia", 200L, MedioDePago.EFECTIVO, 1);
        var nombre = new Nombre("Shiro");
        var telefono = new Telefono(4778905L);
        var event = new ClienteAdicionado(clienteId, datosdepago, nombre, telefono);

        event.setAggregateRootId(tiendaId.value());

        var useCase = new AlertaClienteCreadoUseCase();
        useCase.addRepository(repository);

        when(repository.getEventsBy(Mockito.any())).thenReturn(eventStored());
        List<DomainEvent> events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(tiendaId.value())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        AlertaClienteAdicionado clienteAgregado = (AlertaClienteAdicionado) events.get(0);
        Mockito.verify(repository).getEventsBy(tiendaId.value());

        Assertions.assertEquals("Shiro", clienteAgregado.getCliente().getNombre().value());
    }

    private List<DomainEvent> eventStored(){
        return List.of(new TiendaCreada(new Telefono(852369L), new Direccion("calle wollowick")),
                new ClienteAdicionado(new ClienteId(), new DatosDePago("Bancolombia",
                        200L, MedioDePago.EFECTIVO, 1), new Nombre("Shiro"), new Telefono(4778905L)));
    }

}