package microservices.priceCalculation.BTypePriceCalculation.services;

import lombok.extern.slf4j.Slf4j;
import microservices.priceCalculation.BTypePriceCalculation.domain.Promotion;
import microservices.priceCalculation.BTypePriceCalculation.domain.RequestForPromotion;
import microservices.priceCalculation.BTypePriceCalculation.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("promotionService")
public class PromotionServiceImpl implements PromotionService{

    private PromotionRepository promotionRepository;

    @Autowired
    public PromotionServiceImpl(final PromotionRepository promotionRepository){
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Promotion retrievePromotionForSku(RequestForPromotion request) throws Exception{
        if(request == null){
            throw new Exception("RequestForPromotion cannot be empty!");
        }

        return promotionRepository.findByRequest(
                request.getSku(),
                request.getBrand(),
                request.getCategory(),
                request.getShopId()
        );
    }



}
