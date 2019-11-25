package com.tesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tesis.modelo.Login;
import com.tesis.repo.UsuarioJPA;
import com.tesis.servicio.ProductoServicio;

@Controller
@RequestMapping("/")
public class Controlador {

	@Autowired
	private UsuarioJPA user ;
	@Autowired
	private ProductoServicio prs;
	
	@GetMapping("/index")

	public String listar () {
		
		return "index";
		
	}
	@GetMapping("/welcome")
	public String mos(Model m) {
		String dato = "Redirect:/";
		dato = prs.fastCom(Login.rol);
		if(dato != "redirect:/") {
		m.addAttribute("rol",Login.rol.toUpperCase());
		}
		
		return dato;
	}
}
