package com.tesis.servicio;

public interface UsuarioServicio {
	
	public boolean comprobarLogin(String nombre, String contrasena);
	public String getRol(String nombre, String contrasena);
	

}
