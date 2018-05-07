package microservices.priceCalculation.CTypePriceCalculation.services;

import lombok.extern.slf4j.Slf4j;
import microservices.priceCalculation.CTypePriceCalculation.event.BTypeCalculatedEvent;
import microservices.priceCalculation.CTypePriceCalculation.event.CTypeCalculatedEvent;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service("CTypePriceCalculationService")
@Slf4j
public class CTypeCalculatedServiceImpl implements CTypeCalculatedService{

    @Override
    public CTypeCalculatedEvent getOrderTotal(BTypeCalculatedEvent bTypeCalculatedEvent) throws Exception{

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        kSession.insert(bTypeCalculatedEvent);
        kSession.fireAllRules();
        kSession.dispose();

        return new CTypeCalculatedEvent(
          bTypeCalculatedEvent.getUserId(),
          bTypeCalculatedEvent.getShopId(),
          bTypeCalculatedEvent.getTotal()
        );
    }
}
