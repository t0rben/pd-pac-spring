package com.prodyna.pac.dialogue.controller;

import com.prodyna.pac.model.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @PutMapping(path = "/account/{accountDTO}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable AccountDTO accountDTO) {

        return ResponseEntity.ok(accountDTO);
    }

}
