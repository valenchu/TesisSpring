package com.tesis.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.entidad.Detallevta;
import com.tesis.entidad.Productos;
import com.tesis.entidad.Venta;
import com.tesis.modelo.Movimiento;
import com.tesis.repo.DetallevtaJPA;
import com.tesis.repo.ProductosJPA;
import com.tesis.repo.VentasJPA;

@Service
public class MovimientoServiciolmpl implements MovimientoServicio {

	@Autowired
	public DetallevtaJPA detV;
	@Autowired
	public VentasJPA vta;
	@Autowired
	public ProductosJPA pro;
	
	
	
	
	public Movimiento listMov() {
		Movimiento e = new Movimiento();
		
		
		
		Movimiento m = null;
		return m;
	}




	@Override
	public List<?> todo() {
		
		return vta.findAll();
	}

	
	
}
