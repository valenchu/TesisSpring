package com.tesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tesis.modelo.Login;
import com.tesis.repo.UsuarioJPA;
import com.tesis.servicio.UsuarioServicio;


@Controller
public class ControladorLogin {
	 ModelAndView mo ;
	 @Autowired
	 private UsuarioJPA usu;
	 @Autowired
	 private UsuarioServicio userService;
	
	 //Metodo que verifica usuario contraseña
	@GetMapping(value = "/golum")
	
	public ModelAndView log (//Reques param ayuda a tomar los valores del input he creado una clase login la cual me guarda
			//los paramatros de nombre y contraseña			
			@RequestParam(value = "nombre",defaultValue = "" ,required = false) String nombre,
			@RequestParam(value = "contrasena",defaultValue = "" ,required = false)String contrasena,
			Login l, RedirectAttributes rd) {
		System.out.println("Me trajo esto : "+ nombre);
	
		ModelAndView otro = new ModelAndView("redirect:/");
		
		
		boolean comprobar = true;
		comprobar = userService.comprobarLogin(nombre, contrasena);
		if (comprobar == true) {
			System.out.println("entre en comprobar");
			String tipo = userService.getRol(nombre, contrasena).toLowerCase();
			mo  = new ModelAndView(tipo);
			//Creo objeto login
		l.setNombre(nombre);
		l.setContrasena(contrasena);
		l.setRol(tipo);

		this.mo.addObject("a",nombre);
		this.mo.addObject( "b",contrasena);	
		this.mo.addObject("sesion",l.getRol());
		
		//Lo que digo aca es : Le paso la lista a stream y recorro toda la lista con un foreach instancio en la variable
		//nombre y me traiga todos los usuarios que sean igual a lo que inserte en el input	
		
			return this.mo;			
		}else{
			System.out.println("Todo me retorno falso asique devolvi redirect:/");
			rd.addFlashAttribute("errorms", "Usuario o Contraseña erroneos!");
		return otro;
		}	
		//Model and view lo que hace es pasar la vista por parametrp que sería "lista" y despues retorno el model and  view				
	}


}
