package com.prodyna.pac.dialogue.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Wrong credentials")
public class NotAuthenticatedException extends RuntimeException {

}
