package microservices.priceCalculation.gateway.configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;


public class RibbonConfiguration {

    /**ping服务实例来确定服务是否可用*/
    @Bean
    public IPing ribbonPing(final IClientConfig config) {

        return new PingUrl(false,"/health");
    }

    /**修改网关的负载均衡策略*/
    @Bean
    public IRule ribbonRule(final IClientConfig config) {

        return new AvailabilityFilteringRule();
    }

}
