package com.prodyna.pac.dialogue.service;

import com.prodyna.pac.dialogue.annotation.CustomLogger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceA {

    @CustomLogger
    private Logger logger;

    @Autowired
    @Lazy
    private ServiceB serviceB;

    @PostConstruct
    public void postConstruct() {
        logger.info("Creating ServiceA");
    }

    public void runOtherService() {

        serviceB.serviceForOtherService();
    }

    public void serviceForOtherService() {

        logger.info("Service A executed");

    }

}
