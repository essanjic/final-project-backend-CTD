package com.proyectctd.SpringBack.service;
import com.proyectctd.SpringBack.domain.Categoria;
import com.proyectctd.SpringBack.dto.RegistroCategoriaDTO;
import com.proyectctd.SpringBack.exceptions.BadRequestException;
import com.proyectctd.SpringBack.exceptions.ResourceNotFoundException;
import com.proyectctd.SpringBack.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {


    @PersistenceContext
    private EntityManager entityManager;
    private CategoriaRepository categoriaRepository;


    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;


    }

    public Categoria crearcategoria(Categoria categoria) throws Exception{
        Categoria categoriaCreada= new Categoria();
        categoriaCreada.setNombre(categoria.getNombre());

        return categoriaRepository.save(categoriaCreada);
    }
    public List<Categoria> getAllCategorias(){return  categoriaRepository.findAll();}

    public Categoria getCategoriaById(Long ctaId){
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(ctaId);
        return optionalCategoria.orElse(null);
    }

    @Transactional
    public Categoria editarCategoria(Long id, RegistroCategoriaDTO categoriaDTO) throws Exception {
        try {
            Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

            if (categoriaOptional.isPresent()) {
                Categoria categoria = categoriaOptional.get();

                // Actualiza los campos modificables
                categoria.setNombre(categoriaDTO.getNombre());

                return categoriaRepository.save(categoria);
            } else {
                throw new ResourceNotFoundException("CATEGORÍA NO ENCONTRADA");
            }
        } catch (ResourceNotFoundException ex) {
            throw ex; // Lanzar excepción de recurso no encontrado tal como está
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("ERROR: NO SE PUDO REALIZAR LA ACTUALIZACIÓN DE LA CATEGORÍA");
        }
    }
    public void deleteCategoria(Long cateId){categoriaRepository.deleteById(cateId);}
















}
