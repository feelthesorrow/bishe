package microservices.priceCalculation.BTypePriceCalculation.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "promotions")
public class Promotion {

    @Id
    private Integer promotionId;
    private String title;
    private long startTime;
    private long endTime;
    private PROMOTION_TYPE type;

    private ShopRange shopRange;
    private boolean VIPRequired;

    private int numTakePartIn;
    private int numTotal;

    private String condition;
    private String action;

    private GoodsRange goodsRange;

    public Promotion(){
        promotionId = new Integer(100);
        type = PROMOTION_TYPE.A;
        shopRange = new ShopRange();
        goodsRange = new GoodsRange();
    }
}
