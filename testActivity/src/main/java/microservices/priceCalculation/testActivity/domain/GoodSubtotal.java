package microservices.priceCalculation.testActivity.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class GoodSubtotal implements Serializable{

    //单件商品的price * qty后的小计
    private double subtotal;
}
