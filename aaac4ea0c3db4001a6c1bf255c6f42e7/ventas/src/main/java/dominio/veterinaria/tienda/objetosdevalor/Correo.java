package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.ValueObject;

public class Correo implements ValueObject<String> {

    private final String correo;

    public Correo(String correo){
        this.correo = correo;
    }

    public String value() {
        return correo;
    }
}
