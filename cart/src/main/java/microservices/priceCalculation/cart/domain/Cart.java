package microservices.priceCalculation.cart.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "cart")
public final class Cart {

    @Id
    private final String cartId;
    private int lastModified;


    private TreeSet<Shop> shops = new TreeSet<Shop>();

    public Cart(){
        cartId = "test";
        lastModified = new ObjectId().getTimestamp();
        shops.add(new Shop());
    }
}
