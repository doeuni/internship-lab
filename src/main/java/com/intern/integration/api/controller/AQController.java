package com.intern.integration.api.controller;

import com.intern.integration.api.service.AQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AQController {

    private final AQService aQService;

    // @RequestBody + Map 그대로 받기
    @PostMapping("/aQ")
    public ResponseEntity<?> getAQ(@RequestBody Map<String, Object> requestData) {
        try {
            Map<String, Object> result = aQService.getA7q(requestData);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "API 호출 실패", "details", e.getMessage()));
        }
    }


}