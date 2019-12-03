package com.tesis.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tesis.dto.VentaDTO;
import com.tesis.entidad.Venta;
@Repository
public interface VentasRepository extends JpaRepository<Venta, Integer>{
	
	//@Query("SELECT v FROM Venta v JOIN v.detallevtas d ")
	//public List<?> buscarList();
	
	@Query("SELECT DISTINCT v FROM Venta v JOIN v.detallevtas d WHERE d.fecha BETWEEN :desde AND :hasta")

	Page<Venta> findByAllData(@Param("desde") Object object, @Param("hasta") Object object2, Pageable page);
	


	
}
