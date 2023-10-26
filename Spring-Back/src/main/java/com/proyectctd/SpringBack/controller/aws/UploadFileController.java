package com.proyectctd.SpringBack.controller.aws;

import com.proyectctd.SpringBack.service.api.AWS3Service;
import com.proyectctd.SpringBack.service.impl.AWSS3ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/s3")
public class UploadFileController {

    @Autowired
    private AWS3Service awss3Service;
    @PostMapping(value = "/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam(value = "files") MultipartFile[] files) {
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            String imageUrl = awss3Service.uploadFile(file); // Obtener la URL de la imagen cargada
            imageUrls.add(imageUrl); // Agregar la URL a la lista
        }

        return new ResponseEntity<>(imageUrls, HttpStatus.OK); // Devolver la lista de URLs
    }



    @GetMapping("/list-images-S3")
    public ResponseEntity<List<String>> listImages() {
        try {
            List<String> imageUrls = awss3Service.listImageUrls(); // Debes implementar este método en tu servicio
            return new ResponseEntity<>(imageUrls, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<String>> listFiles() {
        return new ResponseEntity<List<String>>(awss3Service.getObjectsFromS3(), HttpStatus.OK);
    }

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> download(@RequestParam("key") String key) {
        InputStreamResource resource  = new InputStreamResource(awss3Service.downloadFile(key));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+key+"\"").body(resource);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteFile(@RequestParam("key") String key) {
        awss3Service.deleteFile(key);
        String response = "El archivo " + key + " fue eliminado correctamente de S3";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}