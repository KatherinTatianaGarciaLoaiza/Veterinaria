package dominio.veterinaria.tienda;

import co.com.sofka.domain.generic.Entity;
import dominio.veterinaria.tienda.objetosdevalor.*;

public class Cliente  extends Entity<ClienteId> {

    protected DatosDePago datosDePago;
    protected Nombre nombre;
    protected Telefono telefono;
    protected BonoDeDescuento bono;

    public Cliente(ClienteId clienteId, DatosDePago datosDePago, Nombre nombre, Telefono telefono) {
        super(clienteId);
        this.datosDePago = datosDePago;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public BonoDeDescuento getBono() {
        return bono;
    }

    public void setDatosDePago(DatosDePago datosDePago) {
        this.datosDePago = datosDePago;
    }

    public void setNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public void setBono(BonoDeDescuento bono) {
        this.bono = bono;
    }

    private Cliente(ClienteId clienteId){super(clienteId);}

    public void agregarBono(BonoDeDescuento bonoDeDescuento){
        setBono(bonoDeDescuento);
    }

    public void cambiarDatosPersonalesDelCliente(DatosDePago datosDePago, Nombre nombre, Telefono telefono){
        setDatosDePago(datosDePago);
        setNombre(nombre);
        setTelefono(telefono);
    }
}
