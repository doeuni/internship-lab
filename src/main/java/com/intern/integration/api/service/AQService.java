package com.intern.integration.api.service;

import com.intern.integration.api.client.ApiClient;
import com.intern.integration.common.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AQService {
    private final ApiClient apiClient;

    public Map<String, Object> getAq(Map<String, Object> requestData){
        ApiResponse response = apiClient.sendRequestWithRetry("AQ", requestData);
        return response.getData();
    }


}

