package microservices.priceCalculation.ATypePriceCalculation.controllor;

import microservices.priceCalculation.ATypePriceCalculation.domain.ATypePrice;
import microservices.priceCalculation.ATypePriceCalculation.domain.RequestForSku;
import microservices.priceCalculation.ATypePriceCalculation.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public final class ATypePriceCalculationControllor {

    private final PriceService priceService;

    @Autowired
    public ATypePriceCalculationControllor(final PriceService priceService){

        this.priceService = priceService;
    }


    @GetMapping
    ResponseEntity<ATypePrice> getPrice(
            final @RequestParam("sku") String sku,
            final @RequestParam("userId") String userId,
            final @RequestParam("isVIP") boolean isVIP,
            final @RequestParam("qty") int qty
    ){

        ATypePrice aTypePrice;
        RequestForSku requestForSku = new RequestForSku(sku, userId, isVIP, qty);

        try{

            aTypePrice = priceService.retrievePriceForSku(requestForSku);

        }catch (Exception ex){

            return new ResponseEntity<ATypePrice>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(aTypePrice);

    }
}
