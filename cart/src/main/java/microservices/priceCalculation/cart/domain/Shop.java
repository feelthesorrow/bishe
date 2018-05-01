package microservices.priceCalculation.cart.domain;

import lombok.*;
import org.bson.types.ObjectId;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Shop implements Comparable<Shop>{

    public int shopId;
    public String shopName;
    public int lastModified;
    public boolean selected;

    public TreeSet<Group> groups = new TreeSet<Group>();

    public Shop(){
        shopId = -1;
        shopName = "unfinished!";
        lastModified = new ObjectId().getTimestamp();
        selected = false;
        groups.add(new Group());
    }

    @Override
    public int compareTo(Shop shop) {

        if(this.getShopId() == shop.getShopId())
            return 0;
        return shop.lastModified - this.lastModified;
    }

    public boolean equals(Shop shop){
        return this.getShopId()==shop.getShopId();
    }
}
