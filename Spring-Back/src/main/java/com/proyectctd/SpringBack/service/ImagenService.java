package com.proyectctd.SpringBack.service;

import com.proyectctd.SpringBack.domain.Imagen;
import com.proyectctd.SpringBack.domain.Producto;
import com.proyectctd.SpringBack.exceptions.BadRequestException;
import com.proyectctd.SpringBack.repository.ImagenRepository;
import com.proyectctd.SpringBack.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ImagenService {
    @Autowired
    private  ImagenRepository imagenRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    public ImagenService(ImagenRepository imagenRepository, ProductoRepository productoRepository, ProductoRepository productoRepository1) {
        this.imagenRepository = imagenRepository;
        this.productoRepository = productoRepository;
    }


    public List<Imagen> getAllImages(){return  imagenRepository.findAll();}

    public Imagen getImageById(Long imageId) {
        return imagenRepository.findById(imageId).orElse(null);
    }

    /*
    public Imagen createImage(Long productId, Imagen imagen) throws Exception {

        Optional<Producto> productoOptional = productoRepository.findById(productId);
        if (productoOptional.isEmpty()) {
            throw new BadRequestException("Producto no encontrado con el ID: " + productId);
        }
        Producto producto = productoOptional.get();
        imagen.setProducto(producto);
        Imagen imagenCreada = imagenRepository.save(imagen);
        return imagenCreada;
    }


     */

    public void deleteImage(Long imageId) {
        imagenRepository.deleteById(imageId);
    }




}







