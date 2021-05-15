package dominio.veterinaria.tienda.events;

import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.objetosdevalor.*;

public class DatosPersonalesDelVendedorModificados extends DomainEvent {
    private final VendedorId vendedorId;
    private final Telefono telefono;
    private final Nombre nombre;
    private final Direccion direccion;
    private final Correo correo;

    public DatosPersonalesDelVendedorModificados(VendedorId vendedorId, Telefono telefono, Nombre nombre, Direccion direccion, Correo correo) {
        super("veterinaria.tienda.datospersonalesdelvendedormodificados");
        this.vendedorId = vendedorId;
        this.telefono = telefono;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
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
