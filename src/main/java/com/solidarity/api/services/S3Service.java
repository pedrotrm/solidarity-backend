package com.solidarity.api.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.solidarity.api.services.exception.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3;

    @Value("${s3.bucket}")
    public String bucketName;

    public URI uploadFile(MultipartFile multipartFile){
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contexType = multipartFile.getContentType();
            return uploadFile(is, fileName, contexType);
        } catch (IOException e){
            throw new FileException("Erro de IO" + e.getMessage());
        }
    }

    public URI uploadFile(InputStream is, String nomeArquivo, String contexType){
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contexType);
            LOG.info("Iniciando upload");
            s3.putObject(bucketName, nomeArquivo, is, meta);
            LOG.info("Upload finalizado");
            return s3.getUrl(bucketName, nomeArquivo).toURI();
        } catch (URISyntaxException e){
            throw new FileException("Erro ao converter URl para URI");
        }
    }

}
