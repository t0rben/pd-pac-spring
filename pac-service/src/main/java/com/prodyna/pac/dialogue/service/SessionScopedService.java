package com.prodyna.pac.dialogue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedService {

    private static Logger logger = LoggerFactory.getLogger(SessionScopedService.class);

    private AtomicInteger value = new AtomicInteger(0);

    @PostConstruct
    public void postConstruct() {

        logger.info("I'm a Session Scoped Bean and i'm created.");
    }

    @PreDestroy
    public void preDestroy() {

        logger.info("I'm a Session Scoped Bean and i'm going to be destroyed.");
    }

    public void run() {

        logger.info("Session Scoped Bean with value {}", value);

        this.value.addAndGet(1);

    }

}
