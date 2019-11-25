package com.tesis.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tesis.entidad.Detallevta;
import com.tesis.entidad.Venta;
import com.tesis.modelo.Movimiento;
@Repository
public interface VentasJPA extends JpaRepository<Venta, Integer>{
	
	@Query(value = "SELECT venta.idVenta, venta.usuario, detallevta.IDproducto, detallevta.Descripcion,"
			+ "detallevta.Fecha, detallevta.Cant, detallevta.PrecioSIVA, detallevta.oferta,detallevta.Importe"
		+ " FROM venta INNER JOIN detallevta ON venta.idVenta = detallevta.idVenta"			
			, nativeQuery = true )
	List<Movimiento> a =  buscarListMov ();


	

}
