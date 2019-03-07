package com.prodyna.pac.dialogue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Lazy
public class ServiceB {

    private static Logger logger = LoggerFactory.getLogger(ServiceB.class);

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
