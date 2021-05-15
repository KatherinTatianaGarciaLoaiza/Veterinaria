package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.ValueObject;


public class Nombre implements ValueObject<String> {

    private final String nombre;

    public Nombre(String nombre){
        this.nombre = nombre;
    }

    public String value() {
        return nombre;
    }
}
