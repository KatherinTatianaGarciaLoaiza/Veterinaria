package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import dominio.veterinaria.tienda.Tienda;
import dominio.veterinaria.tienda.command.AdicionarVendedor;

public class AdicionarVendedorUseCase extends UseCase<RequestCommand<AdicionarVendedor>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AdicionarVendedor> adicionarVendedorRequestCommand) {
        var command = adicionarVendedorRequestCommand.getCommand();

        var tienda = Tienda.from(command.getTiendaId(), retrieveEvents());

        tienda.adcionarvendedor(command.getVendedorId(),
                command.getTelefono(),
                command.getNombre(),
                command.getDireccion(),
                command.getCorreo());
        emit().onResponse(new ResponseEvents(tienda.getUncommittedChanges()));
    }
}
