package com.tesis.entidad;

import java.io.Serializable;
import javax.persistence.*;
import javax.swing.text.DefaultEditorKit.InsertTabAction;

import org.hibernate.sql.Insert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * The persistent class for the venta database table.
 * 
 */
@Entity
@Table(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idVenta;

	private String usuario;



	//bi-directional many-to-one association to Detallevta
	//El joincolum debe hacer referencia a la llave foranea de detallevta
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idVenta", referencedColumnName="idVenta",insertable = false,updatable = false)
	private Detallevta detallevta;


}