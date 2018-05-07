package microservices.priceCalculation.User.services;


import microservices.priceCalculation.User.domain.User;
import microservices.priceCalculation.User.domain.UserSession;
import microservices.priceCalculation.User.repository.UserRepository;
import microservices.priceCalculation.User.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.bson.types.ObjectId;



@Service("userService")
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserSessionRepository sessionRepository;


    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final UserSessionRepository sessionRepository
    ){

        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public User register(User user) throws Exception {

        if(user.getUserId()==null || "".equals(user.getUserId())){

            throw new Exception("please allow cookies!");
        }

        return userRepository.save(user);
    }

    @Override
    public UserSession logIn(User user) throws Exception{

        if(user.getUserId()==null || "".equals(user.getUserId())){

            throw new Exception("please allow cookies!");
        }

        if(userRepository.existsById(user.getUserId())){

            UserSession userSession = new UserSession(user.getUserName(),
                    );
        }
    }

}
