package dominio.veterinaria.tienda.events;

import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.objetosdevalor.ClienteId;

public class BonoAgregado extends DomainEvent {
    private final ClienteId clienteId;

    public BonoAgregado(ClienteId clienteId) {
        super("veterinaria.tienda.bonoagregado");
        this.clienteId = clienteId;
    }

    public ClienteId getClienteId() {
        return clienteId;
    }
}
