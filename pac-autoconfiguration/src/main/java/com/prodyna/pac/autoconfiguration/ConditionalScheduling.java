package com.prodyna.pac.autoconfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.TaskManagementConfigUtils;

@Configuration
@EnableScheduling
@ConditionalOnMissingBean(name = TaskManagementConfigUtils.SCHEDULED_ANNOTATION_PROCESSOR_BEAN_NAME)
public class ConditionalScheduling {

    @Bean
    @DependsOn("prodynaHealthIndicator")
    public ProdynaScheduler prodynaScheduler(ProdynaHealthIndicator prodynaHealthIndicator) {

        return new ProdynaScheduler(prodynaHealthIndicator);
    }

    class ProdynaScheduler {

        private Logger logger = LoggerFactory.getLogger(ProdynaScheduler.class);

        private ProdynaHealthIndicator prodynaHealthIndicator;

        public ProdynaScheduler(ProdynaHealthIndicator prodynaHealthIndicator) {

            this.prodynaHealthIndicator = prodynaHealthIndicator;
        }

        @Scheduled(fixedRate = 1000)
        public void triggerHealthCheck() {

            final Health health = prodynaHealthIndicator.health();

            logger.info("Current PRODYNA health status: {}", health.getStatus());

        }

    }

}
