package microservices.priceCalculation.cart.event;

import lombok.extern.slf4j.Slf4j;
import microservices.priceCalculation.cart.event.CTypeCalculatedEvent;
import microservices.priceCalculation.cart.repository.CartTotalRepository;
import microservices.priceCalculation.cart.services.CartService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class EventHandler {

    private CartService cartService;


    EventHandler(final CartService cartService){

        this.cartService = cartService;
    }

    @RabbitListener(queues = "${priceCalculation.queue}")
    void handelCTypeCalculated(final CTypeCalculatedEvent calculatedEvent){

        log.info("CTypeCalculated Event received for user: {}", calculatedEvent.getUserId());

        try {

            cartService.saveTotal(calculatedEvent);

        } catch (final Exception e) {

            log.error("Error when trying to process CartGotEvent", e);

            throw new AmqpRejectAndDontRequeueException(e);
        }

    }

}
