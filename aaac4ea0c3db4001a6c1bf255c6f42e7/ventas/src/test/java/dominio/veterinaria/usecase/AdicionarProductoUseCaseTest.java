package dominio.veterinaria.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.command.AdicionarProducto;
import dominio.veterinaria.tienda.events.ProductoAdicionado;
import dominio.veterinaria.tienda.events.TiendaCreada;
import dominio.veterinaria.tienda.objetosdevalor.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdicionarProductoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void AdicionarProductoUseCase(){
        var tiendaId = TiendaId.of("456");
        var productoId = new ProductoId();
        var preciodelproducto = new PrecioProducto(100L);
        var nombredelproducto = new Nombre("Patitas");
        var command = new AdicionarProducto(productoId, tiendaId, preciodelproducto, nombredelproducto);

        var useCase = new AdicionarProductoUseCase();
        useCase.addRepository(repository);

        when(repository.getEventsBy(Mockito.any())).thenReturn(eventStored());
        List<DomainEvent> events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(tiendaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        ProductoAdicionado event = (ProductoAdicionado) events.get(0);
        Mockito.verify(repository).getEventsBy(tiendaId.value());

        Assertions.assertEquals(100L, event.getPrecioProducto().value());
        Assertions.assertEquals("Patitas", event.getNombre().value());
    }

    private List<DomainEvent> eventStored(){
        return List.of(new TiendaCreada(new Telefono(4778905L), new Direccion("Casa de kuro")));
    }

}