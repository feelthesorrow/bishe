package microservices.priceCalculation.cart.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;

    private String priceCalculationExchange;

    private String cartGotRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${priceCalculation.exchange}") final String priceCalculationExchange,
                    @Value("${cart.got.key}") final String cartGotRoutingKey) {

        this.rabbitTemplate = rabbitTemplate;
        this.priceCalculationExchange = priceCalculationExchange;
        this.cartGotRoutingKey = cartGotRoutingKey;
    }

    public void send(final CartGotEvent cartGotEvent) {

        rabbitTemplate.convertAndSend(
                priceCalculationExchange,
                cartGotRoutingKey,
                cartGotEvent);
    }
}
