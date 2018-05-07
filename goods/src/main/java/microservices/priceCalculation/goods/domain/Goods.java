package microservices.priceCalculation.goods.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "goods")
public class Goods {

    @Id
    String sku;
    public String picUrl;
    public String title;
    public String color;
    public String size;
    public String desc;

}
