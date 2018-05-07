package microservices.priceCalculation.BTypePriceCalculation.services;

import microservices.priceCalculation.BTypePriceCalculation.domain.Promotion;
import microservices.priceCalculation.BTypePriceCalculation.domain.RequestForPromotion;

public interface PromotionService {

    Promotion retrievePromotionForSku(RequestForPromotion request) throws Exception;
}
