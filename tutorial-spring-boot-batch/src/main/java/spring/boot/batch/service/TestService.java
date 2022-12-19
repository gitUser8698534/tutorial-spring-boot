package spring.boot.batch.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.boot.batch.entity.TestEntity;

import java.util.List;

// TODO 0. service 생성
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
        // TODO 0.1 MyBatis Mapper Interface 에 등록된 쿼리 함수를 문자열로 실행
        return localTemplate.selectList("selectAll");
    }
}
