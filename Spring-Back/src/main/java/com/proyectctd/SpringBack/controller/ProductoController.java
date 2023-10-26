package com.proyectctd.SpringBack.controller;

import com.proyectctd.SpringBack.domain.Caracteristica;

import com.proyectctd.SpringBack.domain.Producto;
import com.proyectctd.SpringBack.dto.ProductoRequestDTO;
import com.proyectctd.SpringBack.dto.RegistroCategoriaDTO;
import com.proyectctd.SpringBack.exceptions.ResourceNotFoundException;
import com.proyectctd.SpringBack.repository.ProductoRepository;
import com.proyectctd.SpringBack.service.CaracteristicasService;
import com.proyectctd.SpringBack.service.CategoriaService;
import com.proyectctd.SpringBack.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;


@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/api/productos")
public class ProductoController{
    private final CaracteristicasService caracteristicasService;
    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoController(
            CaracteristicasService caracteristicasService,
            CategoriaService categoriaService,
            ProductoService productoService,
            ProductoRepository productoRepository) {
        this.caracteristicasService = caracteristicasService;
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }



    //falta probar


    // SI FUNCIONA ----------------------------------------------------------------------------
    @Transactional
    @GetMapping("/buscar/{id}") //solo basico de entidad
    public ResponseEntity<ProductoRequestDTO> obtenerProductoPorId(@PathVariable Long id) {
        ProductoRequestDTO productoDTO = productoService.getProductoById(id);
        if (productoDTO != null) {
            return ResponseEntity.ok(productoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping("/{id}") //trae todo
    public ResponseEntity<ProductoRequestDTO> getProductById(@PathVariable Long id) {
        ProductoRequestDTO product = productoService.getProductoXId(id);
        ProductoRequestDTO productDTO = new ProductoRequestDTO(product.getId(), product.getNombre(), product.getDescripcion(),product.getPrecioVenta(), product.getImagen(),product.getRegistroCategoriaDTO(),product.getCaracteristicaIds());
        return ResponseEntity.ok(productDTO);
    }




    /*
    @Transactional
    @GetMapping("/listar") // carac vacias(? ver si pasa a json >to String <
    public ResponseEntity<String>  getAllProducts() {
        try{
            List<Producto> products = productoService.getAllProducts();
            return ResponseEntity.ok(products.toString());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productoService.deleteProduct(id);
    }



     */


    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listResponseEntity() throws Exception {
        return ResponseEntity.ok(productoService.getAllProducts());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProductoPorId(@PathVariable Long id) {
        try {
            productoService.eliminarProductoPorId(id);
            return ResponseEntity.ok("Producto eliminado con éxito");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/crear")
    public ResponseEntity<String> crearProducto(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precioVenta") double precioVenta,
            @RequestParam("id_categoria") Long categoriaId,
            @RequestParam("id_caracteristica") List<Caracteristica> caracteristicaIds,
            @RequestParam("imagenes") List<MultipartFile> imagenes) {

        try {
            // Convierte las imágenes a una lista de bytes


            ProductoRequestDTO productoRequestDTO = new ProductoRequestDTO();
            productoRequestDTO.setNombre(nombre);
            productoRequestDTO.setDescripcion(descripcion);
            productoRequestDTO.setPrecioVenta(precioVenta);

            RegistroCategoriaDTO categoriaDTO = new RegistroCategoriaDTO(categoriaId, null);
            productoRequestDTO.setRegistroCategoriaDTO(categoriaDTO);
            productoRequestDTO.setCaracteristicaIds(caracteristicaIds);
            List<byte[]> imagenesBytes = imagenes.stream()
                    .map(file -> {
                        try {
                            return file.getBytes();
                        } catch (IOException e) {
                            // Manejar el error de manera apropiada
                            throw new RuntimeException("Error al cargar la imagen: " + e.getMessage());
                        }
                    }).toList();

            // Llama al servicio para crear el producto
            ProductoRequestDTO responseDTO = productoService.crearProducto(productoRequestDTO, caracteristicaIds, imagenes);

            return ResponseEntity.ok("Producto creado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el producto: " + e.getMessage());
        }
    }
    //NO SE PROBO - NO FUNCIONA ----------------------------------------------------------------------------

    @PutMapping(value = "/update/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> actualizarProducto(
            @PathVariable("id") Long idProducto,
            @RequestBody ProductoRequestDTO productoRequestDTO,
            @RequestParam("caracteristicas") List<Caracteristica> caracteristicaIds,
            @RequestPart("nuevasImagenes") List<MultipartFile> nuevasImagenes) {
        try {
            ProductoRequestDTO productoActualizado = productoService.actualizarProducto(
                    idProducto,
                    productoRequestDTO,
                    caracteristicaIds,
                    nuevasImagenes);

            if (productoActualizado != null) {
                return ResponseEntity.ok(productoActualizado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el producto: " + e.getMessage());
        }
    }
}
