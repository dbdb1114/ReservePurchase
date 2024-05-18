## 🚀 프로젝트 소개
![스크린샷 2024-05-16 오후 4 46 14](https://github.com/dbdb1114/ReservePurchase/assets/105846655/3cfd4aba-b408-4c4c-b440-e53c4a01f477)

해당 서비스는 E-COMMERCE 플랫폼 중에서 특정 시간대에 열리는 한정 수량 판매 서비스입니다.


1. 특정 시간에 몇 가지 상품군을 오픈합니다.
2. 소비자가 구매를 시도하여, 결제 시도 프로세스에 진입합니다.
    - 구매를 시도하고있는 상품수 만큼 재고를 감소시킵니다.
    - 고객 이탈 발생시 즉시 재고를 복구시킵니다.

### 📅 프로젝트 진척 및 일정 관리

---
[**GitHub 칸반보드**](https://github.com/users/dbdb1114/projects/1/views/1)

**개발기간 : 24.04.17 ~ 24.05.15**

**유지보수 : 24.05.16 ~ 24.05.31**

### **🧑🏼‍💻 진행 인원 및 역할**

---

**BE : 유정현 ( 개인 프로젝트 )**

### 🕹️ 주요 기능

---

- **JWT 토큰 활용 유저 인증 인가처리**
- **실시간 재고 확인**
- **이메일 인증을 통한 회원가입**
- **상품 구매 및 환불 시스템 구현**

### 🛠️기술 스택

---

- Spring Boot
- Spring Web Flux
- SpringCloud
    - GATE-WAY
    - NETFLIX-EUREKA
    - CONFIG
    - BUS
- Spring Security
- Redis
- MySQL
- JPA / Hibernate
- NGrinder
- Scouter
- Docker (Compose)

### 🗺️ 아키텍처

---
![스크린샷 2024-05-16 오후 4 40 18](https://github.com/dbdb1114/ReservePurchase/assets/105846655/bd0637b5-e503-4914-94fb-7d9968f9d3de)

## 부하 테스트 및 TroubleShooting

- 재고관리 API의 Reids 동시성 문제 해결
    - **문제** : 약 100건 이상의 동시 요청시 존재하는 데이터에 대해 NPE가 발생
    - **원인** : JUnit Test로 병렬처리 시도의 경우에만 문제 발생하여 동시성 문제로 파악
    - **해결** : Redisson 분산락 방식을 도입하여 해결
- 상품목록 및 상세정보 조회 성능 개선 `TPS : 155.7 → 423`
    - **문제** : 많은 동시 요청시 요청 처리 속도가 최대 6초까지 걸리는 현상
    - **원인** : BlockingIO + DB 연산의 부하가 큰 것으로 추측
    - **해결** : Redis의 Cach를 활용하여, 평균 700ms 수준으로 성능 향상

## 📼 ERD 및 API명세서

---

### 🧾 ERD
### 📜 API명세

**아래 이미지를 클릭하시면 포스트맨 API명세 페이지로 이동합니다!**
[![postman](https://github.com/dbdb1114/ReservePurchase/assets/105846655/ee23fc31-2905-45bc-9054-7c776c0c0cf0)](https://documenter.getpostman.com/view/25908545/2sA3Bt2Upn)

### 🐬 Docker Compose
```yaml
version: "3"

services:
  gateway-api:
    image: reservation-gateway-api
    container_name: reservation-gateway-api
    restart: always
    ports:
      - 8000:8000
    depends_on:
      - config-api
      - discovery-api
      - config-rabbitmq
    networks:
      - my-network

  config-api:
    image: reservation-config-api
    container_name: reservation-config-api
    restart: always
    ports:
      - 8118:8118
    depends_on:
      - config-rabbitmq
    networks:
      - my-network

  discovery-api:
    image: reservation-discovery-api
    container_name: reservation-discovery-api
    restart: always
    ports:
      - 8888:8888
    networks:
      - my-network

  config-rabbitmq:
    image: rabbitmq:latest
    container_name: config-rabbitmq
    volumes:
      - ./.docker/rabbitmq/etc/:/etc/rabbitmq/
      - ./.docker/rabbitmq/data/:/var/lib/rabbitmq/
      - ./.docker/rabbitmq/logs/:/var/log/rabbitmq/
    environment:
      - RABBITMQ_DEFAULT_USER=admin
      - RABBITMQ_DEFAULT_PASS=admin
    ports:
      - 5672:5672 # rabbitMQ default port
      - 15672:15672 # UI를 위한 port
    networks:
      - my-network

  user-mysql:
    image: mysql:latest
    container_name: user-mysql
    restart: always
    ports:
      - 13306:3306
    environment:
      - MYSQL_ROOT_PASSWORD=Tlmm3PjdJ*
      - MYSQL_DATABASE=test
    volumes:
      - ./mysql:/var/lib/mysql
    networks:
      - my-network

  order-mysql:
    image: mysql:latest
    container_name: order-mysql
    restart: always
    ports:
      - 23306:3306
    environment:
      - MYSQL_ROOT_PASSWORD=Tlmm3PjdJ*
      - MYSQL_DATABASE=test
    volumes:
      - ./mysql1:/var/lib/mysql
    networks:
      - my-network

  product-mysql:
    image: mysql:latest
    container_name: product-mysql
    restart: always
    ports:
      - 33306:3306
    environment:
      - MYSQL_ROOT_PASSWORD=Tlmm3PjdJ*
      - MYSQL_DATABASE=test
    volumes:
      - ./mysql2:/var/lib/mysql
    networks:
      - my-network

  redis_container:
    # 사용할 이미지
    image: redis:latest
    # 컨테이너명
    container_name: redis
    # 접근 포트 설정(컨테이너 외부:컨테이너 내부)
    ports:
      - 6379:6379
    # 스토리지 마운트(볼륨) 설정
    volumes:
      - ./redis/data:/data
      - ./redis/conf/redis.conf:/usr/local/conf/redis.conf
    # 컨테이너에 docker label을 이용해서 메타데이터 추가
    labels:
      - "name=redis"
      - "mode=standalone"
    # 컨테이너 종료시 재시작 여부 설정
    restart: always
    command: redis-server /usr/local/conf/redis.conf
    networks:
      - my-network

networks:
  my-network:
    driver: bridge
```
