package microservices.priceCalculation.cart.controller;

import microservices.priceCalculation.cart.domain.Cart;
import microservices.priceCalculation.cart.domain.Item;
import microservices.priceCalculation.cart.services.CartService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@RequestMapping("/v1/cart")
final class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(final CartService cartService1){
        this.cartService = cartService1;
    }

    @PostMapping("/add")
    ResponseEntity<Item> postCart(final @RequestBody Item item) {

        /**id 需要从用户领域获得*/
        final String cartId = "test1";

        item.setAddDate(new ObjectId().getTimestamp());
        Item addedItem;
        try{
            addedItem = cartService.addToCart(cartId, item);

        }catch (Exception ex){

            return new ResponseEntity<Item>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(addedItem);
    }

    @PostMapping("/update")
    ResponseEntity<Cart> updateCartItem(final @RequestBody Cart cart) {

        /**id 需要从用户领域获得*/
        final String cartId = "test";

        Cart updatedCart;
        try{
            updatedCart = cartService.updateCart(cart);

        }catch (Exception ex){

            return new ResponseEntity<Cart>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping
    ResponseEntity<Cart> getCart(){

        /**id 需要从用户领域获得*/
        final String cartId = "test1";

        Cart gotCart;
        try{
            gotCart = cartService.getCart(cartId);

        }catch (Exception ex){

            return new ResponseEntity<Cart>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(gotCart);
    }


}
