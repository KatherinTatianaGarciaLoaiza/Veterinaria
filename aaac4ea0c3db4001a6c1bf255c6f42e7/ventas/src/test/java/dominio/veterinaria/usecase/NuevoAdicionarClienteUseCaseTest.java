package dominio.veterinaria.usecase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import dominio.veterinaria.tienda.command.AdicionarCliente;
import dominio.veterinaria.tienda.events.ClienteAdicionado;
import dominio.veterinaria.tienda.events.TiendaCreada;
import dominio.veterinaria.tienda.objetosdevalor.*;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NuevoAdicionarClienteUseCaseTest extends  UseCaseHandlerTest{

    @Test
    void AdicionarClienteUseCaseTest() throws ExecutionControl.InternalException, InterruptedException {
        var tiendaId = TiendaId.of("789");
        var clienteId = new ClienteId();
        var datosdepago = new DatosDePago("Bancolombia", 123456789L, MedioDePago.EFECTIVO, 1);
        var nombre = new Nombre("Shiro");
        var telefono = new Telefono(4778905L);
        var command = new AdicionarCliente(clienteId, tiendaId, datosdepago, nombre, telefono);

        var useCase = new AdicionarClienteUseCase();
        useCase.addRepository(repository);

        when(repository.getEventsBy(Mockito.any())).thenReturn(eventStored());
        UseCaseHandler.getInstance()
                .setIdentifyExecutor(tiendaId.value())
                .asyncExecutor(useCase, new RequestCommand<>(command))
                .subscribe(subscriber);
        Thread.sleep(800);

        verify(subscriber).onNext(eventCaptor.capture());
        var clienteadicionado = (ClienteAdicionado) eventCaptor.getValue();

        Assertions.assertEquals("Bancolombia", clienteadicionado.getDatosDePago().value().banco());
        Assertions.assertEquals(123456789L, clienteadicionado.getDatosDePago().value().numCuenta());
        Assertions.assertEquals(MedioDePago.EFECTIVO, clienteadicionado.getDatosDePago().value().medioDePago());
        Assertions.assertEquals(1, clienteadicionado.getDatosDePago().value().numCuotas());
        Assertions.assertEquals("Shiro", clienteadicionado.getNombre().value());
        Assertions.assertEquals(4778905L, clienteadicionado.getTelefono().value());

    }

    private List<DomainEvent> eventStored(){
        return List.of(new TiendaCreada(new Telefono(4778905L), new Direccion("Casa de kuro")));
    }

}