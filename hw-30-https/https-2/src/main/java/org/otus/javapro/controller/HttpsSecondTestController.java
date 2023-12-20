package org.otus.javapro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/https2")
public class HttpsSecondTestController {

    private final RestTemplate restTemplate;

    public HttpsSecondTestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    ResponseEntity<String> testHttps2() {
        var response = restTemplate.getForObject("https://localhost:8081/app/api/v1/https1", String.class);
        return ResponseEntity.ok(response);
    }
}
