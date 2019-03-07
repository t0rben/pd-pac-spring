package com.prodyna.pac.dialogue.service;

import com.prodyna.pac.dialogue.annotation.CustomLogger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @CustomLogger
    private Logger logger;

    @Autowired
    private PrototypeScopedService prototypeScopedService;

    @Autowired
    private RequestScopedService requestScopedService;

    @Autowired
    private SessionScopedService sessionScopedService;

    @Autowired
    private ServiceA serviceA;

    public void run() {

        logger.trace("In Business");

        prototypeScopedService.run();
        requestScopedService.run();
        sessionScopedService.run();
        serviceA.runOtherService();
    }

}
