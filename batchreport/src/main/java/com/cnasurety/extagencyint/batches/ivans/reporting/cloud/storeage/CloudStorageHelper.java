package com.cnasurety.extagencyint.batches.ivans.reporting.cloud.storeage;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.config.CloudConfig;
import com.google.api.services.storage.model.Bucket;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageClass;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;
import com.google.common.io.ByteStreams;



@Service
public class CloudStorageHelper {

 @Autowired
 private CloudConfig cloudConfig;

  private static final String DATE_FORMAT= "YYYYMMddHHmmssSSS";
  private static final String CONTENT_TYPE= "text/csv";
  
  
  /**
   * Uploads a file to Google Cloud Storage to the bucket specified in the BUCKET_NAME
   * environment variable, appending a timestamp to end of the uploaded filename.
   */
  public String uploadFile(String name, String fileType, InputStream inputStream, final String bucketName) throws IOException {
    DateTimeFormatter dtf = DateTimeFormat.forPattern(DATE_FORMAT);
    DateTime dt = DateTime.now(DateTimeZone.UTC);
    String dtString = dt.toString(dtf);
    final String fileName = name + dtString+fileType;
    byte[] targetArray = ByteStreams.toByteArray(inputStream);
    
    
/* Use this logic in case if we need to save the files inside the folder on daily basis folder eg:2019-07-12
 * DateTimeFormatter dtfDate = DateTimeFormat.forPattern("YYYY-MM-dd");
   String folderName = DateTime.now(DateTimeZone.UTC).toString(dtfDate)+"/";
   below just replace filname with folderName+filaName 
*/    
    
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(CONTENT_TYPE).build();
        try (WriteChannel writer = cloudConfig.getStorage().writer(blobInfo)) {
          try {
            writer.write(ByteBuffer.wrap(targetArray, 0, targetArray.length));
          } catch (Exception ex) {
            // handle exception
          }
        }
    return blobInfo.getMediaLink();
  }
 
    
    
   
   
   
 
}

