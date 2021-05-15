package dominio.veterinaria.usecase.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import dominio.veterinaria.tienda.command.CrearTienda;
import dominio.veterinaria.tienda.objetosdevalor.Direccion;
import dominio.veterinaria.tienda.objetosdevalor.Telefono;
import dominio.veterinaria.tienda.objetosdevalor.TiendaId;
import dominio.veterinaria.usecase.CrearTiendaUseCase;
import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "veterinaria.tienda.creartienda", aggregate = "tienda")
public class CrearTiendaHandle extends UseCaseExecutor {

    private RequestCommand<CrearTienda> request;


    @Override
    public void run() {
        runUseCase(new CrearTiendaUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {
        var telefono = Objects.requireNonNull(args.get("telefono"));
        var direccion = Objects.requireNonNull(args.get("direccion"));

        request = new RequestCommand<>(new CrearTienda(TiendaId.of(aggregateId()), new Telefono(Long.parseLong(telefono)), new Direccion(direccion)));
    }
}
