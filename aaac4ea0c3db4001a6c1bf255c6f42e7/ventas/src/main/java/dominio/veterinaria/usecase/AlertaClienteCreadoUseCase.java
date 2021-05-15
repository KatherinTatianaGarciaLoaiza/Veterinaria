package dominio.veterinaria.usecase;

import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import dominio.veterinaria.tienda.Cliente;
import dominio.veterinaria.tienda.Tienda;
import dominio.veterinaria.tienda.events.ClienteAdicionado;
import dominio.veterinaria.tienda.objetosdevalor.TiendaId;

@EventListener(eventType = "veterinaria.tienda.clinteadicionado")
public class AlertaClienteCreadoUseCase extends UseCase<TriggeredEvent<ClienteAdicionado>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<ClienteAdicionado> clienteAdicionadoTriggeredEvent) {
        var event = clienteAdicionadoTriggeredEvent.getDomainEvent();

        var tienda = Tienda.from(TiendaId.of(event.aggregateRootId()), retrieveEvents());

        tienda.alertaClienteCreado(new Cliente(event.getClienteId(),
                event.getDatosDePago(),
                event.getNombre(),
                event.getTelefono()));
        emit().onResponse(new ResponseEvents(tienda.getUncommittedChanges()));
    }
}
