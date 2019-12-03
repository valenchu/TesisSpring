package com.tesis.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tesis.entidad.Usuarios;
import com.tesis.modelo.Login;
import com.tesis.repo.UsuarioRepository;
@Service

public class UsuarioServicio {

	@Autowired
	private UsuarioRepository user;
	
	
	//Valida login metodo
	public boolean comprobarLogin(String a, String b) {
		//Comprueba si es null los campos
		if(user.validarNom(a) == null || user.validarPass(b)== null){			
			return false;
			//Comprueba campos vacios
		}else if(user.validarNom(a).isEmpty() || user.validarPass(b).isEmpty()) {
			return false;
			//Correcto login retorna true
		}else if((user.validarNom(a).equalsIgnoreCase(a)) && (user.validarPass(b).equalsIgnoreCase(b))) {			
			return true;
		};
		
		//si no a todo retorna false 
	
		  
	return false;
}

	//Metodo que devuleve rol de la conexion login
	public String getRol(String nombre, String contrasena) {

		System.out.println("entre en get rol");
			String tipo = "";
			List<Usuarios> traeme = user.findByNomAndPass(nombre,contrasena);
			for(int i = 0; i<traeme.size(); i++) {
				Integer val = traeme.get(i).getIDtipou();
				if(val == 1) {
					tipo = "Admin";
				}else if(val == 2) {
					tipo = "Empleado";
				}else {
					tipo = "redirect:/";
				}
			}
			return tipo;
		
	}
	//Metodo que pasado cierto tiempo cierra sesion;
@Scheduled(fixedRate = 900000)	
public String borrarRed() {
	Login.setNombre("");
	Login.setContrasena("");
	return "redirect:/";
}
@Scheduled(fixedRate = 200000)
public void mostrarDatosLogin() {
	String nom,clave;
	clave =Login.getContrasena();
	nom = Login.getNombre();
	System.out.println("Mi nombre es = "+nom+" y mi clave es= "+clave);
}



}
