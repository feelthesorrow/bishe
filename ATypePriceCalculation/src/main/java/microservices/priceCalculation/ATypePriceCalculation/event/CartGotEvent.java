package microservices.priceCalculation.ATypePriceCalculation.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

@RequiredArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class CartGotEvent implements Serializable{

    private String userId;
    private boolean isVIP = false;
    private int shopId;
    private ArrayList<ActivityGroup> groups;

}
