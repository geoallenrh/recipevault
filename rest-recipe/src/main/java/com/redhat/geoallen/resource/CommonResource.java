package com.redhat.geoallen.resource;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;


import org.jboss.logging.Logger;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.redhat.geoallen.view.RecipeFormData;

import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

abstract public class CommonResource {

    private final static String TEMP_DIR = System.getProperty("java.io.tmpdir");

    

    @Inject
   Logger log; 

    @ConfigProperty(name = "bucket.name")
    String bucketName;


    protected PutObjectRequest buildPutRequest(String fileName ,String mimeType) {
        return PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(mimeType)
                .build();
    }


    protected PutObjectRequest buildPutRequest(RecipeFormData formData) {
        return PutObjectRequest.builder()
                .bucket(bucketName)
                .key(formData.filename)
                .contentType(formData.mimetype)
                .build();
    }

    protected GetObjectRequest buildGetRequest(String objectKey) {
        return GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();
    }

    protected DeleteObjectRequest buildDeleteRequest(String objectKey) {
        return DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();
    }

    protected File tempFilePath() {
        return new File(TEMP_DIR, new StringBuilder().append("s3AsyncDownloadedTemp")
                .append((new Date()).getTime()).append(UUID.randomUUID())
                .append(".").append(".tmp").toString());
    }

    protected File uploadToTemp(InputStream data) {
        File tempPath;
        try {
            tempPath = File.createTempFile("uploadS3Tmp", ".tmp");
            Files.copy(data, tempPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return tempPath;
    }

    
}
