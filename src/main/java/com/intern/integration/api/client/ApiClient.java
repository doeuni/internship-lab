package com.intern.integration.api.client;
import com.intern.integration.common.dto.ApiRequestCommon;
import com.intern.integration.common.dto.ApiResponse;
import com.intern.integration.token.service.TokenCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiClient {
    private final ApiUriProvider apiUriRegistry;
    private final TokenCacheService tokenCacheService;
    private final RestTemplate restTemplate = new RestTemplate();

    // application.yml에서 설정값 주입
    @Value("${auth.source-ip:}")
    private String sourceIp;

    public ApiResponse sendRequestWithRetry(String apiName, Map<String, Object> data) {

        //apiName으로 uri 조회
        String uri = apiUriRegistry.getUri(apiName);

        //token 가져오기
        String token = tokenCacheService.getToken();
        if (token == null) {
            log.error("token이 null입니다");
            throw new RuntimeException("token이 null입니다");
        }

        HttpEntity<ApiRequestCommon> entity =  prepareRequest(token, data);

        try {
            return executeApiRequest(uri, entity);
        } catch (HttpClientErrorException e) {
            // 401 Error 처리
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED ) {
                log.warn("401 토큰재발급시도");
                String newToken = tokenCacheService.refreshToken();
                HttpEntity<ApiRequestCommon> newEntity =  prepareRequest(newToken, data);
            // 토큰 갱신 후 다시 요청
                return executeApiRequest(uri, newEntity);
            } else {
            // 다른 4xx 오류 처리
                log.error("API 클라이언트 오류 - API: {}, URI: {}, 상태 코드: {}, 오류: {}",
                        apiName, uri, e.getStatusCode(), e.getMessage());
                throw new RuntimeException("API 요청 중 클라이언트 오류 발생: " + e.getMessage(), e);
            }
        } catch (Exception e) {
            // 4xx 외 다른 모든 오류 처리
            log.error("API 요청 실패 - API: {}, URI: {}, 오류: {}", apiName, uri, e.getMessage());
            throw new RuntimeException("API 요청 중 오류 발생", e);
        }
    }

    private ApiResponse executeApiRequest(String uri, HttpEntity<ApiRequestCommon> entity) {
        // api 요청
        ResponseEntity<ApiResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                entity,
                ApiResponse.class
        );
        return response.getBody();
    }

    private HttpEntity<ApiRequestCommon> prepareRequest(String token, Map<String, Object> data) {
        String localIp = this.sourceIp != null ? this.sourceIp : "";
        ApiRequestCommon requestBody = new ApiRequestCommon(localIp, data);

    // DTO의 메서드를 사용하여 헤더 생성
        HttpHeaders headers = requestBody.createHttpHeaders(token);

        return new HttpEntity<>(requestBody, headers);
    }
}