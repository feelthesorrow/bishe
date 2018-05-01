package microservices.priceCalculation.cart.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import microservices.priceCalculation.cart.domain.Cart;
import microservices.priceCalculation.cart.domain.Group;
import microservices.priceCalculation.cart.domain.Item;
import microservices.priceCalculation.cart.domain.Shop;

import microservices.priceCalculation.cart.event.Good;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

@RequiredArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class CartGotEvent implements Serializable{

    /*private class GoodComparator implements Comparator<Good>,Serializable {

        public int compare(Good good1, Good good2) {

            if(good1.sku.equals(good2.sku)){
                good1.qty += good2.qty;
                return 0;
            }

            return 1;
        }
    }

    private class ActivityGroupComparator implements Comparator<ActivityGroup>,Serializable {

        public int compare(ActivityGroup activityGroup1, ActivityGroup activityGroup2) {

            if(activityGroup1.activityId == activityGroup2.activityId){

                activityGroup1.goods.addAll(activityGroup2.goods);
                return 0;

            }

            return 1;
        }
    }*/

    private String userId;
    private boolean isVIP = false;
    private int shopId;
    private ArrayList<ActivityGroup> groups;

    public CartGotEvent(Cart cart){
        userId = cart.getCartId();

        for(Shop shop:cart.getShops()){

            if(shop.isSelected()){

                shopId = shop.shopId;
                groups = new ArrayList<>();
                //groups = new TreeSet<>(new ActivityGroupComparator());

                for(Group group:shop.getGroups()){

                    ActivityGroup activityGroup = new ActivityGroup();
                    activityGroup.setActivityId(group.getGroupId());
                    activityGroup.setGoods(new ArrayList<>());
                    //activityGroup.goods = new TreeSet<>(new GoodComparator());

                    for(Item item:group.getItems()){

                        Good good = new Good();
                        good.setSku(item.getSku());
                        good.setQty(item.getQty());

                        activityGroup.getGoods().add(good);
                    }

                    this.getGroups().add(activityGroup);
                }

            }
        }
    }
}
