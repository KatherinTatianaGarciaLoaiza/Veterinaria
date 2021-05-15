package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import dominio.veterinaria.tienda.Tienda;
import dominio.veterinaria.tienda.command.CrearTienda;

public class CrearTiendaUseCase extends UseCase<RequestCommand<CrearTienda>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearTienda> crearTiendaRequestCommand) {
        var command = crearTiendaRequestCommand.getCommand();

            var tienda = new Tienda(
                    command.getTiendaId(),
                    command.getTelefono(),
                    command.getDireccion());
            emit().onResponse(new ResponseEvents(tienda.getUncommittedChanges()));
    }
}
