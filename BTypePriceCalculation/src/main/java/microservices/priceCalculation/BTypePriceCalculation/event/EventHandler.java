package microservices.priceCalculation.BTypePriceCalculation.event;

import lombok.extern.slf4j.Slf4j;
import microservices.priceCalculation.BTypePriceCalculation.client.ActivitySubtotalClient;
import microservices.priceCalculation.BTypePriceCalculation.client.dto.ActivityGroup;
import microservices.priceCalculation.BTypePriceCalculation.client.dto.ActivitySubtotal;
import microservices.priceCalculation.BTypePriceCalculation.domain.Promotion;
import microservices.priceCalculation.BTypePriceCalculation.services.PromotionService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class EventHandler {

    private PromotionService promotionService;
    private EventDispatcher eventDispatcher;

    private ActivitySubtotalClient subtotalClient;

    EventHandler(final PromotionService promotionService,
                 final  EventDispatcher eventDispatcher,
                 final ActivitySubtotalClient subtotalClient
    ){

        this.promotionService = promotionService;
        this.eventDispatcher = eventDispatcher;
        this.subtotalClient = subtotalClient;
    }

    @RabbitListener(queues = "${priceCalculation.queue}")
    void handelCartGot(final ATypeCalculatedEvent calculatedEvent){

        log.info("ATypeCalculated Event received for user: {}", calculatedEvent.getUserId());

        try {

            BTypeCalculatedEvent bTypeCalculatedEvent = new BTypeCalculatedEvent();
            bTypeCalculatedEvent.setShopId(calculatedEvent.getShopId());
            bTypeCalculatedEvent.setUserId(calculatedEvent.getUserId());
            bTypeCalculatedEvent.setVIP(calculatedEvent.isVIP());

            double total = 0 ;

            for(ActivityGroupForSubtotal activityGroupForSubtotal:calculatedEvent.getGroups()){

                ActivityGroup activityGroup = new ActivityGroup();
                activityGroup.setActivityId(activityGroupForSubtotal.getActivityId());
                activityGroup.setGoods(activityGroupForSubtotal.getGoods());
                activityGroup.setShopId(calculatedEvent.getShopId());
                activityGroup.setUserId(calculatedEvent.getUserId());
                activityGroup.setVIP(calculatedEvent.isVIP());

                ActivitySubtotal subtotal = subtotalClient.retrieveActivitySubtotal(activityGroup);

                total += subtotal.getSubtotal();
            }

            bTypeCalculatedEvent.setTotal(total);

            //publish BTypeCalculatedEvent
            eventDispatcher.send(bTypeCalculatedEvent);

        } catch (final Exception e) {

            log.error("Error when trying to process CartGotEvent", e);

            throw new AmqpRejectAndDontRequeueException(e);
        }

    }

}
