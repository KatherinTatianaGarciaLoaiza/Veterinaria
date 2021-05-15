package dominio.veterinaria.tienda;

import co.com.sofka.domain.generic.EventChange;
import dominio.veterinaria.tienda.events.*;
import dominio.veterinaria.tienda.objetosdevalor.BonoDeDescuento;
import dominio.veterinaria.tienda.objetosdevalor.FormulaMedica;
import java.util.ArrayList;

public class TiendaChange extends EventChange {

    public TiendaChange(Tienda tienda) {
        apply((TiendaCreada event) -> {
            tienda.vendedores = new ArrayList<Vendedor>();
            tienda.productos = new ArrayList<Producto>();
            tienda.clientes = new ArrayList<Cliente>();
            tienda.formulasMedicas = new ArrayList<FormulaMedica>();
            tienda.facturas = new ArrayList<Factura>();
            tienda.direccion = event.getDireccion();
            tienda.telefono = event.getTelefono();
        });

        apply((VendedorAdicionado event) -> {
            tienda.vendedores.add(new Vendedor(event.getVendedorId(), event.getTelefono(), event.getNombre(), event.getDireccion(), event.getCorreo()));
        });

        apply((ProductoAdicionado event) -> {
            tienda.productos.add(new Producto(event.getProductoId(), event.getPrecioProducto(), event.getNombre()));
        });

        apply((ClienteAdicionado event) -> {
            tienda.clientes.add(new Cliente(event.getClienteId(), event.getDatosDePago(), event.getNombre(), event.getTelefono()));
        });

        apply((AlertaClienteAdicionado event) -> {
            System.out.println("!!!CLIENTE ADICIONADO¡¡¡");
        });

        apply((DatosPersonalesDelVendedorModificados event) -> {
            for (Vendedor vendedor: tienda.vendedores) {
                if(vendedor.identity().equals(event.getVendedorId())) {
                    vendedor.cambiarDatosPersonalesDelVendedor(event.getTelefono(), event.getNombre(), event.getDireccion(), event.getCorreo());
                }
            }
        });

        apply((DatosPersonalesDelClienteModificados event) -> {
            for (Cliente cliente: tienda.clientes){
                if (cliente.identity().equals(event.getClienteId())){
                    cliente.cambiarDatosPersonalesDelCliente(event.getDatosDePago(), event.getNombre(), event.getTelefono());
                }
            }
        });

        apply((BonoAgregado event) -> {
            for (Cliente cliente: tienda.clientes){
                if(cliente.identity().equals(event.getClienteId())){
                    cliente.agregarBono(new BonoDeDescuento(20000L));
                }
            }
        });
    }
}
