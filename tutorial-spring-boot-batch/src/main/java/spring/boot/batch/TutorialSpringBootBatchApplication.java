package spring.boot.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO 1. EnableBatchProcessing 어노테이션 설정
// TODO 1.1 JobConfig 을 DefaultBatchConfiguration 상속으로 사용할 수도 있음

// TODO 2 Batch Meta Data 저장을 위한 DataSource, TransactionManager 설정
@SpringBootApplication
@EnableBatchProcessing(dataSourceRef = "batchDataSource",
        transactionManagerRef = "batchTransactionManager"
)
public class TutorialSpringBootBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialSpringBootBatchApplication.class, args);
    }

}
