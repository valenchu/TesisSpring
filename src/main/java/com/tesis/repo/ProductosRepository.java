package com.tesis.repo;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tesis.entidad.Productos;
@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer>, PagingAndSortingRepository<Productos, Integer> {
	
	//Page<Productos> findByAll(Pageable pageable);
	List<Productos> findByCant(@Param("cant") int cant );
}
