package org.otus.javapro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/https1")
public class HttpsFirstTestController {

    @GetMapping
    ResponseEntity<String> testHttps() {
        return ResponseEntity.ok("Get response from https1 module");
    }
}
