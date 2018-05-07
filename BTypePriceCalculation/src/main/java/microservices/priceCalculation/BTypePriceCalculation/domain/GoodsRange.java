package microservices.priceCalculation.BTypePriceCalculation.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsRange{
    GOODS_RANGE range;
    String val;

    public GoodsRange(){
        range = GOODS_RANGE.ITEM;
        val = "1";
    }
}
