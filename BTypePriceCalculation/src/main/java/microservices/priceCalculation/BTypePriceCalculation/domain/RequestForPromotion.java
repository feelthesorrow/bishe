package microservices.priceCalculation.BTypePriceCalculation.domain;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class RequestForPromotion{

    private String sku;
    private String brand;
    private String category;
    private Integer shopId;

}
