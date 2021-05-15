package dominio.veterinaria.tienda.command;

import co.com.sofka.domain.generic.Command;
import dominio.veterinaria.tienda.objetosdevalor.Nombre;
import dominio.veterinaria.tienda.objetosdevalor.PrecioProducto;
import dominio.veterinaria.tienda.objetosdevalor.ProductoId;
import dominio.veterinaria.tienda.objetosdevalor.TiendaId;

public class AdicionarProducto implements Command {

    private final ProductoId productoId;
    private final TiendaId tiendaId;
    private final PrecioProducto precioProducto;
    private final Nombre nombre;

    public AdicionarProducto(ProductoId productoId, TiendaId tiendaId, PrecioProducto precioProducto, Nombre nombre){
        this.productoId = productoId;
        this.tiendaId = tiendaId;
        this.precioProducto = precioProducto;
        this.nombre = nombre;
    }

    public ProductoId getProductoId() {
        return productoId;
    }

    public TiendaId getTiendaId() {
        return tiendaId;
    }

    public PrecioProducto getPrecioProducto() {
        return precioProducto;
    }

    public Nombre getNombre() {
        return nombre;
    }
}
