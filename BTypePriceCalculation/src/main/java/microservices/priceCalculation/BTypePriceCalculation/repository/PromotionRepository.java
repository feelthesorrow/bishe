package microservices.priceCalculation.BTypePriceCalculation.repository;

import microservices.priceCalculation.BTypePriceCalculation.domain.Promotion;
import microservices.priceCalculation.BTypePriceCalculation.domain.RequestForPromotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PromotionRepository extends MongoRepository<Promotion,Integer>{

    Promotion findByPromotionId(Integer id);

    @Query("{ 'shopRange.val':?3 , $or:[{'goodsRange.val':?1},{'goodsRange.val':?2},{'goodsRange.val':?0}] }")
    Promotion findByRequest(String sku,String brand, String category,Integer shopId);
}
