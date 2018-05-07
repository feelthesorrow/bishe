package microservices.priceCalculation.User.repository;

import microservices.priceCalculation.User.domain.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRepository extends CrudRepository<UserSession, String>{
}
