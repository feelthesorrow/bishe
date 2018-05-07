package microservices.priceCalculation.ATypePriceCalculation.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;

    private String priceCalculationExchange;

    private String ATypeCalculatedRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${priceCalculation.exchange}") final String priceCalculationExchange,
                    @Value("${price.ATypeCalculated.key}") final String ATypeCalculatedRoutingKey) {

        this.rabbitTemplate = rabbitTemplate;
        this.priceCalculationExchange = priceCalculationExchange;
        this.ATypeCalculatedRoutingKey = ATypeCalculatedRoutingKey;
    }

    public void send(final ATypeCalculatedEvent calculatedEvent) {

        rabbitTemplate.convertAndSend(
                priceCalculationExchange,
                ATypeCalculatedRoutingKey,
                calculatedEvent);
    }
}
