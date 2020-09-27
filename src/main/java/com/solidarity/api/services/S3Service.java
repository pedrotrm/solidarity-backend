package com.solidarity.api.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3;

    @Value("${s3.bucket}")
    public String bucketName;

    public void uploadFile(String localFilePath){
        try {
            File file = new File(localFilePath);
            LOG.info("Iniciando upload");
            s3.putObject(new PutObjectRequest(bucketName, "teste", file));
            LOG.info("Upload finalizado");
        }
        catch (AmazonServiceException e){
            LOG.info("AmazonServiceException: "+e.getErrorMessage());
            LOG.info("Status code: "+ e.getErrorCode());
        }
        catch (AmazonClientException e){
            LOG.info("AmazonClientException: "+e.getMessage());
        }
    }
}
