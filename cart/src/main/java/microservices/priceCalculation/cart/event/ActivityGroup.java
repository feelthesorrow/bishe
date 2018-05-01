package microservices.priceCalculation.cart.event;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Setter
@Getter
public class ActivityGroup implements Serializable {

    private int activityId;
    private ArrayList<Good> goods;

}
