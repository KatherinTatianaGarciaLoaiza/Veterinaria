package dominio.veterinaria.Infraestructura.bus;

public interface EventSubscriber {
    void subscribe(String eventType, String exchange);
}
