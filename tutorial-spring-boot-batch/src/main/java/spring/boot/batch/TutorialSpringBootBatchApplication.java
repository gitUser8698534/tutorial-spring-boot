package spring.boot.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing(dataSourceRef = "batchDataSource",
        transactionManagerRef = "batchTransactionManager"
)
public class TutorialSpringBootBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialSpringBootBatchApplication.class, args);
    }

}
