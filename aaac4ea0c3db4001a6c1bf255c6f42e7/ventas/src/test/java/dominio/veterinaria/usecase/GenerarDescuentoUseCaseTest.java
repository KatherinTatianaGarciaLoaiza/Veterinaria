package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.events.BonoAgregado;
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
class GenerarDescuentoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void GenerarDescuentoUseCaseTest() {
        var tiendaId = TiendaId.of("741");
        var clienteId = ClienteId.of("963");

        var event = new ClienteAdicionado(clienteId, new DatosDePago("Bancolombia",
                200L, MedioDePago.EFECTIVO, 1), new Nombre("Shiro"), new Telefono(4778905L));
        event.setAggregateRootId(tiendaId.value());

        var useCase = new GenerarDescuentoUseCase();
        useCase.addRepository(repository);

        when(repository.getEventsBy(Mockito.any())).thenReturn(eventStored());
        List<DomainEvent> events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(tiendaId.value())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        BonoAgregado bonoAgregado = (BonoAgregado) events.get(0);
        Mockito.verify(repository).getEventsBy(tiendaId.value());

        Assertions.assertEquals(clienteId.value(), event.getClienteId().value());
    }

    private List<DomainEvent> eventStored(){
        return List.of(new TiendaCreada(new Telefono(852369L), new Direccion("calle wollowick")),
                new ClienteAdicionado(new ClienteId(), new DatosDePago("Bancolombia",
                        200L, MedioDePago.EFECTIVO, 1), new Nombre("Shiro"), new Telefono(4778905L)));
    }
}