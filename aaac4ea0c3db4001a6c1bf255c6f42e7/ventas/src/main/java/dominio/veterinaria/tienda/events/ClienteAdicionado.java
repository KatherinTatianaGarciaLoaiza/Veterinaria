package dominio.veterinaria.tienda.events;

import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.objetosdevalor.*;

public class ClienteAdicionado extends DomainEvent {
    private final ClienteId clienteId;
    private final DatosDePago datosDePago;
    private final Nombre nombre;
    private final Telefono telefono;

    public ClienteAdicionado(ClienteId clienteId, DatosDePago datosDePago, Nombre nombre, Telefono telefono) {
        super("veterinaria.tienda.clienteadicionado");
        this.clienteId = clienteId;
        this.datosDePago = datosDePago;
        this.nombre = nombre;
        this.telefono = telefono;
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
