package com.tesis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tesis.entidad.Usuarios;
@Repository
public interface UsuarioJPA extends JpaRepository<Usuarios, Long> {

	String findByNom(String nom);

	List<?> findByPass(String pass);

	List<Usuarios> findByNomAndPass(String nom, String pass);

	@Query(value = "SELECT DISTINCT user FROM usuarios WHERE user = :nom", nativeQuery = true)
	String validarNom(@Param("nom") String a);
	@Query(value = "SELECT DISTINCT pass FROM usuarios WHERE pass = :pass", nativeQuery = true)
	String validarPass(@Param("pass") String b);
	
}
