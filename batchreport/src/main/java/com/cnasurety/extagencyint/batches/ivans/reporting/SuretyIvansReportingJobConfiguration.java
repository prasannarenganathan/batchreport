package com.cnasurety.extagencyint.batches.ivans.reporting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.service.ExportService;

@Configuration
@EnableBatchProcessing
public class SuretyIvansReportingJobConfiguration {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    ExportService exportService;

    @Bean
    @StepScope
    public Tasklet exportEventAuditTasklet(@Value("#{jobParameters['message']}") String message) {

        return (stepContribution, chunkContext) -> {
            exportService.exportEventAuditTable();
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    @StepScope
    public Tasklet exportKeyValueTasklet(@Value("#{jobParameters['message']}") String message) {

        return (stepContribution, chunkContext) -> {
            exportService.exportKeyValueTable();
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step exportEventAuditStep() {

        return stepBuilderFactory.get("exportEventAuditTasklet").tasklet(exportEventAuditTasklet(null)).build();

    }

    @Bean
    public Step exportKeyValueStep() {

        return stepBuilderFactory.get("exportKeyValueTasklet").tasklet(exportKeyValueTasklet(null)).build();

    }

    @Bean
    public Job helloWorldJob() {

        JobBuilder jobBuilder = jobBuilderFactory
                .get("helloworld - test" + String.valueOf(new java.util.Random().nextInt()));

        SimpleJobBuilder sbuilder = jobBuilder.start(exportEventAuditStep()).next(exportKeyValueStep());

        Job job = sbuilder.build();

        return job;

    }
}
