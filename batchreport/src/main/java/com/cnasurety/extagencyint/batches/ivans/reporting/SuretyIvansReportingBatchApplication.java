package com.cnasurety.extagencyint.batches.ivans.reporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.cnasurety.extagencyint.batches.ivans.*" })
public class SuretyIvansReportingBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuretyIvansReportingBatchApplication.class, args);
        System.exit(0);
    }

}
