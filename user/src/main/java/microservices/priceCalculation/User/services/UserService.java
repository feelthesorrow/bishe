package microservices.priceCalculation.User.services;


import microservices.priceCalculation.User.domain.User;
import microservices.priceCalculation.User.domain.UserSession;

public interface UserService {

    User register(User user) throws Exception;
    UserSession logIn(User user) throws Exception;
}
