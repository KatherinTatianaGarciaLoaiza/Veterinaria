package dominio.veterinaria.tienda.command;

import co.com.sofka.domain.generic.Command;
import dominio.veterinaria.tienda.objetosdevalor.*;

public class ModificarDatosPersonalesVendedor  implements Command {

    private final VendedorId vendedorId;
    private final TiendaId tiendaId;
    private final Telefono telefono;
    private final Nombre nombre;
    private final Direccion direccion;
    private final Correo correo;

    public ModificarDatosPersonalesVendedor(VendedorId vendedorId, TiendaId tiendaId, Telefono telefono, Nombre nombre, Direccion direccion, Correo correo){
        this.vendedorId = vendedorId;
        this.tiendaId = tiendaId;
        this.telefono = telefono;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
    }

    public TiendaId getTiendaId() {
        return tiendaId;
    }

    public VendedorId getVendedorId() {
        return vendedorId;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Correo getCorreo() {
        return correo;
    }
}
