package dominio.veterinaria.tienda.events;

import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.objetosdevalor.ClienteId;
import dominio.veterinaria.tienda.objetosdevalor.DatosDePago;
import dominio.veterinaria.tienda.objetosdevalor.Nombre;
import dominio.veterinaria.tienda.objetosdevalor.Telefono;

public class DatosPersonalesDelClienteModificados extends DomainEvent {
    private final ClienteId clienteId;
    private final DatosDePago datosDePago;
    private final Nombre nombre;
    private final Telefono telefono;

    public DatosPersonalesDelClienteModificados(ClienteId clienteId, DatosDePago datosDePago, Nombre nombre, Telefono telefono) {
        super("veterinaria.tienda.datospersonalesdelvendedormodificados");
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
