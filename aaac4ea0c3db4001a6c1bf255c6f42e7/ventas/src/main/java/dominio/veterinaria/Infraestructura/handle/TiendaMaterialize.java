package dominio.veterinaria.Infraestructura.handle;

import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
public class TiendaMaterialize {

    private static final String COLLECTION_NAME = "tienda";
    private static final Logger logger = Logger.getLogger(TiendaMaterialize.class.getName());

    @Autowired
    @Qualifier("mongoTemplateQueries")
    private MongoTemplate mongoTemplate;

    @Async
    @EventListener
    public void handleEventTiendacreada(TiendaCreada tiendaCreada) {
        logger.info("****** Handle event tiendacreada");
        Map<String, Object> data = new HashMap<>();
        data.put("_id", tiendaCreada.aggregateRootId());
        data.put("direccion", tiendaCreada.getDireccion().value());
        data.put("telefono", tiendaCreada.getTelefono().value());
        mongoTemplate.save(data, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventVendedorAdicionado(VendedorAdicionado vendedorAdicionado) {
        logger.info("****** Handle event vendedoradicionado");
        Update update = new Update();
        var id = vendedorAdicionado.getVendedorId();
        update.set("vendedor."+id+".tiendaid", vendedorAdicionado.aggregateRootId());
        update.set("vendedor."+id+".vendedorid", vendedorAdicionado.getVendedorId().value());
        update.set("vendedor."+id+".nombre", vendedorAdicionado.getNombre().value());
        update.set("vendedor."+id+".direccion", vendedorAdicionado.getDireccion().value());
        update.set("vendedor."+id+".correo", vendedorAdicionado.getCorreo().value());
        update.set("vendedor."+id+".telefono", vendedorAdicionado.getTelefono().value().longValue());
        mongoTemplate.updateFirst(getFilterByAggregateId(vendedorAdicionado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventClienteAdicionado(ClienteAdicionado clienteAdicionado) {
        logger.info("****** Handle event vendedoradicionado");
        Update update = new Update();
        var id = clienteAdicionado.getClienteId();
        update.set("cliente."+id+".tiendaid", clienteAdicionado.aggregateRootId());
        update.set("cliente."+id+".clienteid", clienteAdicionado.getClienteId().value());
        update.set("cliente."+id+".nombre", clienteAdicionado.getNombre().value());
        update.set("cliente."+id+".telefono", clienteAdicionado.getTelefono().value());
        update.set("cliente."+id+".banco", clienteAdicionado.getDatosDePago().value().banco());
        update.set("cliente."+id+".numcuenta", clienteAdicionado.getDatosDePago().value().numCuenta());
        update.set("cliente."+id+".medio de pago", clienteAdicionado.getDatosDePago().value().medioDePago().toString().toUpperCase());
        update.set("cliente."+id+".numero de cuotas", clienteAdicionado.getDatosDePago().value().numCuotas());
        mongoTemplate.updateFirst(getFilterByAggregateId(clienteAdicionado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventProductoAdicionado(ProductoAdicionado productoAdicionado){
        logger.info("****** Handle event productoadicionado");
        Update update = new Update();
        var id = productoAdicionado.getProductoId();
        update.set("producto."+id+".tiendaid", productoAdicionado.aggregateRootId());
        update.set("producto."+id+".productoid", productoAdicionado.getProductoId().value());
        update.set("producto."+id+".precio del producto", productoAdicionado.getPrecioProducto().value().longValue());
        update.set("producto."+id+".nombre", productoAdicionado.getNombre().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(productoAdicionado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventDatosPersonalesDeClienteModificados(DatosPersonalesDelClienteModificados datosPersonalesDelClienteModificados){
        logger.info("****** Handle event datosPersonalesDelClienteModificados");
        Update update = new Update();
        var id = datosPersonalesDelClienteModificados.getClienteId();
        update.set("cliente."+id+".tiendaid", datosPersonalesDelClienteModificados.aggregateRootId());
        update.set("cliente."+id+".clienteid", datosPersonalesDelClienteModificados.getClienteId().value());
        update.set("cliente."+id+".nombre", datosPersonalesDelClienteModificados.getNombre().value());
        update.set("cliente."+id+".telefono", datosPersonalesDelClienteModificados.getTelefono().value());
        update.set("cliente."+id+".banco", datosPersonalesDelClienteModificados.getDatosDePago().value().banco());
        update.set("cliente."+id+".numero de cuenta", datosPersonalesDelClienteModificados.getDatosDePago().value().numCuenta());
        update.set("cliente."+id+".medio de pago", datosPersonalesDelClienteModificados.getDatosDePago().value().medioDePago().toString().toUpperCase());
        update.set("cliente."+id+".numero de cuotas", datosPersonalesDelClienteModificados.getDatosDePago().value().numCuotas());
        mongoTemplate.updateFirst(getFilterByAggregateId(datosPersonalesDelClienteModificados), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventDatosPersonalesDeVendedorModificados(DatosPersonalesDelVendedorModificados datosPersonalesDelVendedorModificados) {
        logger.info("****** Handle event datosPersonalesDelVendedorModificados");
        Update update = new Update();
        var id = datosPersonalesDelVendedorModificados.getVendedorId();
        update.set("vendedor."+id+".tiendaid", datosPersonalesDelVendedorModificados.aggregateRootId());
        update.set("vendedor."+id+".vendedorid", datosPersonalesDelVendedorModificados.getVendedorId().value());
        update.set("vendedor."+id+".nombre", datosPersonalesDelVendedorModificados.getNombre().value());
        update.set("vendedor."+id+".direccion", datosPersonalesDelVendedorModificados.getDireccion().value());
        update.set("vendedor."+id+".correo", datosPersonalesDelVendedorModificados.getCorreo().value());
        update.set("vendedor."+id+".telefono", datosPersonalesDelVendedorModificados.getTelefono().value().longValue());
        mongoTemplate.updateFirst(getFilterByAggregateId(datosPersonalesDelVendedorModificados), update, COLLECTION_NAME);
    }
    //preguntar por alertaclienteadicionado y bonoagregado

    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }
}