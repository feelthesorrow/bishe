package microservices.priceCalculation.testActivity.controllor;

import microservices.priceCalculation.testActivity.domain.ActivityGroup;
import microservices.priceCalculation.testActivity.domain.ActivitySubtotal;
import microservices.priceCalculation.testActivity.domain.Fact;
import microservices.priceCalculation.testActivity.services.ActivityGroupSubtotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subtotal/")
public class TestActivityControllor {

    private ActivityGroupSubtotalService groupSubtotalService;

    @Autowired
    public TestActivityControllor(final ActivityGroupSubtotalService groupSubtotalService){

        this.groupSubtotalService = groupSubtotalService;
    }

    @PostMapping
    ResponseEntity<ActivitySubtotal> getActivitySubtotal(final @RequestBody ActivityGroup activityGroup){

        Fact fact = new Fact(activityGroup.getActivityId(),
                activityGroup.getGoods(),
                0,
                0,
                activityGroup.getUserId(),
                activityGroup.isVIP(),
                activityGroup.getShopId(),
                new ActivitySubtotal()
                );

        try {

            groupSubtotalService.retrieveSubtotalForActivityGroup(fact);

        }catch (Exception ex){

            return new ResponseEntity<ActivitySubtotal>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(fact.getSubtotal());
    }
}
