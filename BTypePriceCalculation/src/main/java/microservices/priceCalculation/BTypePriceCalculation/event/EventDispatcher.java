package microservices.priceCalculation.BTypePriceCalculation.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;

    private String priceCalculationExchange;

    private String BTypeCalculatedRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${priceCalculation.exchange}") final String priceCalculationExchange,
                    @Value("${price.BTypeCalculated.key}") final String BTypeCalculatedRoutingKey) {

        this.rabbitTemplate = rabbitTemplate;
        this.priceCalculationExchange = priceCalculationExchange;
        this.BTypeCalculatedRoutingKey = BTypeCalculatedRoutingKey;
    }

    public void send(final BTypeCalculatedEvent calculatedEvent) {

        rabbitTemplate.convertAndSend(
                priceCalculationExchange,
                BTypeCalculatedRoutingKey,
                calculatedEvent);
    }
}
