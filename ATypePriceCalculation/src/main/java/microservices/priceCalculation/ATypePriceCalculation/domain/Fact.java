package microservices.priceCalculation.ATypePriceCalculation.domain;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Fact {

    private RequestForSku requestForSku;

    private ATypePrice aTypePrice;
}
