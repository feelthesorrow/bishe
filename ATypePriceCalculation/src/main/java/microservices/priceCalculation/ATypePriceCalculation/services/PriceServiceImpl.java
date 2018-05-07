package microservices.priceCalculation.ATypePriceCalculation.services;

import lombok.extern.slf4j.Slf4j;
import microservices.priceCalculation.ATypePriceCalculation.domain.ATypePrice;
import microservices.priceCalculation.ATypePriceCalculation.domain.Fact;
import microservices.priceCalculation.ATypePriceCalculation.domain.Prices;
import microservices.priceCalculation.ATypePriceCalculation.domain.RequestForSku;
import microservices.priceCalculation.ATypePriceCalculation.event.EventDispatcher;
import microservices.priceCalculation.ATypePriceCalculation.repository.PricesRepository;
import org.kie.api.KieServices;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("priceService")
@Slf4j
public class PriceServiceImpl implements PriceService{

    private PricesRepository pricesRepository;

    @Autowired
    public PriceServiceImpl(final PricesRepository pricesRepository){

        this.pricesRepository = pricesRepository;

    }

    @Override
    public ATypePrice retrievePriceForSku(final RequestForSku requestForSku) throws Exception{

        if(requestForSku.getSku().isEmpty() || "".equals(requestForSku.getSku())){
            throw new Exception("sku cannot be empty!");
        }

        if(pricesRepository.existsById(requestForSku.getSku())){

            Prices priceForSku = pricesRepository.findBySku(requestForSku.getSku());

            ATypePrice aTypePrice = new ATypePrice(
                    priceForSku.getPrice(),
                    requestForSku.getQty()*priceForSku.getPrice()
            );
            Fact fact = new Fact(requestForSku, aTypePrice);

            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");

            kSession.insert(fact);
            kSession.fireAllRules();
            kSession.dispose();

            return fact.getATypePrice();

        }else
            return null;

    }
}
