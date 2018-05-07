package microservices.priceCalculation.BTypePriceCalculation.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

@Setter
@Getter
@ToString
public class ActivityGroupForSubtotal implements Serializable{

    private int activityId;
    private ArrayList<GoodSubtotal> goods;
}
