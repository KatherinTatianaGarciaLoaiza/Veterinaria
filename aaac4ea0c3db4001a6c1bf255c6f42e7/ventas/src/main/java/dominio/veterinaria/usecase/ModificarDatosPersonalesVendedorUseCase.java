package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import dominio.veterinaria.tienda.Tienda;
import dominio.veterinaria.tienda.command.ModificarDatosPersonalesVendedor;

public class ModificarDatosPersonalesVendedorUseCase  extends UseCase<RequestCommand<ModificarDatosPersonalesVendedor>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<ModificarDatosPersonalesVendedor> modificarDatosPersonalesVendedorRequestCommand) {
        var command = modificarDatosPersonalesVendedorRequestCommand.getCommand();

        var tienda = Tienda.from(command.getTiendaId(), retrieveEvents());

        tienda.modificarDatosPersonalesVendedor(command.getVendedorId(),
                command.getTelefono(),
                command.getNombre(),
                command.getDireccion(),
                command.getCorreo());
        emit().onResponse(new ResponseEvents(tienda.getUncommittedChanges()));
    }
}
