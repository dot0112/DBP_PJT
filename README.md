# DBP 장비 대여 서비스

## 사전 요구 사항
- **Java Development Kit (JDK) 17** 이상
- **Maven** (의존성 관리를 위한 선택 사항)
- **Oracle Database** (데이터베이스 용도)
- **Git** (저장소를 클론하기 위해 필요)

## 프로젝트 설정

### 저장소 클론
다음 명령어를 실행하여 저장소를 클론합니다:

```bash
git clone https://github.com/your-repository/DBP-equipmentRentalService.git
cd DBP-equipmentRentalService
```

### 의존성
이 프로젝트는 Maven Central에서 의존성을 관리합니다. 필요한 의존성은 build.gradle 파일에 정의되어 있습니다.
주요 의존성:
- **Spring Boot Starter Thymeleaf**: 웹 템플릿 엔진.
- **Spring Boot Starter Web**: RESTful API 개발.
- **Spring Boot Starter Data JPA**: 데이터베이스 상호작용.
- **Oracle JDBC (ojdbc8)**: Oracle 데이터베이스 연결.
