package com.tesis.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.tesis.modelo.Login;
import com.tesis.repo.ProductosJPA;

@Service
public class ProductoServiciolmpl implements ProductoServicio {

	@Autowired
	private ProductosJPA pro;
	@Autowired
	private UsuarioServicio log ;

//Devuelve comprobacion login agregar a los metodos para que rediriga en caso de login off
	@Override
	public String fastCom(String com) {
		boolean b = true;
		b = log.comprobarLogin(Login.getNombre(), Login.getContrasena());
		if(b == true) {
			return com;
		}else if(b == false) {
		return "redirect:/";
		}else if (com.isEmpty()) {
		return "redirect:/";
		}else {
			return "redirect:/";
		}
	}
	//Metodo que retorna la vista
	public String retornarVista() {
		ModelMap m = new ModelMap();
	 String dato = "Redirect:/";
	dato = fastCom(Login.rol);
	if(dato != "redirect:/") {
	m.addAttribute("rol",Login.rol.toUpperCase());
	
	}
	return dato;
	}
//Paginado lista de productos
	public PageRequest paginado(int numPag, int tamanoPag, String ordenarPor) {
		
		
		//Paso los datos para paginar numPag: Numero de pagina , tamanoPag: El tamaño que poseera la lista 
		//ordenarPor: Sera ordenada por la variable q se pase ejemplo descripcion ID cant.
	PageRequest pag = PageRequest.of(numPag, tamanoPag,Sort.by(ordenarPor));
	return pag;
	}
	
	
}
