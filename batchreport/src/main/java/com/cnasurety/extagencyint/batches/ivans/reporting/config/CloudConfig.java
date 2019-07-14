package com.cnasurety.extagencyint.batches.ivans.reporting.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Component
@ConfigurationProperties(prefix="workflow.cloud.gcp")
@PropertySources(value = {@PropertySource(value = "classpath:gcloud.properties")})
public class CloudConfig { 
	    
	    String credentials;
	    String projectId;
		public String getCredentials() {
			return credentials;
		}
		public void setCredentials(String credentials) {
			this.credentials = credentials;
		}
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
		
		
		
		@Bean
		public Storage getStorage() {
			
			    //storage = StorageOptions.getDefaultInstance().getService();
			    Credentials credentials;
				try {
					credentials = GoogleCredentials.fromStream(new FileInputStream(getCredentials()));
					return StorageOptions.newBuilder().setCredentials(credentials).setProjectId(getProjectId()).build().getService();
				} catch (IOException e) {
					
				}
			  return null; 
			  
		}
}
