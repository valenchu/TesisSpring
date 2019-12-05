package com.tesis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.istack.Nullable;
import com.tesis.modelo.Login;
import com.tesis.servicio.ProductoServicio;

@Controller

public class ControladorProducto {

	@Autowired
	private ProductoServicio productoServicio;

	// Metodo para paginar
	@GetMapping(value = "/tabla")
	@Nullable
	public String paginar(@RequestParam Map<String, Object> parametros, Model m) {
		m = productoServicio.paginado(20, "idProducto", m, parametros);

		String dato = productoServicio.fastCom(Login.rol);
		return dato;
	}

	//Metodo que busca por cantidad
	
	@GetMapping(value = "/tor{cantMin}{cantMax}")
	public String buscarPorCant(
	@RequestParam(value = "cantMin", required = false)  String cantMin,	
	@RequestParam(value = "cantMax", required = false) String cantMax, ModelMap m, RedirectAttributes errMensaje) {
		m = productoServicio.buscarPorCant(cantMin, cantMax, m);
		if(m.get("erroMR").equals(false)) {
			errMensaje.addFlashAttribute("errorMS","Debe ingresar solo numero y mayor que 0");
			return "redirect:/tabla";
		}else if(m.get("busc").equals(false)) {
			errMensaje.addFlashAttribute("errorMSbusc","No se encontraron datos en la BD");
			return "redirect:/tabla";
		}
			m.addAttribute("ocultar", 1);
			m.addAttribute("rol", Login.rol.toUpperCase());
			return productoServicio.fastCom(Login.rol);
	}


}
