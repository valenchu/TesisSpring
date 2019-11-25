package com.tesis.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.istack.Nullable;
import com.tesis.entidad.Productos;
import com.tesis.modelo.Login;
import com.tesis.repo.ProductosJPA;
import com.tesis.servicio.ProductoServicio;

@Controller
public class ControladorProducto{
		
	@Autowired
	private ProductosJPA pr;
	@Autowired
	private ProductoServicio sep;
/*	@GetMapping("/tabla")
	public String tablaPro(Model m) {
	
		List<Productos> tbl = new ArrayList<Productos>();
		tbl.addAll(pr.findAll());
		System.out.println("Entre en tabla productos");
		m.addAttribute("tabla", tbl);
		m.addAttribute("rol",Login.rol);
		String dato = sep.fastCom("admin");
		System.out.println(dato);
		return dato;
		
	}*/
	//Metodo para paginar
	@GetMapping("/tabla")
	@Nullable public String paginar(@RequestParam Map<String, Object> parametros, Model m) {
		Integer pagee = null;
		//Verifico parametro != de null. Si este parametro es != null asigna el valor pedido por el usuario si no no lo asigna
		if(parametros.get("num") != null) {
			pagee = Integer.valueOf(parametros.get("num").toString())-1;
		}else {
			pagee =0;
		}		
		System.out.println(pagee+"  ");
		//Paso los datos al metodo de servicio
		PageRequest Page = sep.paginado(pagee, 20, "idProducto");
		//busco el total de pag
		Page<Productos> pa = pr.findAll(Page);
		//intancio total de pag que tiene la base de datos en base a la paga
		int totalPag = pa.getTotalPages();
		if(totalPag > 0) {
			//Uso el metodo intStream.rangeClosed, para pasar el rango de pag que quiero guardar en una lista.
			//Con el metodo .boxed y collect collectors. Paso la coleccion a una lista para crearla; 
			List<Integer> listPag = IntStream.rangeClosed(1, totalPag).boxed().collect(Collectors.toList());
			 m.addAttribute("num", listPag);
		}
		
		// Page<Productos> pag = (Page) pr.findAll(PageRequest.of(0, 20));
		//paso rol para mostar el rol del que se logueo
		m.addAttribute("rol",Login.rol);
		//Paso tabla con todas las filas para paginarlas
		m.addAttribute("tabla1",pa.getContent());
		//Paso los 3 botones para siguiente y anterior
		m.addAttribute("reciente", pagee +1);
		m.addAttribute("Siguiente", pagee +2);
		m.addAttribute("Anterior", pagee);
		//Variable que devuelve el total de pag
		m.addAttribute("Ultima", totalPag);
		//Variable ocultar para ocultar la pestaña welcome
		m.addAttribute("ocultar", 1);
		//Verifico login para redireccionar si no se logueo y intenta entrar por URL
		String dato = sep.fastCom(Login.rol);
		return dato;
	}

}
