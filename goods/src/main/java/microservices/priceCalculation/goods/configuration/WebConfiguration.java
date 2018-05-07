package microservices.priceCalculation.cart.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Enables Cross-Origin Resource Sharing (CORS)
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**");
    }

}
