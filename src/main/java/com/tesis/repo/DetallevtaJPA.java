package com.tesis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tesis.entidad.Detallevta;
import com.tesis.modelo.Movimiento;
@Repository
public interface DetallevtaJPA extends JpaRepository<Detallevta, Integer> {
	

}
