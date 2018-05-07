package microservices.priceCalculation.testActivity.services;

import microservices.priceCalculation.testActivity.domain.ActivitySubtotal;
import microservices.priceCalculation.testActivity.domain.Fact;

public interface ActivityGroupSubtotalService {

    void retrieveSubtotalForActivityGroup(Fact fact) throws Exception;
}
