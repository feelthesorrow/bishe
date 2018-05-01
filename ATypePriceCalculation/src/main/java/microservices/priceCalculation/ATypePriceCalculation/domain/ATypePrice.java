package microservices.priceCalculation.ATypePriceCalculation.domain;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ATypePrice {

    private double price;
    private double subtotal;
}
