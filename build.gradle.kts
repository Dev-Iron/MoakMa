plugins {
    // Java 플러그인
    java

    // Spring Boot 플러그인 (버전 2.7.5)
    id("org.springframework.boot") version "2.7.5"

    // Spring Dependency Management 플러그인 (버전 1.1.5)
    id("io.spring.dependency-management") version "1.1.5"
}

group = "java"
version = "1.0"

java {
    toolchain {
        // Java 17 버전 사용 설정
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        // annotationProcessor 구성 확장
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    // Maven Central 리포지토리 사용
    mavenCentral()
}

dependencies {

    // Spring Boot 스타터 의존성 (웹 애플리케이션 개발용)
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Boot 스타터 의존성 (웹 서비스 개발용)
    implementation("org.springframework.boot:spring-boot-starter-web-services")

    // Spring Boot 스타터 의존성 (Thymeleaf 사용)
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // MyBatis Spring Boot 스타터 (MyBatis 사용)
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.0")

    // Spring Boot 스타터 의존성 (AOP 사용)
    implementation("org.springframework.boot:spring-boot-starter-aop:2.7.5")

    // log4jdbc-log4j2 (JDBC 로그를 로깅하기 위해 사용)
    implementation("org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16")

    // Lombok 라이브러리 (컴파일 시에만 사용)
    compileOnly("org.projectlombok:lombok")

    // 개발 환경에서만 사용하는 Spring Boot DevTools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Thumbnailator (이미지 처리 라이브러리)
    implementation("net.coobird:thumbnailator:0.4.8")

    // Spring Boot 스타터 의존성 (Quartz 스케줄러 사용)
    // implementation("org.springframework.boot:spring-boot-starter-quartz:2.7.5")

    // Gson (JSON 처리를 위한 라이브러리)
    implementation("com.google.code.gson:gson:2.8.2")

    // MySQL 커넥터 (런타임에만 사용)
    runtimeOnly("com.mysql:mysql-connector-j")

    // Spring Boot Configuration Processor (애노테이션 프로세서)
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.7.5")

    // 테스트 코드에서 Lombok 사용
    testImplementation("org.projectlombok:lombok:1.18.24")

    // Lombok 애노테이션 프로세서
    annotationProcessor("org.projectlombok:lombok")

    // 테스트 코드에서 Lombok 애노테이션 프로세서 사용
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")

    // 테스트 코드에서 Lombok 사용
    testImplementation("org.projectlombok:lombok:1.18.24")

    // Spring Boot 스타터 의존성 (테스트용)
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // JUnit 플랫폼 런처 (테스트 런타임에만 사용)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.withType<Test> {
    // JUnit 플랫폼 사용 설정
    useJUnitPlatform()
}
