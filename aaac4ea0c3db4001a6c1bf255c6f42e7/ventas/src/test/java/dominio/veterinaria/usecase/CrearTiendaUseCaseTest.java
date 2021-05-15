package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.command.CrearTienda;
import dominio.veterinaria.tienda.events.TiendaCreada;
import dominio.veterinaria.tienda.objetosdevalor.Direccion;
import dominio.veterinaria.tienda.objetosdevalor.Telefono;
import dominio.veterinaria.tienda.objetosdevalor.TiendaId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CrearTiendaUseCaseTest {

    @Test
    void CrearTienda(){
        var tiendaId = new TiendaId();
        var telefono = new Telefono(4778905L);
        var direccion = new Direccion("Casa de Shiro");
        var command = new CrearTienda(tiendaId, telefono, direccion);

        var useCase = new CrearTiendaUseCase();

        List<DomainEvent> events = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        TiendaCreada event = (TiendaCreada)  events.get(0);

        Assertions.assertEquals(4778905L, event.getTelefono().value());
        Assertions.assertEquals("Casa de Shiro", event.getDireccion().value());

    }

}