package dominio.veterinaria.usecase.handle;


import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import dominio.veterinaria.tienda.command.ModificarDatosPersonalesCliente;
import dominio.veterinaria.tienda.objetosdevalor.*;
import dominio.veterinaria.usecase.ModificarDatosPersonalesClienteUseCase;
import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "veterinaria.tienda.modificardatospersonalescliente", aggregate = "tienda")
public class ModificarDatosPersonalesClienteHandle extends UseCaseExecutor {

    private RequestCommand<ModificarDatosPersonalesCliente> request;

    @Override
    public void run() {
        runUseCase(new ModificarDatosPersonalesClienteUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {
        var clienteId = Objects.requireNonNull(args.get("clienteId"));
        var banco = Objects.requireNonNull(args.get("banco"));
        var numCuenta = Objects.requireNonNull(args.get("nume de cuenta"));
        var mediodepago = Objects.requireNonNull(args.get("medio de pago"));
        var numcuotas = Objects.requireNonNull(args.get("numero de cuotas"));
        var datosDePago = new DatosDePago(banco, Long.parseLong(numCuenta), MedioDePago.valueOf(mediodepago.toUpperCase()),
                Integer.parseInt(numcuotas));
        var nombre = Objects.requireNonNull(args.get("nombre"));
        var telefono = Objects.requireNonNull("telefono");

        request = new RequestCommand<>(new ModificarDatosPersonalesCliente(ClienteId.of(clienteId), TiendaId.of(aggregateId()),
                datosDePago,new Nombre(nombre), new Telefono(Long.parseLong(telefono))));
    }
}
