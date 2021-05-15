package dominio.veterinaria.Infraestructura.bus;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.infraestructure.bus.EventBus;
import co.com.sofka.infraestructure.bus.notification.SuccessNotification;
import co.com.sofka.infraestructure.bus.serialize.SuccessNotificationSerializer;
import co.com.sofka.infraestructure.event.ErrorEvent;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RabbitMQEventBus implements EventBus {

    private final String EXCHANGE = "core-veterinaria";
    private final String TOPIC_BUSSINES_ERROR = "veterinaria.error";
    private final Logger logger = Logger.getLogger(RabbitMQEventBus.class.getName());

    private final RabbitTemplate rabbitTemplate;
    private final RabbitAdmin rabbitAdmin;

    public RabbitMQEventBus(@Value("${spring.bus.uri}") String uri) {
        this.rabbitTemplate = new RabbitTemplate(new CachingConnectionFactory(URI.create(uri)));
        this.rabbitAdmin = new RabbitAdmin(this.rabbitTemplate);
    }

    @Override
    public void publish(DomainEvent event) {
        try{
            var notification = SuccessNotification.wrapEvent(EXCHANGE, event);
            var notificationSerialization = SuccessNotificationSerializer.instance().serialize(notification);
            rabbitAdmin.declareExchange(new TopicExchange(EXCHANGE));
            rabbitTemplate.convertAndSend(EXCHANGE, event.type, notificationSerialization.getBytes());
            logger.log(Level.INFO, "Event pusblished to {0}/{1}", new String[]{event.type, event.aggregateRootId()});
        }catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void publishError(ErrorEvent errorEvent) {
        if (errorEvent.error instanceof BusinessException){
            publishError(errorEvent);
        }
        logger.log(Level.SEVERE, errorEvent.error.getMessage(), errorEvent.error);
    }

    private void publishErrorLog(ErrorEvent errorEvent){
        logger.log(Level.WARNING, "Error event published to {0}/{1}",
                new String[]{TOPIC_BUSSINES_ERROR, errorEvent.identify});
    }
}
