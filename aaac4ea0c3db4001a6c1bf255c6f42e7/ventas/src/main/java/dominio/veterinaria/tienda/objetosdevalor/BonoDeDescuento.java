package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.ValueObject;

public class BonoDeDescuento implements ValueObject<Long> {

    private final Long bono;

    public BonoDeDescuento(Long bono){

        this.bono = bono;
    }

    @Override
    public Long value() {
        return bono;
    }
}
