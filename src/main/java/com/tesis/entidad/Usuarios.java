package com.tesis.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuarios implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long IDU;
	@Column(name= "user")
	private String nom;
	private String pass;
	private int Edad;
	private String Sexo;
	private int IDtipou;
	public Usuarios(long iDU, String nom, String pass, int edad, String sexo, int iDtipou) {

		IDU = iDU;
		this.nom = nom;
		this.pass = pass;
		Edad = edad;
		Sexo = sexo;
		IDtipou = iDtipou;
	}
	public Usuarios() {
		
	}
	
	
	
	
}
