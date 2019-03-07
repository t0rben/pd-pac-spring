package com.prodyna.pac.dialogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private PrototypeScopedService prototypeScopedService;

    @Autowired
    private RequestScopedService requestScopedService;

    @Autowired
    private SessionScopedService sessionScopedService;

    @Autowired
    private ServiceA serviceA;

    public void run() {

        prototypeScopedService.run();
        requestScopedService.run();
        sessionScopedService.run();
        serviceA.runOtherService();
    }

}
