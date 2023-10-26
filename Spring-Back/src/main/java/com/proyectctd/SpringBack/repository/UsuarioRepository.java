package com.proyectctd.SpringBack.repository;

import com.proyectctd.SpringBack.domain.Rol;
import com.proyectctd.SpringBack.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findById(int id);

    Usuario findByNombre(String nombre);

    List<Usuario> findByRol(String rol);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.rol = :rol WHERE u.id_usuario = :userId")
    int updateRol(@Param("userId") int userId, @Param("rol") Rol rol);
}
