package microservices.priceCalculation.User.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RedisHash("sessions")
public class UserSession {

    @Id
    private String userName;
    private String session;
    @TimeToLive
    private Long expiration;
}
