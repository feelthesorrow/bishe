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
public class Group implements Comparable<Group>{
    /*
     * 一个商品组 比如满足同一个活动的几个商品
     */
    public int groupId;
    public String groupName;
    public int lastModified;
    TreeSet<Item> items = new TreeSet<Item>();

    public Group(){
        groupId = -1;
        groupName = "unfinished!";
        lastModified = new ObjectId().getTimestamp();
        items.add(new Item());
    }

    @Override
    public int compareTo(Group group) {

        if(this.getGroupId() == group.getGroupId())
            return 0;
        return group.lastModified - this.lastModified;
    }

    public boolean equals(Group group){
        return this.groupName.equals(group.groupName);
    }
}
