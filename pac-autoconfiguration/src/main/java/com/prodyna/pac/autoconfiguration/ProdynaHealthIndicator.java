package com.prodyna.pac.autoconfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdynaHealthIndicator implements HealthIndicator, ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(ProdynaHealthIndicator.class);

    private AtomicInteger retries = new AtomicInteger(0);

    private ProdynaHealthProperties prodynaHealthProperties;

    private RestTemplate restTemplate;

    private ApplicationContext applicationContext;

    public ProdynaHealthIndicator(ProdynaHealthProperties prodynaHealthProperties, RestTemplateBuilder restTemplateBuilder) {

        this.prodynaHealthProperties = prodynaHealthProperties;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Health health() {

        try {

            logger.debug("Performing Health Check for {}", prodynaHealthProperties.getUrl());

            restTemplate.headForHeaders(URI.create(prodynaHealthProperties.getUrl()));

            retries.set(0);

        } catch (Exception e) {
            retries.incrementAndGet();

            logger.warn("Health check for {} failed the {} time.", prodynaHealthProperties.getUrl(), retries.get());

            if (retries.get() >= prodynaHealthProperties.getRetryLimit()) {

                if (prodynaHealthProperties.isStopApplication()) {
                    logger.warn("PRODYNA HealthCheck failed and the application will be shutdown.");
                    ((ConfigurableApplicationContext) this.applicationContext).close();
                }

                return Health.down().build();
            }

        }

        return Health.up().build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }
}
