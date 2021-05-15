package dominio.veterinaria.tienda.command;

import co.com.sofka.domain.generic.Command;
import dominio.veterinaria.tienda.objetosdevalor.*;

public class CrearTienda implements Command {

    private final TiendaId tiendaId;
    private final Telefono telefono;
    private final Direccion direccion;

    public CrearTienda(TiendaId tiendaId, Telefono telefono, Direccion direccion){
        this.tiendaId = tiendaId;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public TiendaId getTiendaId() {
        return tiendaId;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
