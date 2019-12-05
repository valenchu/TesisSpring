package com.tesis.servicio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.java.Log;

@ControllerAdvice
@Log
public class ManejadorDeServicios {
	public static final String mensaje = "Hubo un error en el proceso de ejecucion verifique su codigo";
	  
	
	

}
