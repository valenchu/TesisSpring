package com.tesis.modelo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;





@Data

 
public class Login {
	
	@NotEmpty
	@Length(max = 20, message = "Maximo de caracteres 20")
	@Getter @Setter
	private static String nombre;
	@NotEmpty
	@Length(max = 20, message = "Maximo de caracteres 20")
	@Getter @Setter
	private static String contrasena;
	@Getter @Setter
	public static String rol;	
	
}
