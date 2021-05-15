package dominio.veterinaria.tienda.events;

import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.objetosdevalor.*;

public class TiendaCreada extends DomainEvent {

    private final Telefono telefono;
    private final Direccion direccion;

    public TiendaCreada(Telefono telefono, Direccion direccion) {
        super("veterinaria.tienda.tiendacreada");
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
