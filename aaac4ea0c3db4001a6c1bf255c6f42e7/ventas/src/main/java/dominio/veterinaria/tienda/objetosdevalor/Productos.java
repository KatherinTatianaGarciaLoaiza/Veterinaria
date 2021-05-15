package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.ValueObject;

import java.util.List;

public class Productos  implements ValueObject<List> {

    private final List listaDeProductos;

    public Productos(List listaDeProductos){
        this.listaDeProductos = listaDeProductos;
    }

    public List value() {
        return listaDeProductos;
    }
}
