package spring.boot.batch.batch.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import spring.boot.batch.service.TestService;

@Slf4j
public class TestTasklet implements Tasklet {
    private final TestService service;

    public TestTasklet(TestService service) {
        this.service = service;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.info("Tasklet 실행");

        return RepeatStatus.FINISHED;
    }
}
