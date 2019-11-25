package com.tesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.modelo.Login;
import com.tesis.repo.DetallevtaJPA;
import com.tesis.repo.VentasJPA;
import com.tesis.servicio.MovimientoServicio;
import com.tesis.servicio.ProductoServicio;

@Controller
public class ControladorMovimiento {

	@Autowired
	private MovimientoServicio mv;
	@Autowired
	private VentasJPA dt;
	@Autowired
	private ProductoServicio prs;
	
	@GetMapping("/mov")
	public String listMov(Model m){
		System.out.println("Soy una lista "+dt.buscarListMov().toString());
	
		m.addAttribute("mov",dt.findAll());
		return prs.retornarVista();
	}
}
