package microservices.priceCalculation.CTypePriceCalculation.event;

import lombok.*;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CTypeCalculatedEvent {

    private String userId;
    private int shopId;
    private double total;
}
