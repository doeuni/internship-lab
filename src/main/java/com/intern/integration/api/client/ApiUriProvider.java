package com.intern.integration.api.client;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApiUriProvider {

    private final Map<String, String> uriMap;

    public ApiUriProvider() {
        uriMap = new HashMap<>();

    }

    public String getUri(String apiName) {
        if (!uriMap.containsKey(apiName)) {
            throw new IllegalArgumentException("Unknown API name: " + apiName);
        }
        return uriMap.get(apiName);
    }
}

