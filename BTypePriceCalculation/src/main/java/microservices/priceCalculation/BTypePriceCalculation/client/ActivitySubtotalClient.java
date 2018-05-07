package microservices.priceCalculation.BTypePriceCalculation.client;

import microservices.priceCalculation.BTypePriceCalculation.client.dto.ActivityGroup;
import microservices.priceCalculation.BTypePriceCalculation.client.dto.ActivitySubtotal;



public interface ActivitySubtotalClient {

    ActivitySubtotal retrieveActivitySubtotal(ActivityGroup activityGroup);

}
