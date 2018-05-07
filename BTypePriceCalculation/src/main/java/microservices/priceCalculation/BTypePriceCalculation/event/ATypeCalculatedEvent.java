package microservices.priceCalculation.BTypePriceCalculation.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class ATypeCalculatedEvent {

    private String userId;
    private boolean isVIP = false;
    private int shopId;
    private ArrayList<ActivityGroupForSubtotal> groups;
}
