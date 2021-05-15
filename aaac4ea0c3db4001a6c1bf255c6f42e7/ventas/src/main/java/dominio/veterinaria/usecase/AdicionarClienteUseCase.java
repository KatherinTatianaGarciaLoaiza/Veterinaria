package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import dominio.veterinaria.tienda.Tienda;
import dominio.veterinaria.tienda.command.AdicionarCliente;

public class AdicionarClienteUseCase extends UseCase<RequestCommand<AdicionarCliente>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AdicionarCliente> adicionarClienteRequestCommand) {
        var command = adicionarClienteRequestCommand.getCommand();

        var tienda = Tienda.from(command.getTiendaId(), retrieveEvents());

        tienda.adicionarcliente(command.getClienteId(),
                command.getDatosDePago(),
                command.getNombre(),
                command.getTelefono());
        emit().onResponse(new ResponseEvents(tienda.getUncommittedChanges()));
    }
}
