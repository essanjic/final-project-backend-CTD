package com.proyectctd.SpringBack.service.impl;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.proyectctd.SpringBack.service.api.AWS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AWSS3ServiceImpl implements AWS3Service {


    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    @Value("${aws.s3.bucket}")
    private String bucketName;



    @Override
    public String uploadFile(MultipartFile file) {
        File mainFile = new File(file.getOriginalFilename());
        try (FileOutputStream stream = new FileOutputStream(mainFile)) {
            stream.write(file.getBytes());
            String newFileName = System.currentTimeMillis() + "_" + mainFile.getName();
            PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mainFile);
            amazonS3.putObject(request);

            // Construir y devolver la URL del archivo subido
            String imageUrl = amazonS3.getUrl(bucketName, newFileName).toString();
            return imageUrl;
        } catch (IOException e) {
            // Manejar el error de manera apropiada
            return null;
        }
    }


    @Override
    public String getImageUrl(String fileName) {
        try {
            // Obtén la URL pública del archivo en el bucket
            String imageUrl = amazonS3.getUrl(bucketName, fileName).toString();
            return imageUrl;
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            // Maneja las excepciones de Amazon S3 si es necesario
            return null;
        }
    }

    @Override
    public String uploadImage(String imageUrl) {
        // Puedes generar la URL pública de la imagen en el bucket de S3
        String imageUrlInS3 = "https://" + bucketName + ".s3.amazonaws.com/" + imageUrl;
        return imageUrlInS3;
    }

    @Override
    public List<String> getObjectsFromS3() {
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        List<String> list = objects.stream().map(item -> {
            return item.getKey();
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public InputStream downloadFile(String key) {
        S3Object object = amazonS3.getObject(bucketName, key);
        return object.getObjectContent();
    }

    @Override
    public void deleteFile(String key) {
        amazonS3.deleteObject(bucketName, key);
    }

    @Override
    public List<String> listImageUrls() {
        List<String> imageUrls = new ArrayList<>();

        ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request().withBucketName(bucketName);
        ListObjectsV2Result listObjectsResult;

        do {

            listObjectsResult = amazonS3.listObjectsV2(listObjectsRequest);

            for (S3ObjectSummary objectSummary : listObjectsResult.getObjectSummaries()) {
                String imageUrl = amazonS3.getUrl(bucketName, objectSummary.getKey()).toString();
                imageUrls.add(imageUrl);
            }

            listObjectsRequest.setContinuationToken(listObjectsResult.getNextContinuationToken());
        } while (listObjectsResult.isTruncated());

        return imageUrls;
    }



}