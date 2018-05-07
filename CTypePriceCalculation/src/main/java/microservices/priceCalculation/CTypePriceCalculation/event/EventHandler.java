package microservices.priceCalculation.CTypePriceCalculation.event;

import lombok.extern.slf4j.Slf4j;
import microservices.priceCalculation.CTypePriceCalculation.services.CTypeCalculatedService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class EventHandler {

    private CTypeCalculatedService service;
    private EventDispatcher eventDispatcher;


    EventHandler(final CTypeCalculatedService service,
                 final  EventDispatcher eventDispatcher
    ){

        this.service = service;
        this.eventDispatcher = eventDispatcher;
    }

    @RabbitListener(queues = "${priceCalculation.queue}")
    void handelBTypeCalculated(final BTypeCalculatedEvent calculatedEvent){

        log.info("ATypeCalculated Event received for user: {}", calculatedEvent.getUserId());

        try {

            CTypeCalculatedEvent cTypeCalculatedEvent = service.getOrderTotal(calculatedEvent);

            //publish CTypeCalculatedEvent
            eventDispatcher.send(cTypeCalculatedEvent);

        } catch (final Exception e) {

            log.error("Error when trying to process CartGotEvent", e);

            throw new AmqpRejectAndDontRequeueException(e);
        }

    }

}
