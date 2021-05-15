package dominio.veterinaria.tienda.command;

import co.com.sofka.domain.generic.Command;
import dominio.veterinaria.tienda.objetosdevalor.*;

public class ModificarDatosPersonalesCliente implements Command {

    private final ClienteId clienteId;
    private final TiendaId tiendaId;
    private final DatosDePago datosDePago;
    private final Nombre nombre;
    private final Telefono telefono;

    public ModificarDatosPersonalesCliente(ClienteId clienteId, TiendaId tiendaId, DatosDePago datosDePago, Nombre nombre, Telefono telefono){
        this.clienteId = clienteId;
        this.tiendaId = tiendaId;
        this.datosDePago = datosDePago;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public TiendaId getTiendaId() {
        return tiendaId;
    }

    public ClienteId getClienteId() {
        return clienteId;
    }

    public DatosDePago getDatosDePago() {
        return datosDePago;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Telefono getTelefono() {
        return telefono;
    }
}
