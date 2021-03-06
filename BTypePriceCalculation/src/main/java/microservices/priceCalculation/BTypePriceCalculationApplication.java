package microservices.priceCalculation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BTypePriceCalculationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BTypePriceCalculationApplication.class, args);
	}
}
