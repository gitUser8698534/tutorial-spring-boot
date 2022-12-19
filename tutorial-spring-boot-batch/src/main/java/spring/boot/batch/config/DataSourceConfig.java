package spring.boot.batch.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // TODO 2. batch 용 DataSource 에 초기화 sql 설정
    // TODO 4. batch 라이브러리 내에 sql resource 디렉토리로 이동하여 사용
    @Bean
    @Primary
    public DataSourceInitializer batchDataSourceInitializer(@Qualifier("batchDataSource") DataSource dataSource) {
        ResourceDatabasePopulator databasePopulate = new ResourceDatabasePopulator();
        databasePopulate.addScript(new ClassPathResource("batch/schema-drop-mysql.sql"));
        databasePopulate.addScript(new ClassPathResource("batch/schema-mysql.sql"));
        databasePopulate.setIgnoreFailedDrops(true);

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulate);

        return initializer;
    }

    // TODO 3. 테스트로 초기화 시킬 sql 설정
    @Bean
    public DataSourceInitializer localDataSourceInitializer(@Qualifier("localDataSource") DataSource dataSource) {
        ResourceDatabasePopulator databasePopulate = new ResourceDatabasePopulator();
        databasePopulate.addScript(new ClassPathResource("schema-test.sql"));
        databasePopulate.setIgnoreFailedDrops(true);

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulate);

        return initializer;
    }

    // TODO 1. DataSource 3개 등록
    // 다른 곳에서 사용시 Qualifier 어노테이션에 해당 DataSource 빈 이름으로 주입
    @Bean
    @Primary
    public DataSource localDataSource(@Value("${spring.datasource.local.url}") String url,
                                      @Value("${spring.datasource.local.username}") String username,
                                      @Value("${spring.datasource.local.password}") String password,
                                      @Value("${spring.datasource.driver-class-name}") String driver){
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driver)
                .build();
    }
    @Bean
    public DataSource batchDataSource(@Value("${spring.datasource.batch.url}") String url,
                                      @Value("${spring.datasource.batch.username}") String username,
                                      @Value("${spring.datasource.batch.password}") String password,
                                      @Value("${spring.datasource.driver-class-name}") String driver){
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driver)
                .build();
    }
    @Bean
    public DataSource awsDataSource(@Value("${spring.datasource.aws.url}") String url,
                                    @Value("${spring.datasource.aws.username}") String username,
                                    @Value("${spring.datasource.aws.password}") String password,
                                    @Value("${spring.datasource.driver-class-name}") String driver){
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driver).build();
    }
}
