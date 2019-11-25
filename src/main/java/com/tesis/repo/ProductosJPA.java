package com.tesis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tesis.entidad.Productos;
@Repository
public interface ProductosJPA extends JpaRepository<Productos, Integer>, PagingAndSortingRepository<Productos, Integer> {
	

}
