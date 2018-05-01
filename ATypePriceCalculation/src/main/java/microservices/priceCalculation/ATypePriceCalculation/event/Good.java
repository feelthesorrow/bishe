package microservices.priceCalculation.ATypePriceCalculation.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Good implements Serializable {

    private String sku;
    private int qty;

}