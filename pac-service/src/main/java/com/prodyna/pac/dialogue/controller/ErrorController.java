package com.prodyna.pac.dialogue.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {

        return "Error handling";
    }

    @Override
    public String getErrorPath() {

        return PATH;
    }
}
