package java.moakma.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration //이 클래스가 Spring의 설정 클래스임을 나타냅니다. Spring은 이 클래스를 읽어서 Bean을 등록하고 관리합니다.
@RequiredArgsConstructor //Lombok에서 제공하는 어노테이션으로, 필드 기반 생성자를 자동으로 생성합니다. 이 클래스의 생성자는 applicationContext 필드를 받습니다.
public class MyBatisConfiguration {
    private final ApplicationContext applicationContext;

    @Bean // Spring IoC 컨테이너에 등록될 Bean을 생성하는 메서드를 나타냅니다.
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    // application.properties 또는 application.yml 파일에서 spring.datasource.hikari 프리픽스로 시작하는
    // 설정을 가져와서 HikariConfig 객체에 매핑합니다. 이를 통해 HikariCP 데이터베이스 연결 풀의 설정을 로드합니다.
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig()); //앞서 생성한 hikariConfig() 메서드를 통해 설정된 HikariCP 데이터 소스를 반환합니다.
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); // MyBatis의 SqlSessionFactory를 생성하기 위한 Spring의 팩토리 빈입니다.
        sqlSessionFactoryBean.setDataSource(dataSource()); // 데이터 소스를 설정합니다. 앞서 생성한 dataSource() 메서드를 통해 HikariCP 데이터 소스를 사용합니다.
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml")); // MyBatis 매퍼 파일의 위치를 지정합니다. classpath*:/mapper/*.xml 경로에 있는 XML 매퍼 파일을 사용합니다.
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:config/config.xml")); // MyBatis 설정 파일의 위치를 지정합니다. classpath:/config/config.xml 파일을 사용합니다.
        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBean().getObject();
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true); // MyBatis의 설정을 구성합니다. 이 경우, 데이터베이스의 칼럼명이 스네이크 케이스(snake_case)인 경우에도 자바의 카멜 케이스(camelCase)와 매핑할 수 있도록 설정합니다.
            return sqlSessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 위의 코드는 Spring Boot 애플리케이션에서 MyBatis를 사용하여 데이터베이스와 연결하고, HikariCP를 통해 데이터베이스 커넥션 풀을 설정하는 방법을 보여줍니다.
    // MyBatisConfiguration 클래스는 Spring의 @Configuration 어노테이션을 사용하여 설정 클래스로 지정되며, 필요한 Bean들을 정의하여 Spring IoC 컨테이너에 등록합니다.

}
