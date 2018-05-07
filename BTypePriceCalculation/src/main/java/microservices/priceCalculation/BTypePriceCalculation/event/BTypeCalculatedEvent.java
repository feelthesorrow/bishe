package microservices.priceCalculation.BTypePriceCalculation.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class BTypeCalculatedEvent {

    private String userId;
    private boolean isVIP;
    private int shopId;
    private double total;
}
