package microservices.priceCalculation.cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import microservices.priceCalculation.cart.domain.Cart;
import org.springframework.data.mongodb.repository.Query;

public interface CartRepository extends MongoRepository<Cart,String>{

    Cart findByCartId(String id);
}
