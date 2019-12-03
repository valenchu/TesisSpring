package com.tesis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.istack.Nullable;
import com.tesis.modelo.Login;
import com.tesis.servicio.ProductoServicio;

@Controller
public class ControladorProducto {

	@Autowired
	private ProductoServicio productoServicio;

	// Metodo para paginar
	@GetMapping("/tabla")
	@Nullable
	public String paginar(@RequestParam Map<String, Object> parametros, Model m) {
		m = productoServicio.paginado(20, "idProducto", m, parametros);

		String dato = productoServicio.fastCom(Login.rol);
		return dato;
	}

	@GetMapping("/tor{cant}")
@ResponseBody
	public ModelMap buscarPorCant(@RequestParam(value = "cant", required = false) int cant, ModelMap m) {

		productoServicio.buscarPorCant(cant, m);

		return m;

	}

}
