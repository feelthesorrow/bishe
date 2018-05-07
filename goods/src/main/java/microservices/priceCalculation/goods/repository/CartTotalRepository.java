package microservices.priceCalculation.cart.repository;

import microservices.priceCalculation.cart.event.CTypeCalculatedEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartTotalRepository extends MongoRepository<CTypeCalculatedEvent,String>{

    CTypeCalculatedEvent findByUserId(String userId) throws Exception;
}
