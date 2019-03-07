package com.prodyna.pac.autoconfiguration;

import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(ProdynaHealthProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Import(ConditionalScheduling.class)
public class ProdynaHealthAutoconfiguration {

    private RestTemplateBuilder restTemplateBuilder;

    private ProdynaHealthProperties prodynaHealthProperties;

    public ProdynaHealthAutoconfiguration(RestTemplateBuilder restTemplateBuilder, ProdynaHealthProperties prodynaHealthProperties) {

        this.restTemplateBuilder = restTemplateBuilder;
        this.prodynaHealthProperties = prodynaHealthProperties;
    }

    @Bean
    public HealthIndicator prodynaHealthIndicator() {

        return new ProdynaHealthIndicator(prodynaHealthProperties, restTemplateBuilder);
    }

}
