package dominio.veterinaria.tienda;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.events.*;
import dominio.veterinaria.tienda.objetosdevalor.*;
import java.util.List;


public class Tienda extends AggregateEvent<TiendaId> {

    protected List<FormulaMedica> formulasMedicas;
    protected List<Vendedor> vendedores;
    protected List<Cliente> clientes;
    protected List<Producto> productos;
    protected Telefono telefono;
    protected Direccion direccion;

    private Tienda(TiendaId tiendaId) {
        super(tiendaId);
        subscribe(new TiendaChange(this));
    }

    public Tienda(TiendaId tiendaId, Telefono telefono, Direccion direccion){
        super(tiendaId);
        appendChange(new TiendaCreada(telefono, direccion)).apply();
    }

    public static Tienda from(TiendaId tiendaId, List<DomainEvent> events){
        Tienda tienda = new Tienda(tiendaId);
        events.forEach(tienda :: applyEvent);
        return tienda;
    }

    public void adcionarvendedor(VendedorId vendedorId, Telefono telefono, Nombre nombre,
                                 Direccion direccion, Correo correo){
       appendChange(new VendedorAdicionado(vendedorId, telefono, nombre, direccion, correo)).apply();
    }

    public void adicionarproducto(ProductoId productoId, PrecioProducto precioProducto, Nombre nombre){
        appendChange(new ProductoAdicionado(productoId, precioProducto, nombre)).apply();
    }

    public void  adicionarcliente(ClienteId clienteId, DatosDePago datosDePago,
                                   Nombre nombre, Telefono telefono){
        appendChange(new ClienteAdicionado(clienteId, datosDePago, nombre, telefono)).apply();
    }

    public void modificarDatosPersonalesVendedor(VendedorId vendedorId, Telefono telefono, Nombre nombre, Direccion direccion, Correo correo){
        appendChange(new DatosPersonalesDelVendedorModificados(vendedorId, telefono, nombre, direccion, correo)).apply();
    }

    public void modificarDatosPersonalesCliente(ClienteId clienteId, DatosDePago datosDePago, Nombre nombre, Telefono telefono){
        appendChange(new DatosPersonalesDelClienteModificados(clienteId, datosDePago, nombre, telefono)).apply();
    }

    public void agregarBono(ClienteId clienteId){
        appendChange(new BonoAgregado(clienteId)).apply();
    }

    public void alertaClienteCreado(Cliente cliente){
        appendChange(new AlertaClienteAdicionado(cliente)).apply();
    }

}
