package microservices.priceCalculation.User.domain;

import lombok.*;
import javax.persistence.Entity;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public final class User {

    @Id
    private String userId;
    private String userName;
    private String pwd;
    private boolean isVIP;

}
