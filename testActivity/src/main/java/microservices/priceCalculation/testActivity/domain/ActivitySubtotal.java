package microservices.priceCalculation.testActivity.domain;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor

public class ActivitySubtotal {

    private int activityId;
    private double subtotal;
}
