package dominio.veterinaria.tienda.events;

import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.objetosdevalor.Nombre;
import dominio.veterinaria.tienda.objetosdevalor.PrecioProducto;
import dominio.veterinaria.tienda.objetosdevalor.ProductoId;

public class ProductoAdicionado extends DomainEvent {
    private final ProductoId productoId;
    private final PrecioProducto precioProducto;
    private final Nombre nombre;

    public ProductoAdicionado(ProductoId productoId, PrecioProducto precioProducto, Nombre nombre) {
        super("veterinaria.tienda.productoadicionado");
        this.productoId = productoId;
        this.precioProducto = precioProducto;
        this.nombre = nombre;
    }

    public ProductoId getProductoId() {
        return productoId;
    }

    public PrecioProducto getPrecioProducto() {
        return precioProducto;
    }

    public Nombre getNombre() {
        return nombre;
    }
}
