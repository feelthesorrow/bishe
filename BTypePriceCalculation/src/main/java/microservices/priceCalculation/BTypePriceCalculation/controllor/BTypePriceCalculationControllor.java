package microservices.priceCalculation.BTypePriceCalculation.controllor;

import microservices.priceCalculation.BTypePriceCalculation.domain.Promotion;
import microservices.priceCalculation.BTypePriceCalculation.domain.RequestForPromotion;
import microservices.priceCalculation.BTypePriceCalculation.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotion")
public class BTypePriceCalculationControllor {

    private final PromotionService promotionService;

    @Autowired
    public BTypePriceCalculationControllor(final PromotionService promotionService){
        this.promotionService = promotionService;
    }

    @GetMapping
    ResponseEntity<Promotion> getPrice(
            final @RequestParam("sku") String sku,
            final @RequestParam("brand") String brand,
            final @RequestParam("category") String category,
            final @RequestParam("shopId") Integer shopId
    ){

        RequestForPromotion request = new RequestForPromotion(sku,brand,category,shopId);
        Promotion promotion;

        try{

            promotion = promotionService.retrievePromotionForSku(request);

        }catch (Exception ex){

            return new ResponseEntity<Promotion>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(promotion);

    }
}
