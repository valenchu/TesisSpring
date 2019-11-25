package com.tesis.modelo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {
	
	private int idVenta;
	private String user;
	private int idProducto;
	private String desc;
	private Date fecha;
	private int cant;
	private  double precioSiva;
	private int oferta;
	private double importe;

}
