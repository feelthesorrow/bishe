package microservices.priceCalculation.cart.event;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Good implements Serializable {

    private String sku;
    private int qty;

}