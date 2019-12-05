package com.tesis.servicio;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.tesis.entidad.Productos;
import com.tesis.modelo.Login;
import com.tesis.repo.ProductosRepository;

@Service
public class ProductoServicio {

	@Autowired
	private UsuarioServicio log;
	@Autowired
	private ProductosRepository productosRepository;

//Devuelve comprobacion login agregar a los metodos para que rediriga en caso de login off
	public String fastCom(String com) {
		boolean b = true;
		b = log.comprobarLogin(Login.getNombre(), Login.getContrasena());
		if (b == true) {
			return com;
		} else if (b == false) {
			return "redirect:/";
		} else if (com.isEmpty()) {
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}

	// Metodo que retorna la vista
	public String retornarVista() {
		ModelMap m = new ModelMap();
		String dato = "Redirect:/";
		dato = fastCom(Login.rol);
		if (dato != "redirect:/") {
			m.addAttribute("rol", Login.rol.toUpperCase());

		}
		return dato;
	}

//Paginado lista de productos
	public Model paginado(int cantPagina, String ordenarPor, Model m, Map<String, Object> param) {
		// Verifico parametro != de null. Si este parametro es != null asigna el valor
		Integer pagee = param.get("num") != null ? (Integer.valueOf(param.get("num").toString()) - 1) : 0;

		// Paso los datos al metodo de servicio
		PageRequest page = PageRequest.of(pagee, cantPagina, Sort.by(ordenarPor));
		// busco el total de pag
		Page<Productos> pa = productosRepository.findAll(page);
		// intancio total de pag que tiene la base de datos en base a la paga
		int totalPag = pa.getTotalPages();
		if (totalPag > 0) {
			// Uso el metodo intStream.rangeClosed, para pasar el rango de pag que quiero
			// guardar en una lista.
			// Con el metodo .boxed y collect collectors. Paso la coleccion a una lista para
			// crearla;
			List<Integer> listPag = IntStream.rangeClosed(1, totalPag).boxed().collect(Collectors.toList());
			m.addAttribute("num", listPag);
		}

		// Page<Productos> pag = (Page) pr.findAll(PageRequest.of(0, 20));
		// paso rol para mostar el rol del que se logueo
		m.addAttribute("rol", Login.rol);
		// Paso tabla con todas las filas para paginarlas
		m.addAttribute("tabla1", pa.getContent());
		// Paso los 3 botones para siguiente y anterior
		m.addAttribute("reciente", pagee + 1);
		m.addAttribute("Siguiente", pagee + 2);
		m.addAttribute("Anterior", pagee);
		// Variable que devuelve el total de pag
		m.addAttribute("Ultima", totalPag);
		// Variable ocultar para ocultar la pestaña welcome
		m.addAttribute("ocultar", 1);
		// Verifico login para redireccionar si no se logueo y intenta entrar por URL
		return m;
	}

	public ModelMap buscarPorCant(String cantMi, String cantMa, ModelMap m) {
		List<Productos> p = null;
		boolean errorM = false;
		boolean busqueda=false;
		int cantMin = 0;
		int cantMax = 0;
		if(cantMi.isEmpty() || cantMa.isEmpty() || cantMi.equals("") || cantMa.equals("") || cantMa.equals(null) || cantMi.equals(null)) {
			errorM = false;
		}else if(cantMi.equals("[a-zA-Z!\"#$%&'()*+,-./:;<=>?@[]^_`{|}~]") || cantMa.equals("[a-zA-Z!\"#$%&'()*+,-./:;<=>?@[]^_`{|}~]")) {
			errorM = false;	
		}else {
			cantMin = Integer.valueOf(cantMi);
			cantMax = Integer.valueOf(cantMa);
			
		}
		if(cantMin <= cantMax && cantMin > 0 && cantMin != 0) {
			errorM = true;
		
		}	
		if(errorM == true) {
			p =productosRepository.findByCantBetweenCantminAndCantmax(cantMin, cantMax);
		}
		if(p != null && !p.isEmpty() &&  p.size() != 0) {
		busqueda=true;	
		m.addAttribute("lista", p);
		}
		
		m.addAttribute("erroMR" , errorM);
		m.addAttribute("busc", busqueda);
		return m;
	}

}
