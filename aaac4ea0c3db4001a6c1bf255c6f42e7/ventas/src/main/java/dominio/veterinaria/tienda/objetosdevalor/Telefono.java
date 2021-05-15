package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.ValueObject;

public class Telefono implements ValueObject<Long> {

    private final Long telefono;

    public Telefono(Long telefono){
        this.telefono = telefono;
    }

    public Long value() {
        return telefono;
    }
}

