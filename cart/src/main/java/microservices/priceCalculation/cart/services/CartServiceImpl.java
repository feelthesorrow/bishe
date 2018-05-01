package microservices.priceCalculation.cart.services;

import microservices.priceCalculation.cart.domain.Cart;
import microservices.priceCalculation.cart.domain.Item;
import microservices.priceCalculation.cart.domain.Group;
import microservices.priceCalculation.cart.domain.Shop;
import microservices.priceCalculation.cart.event.CartGotEvent;
import microservices.priceCalculation.cart.event.EventDispatcher;
import microservices.priceCalculation.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.bson.types.ObjectId;

import java.util.Iterator;
import java.util.Optional;
import java.util.TreeSet;

@Service("cartService")
class CartServiceImpl implements CartService{

    private CartRepository cartRepository;

    private EventDispatcher eventDispatcher;

    @Autowired
    public CartServiceImpl(final CartRepository cartRepository1,
                           final EventDispatcher eventDispatcher1){
        this.cartRepository = cartRepository1;
        this.eventDispatcher = eventDispatcher1;
    }

    private Item findByItemSku(TreeSet<Item> items, Item item){

        for (Item item1:items){
            if(item1.equals(item))
                return item1;
        }

        return new Item();
    }

    private void setShopSelected(TreeSet<Shop> shops, Shop shop){

        for(Shop shop1:shops){

            if(shop1.equals(shop))
                shop1.setSelected(true);

            else shop1.setSelected(false);
        }
    }

    private Shop findByShopId(TreeSet<Shop> shops, Shop shop){

        for(Shop shop1:shops){
            if(shop1.equals(shop))
                return shop1;
        }

        return new Shop();
    }

    private Group findByGroupId(TreeSet<Group> groups, Group group){
        /*Iterator iterator = groups.iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals(group))
                return (Group) iterator.next();
        }*/

        for (Group group1:groups){
            if(group1.equals(group))
                return group1;
        }

        return new Group();
    }


    @Transactional
    @Override
    public Item addToCart(final String cartId, Item item) throws Exception{

        if(cartId.isEmpty() || item.getSku().isEmpty()){
            throw new Exception("Cart or item can not be empty!");
        }

        if(cartRepository.existsById(cartId)){

            Cart cart = cartRepository.findByCartId(cartId);
            TreeSet<Shop> shops = cart.getShops();

            if(shops.contains(
                    new Shop(item.getShopId(),
                    item.getShopName(),
                    0,
                    false,
                    null)
            )){

                Shop shop = findByShopId(shops,
                        new Shop(item.getShopId(),
                                item.getShopName(),
                                0,
                                false,
                                null)
                );
                TreeSet<Group> groups = shop.getGroups();

                if(groups.contains(
                        new Group(item.getGroupId(),
                                item.getGroupName(),
                                0,
                                null)
                )){

                    Group group = findByGroupId(groups, new Group(item.getGroupId(), item.getGroupName(),0,null));
                    TreeSet<Item> items = group.getItems();

                    /**已有购物车 有对应店铺 有对应商品组 有一样的item item数量增加*/
                    if(items.contains(item)){

                        Item item1 = findByItemSku(items, item);

                        item1.setQty(item1.getQty() + item.getQty());
                        item1.setAddDate(new ObjectId().getTimestamp());
                        cart.setLastModified(item1.getAddDate());
                        shop.setLastModified(item1.getAddDate());
                        group.setLastModified(item1.getAddDate());

                        cartRepository.save(cart);

                        item = item1;
                        /**已有购物车 有对应店铺 有对应商品组 直接添加item*/
                    }else {
                        items.add(item);
                        cart.setLastModified(item.getAddDate());
                        shop.setLastModified(item.getAddDate());
                        group.setLastModified(item.getAddDate());
                        cartRepository.save(cart);
                    }

                /*已有购物车 有对应店铺 没有对应商品组 新建一个商品组 Group*/
                }else{

                    TreeSet<Item> items = new TreeSet<Item>();
                    items.add(item);
                    Group group = new Group(item.getGroupId(),
                            item.getGroupName(),
                            new ObjectId().getTimestamp(),
                            items);

                    groups.add(group);

                    cart.setLastModified(item.getAddDate());
                    shop.setLastModified(item.getAddDate());
                    group.setLastModified(item.getAddDate());

                    cartRepository.save(cart);
                }

            setShopSelected(shops, shop);

                /**已有购物车 没有对应店铺 新建一个店铺 shop*/
            }else{

                TreeSet<Item> items = new TreeSet<Item>();
                items.add(item);
                Group group = new Group(item.getGroupId(),
                        item.getGroupName(),
                        new ObjectId().getTimestamp(),
                        items);

                TreeSet<Group> groups = new TreeSet<Group>();
                groups.add(group);
                Shop shop = new Shop(item.getShopId(),
                        item.getShopName(),
                        group.getLastModified(),
                        true,
                        groups);

                cart.getShops().add(shop);

                setShopSelected(cart.getShops(), shop);

                cart.setLastModified(item.getAddDate());
                shop.setLastModified(item.getAddDate());
                group.setLastModified(item.getAddDate());

                cartRepository.save(cart);
            }

        /**publish event*/
        CartGotEvent cartGotEvent = new CartGotEvent(cart);
        eventDispatcher.send(
                cartGotEvent
        );

        /*新建整个购物车 cart**/
        }else {

            TreeSet<Item> items = new TreeSet<Item>();
            items.add(item);
            Group group = new Group(item.getGroupId(),
                    item.getGroupName(),
                    new ObjectId().getTimestamp(),
                    items);

            TreeSet<Group> groups = new TreeSet<Group>();
            groups.add(group);
            Shop shop = new Shop(item.getShopId(),
                    item.getShopName(),
                    group.getLastModified(),
                    true,
                    groups);

            TreeSet<Shop> shops = new TreeSet<Shop>();
            shops.add(shop);
            Cart cart = new Cart(cartId,
                    shop.getLastModified(),
                    0,
                    shops);

            cart.setLastModified(item.getAddDate());
            shop.setLastModified(item.getAddDate());
            group.setLastModified(item.getAddDate());

            cartRepository.save(cart);

            /**publish event*/
            eventDispatcher.send(
                    new CartGotEvent(cart)
            );
        }


    /*Item added to the cart should be returned.*/
    return item;
    }


    @Transactional
    @Override
    public Cart updateCart(final Cart cart) throws Exception{

        if(cart.getCartId() == null || "".equals(cart.getCartId())){
            throw new Exception("cart id cannot be null or empty.");
        }

        return cartRepository.save(cart);
    }


    @Override
    public Cart getCart(final String cartId) throws Exception{

        if(cartId == null || "".equals(cartId)){
            throw new Exception("cart id cannot be null or empty.");
        }
        return cartRepository.findByCartId(cartId);
    }
}
