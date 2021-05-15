package dominio.veterinaria.usecase.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import dominio.veterinaria.tienda.command.ModificarDatosPersonalesVendedor;
import dominio.veterinaria.tienda.objetosdevalor.*;
import dominio.veterinaria.usecase.ModificarDatosPersonalesVendedorUseCase;

import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "veterinaria.tienda.modificardatospersonalesvendedor", aggregate = "tienda")
public class ModificarDatosPersonalesVendedorHandle extends UseCaseExecutor {

    private RequestCommand<ModificarDatosPersonalesVendedor> request;

    @Override
    public void run() {
        runUseCase(new ModificarDatosPersonalesVendedorUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {
        var vendedorId = Objects.requireNonNull(args.get("vendedorId"));
        var telefono = Objects.requireNonNull(args.get("telefono"));
        var nombre = Objects.requireNonNull(args.get("nombre"));
        var direccion = Objects.requireNonNull(args.get("direccion"));
        var correo = Objects.requireNonNull(args.get("correo"));

        request = new RequestCommand<>(new ModificarDatosPersonalesVendedor(VendedorId.of(vendedorId), TiendaId.of(aggregateId()),
                new Telefono(Long.parseLong(telefono)), new Nombre(nombre), new Direccion(direccion), new Correo(correo)));
    }
}
