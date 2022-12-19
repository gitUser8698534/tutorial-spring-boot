package spring.boot.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

// TODO 1. Schedule 등록
@Configuration
public class ScheduleConfig {
    // TODO 2. JobLauncher 주입
    private final JobLauncher launcher;
    // TODO 3. Job 주입
    private final Job testJob;

    public ScheduleConfig(JobLauncher launcher, Job testJob) {
        this.launcher = launcher;
        this.testJob = testJob;
    }

    @Scheduled(cron = "0 0/25 0-5,18-23 ? * 1-5")
    public void schedule() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        // TODO 4. Job 실행
        // TODO 4.1 생성된 Job 은 JobInstance 이고 JobInstance 가 실행되는 것을 JobExecution 이라고 부름
        // TODO 4.2 JobInstance 와 JobExecution 은 1:N 관계이고 (JobInstance, JobParameters)로 중복을 비교
        // TODO 4.3 Job 을 실행할 때 JobParameters 를 설정하지 않거나 전에 실행했을 때와 동일하면 JobExecution 이 중복으로 인식되어 실행되지 않음

        // JOB parameter를 설정 하지 않았을 때 2번째 실행부터는 BATCH_JOB_EXECUTION 테이블에 EXIT_CODE가 NOOB으로 기록됨
        // JOB parameter의 식별 요소가 동일할 경우 JobInstanceAlreadyCompleteException 에외 발생
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("job execution time", LocalDateTime.now().toString(), true)
                .addString("parameter", LocalDateTime.now().toString(), false)
                .toJobParameters();
        launcher.run(testJob, jobParameters);
    }
}
