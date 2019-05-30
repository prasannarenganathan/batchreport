package com.cnasurety.extagencyint.batches.ivans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.cnasurety.extagencyint.batches.ivans.workflow.service.ExportService;

@SpringBootApplication
@ComponentScan(basePackages = { "com.cnasurety.extagencyint.batches.ivans.workflow.*",
        "com.cnasurety.extagencyint.batches.ivans.reporting.*" })
public class SuretyIvansReportingBatchApplication implements CommandLineRunner {
    @Autowired
    ExportService exportService;

    public static void main(String[] args) {
        SpringApplication.run(SuretyIvansReportingBatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        exportService.exportEventAuditTable();
        exportService.exportKeyValueTable();
    }

}
