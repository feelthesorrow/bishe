package microservices.priceCalculation.ATypePriceCalculation.services;


import microservices.priceCalculation.ATypePriceCalculation.domain.ATypePrice;
import microservices.priceCalculation.ATypePriceCalculation.domain.Fact;
import microservices.priceCalculation.ATypePriceCalculation.domain.RequestForSku;

public interface PriceService {

    ATypePrice retrievePriceForSku(RequestForSku requestForSku) throws Exception;
}
