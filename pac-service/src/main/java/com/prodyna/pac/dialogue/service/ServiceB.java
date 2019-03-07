package com.prodyna.pac.dialogue.service;

import com.prodyna.pac.dialogue.annotation.CustomLogger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Lazy
public class ServiceB {

    @CustomLogger
    private Logger logger;

    @Autowired
    private ServiceA serviceA;

    @PostConstruct
    public void postConstruct() {
        logger.info("Creating ServiceB");
    }

    public void runOtherService() {
        serviceA.serviceForOtherService();
    }

    public void serviceForOtherService() {

        logger.info("Service B executed");

    }

}
