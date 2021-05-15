package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import dominio.veterinaria.tienda.Tienda;
import dominio.veterinaria.tienda.command.AdicionarProducto;

public class AdicionarProductoUseCase extends UseCase<RequestCommand<AdicionarProducto>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AdicionarProducto> adicionarProductoRequestCommand) {
        var command = adicionarProductoRequestCommand.getCommand();

        var tienda = Tienda.from(command.getTiendaId(), retrieveEvents());

        tienda.adicionarproducto(command.getProductoId(),
                command.getPrecioProducto(),
                command.getNombre());
        emit().onResponse(new ResponseEvents(tienda.getUncommittedChanges()));
    }
}
