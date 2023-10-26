package com.proyectctd.SpringBack.service.api;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface AWS3Service {





    String uploadFile(MultipartFile file);

    String getImageUrl(String fileName);

    String uploadImage(String imageUrl);
    List<String> getObjectsFromS3();

    InputStream downloadFile(String key);
    void deleteFile(String key);


    List<String> listImageUrls();

}