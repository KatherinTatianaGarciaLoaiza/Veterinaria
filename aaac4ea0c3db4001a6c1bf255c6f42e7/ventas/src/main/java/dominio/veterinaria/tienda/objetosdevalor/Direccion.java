package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.ValueObject;

public class Direccion implements ValueObject<String> {

    private final String direccion;

    public Direccion(String direccion){
        this.direccion = direccion;
    }

    public String value() {
        return direccion;
    }
}
