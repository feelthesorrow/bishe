package microservices.priceCalculation.CTypePriceCalculation.services;

import microservices.priceCalculation.CTypePriceCalculation.event.BTypeCalculatedEvent;
import microservices.priceCalculation.CTypePriceCalculation.event.CTypeCalculatedEvent;

public interface CTypeCalculatedService {

    CTypeCalculatedEvent getOrderTotal(BTypeCalculatedEvent bTypeCalculatedEvent) throws  Exception;
}
