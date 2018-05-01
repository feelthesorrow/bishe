package microservices.priceCalculation.ATypePriceCalculation.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Document(collection = "prices")
public class Prices {

    @Id
    private String sku;
    private double price;

}
