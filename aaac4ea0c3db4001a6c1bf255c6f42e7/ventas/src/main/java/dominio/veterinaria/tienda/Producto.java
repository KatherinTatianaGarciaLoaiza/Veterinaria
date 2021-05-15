package dominio.veterinaria.tienda;

import co.com.sofka.domain.generic.Entity;
import dominio.veterinaria.tienda.objetosdevalor.ProductoId;
import dominio.veterinaria.tienda.objetosdevalor.Nombre;
import dominio.veterinaria.tienda.objetosdevalor.PrecioProducto;

public class Producto extends Entity<ProductoId> {

    protected PrecioProducto precioProducto;
    protected Nombre nombre;

    public Producto(ProductoId productoId, PrecioProducto precioProducto, Nombre nombre) {
        super(productoId);
        this.precioProducto = precioProducto;
        this.nombre = nombre;
    }

    private Producto(ProductoId productoId){super(productoId);}
}
