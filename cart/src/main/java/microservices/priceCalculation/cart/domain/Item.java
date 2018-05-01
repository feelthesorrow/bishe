package microservices.priceCalculation.cart.domain;

import lombok.*;
import org.bson.types.ObjectId;

import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public final class Item implements Comparable<Item>{

    public String sku;
    public int qty;

    public String picUrl;
    public String title;
    public String color;
    public String size;
    public String desc;

    public boolean selected;
    public int addDate;

    public int shopId;
    public String shopName;

    public int groupId;
    public String groupName;

    public Item(){
        sku = "test";
        qty = -1;

        picUrl = "test";
        title = "test";
        color = "red";
        size = "M";
        desc = "test";

        selected = true;
        addDate = new ObjectId().getTimestamp();

        shopId = -1;
        shopName = "test";

        groupId = -1;
        groupName = "test";
    }

    @Override
    public int compareTo(Item item) {

        if(this.getSku().equals(item.getSku())
                && this.getColor().equals(item.getColor())
                && this.getSize().equals(item.getSize())
                )
            return 0;

        return item.getAddDate() - this.getAddDate();
    }

    public boolean equals(Item item){
        if(this.getSku().equals(item.getSku())){

            if(this.getColor().equals(item.getColor()))

                return this.getSize().equals(item.getSize());

            return false;
        }
        return false;
    }
}
