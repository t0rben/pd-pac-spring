package com.prodyna.pac.dialogue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceA {

    private static Logger logger = LoggerFactory.getLogger(ServiceA.class);

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
