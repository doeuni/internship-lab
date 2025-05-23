# 🔐 Internship Lab
> 인턴십 개인 과제: 사내 인증 시스템과 연동된 토큰 기반 API 요청 모듈 구현

![image](https://github.com/user-attachments/assets/c6c80f7b-b221-4a30-a2e8-78631004c415)



---

## 📌 프로젝트 개요

본 리포지토리는 인턴십 기간 중 수행한 개인 과제로,  
사내 OAuth2 인증 서버에서 토큰을 발급받아,  
내부 API에 **보안 요청을 자동화**하는 모듈을 개발한 프로젝트입니다.

목표는 실무 환경에서 재사용 가능한 구조로,  
인증 → 요청 → 응답 처리 흐름을 안정적으로 구성하는 것이었습니다.

---
## 🤍 과제개요 및 구체화

![image](https://github.com/user-attachments/assets/c02973f1-249f-44d9-82cf-ca4d615d3219)
![image](https://github.com/user-attachments/assets/14ee79a1-43f3-4546-930d-a6683217a7e1)
![image](https://github.com/user-attachments/assets/092d84f8-87b5-4746-9ae7-c4dc45730844)



## 🛠️ 기술 스택

- Java 11  
- Spring Boot (RestTemplate 기반)  
- OAuth2 인증 방식  
- Maven  
- IntelliJ IDEA

---

## 💡 주요 기능

- 🔑 **토큰 발급 기능**
  - 내부 인증 API를 통한 OAuth2 방식의 액세스 토큰 발급
- 🧾 **표준화된 요청/응답 DTO**
  - 공통 DTO 설계로 다양한 API 호출 시 일관성 유지
- 🧩 **유연한 API URI 매핑**
  - API 이름 기반으로 URI 자동 맵핑
- 🧪 **에러 대응 로직**
  - 401 인증 실패 시 예외 처리 및 로그 출력

---



## ✅ 테스트 시나리오

- 유효한 Client ID/Secret을 통한 토큰 발급  
- 토큰을 포함한 POST 요청 처리  
- 인증 실패(401) 시 예외 처리  
- Mock API 응답을 통한 통합 테스트

---

## 🧳 프로젝트 결과

- 토큰 기반 공통 모듈을 통해  
  여러 내부 API를 안전하게 호출할 수 있도록 구조화하였습니다.

- 이후 다른 사내 API에 쉽게 확장 적용 가능한 형태로 설계되었습니다.

---

