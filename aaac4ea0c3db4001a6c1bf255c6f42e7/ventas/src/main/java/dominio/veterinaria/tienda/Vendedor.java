package dominio.veterinaria.tienda;

import co.com.sofka.domain.generic.Entity;
import dominio.veterinaria.tienda.objetosdevalor.*;

public class Vendedor extends Entity<VendedorId> {

    protected Telefono telefono;
    protected Nombre nombre;
    protected Direccion direccion;
    protected Correo correo;

    public Vendedor(VendedorId vendedorId, Telefono telefono, Nombre nombre, Direccion direccion, Correo correo) {
        super(vendedorId);
        this.telefono = telefono;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
    }

    private Vendedor(VendedorId vendedorId){super(vendedorId);}

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

    public void cambiarDatosPersonalesDelVendedor(Telefono telefono, Nombre nombre, Direccion direccion, Correo correo){
        setTelefono(telefono);
        setNombre(nombre);
        setDireccion(direccion);
        setCorreo(correo);
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public void setNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(Correo correo) {
        this.correo = correo;
    }
}
