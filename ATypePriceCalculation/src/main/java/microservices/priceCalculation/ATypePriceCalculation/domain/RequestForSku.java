package microservices.priceCalculation.ATypePriceCalculation.domain;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class RequestForSku {

    private String sku;
    private String userId;
    private boolean isVIP;
    private int qty;
}
