package com.intoThe.controller;

import com.intoThe.service.impl.TokenVerificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
//@Tag(
//        name = "Token Management APIs",
//        description = "APIs for managing tokens"
//)
//Instead of using it in the controllers we can manage them from global openapi configuration class.
@Tag(name = "Token Management APIs")
public class TokenController {

    private final TokenVerificationService verificationService;
    public TokenController(TokenVerificationService verificationService){
        this.verificationService = verificationService;
    }

    @GetMapping("/verifyUserAccount")
    public ResponseEntity<?> verifyUser(@RequestParam String verificationToken) throws Exception {
        return new ResponseEntity<>(verificationService.verifyToken(verificationToken), HttpStatus.OK);
    }

    @PostMapping("/regenerateToken")
    private ResponseEntity<?> regenerateToken(@RequestHeader String userEmail){
        return verificationService.regenerateVerificationLink(userEmail);
    }
}
