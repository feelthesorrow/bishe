package microservices.priceCalculation.testActivity.services;

import lombok.extern.slf4j.Slf4j;
import microservices.priceCalculation.testActivity.domain.ActivitySubtotal;
import microservices.priceCalculation.testActivity.domain.Fact;
import microservices.priceCalculation.testActivity.domain.GoodSubtotal;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;


@Service("ActivityGroupSubtotalService")
@Slf4j
public class ActivityGroupSubtotalServiceImpl implements ActivityGroupSubtotalService {

    @Override
    public void retrieveSubtotalForActivityGroup(Fact fact) throws Exception{

        fact.setQty(fact.getGoods().size());
        double totalBefore = 0;
        for(GoodSubtotal goodSubtotal: fact.getGoods()){
            totalBefore += goodSubtotal.getSubtotal();
        }
        fact.setSubtotalBefore(totalBefore);


        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        kSession.insert(fact);
        kSession.fireAllRules();
        kSession.dispose();

    }
}
