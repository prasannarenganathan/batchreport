package com.cnasurety.extagencyint.batches.ivans.reporting.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("workflow.report")
@PropertySources(value = {@PropertySource(value = "classpath:application.properties")})
public class ApplicationConfig { 
	String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	

}
