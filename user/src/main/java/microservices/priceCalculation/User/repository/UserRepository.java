package microservices.priceCalculation.User.repository;

import microservices.priceCalculation.User.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {

    User findByUserId(String id);
}
