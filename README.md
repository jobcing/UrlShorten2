# DataSaver - API
당신의 데이터 요금을 아껴주는 똑똑한 와이파이 도우미

# **DataSaver**
DataSaver는 다음과 같은 특징이 있습니다.
- 사용자가 설정에서 와이파이를 연결 및 해제하는 번거로운 과정을 앱의 백그라운드 작업을 이용하여 자동으로 진행합니다.
- 와이파이의 품질이 매우 낮아 실제로 연결이 불가능하거나 신뢰할 수 없는 경우 자동으로 제외시킵니다.
- 지인이 알고있는 와이파이 정보를 쉽게 공유하거나 자신의 와이파이 정보를 백업하는 기능을 제공합니다.

# 개발 환경
개발 환경은 아래와 같습니다.
- STS 3.9.0.RELEASE 
- Amazon EC2 (https://aws.amazon.com/ko/ec2/?nc2=h_m1)
- Amazon S3 (https://aws.amazon.com/ko/s3/?nc2=h_m1)
- MySQL 5.5.57

# 의존성 목록
자세한 사항은 프로젝트 소스 내 build.gradle 파일을 확인해주세요.
```build.gradle
dependencies {
  // spring-boot-starter
  compile('org.springframework.boot:spring-boot-starter-aop')
  compile('org.springframework.boot:spring-boot-starter-data-jpa')
  compile('org.springframework.boot:spring-boot-starter-web')
  compile('org.springframework.boot:spring-boot-configuration-processor')
  compile('org.springframework.boot:spring-boot-starter-mail')
  testCompile('org.springframework.boot:spring-boot-starter-test')
  
  // mysql-connector-java
  runtime('mysql:mysql-connector-java')
	
  // java-jwt
  compile('com.auth0:java-jwt:3.2.0')
  
  // springfox-swagger2
  compile('io.springfox:springfox-swagger2:2.2.2')
  compile('io.springfox:springfox-swagger-ui:2.2.2')
  
  // gson
  compile('com.google.code.gson:gson:2.7')
  
  // commons-lang3
  compile('org.apache.commons:commons-lang3:3.0')
  
  // aws-java-sdk
  compile('com.amazonaws:aws-java-sdk:1.11.66')
  
  // commons-io
  compile('commons-io:commons-io:2.4')
}
```
# 빠른 시작
1) Git 클론
```text
git clone "https://github.com/DataSaver-Dev/DataSaver-API.git"
```
2) STS 임포트
- 패키지 익스플로러에서 마우스 우측 버튼을 눌러 임포트를 선택합니다.
- 'Gradle (STS) &gt; Gradle (STS) project'을 선택하고 다음을 누릅니다.
- 아래와 같은 팝업에서 빌드 모델을 반드시 누른 후 완료를 누릅니다.

![](https://raw.githubusercontent.com/DataSaver-Dev/DataSaver-API/master/images/build_gradle_ex.png)

3) application.properties 파일 생성
- application.properties 파일은 API 연동에 필요한 귀중한 정보(데이터베이스, 구글 웹 메일, 아마존 서비스 등)를 포함합니다.
- 'DataSaver-API > src/main/java'에서 해당 파일에서 소스 폴더명를 resources로 생성합니다.
- resources에 파일명을 application.properties로 생성합니다.
- 아래와 같은 application.properties 파일을 자신의 개발 환경에 맞게 수정해서 저장합니다.
```application.properties
server.contextPath=/v1

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database=mysql
spring.jpa.show-sql=true
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://[데이터베이스 URL]:[데이터베이스 포트]/[데이터베이스 이름]?useUnicode=true&characterEncoding=utf8
spring.datasource.username=[데이터베이스 계정]
spring.datasource.password=[데이터베이스 비밀번호]

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=[구글 계정]
spring.mail.password=[구글 비밀번호]
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

amazon.accessKeyId=[아마존 접근 키 아이디]
amazon.accessSecretKey=[아마존 비밀 키]
amazon.userProfileImgBucketName=[사용자 프로필 이미지 버킷 이름]
amazon.endPoint=[아마존 엔드 포인트]

gcm.apiKey=[구글 클라우드 메시지 API 키]
```
4) 실행 및 테스트<br>
- 최초 실행시 'DataSaver-API > src/main/java > DataSaverApplication.java'를 실행합니다.
- 아래와 같이 스웨거를 통해 API 명세를 확인할 수 있습니다. (http://127.0.0.1/v1/swagger-ui.html)

![](https://raw.githubusercontent.com/DataSaver-Dev/DataSaver-API/master/images/intro.png)

# 모델 설계
클릭하시면 크게보실 수 있습니다.

![](https://raw.githubusercontent.com/DataSaver-Dev/DataSaver-API/master/images/datasaver_api_diagram.png)

# 프로젝트 구성
DataSaver는 다음과 같이 구성되어 있습니다.
```text
DataSaver-API
├── build.gradle (클래스패스 파일)
├── build (빌드 디렉토리)
├── gradle (그래들 디렉토리)
├── images (이미지 디렉토리)
├── LICENSE (프로젝트 라이센스 파일)
├── README.md (프로젝트 설명서 파일)
├── src (소스 디렉토리)
├── resources (리소스 디렉토리)
├── src/main/java (자바 소스코드 디렉토리)
│   ├── com.datasaver.api (루트 패키지)
│   │   ├── DataSaverApplication.java (Spring Boot 앱 소스파일)
│   │   ├── controllers (컨트롤러 패키지)
│   │   │   ├── forms (폼 패키지)
│   │   │   ├── responses (리스폰스 패키지)
│   │   │   │   ├── data (데이터 패키지)
│   │   ├── domains (도메인 패키지)
│   │   │   ├── views (뷰 패키지)
│   │   ├── payloads (페이로드 패키지)
│   │   ├── repositories (레포지토리 패키지)
│   │   ├── services (서비스 패키지)
│   │   │   ├── interfaces (인터페이스 패키지)
│   │   ├── utils (유틸 패키지)
│   │   │   ├── auth (인증 패키지)
│   │   │   ├── aws (아마존 서비스 패키지)
│   │   │   ├── gcm (구글 클라우드 메세징 패키지)
│   │   │   ├── log (로그 패키지)
│   │   │   ├── mail (메일 패키지)
│   │   │   ├── password (비밀번호 패키지)
│   │   │   ├── res (리소스 패키지)
│   │   │   ├── swagger (스웨거 패키지)
...
```

# 라이센스
본 프로젝트는 Apache 2.0 License를 따릅니다. http://www.apache.org/licenses/LICENSE-2.0

# 문의사항
기타 문의사항이 있으실 경우 아래의 **문의 수단**으로 연락해주세요.
- 메일 : **devetude@naver.com**, **quentin1992@naver.com**, **minuk8932@naver.com**
- github : **https://github.com/DataSaver-Dev/DataSaver-API/issues**
