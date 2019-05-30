package com.workflow.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.workflow.report.service.ExportService;

@SpringBootApplication
public class BatchreportApplication implements CommandLineRunner {

    @Autowired
    ExportService exportService;

    public static void main(String[] args) {
        SpringApplication.run(BatchreportApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        exportService.exportEventAuditTable();
        exportService.exportKeyValueTable();
    }

}
