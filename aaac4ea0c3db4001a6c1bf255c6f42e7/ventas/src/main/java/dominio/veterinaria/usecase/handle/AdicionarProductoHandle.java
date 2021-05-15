package dominio.veterinaria.usecase.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import dominio.veterinaria.tienda.command.AdicionarProducto;
import dominio.veterinaria.tienda.objetosdevalor.Nombre;
import dominio.veterinaria.tienda.objetosdevalor.PrecioProducto;
import dominio.veterinaria.tienda.objetosdevalor.ProductoId;
import dominio.veterinaria.tienda.objetosdevalor.TiendaId;
import dominio.veterinaria.usecase.AdicionarProductoUseCase;
import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "veterinaria.tienda.adicionarproducto", aggregate = "tienda")
public class AdicionarProductoHandle  extends UseCaseExecutor {

    private RequestCommand<AdicionarProducto> request;


    @Override
    public void run() {
        runUseCase(new AdicionarProductoUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {
        var productoId = Objects.requireNonNull(args.get("productoId"));
        var precioProducto = Objects.requireNonNull(args.get("precio del producto"));
        var nombre = Objects.requireNonNull(args.get("nombre"));

        request = new RequestCommand<>(new AdicionarProducto(ProductoId.of(productoId), TiendaId.of(aggregateId()),
                new PrecioProducto(Long.parseLong(precioProducto)), new Nombre(nombre)));
    }
}
