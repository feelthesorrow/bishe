package microservices.priceCalculation.BTypePriceCalculation.client;

import microservices.priceCalculation.BTypePriceCalculation.client.dto.ActivityGroup;
import microservices.priceCalculation.BTypePriceCalculation.client.dto.ActivitySubtotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
class ActivitySubtotalClientImpl implements ActivitySubtotalClient {

    private final RestTemplate restTemplate;
    private final String multiplicationHost;

    @Autowired
    public ActivitySubtotalClientImpl(
            final RestTemplate restTemplate,
            @Value("${activityHost}") final String multiplicationHost)
    {
        this.restTemplate = restTemplate;
        this.multiplicationHost = multiplicationHost;
    }

    @Override
    public ActivitySubtotal retrieveActivitySubtotal(ActivityGroup activityGroup) {
        return restTemplate.postForObject(
                multiplicationHost + "/subtotal/" ,
                activityGroup,
                ActivitySubtotal.class);
    }
}