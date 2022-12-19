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
