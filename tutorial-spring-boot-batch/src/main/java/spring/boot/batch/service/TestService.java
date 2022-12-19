package spring.boot.batch.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.boot.batch.entity.TestEntity;

import java.util.List;

@Service
public class TestService {
    private final SqlSessionTemplate localTemplate;
    private final SqlSessionTemplate awsTemplate;

    public TestService(@Qualifier("localSqlSessionTemplate") SqlSessionTemplate localTemplate,
                       @Qualifier("awsSqlSessionTemplate") SqlSessionTemplate awsTemplate) {
        this.localTemplate = localTemplate;
        this.awsTemplate = awsTemplate;
    }

    List<TestEntity> getAll(){
        return localTemplate.selectList("selectAll");
    }
}
