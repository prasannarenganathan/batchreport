package com.cnasurety.extagencyint.batches.ivans.reporting;

import java.io.File;
import java.sql.Timestamp;

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
import org.springframework.stereotype.Component;

import com.cnasurety.extagencyint.batches.ivans.reporting.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.BatchJobExecutionRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.service.WorkFlowExportService;

@Configuration
@EnableBatchProcessing
@Component
public class SuretyIvansReportingJobConfiguration {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private BatchJobExecutionRepository batchJobExecutionRepository;

    @Autowired
    WorkFlowExportService exportService;
    
    @Autowired
    ApplicationConfig applicationConfig;

    Timestamp lastExecutedJobTimeStamp;

    @Bean
    @StepScope
    public Tasklet exportEventAuditTasklet(@Value("#{jobParameters['message']}") String message) {
        LOGGER.info(message);
        return (stepContribution, chunkContext) -> {
            chunkContext.setAttribute("EVENT_AUDIT_TBL", "EVENT_AUDIT_TBL");
            exportService.exportEventAuditTable(getLastExecutedJobTimeStamp());
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    @StepScope
    public Tasklet exportKeyValueTasklet(@Value("#{jobParameters['message']}") String message) {
        LOGGER.info(message);

        return (stepContribution, chunkContext) -> {
            exportService.exportKeyValueTable(getLastExecutedJobTimeStamp());
            return RepeatStatus.FINISHED;
        };
    }
    
    @Bean
    @StepScope
    public Tasklet exportIvansMessageTablesTasklet(@Value("#{jobParameters['message']}") String message) {
        LOGGER.info(message);

        return (stepContribution, chunkContext) -> {
            exportService.exportIvansMessageTables(getLastExecutedJobTimeStamp());
            return RepeatStatus.FINISHED;
        };
    }

    
    @Bean
    @StepScope
    public Tasklet exportNotificationsTablesTasklet(@Value("#{jobParameters['message']}") String message) {
        LOGGER.info(message);

        return (stepContribution, chunkContext) -> {
            exportService.exportNotificationTables(getLastExecutedJobTimeStamp());
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step exportEventAuditStep() {

        return stepBuilderFactory.get("exportEventAuditTasklet")
                .tasklet(exportEventAuditTasklet("Exporting Event Audit Table")).build();

    }

    @Bean
    public Step exportKeyValueStep() {

        return stepBuilderFactory.get("exportKeyValueTasklet")
                .tasklet(exportKeyValueTasklet("Exporting Key Value Table")).build();

    }
    
    @Bean
    public Step exportIvansMessageStep() {

        return stepBuilderFactory.get("exportIvansMessageTablesTasklet")
                .tasklet(exportIvansMessageTablesTasklet("Exporting IvansMessage and IvansMessageAttachment Table")).build();

    }

    @Bean
    public Step exportNotificationStep() {

        return stepBuilderFactory.get("exportNotificationsTablesTasklet")
                .tasklet(exportNotificationsTablesTasklet("Exporting Notification, NOTIFICATION_AGENCY_EXTENSION_TBL,  package and  Document Table")).build();

    }
    
    @Bean
    public Job ExportJob() {
    	
        
        File directory = new File(applicationConfig.getFilePath());
        if (! directory.exists()){
            directory.mkdir();
        }
    	setLastExecutedJobTimeStamp(batchJobExecutionRepository.findLastExecutedTimeStamp());
        JobBuilder jobBuilder = jobBuilderFactory
                .get("Export Job: " + String.valueOf(new java.util.Random().nextInt()));
        SimpleJobBuilder sbuilder = jobBuilder.
        		start(exportEventAuditStep()).
        		next (exportKeyValueStep()).
        		next (exportIvansMessageStep()).
        		next (exportNotificationStep());
        Job job = sbuilder.build();
        return job;

    }

    public Timestamp getLastExecutedJobTimeStamp() {
        return lastExecutedJobTimeStamp;
    }

    public void setLastExecutedJobTimeStamp(Timestamp lastExecutedJobTimeStamp) {
        this.lastExecutedJobTimeStamp = lastExecutedJobTimeStamp;
    }

}
