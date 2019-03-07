package com.prodyna.pac.dialogue.service;

import com.prodyna.pac.dialogue.annotation.CustomLogger;
import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Scope(
        value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PrototypeScopedService {

    @CustomLogger
    private static Logger logger;

    private AtomicInteger value = new AtomicInteger(0);

    @PostConstruct
    public void postConstruct() {

        logger.info("I'm a Prototype Scoped Bean and i'm created.");
    }

    @PreDestroy
    public void preDestroy() {

        logger.info("I'm a Prototype Scoped Bean and i'm going to be destroyed.");
    }

    public void run() {

        logger.info("Prototype Scoped Bean with value {}", value);

        this.value.addAndGet(1);

    }

}
