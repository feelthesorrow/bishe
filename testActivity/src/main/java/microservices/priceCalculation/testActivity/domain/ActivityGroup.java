package microservices.priceCalculation.testActivity.domain;

import lombok.*;
import microservices.priceCalculation.testActivity.domain.GoodSubtotal;

import java.util.ArrayList;

/*每一个B类型促销活动服务接受此类对象用于计算优惠后的价格 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class ActivityGroup {

    private int activityId;
    private ArrayList<GoodSubtotal> goods;

    private String userId;
    private boolean isVIP;
    private int shopId;
}
