package microservices.priceCalculation.ATypePriceCalculation.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class GoodSubtotal implements Serializable{

    private double subtotal;
}
