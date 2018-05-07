package microservices.priceCalculation.cart.event;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "cartTotal")
public class CTypeCalculatedEvent {

    @Id
    private String userId;
    private int shopId;
    private double total;
}
