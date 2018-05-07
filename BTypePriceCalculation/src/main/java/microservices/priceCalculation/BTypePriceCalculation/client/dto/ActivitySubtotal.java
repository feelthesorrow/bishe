package microservices.priceCalculation.BTypePriceCalculation.client.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/*B类型促销活动计算后的小计*/

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor

public class ActivitySubtotal {

    private int activityId;
    private double subtotal;
}
