package microservices.priceCalculation.ATypePriceCalculation.repository;

import microservices.priceCalculation.ATypePriceCalculation.domain.Prices;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PricesRepository extends MongoRepository<Prices, String> {

    Prices findBySku(String sku);

}
