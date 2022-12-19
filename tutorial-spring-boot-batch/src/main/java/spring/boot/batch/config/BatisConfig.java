package spring.boot.batch.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatisConfig {
    // TODO 5. MyBatis 사용을 위한 SqlSessionFactory, SqlSessionTemplate 설정
    // TODO 5.1 MyBatis 사용하는 DataSource 가 여러개이므로 여러개로 나눠서 설정
    // TODO 5.2 Batch 용 DataSource 는 TransactionManager 설정
    // TODO 5.3 MyBatis Mapper Interface 는 서로 다른 SqlSessionFactory 에서도 공통으로 사용할 수 있
    @Bean
    public DataSourceTransactionManager batchTransactionManager(@Qualifier("batchDataSource") DataSource batchDataSource){
        return new DataSourceTransactionManager(batchDataSource);
    }

    @Primary
    @Bean
    public SqlSessionFactory localSqlSessionFactory(@Qualifier("localDataSource") DataSource localDataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(localDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("spring.boot.batch.entity, spring.boot.batch.entity.local");
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean
    public SqlSessionTemplate localSqlSessionTemplate(SqlSessionFactory localSqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(localSqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory awsSqlSessionFactory(@Qualifier("awsDataSource") DataSource awsDataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(awsDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("spring.boot.batch.entity, spring.boot.batch.entity.aws");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate awsSqlSessionTemplate(SqlSessionFactory awsSqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(awsSqlSessionFactory);
    }
}
