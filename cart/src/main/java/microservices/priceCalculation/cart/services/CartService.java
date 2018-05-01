package microservices.priceCalculation.cart.services;

import microservices.priceCalculation.cart.domain.Cart;
import microservices.priceCalculation.cart.domain.Item;

import java.util.Optional;

public interface CartService {
    Item addToCart(final String cartId, final Item i) throws Exception;
    Cart updateCart(final Cart cart) throws Exception;
    Cart getCart(final String cartId) throws Exception;
}
