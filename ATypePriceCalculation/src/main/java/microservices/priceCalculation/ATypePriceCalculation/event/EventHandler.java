package microservices.priceCalculation.ATypePriceCalculation.event;

import lombok.extern.slf4j.Slf4j;
import microservices.priceCalculation.ATypePriceCalculation.domain.RequestForSku;
import microservices.priceCalculation.ATypePriceCalculation.services.PriceService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class EventHandler {

    private PriceService priceService;

    EventHandler(final PriceService priceService){

        this.priceService = priceService;
    }

    @RabbitListener(queues = "${priceCalculation.queue}")
    void handelCartGot(final CartGotEvent gotEvent){

        log.info("CartGot Event received for user: {}", gotEvent.getUserId());

        try {

            ATypeCalculatedEvent calculatedEvent = new ATypeCalculatedEvent();

            calculatedEvent.setUserId(gotEvent.getUserId());
            calculatedEvent.setShopId(gotEvent.getShopId());
            calculatedEvent.setVIP(gotEvent.isVIP());
            calculatedEvent.setGroups(new ArrayList<>());

            RequestForSku request;

            for(ActivityGroup activityGroup: gotEvent.getGroups()){

                ActivityGroupForSubtotal groupForSubtotal = new ActivityGroupForSubtotal();

                groupForSubtotal.setActivityId(activityGroup.getActivityId());
                groupForSubtotal.setGoods(new ArrayList<>());

                for(Good good: activityGroup.getGoods()){

                    request = new RequestForSku(good.getSku(),
                            gotEvent.getUserId(),
                            gotEvent.isVIP(),
                            good.getQty()
                            );

                    GoodSubtotal goodSubtotal = new GoodSubtotal();
                    goodSubtotal.setSubtotal(
                            priceService.retrievePriceForSku(request).getSubtotal()
                    );

                    groupForSubtotal.getGoods().add(goodSubtotal);

                }

                calculatedEvent.getGroups().add(groupForSubtotal);
            }

            //publish ATypeCalculatedEvent

        } catch (final Exception e) {

            log.error("Error when trying to process CartGotEvent", e);

            throw new AmqpRejectAndDontRequeueException(e);
        }

    }

}
