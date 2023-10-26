package com.proyectctd.SpringBack.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectctd.SpringBack.domain.Caracteristica;
import com.proyectctd.SpringBack.domain.Categoria;
import com.proyectctd.SpringBack.domain.Imagen;
import com.proyectctd.SpringBack.domain.Producto;
import com.proyectctd.SpringBack.dto.ProductoRequestDTO;
import com.proyectctd.SpringBack.dto.RegistroCategoriaDTO;
import com.proyectctd.SpringBack.exceptions.ResourceNotFoundException;
import com.proyectctd.SpringBack.repository.CaracteristicasRepository;
import com.proyectctd.SpringBack.repository.CategoriaRepository;
import com.proyectctd.SpringBack.repository.ImagenRepository;
import com.proyectctd.SpringBack.repository.ProductoRepository;
import com.proyectctd.SpringBack.service.impl.AWSS3ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@Service
public class ProductoService {



    private  ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired

    private CaracteristicasRepository caracteristicasRepository;

    @Autowired
    private ImagenRepository imagenRepository;
    @Autowired

    private AWSS3ServiceImpl aws3Service;
    @Autowired

    private ObjectMapper objectMapper;




    @Autowired

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository,
                           CaracteristicasRepository caracteristicasRepository,
                           ImagenRepository imagenRepository, AWSS3ServiceImpl aws3Service,
                           ObjectMapper objectMapper) {
       this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.caracteristicasRepository = caracteristicasRepository;
        this.imagenRepository = imagenRepository;
        this.aws3Service = aws3Service;
        this.objectMapper = objectMapper;
    }


    // LO QUE SI ANDA ---------------------------------------------------------

    //buscar por id solo info basica
    public ProductoRequestDTO getProductoById(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();


            ProductoRequestDTO responseDTO = new ProductoRequestDTO();
            responseDTO.setId(producto.getId_producto());
            responseDTO.setNombre(producto.getNombre());
            responseDTO.setDescripcion(producto.getDescripcion());
            responseDTO.setPrecioVenta(producto.getPrecioVenta());


            return responseDTO;
        }
        return null;
    }

    public ProductoRequestDTO getProductoXId(Long productoId) {
        Producto product = productoRepository.findById(productoId).orElse(null);
        if (product != null) {
            product.getImagenes().size();
            product.getCategoria().getNombre();
            product.getCaracteristicas().size();


            ProductoRequestDTO productoRequestDTO = new ProductoRequestDTO();
            productoRequestDTO.setId(product.getId_producto());
            productoRequestDTO.setNombre(product.getNombre());
            productoRequestDTO.setDescripcion(product.getDescripcion());
            productoRequestDTO.setPrecioVenta(product.getPrecioVenta());
            productoRequestDTO.setImagen(product.getImagenes());
            productoRequestDTO.setCaracteristicaIds(product.getCaracteristicas());
            RegistroCategoriaDTO categoriaDTO = new RegistroCategoriaDTO(product.getCategoria().getId_categoria(), product.getCategoria().getNombre());
            productoRequestDTO.setRegistroCategoriaDTO(categoriaDTO);

            return productoRequestDTO;
        } else {
            return null;
        }
    }


    public  List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    public void deleteProduct(Long productId) {
        productoRepository.deleteById(productId);
    }



    public ProductoRequestDTO crearProducto(
            ProductoRequestDTO productoRequestDTO,
            List<Caracteristica> caracteristicaIds,
            List<MultipartFile> files) throws Exception {
        if (productoRepository.existsByNombre(productoRequestDTO.getNombre())) {
            throw new Exception("Ya existe un producto registrado con el nombre ingresado.");
        }

        if (caracteristicaIds.size() < 3) {
            throw new Exception("Debe seleccionar al menos tres características para el producto.");
        }

        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(productoRequestDTO.getNombre());
        nuevoProducto.setDescripcion(productoRequestDTO.getDescripcion());
        nuevoProducto.setPrecioVenta(productoRequestDTO.getPrecioVenta());

        // Asigna la categoría
        Categoria categoria = categoriaRepository.findById(productoRequestDTO.getRegistroCategoriaDTO().getId_categoria())
                .orElseThrow(() -> new Exception("Categoría no encontrada con el ID proporcionado."));
        nuevoProducto.setCategoria(categoria);

        // Asigna las características existentes
        nuevoProducto.setCaracteristicas(caracteristicaIds);

        // Guarda el nuevo producto en la base de datos
        Producto productoGuardado = productoRepository.save(nuevoProducto);

        // Procesa las imágenes y guárdalas en la base de datos, si es necesario
        List<Imagen> imagenes = new ArrayList<>();
        for (MultipartFile file : files) {
            String imagenUrl = aws3Service.uploadFile(file);
            Imagen img = new Imagen();
            img.setImagenesUrls(imagenUrl);
            // Asigna el producto a la imagen antes de guardarla
            img.setProducto(productoGuardado);
            imagenes.add(img);
        }

        // Guarda todas las imágenes en la base de datos
        imagenRepository.saveAll(imagenes);

        // Asigna las imágenes al producto y actualiza en la base de datos
        productoGuardado.setImagenes(imagenes);
        productoRepository.save(productoGuardado);

        // Crea un ProductoRequestDTO para devolver como respuesta
        ProductoRequestDTO responseDTO = new ProductoRequestDTO();
        responseDTO.setNombre(productoGuardado.getNombre());
        responseDTO.setDescripcion(productoGuardado.getDescripcion());
        responseDTO.setPrecioVenta(productoGuardado.getPrecioVenta());
        responseDTO.setImagen(productoGuardado.getImagenes());
        responseDTO.setCaracteristicaIds(productoGuardado.getCaracteristicas());

        RegistroCategoriaDTO categoriaDTO = new RegistroCategoriaDTO(productoGuardado.getCategoria().getId_categoria(), productoGuardado.getCategoria().getNombre());
        responseDTO.setRegistroCategoriaDTO(categoriaDTO);

        return responseDTO;
    }

    public void eliminarProductoPorId(Long id) {
        // Verifica si el producto existe antes de eliminarlo
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se encontró un producto con el ID: " + id);
        }
    }
//ESTO FALTA PROBAR / NO FUNCIONA -----------------------------------------------------------

    public ProductoRequestDTO actualizarProducto(
            Long idProducto,
            ProductoRequestDTO productoRequestDTO,
            List<Caracteristica> caracteristicaIds,
            List<MultipartFile> nuevasImagenes) throws Exception {
        // Verificar si el producto existe
        Optional<Producto> productoOptional = productoRepository.findById(idProducto);
        if (productoOptional.isEmpty()) {
            throw new Exception("El producto con el ID proporcionado no existe.");
        }

        // Obtener el producto existente
        Producto productoExistente = productoOptional.get();

        // Actualizar los atributos del producto
        productoExistente.setNombre(productoRequestDTO.getNombre());
        productoExistente.setDescripcion(productoRequestDTO.getDescripcion());
        productoExistente.setPrecioVenta(productoRequestDTO.getPrecioVenta());

        // Actualizar la categoría si es necesario
        Categoria categoria = categoriaRepository.findById(productoRequestDTO.getRegistroCategoriaDTO().getId_categoria())
                .orElseThrow(() -> new Exception("Categoría no encontrada con el ID proporcionado."));
        productoExistente.setCategoria(categoria);

        // Actualizar las características
        productoExistente.setCaracteristicas(caracteristicaIds);

        // Actualizar las imágenes
        List<Imagen> imagenesActualizadas = new ArrayList<>();
        for (MultipartFile nuevaImagen : nuevasImagenes) {
            String imagenUrl = aws3Service.uploadFile(nuevaImagen);
            Imagen nuevaImagenObj = new Imagen();
            nuevaImagenObj.setImagenesUrls(imagenUrl);
            nuevaImagenObj.setProducto(productoExistente);
            imagenesActualizadas.add(nuevaImagenObj);
        }

        // Eliminar las imágenes existentes
        imagenRepository.deleteAll(productoExistente.getImagenes());

        // Guardar las nuevas imágenes en la base de datos
        imagenRepository.saveAll(imagenesActualizadas);

        // Actualizar la lista de imágenes del producto
        productoExistente.setImagenes(imagenesActualizadas);

        // Guardar el producto actualizado en la base de datos
        Producto productoActualizado = productoRepository.save(productoExistente);

        // Crear un ProductoRequestDTO para devolver como respuesta
        ProductoRequestDTO responseDTO = new ProductoRequestDTO();
        responseDTO.setNombre(productoActualizado.getNombre());
        responseDTO.setDescripcion(productoActualizado.getDescripcion());
        responseDTO.setPrecioVenta(productoActualizado.getPrecioVenta());
        responseDTO.setImagen(productoActualizado.getImagenes());
        responseDTO.setCaracteristicaIds(productoActualizado.getCaracteristicas());

        RegistroCategoriaDTO categoriaDTO = new RegistroCategoriaDTO(productoActualizado.getCategoria().getId_categoria(), productoActualizado.getCategoria().getNombre());
        responseDTO.setRegistroCategoriaDTO(categoriaDTO);

        return responseDTO;
    }



}