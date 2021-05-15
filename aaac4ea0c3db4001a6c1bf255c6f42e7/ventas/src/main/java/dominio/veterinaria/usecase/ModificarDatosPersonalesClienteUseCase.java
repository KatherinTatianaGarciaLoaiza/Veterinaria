package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import dominio.veterinaria.tienda.Tienda;
import dominio.veterinaria.tienda.command.ModificarDatosPersonalesCliente;

public class ModificarDatosPersonalesClienteUseCase extends UseCase<RequestCommand<ModificarDatosPersonalesCliente>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<ModificarDatosPersonalesCliente> modificarDatosPersonalesClienteRequestCommand) {
        var command = modificarDatosPersonalesClienteRequestCommand.getCommand();

        var tienda = Tienda.from(command.getTiendaId(), retrieveEvents());

        tienda.modificarDatosPersonalesCliente(command.getClienteId(),
                command.getDatosDePago(),
                command.getNombre(),
                command.getTelefono());
        emit().onResponse(new ResponseEvents(tienda.getUncommittedChanges()));

    }
}
