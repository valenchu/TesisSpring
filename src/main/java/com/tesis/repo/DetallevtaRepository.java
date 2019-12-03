package com.tesis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tesis.entidad.Detallevta;
@Repository
public interface DetallevtaRepository extends JpaRepository<Detallevta, Integer> {
	

}
