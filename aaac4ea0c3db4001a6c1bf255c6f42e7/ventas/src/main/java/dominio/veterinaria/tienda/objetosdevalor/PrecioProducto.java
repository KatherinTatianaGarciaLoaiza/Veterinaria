package dominio.veterinaria.tienda.objetosdevalor;

import co.com.sofka.domain.generic.ValueObject;

public class PrecioProducto implements ValueObject<Long> {
    private final Long precio;

    public PrecioProducto(Long precio){
            this.precio = precio;
        }

        public Long value() {
            return precio;
        }
}
