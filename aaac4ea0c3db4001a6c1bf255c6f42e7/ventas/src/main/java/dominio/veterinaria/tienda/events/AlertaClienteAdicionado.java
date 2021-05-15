package dominio.veterinaria.tienda.events;

import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.Cliente;

public class AlertaClienteAdicionado extends DomainEvent {
    private final Cliente cliente;

    public AlertaClienteAdicionado(Cliente cliente) {
        super("vetrinaria.tienda.alertaclienteadicionado");
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
