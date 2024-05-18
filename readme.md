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

- ngrinder를 활용한 100건 이상의 동시 요청시 NPE 발생
    - Junit 테스트 코드를 활용한 조회, 삽입, 병렬 키워드의 문제 확인
    - ```java
        @Test
        @DisplayName("다건 병렬 재고 조회")
        void 다건_동시_조회() {
            IntStream.range(1,10000).parallel()
            .forEach(x-> System.out.println("*******************optionalById가 Null인가? : " + repository.findById(1L).isPresent));
        }
      
        @Test
        @DisplayName("다건 병렬 재고 변경")
        void 다건_동시_재고변경() {
            IntStream.range(1,10000).parallel()
                    .forEach(x->{
                        System.out.println("***************Start**************");
                        System.out.println("*******************Stock data 조회");
                        Optional<StockRedis> byId = repository.findById(1L);
                        System.out.println("*******************Stock data get 및 재고변경");
                        StockRedis stock = byId.get().increaseInventory(1);
                        System.out.println("*******************Stock data 저장 ");
                        repository.save(stock);
                        System.out.println("***************END**************");
                    });
        } 


    - `.parallel`키워드가 없을 때 이상 없음을 확인한 후 동시성문제를 선정
    -  마켓컬리의 분산락 사용 방식 적용하여 해결
    -  정리문서 [Link!](https://azure-attention-c2e.notion.site/Redisson-52dd430bc9494bd7b05725b585efa503?pvs=4)
- 상품목록 및 상세정보 조회 성능 개선 `TPS : 155.7 → 423`
    - 동시 접속 시나리오 테스트 진행시 요청 처리 속도가 최대 6초까지 걸리는 현상 발생하여 Redis 캐싱 전략 도입
    - Scouter와 Ngrinder 기반 결과 정리문서 [Link!](https://azure-attention-c2e.notion.site/PRODUCT-LIST-0b20f869c7d9409cad199df4afc1c5b1?pvs=4)
    - | **수치** | 캐싱 적용 전 | 캐싱 적용 후 |
        | --- | --- | --- |
        | **평균 TPS** | 155.7  | 423  |
        | **Peek TPS** | 269.5  | 753  |
        | **평균 테스트 시간** | 3,019  | 1,120  |
        | **성공한 테스트** | 26,244  | 70,806  |
        
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
