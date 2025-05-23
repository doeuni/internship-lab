package com.intern.integration.token.client;


import com.intern.integration.common.dto.ApiRequestCommon;
import com.intern.integration.common.dto.ApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenApiClient {


    private final RestTemplate restTemplate = new RestTemplate();


    public ApiResponse requestAccessToken() {
        //헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        String accessToken = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(accessToken.getBytes());
        headers.set("Authorization", "Basic "+ encodedAuth);
        System.out.println("토큰최종요청헤더" + headers);




        //data부분 생성
        Map<String, Object> data = new HashMap<>();


        //ApiRequestDTO 조립
        ApiRequestCommon requestBody = new ApiRequestCommon("", data);

        //요청보내기
        HttpEntity<ApiRequestCommon> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<ApiResponse> response = restTemplate.exchange(
                TOKEN_URL,
                HttpMethod.POST,
                request,
                ApiResponse.class
        );
        return response.getBody();
    };


}
