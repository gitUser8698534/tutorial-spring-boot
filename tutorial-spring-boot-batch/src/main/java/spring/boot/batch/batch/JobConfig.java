package spring.boot.batch.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import spring.boot.batch.batch.tasklet.TestTasklet;
import spring.boot.batch.service.TestService;

@Configuration
public class JobConfig {

    @Bean
    public Job testJob(JobRepository repository){
        return new JobBuilder("test job", repository)
                .start(testStep_Tasklet(repository, null))
                .build();
    }

    @Bean
    public Step testStep_Tasklet(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("testStep", jobRepository)
                .tasklet(testTasklet(null, null), transactionManager)
                .build();
    }

    // TODO 5. Value 어노테이션을 통해 JobParameters 접근
    // TODO 5.1 JobParameters 에는 일반적인 properties 도 접근할 수 있음
    // TODO 5.2 properties 에 init.file.name 항목이 있다면 @Value("#{jobParameters[init.file.name]}") 로 주입 받을 수 있음
    @Bean
    // TODO 6. scope 지정
    @StepScope
    public TestTasklet testTasklet(@Value("#{jobParameters[parameter]}") String parameter,TestService service){
        return new TestTasklet(service);
    }
}
