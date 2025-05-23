package com.intern.integration.token.service;

import com.intern.integration.common.dto.ApiResponse;
import com.intern.integration.token.client.TokenApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import java.util.Map;
@Slf4j
@Component
@RequiredArgsConstructor
@EnableCaching
public class TokenCacheService {

     private final TokenApiClient tokenApiClient;
     private final CacheManager cacheManager;

     //token이 401일때만 실행
    public String refreshToken(){
        cacheManager.getCache("accessToken").clear();
        return getToken();
    }
    //토큰 캐싱(applicaiton.yml 기반)
    @Cacheable(value = "accessToken", key = "'token'")
    public String getToken() {
        System.out.println("토큰발급시도");


        //새토큰 발급
        ApiResponse response = tokenApiClient.requestAccessToken();

        if (response == null || response.getData() == null ){
            return null;
        }

        Map<String, Object> data = response.getData();
        String accessToken = data.get("accessToken").toString();

        return accessToken;

    };




}
