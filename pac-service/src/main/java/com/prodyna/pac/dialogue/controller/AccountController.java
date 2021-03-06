package com.prodyna.pac.dialogue.controller;

import com.prodyna.pac.dialogue.annotation.CustomLogger;
import com.prodyna.pac.dialogue.annotation.LogExecutionTime;
import com.prodyna.pac.dialogue.annotation.SecureBasicAuth;
import com.prodyna.pac.dialogue.service.BusinessService;
import com.prodyna.pac.dialogue.service.PrototypeScopedService;
import com.prodyna.pac.dialogue.service.RequestScopedService;
import com.prodyna.pac.dialogue.service.SessionScopedService;
import com.prodyna.pac.model.dto.AccountDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @CustomLogger
    private Logger logger;

    @Autowired
    private PrototypeScopedService prototypeScopedService;

    @Autowired
    private RequestScopedService requestScopedService;

    @Autowired
    private SessionScopedService sessionScopedService;

    @Autowired
    private BusinessService businessService;

    @PutMapping(path = "/account/{accountDTO}")
    @LogExecutionTime
    public ResponseEntity<?> updateAccount(@PathVariable AccountDTO accountDTO) {

        logger.info("Received AccountDTO. {}", accountDTO);

        prototypeScopedService.run();
        requestScopedService.run();
        sessionScopedService.run();

        businessService.run();

        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping(path = "/pac/login")
    @SecureBasicAuth
    public ResponseEntity<Void> checkSecure() {

        return ResponseEntity.ok().build();
    }

}
