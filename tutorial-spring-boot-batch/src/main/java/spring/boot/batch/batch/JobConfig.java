package spring.boot.batch.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import spring.boot.batch.batch.tasklet.TestTasklet;
import spring.boot.batch.service.TestService;

// TODO 3. Job 생성
// TODO 3.1 Configuration 어노테이션 사용
@Configuration
public class JobConfig {

    // TODO 7 Job 생성에 JobRepository 가 필요하고 JobRepository 가 EnableBatchProcessing 에서 설정한 DataSource, TransactionManager 를 통해 Meta Data 관리
    @Bean
    public Job testJob(JobRepository repository){
        return new JobBuilder("test job", repository)
                .start(testStep_Tasklet(repository, null))
                .build();
    }

    // TODO 6. tasklet 을 실행하는 step 생성
    @Bean
    public Step testStep_Tasklet(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("testStep", jobRepository)
                .tasklet(testTasklet(null), transactionManager)
                .build();
    }

    // TODO 5. tasklet 을 빈으로 생성
    @Bean
    public TestTasklet testTasklet(TestService service){
        return new TestTasklet(service);
    }
}
