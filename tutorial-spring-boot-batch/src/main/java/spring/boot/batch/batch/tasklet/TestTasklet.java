package spring.boot.batch.batch.tasklet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import spring.boot.batch.service.TestService;

// TODO 4. Tasklet 작성
// TODO 4.1 Job 에 등록 가능한 건 flow, step 두가지
// TODO 4.2 step 으로 사용할 수 있는건 itemReader, processor, writer 로 따로 설장하여 사용하는 방법과 한번에 작성하는 tasklet 이 있음
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
