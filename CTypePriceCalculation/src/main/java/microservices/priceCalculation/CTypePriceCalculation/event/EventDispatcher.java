package microservices.priceCalculation.CTypePriceCalculation.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;

    private String priceCalculationExchange;

    private String CTypeCalculatedRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${priceCalculation.exchange}") final String priceCalculationExchange,
                    @Value("${price.CTypeCalculated.key}") final String CTypeCalculatedRoutingKey) {

        this.rabbitTemplate = rabbitTemplate;
        this.priceCalculationExchange = priceCalculationExchange;
        this.CTypeCalculatedRoutingKey = CTypeCalculatedRoutingKey;
    }

    public void send(final CTypeCalculatedEvent calculatedEvent) {

        rabbitTemplate.convertAndSend(
                priceCalculationExchange,
                CTypeCalculatedRoutingKey,
                calculatedEvent);
    }
}
