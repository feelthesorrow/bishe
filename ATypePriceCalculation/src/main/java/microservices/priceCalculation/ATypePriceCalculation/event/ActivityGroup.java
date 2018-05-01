package microservices.priceCalculation.ATypePriceCalculation.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

@Setter
@Getter
@ToString
public class ActivityGroup implements Serializable {

    private int activityId;
    private ArrayList<Good> goods;

}
