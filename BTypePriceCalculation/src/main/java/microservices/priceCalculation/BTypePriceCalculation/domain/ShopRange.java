package microservices.priceCalculation.BTypePriceCalculation.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.TreeSet;

@Getter
@Setter
@ToString
public class ShopRange{
    private SHOP_RANGE range;
    private TreeSet<Integer> val;

    public ShopRange(){
        range = SHOP_RANGE.SHOPS;

        val = new TreeSet<>();
        val.add(3);
        val.add(4);
    }
}
