package com.tesis.entidad;

import java.io.Serializable;
import javax.persistence.*;
import javax.swing.text.DefaultEditorKit.InsertTabAction;

import org.hibernate.sql.Insert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


/**
 * The persistent class for the venta database table.
 * 
 */
@Entity
@Table(name = "venta")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVenta;

	private String usuario;



	//bi-directional many-to-one association to Detallevta
	//El joincolum debe hacer referencia a la llave foranea de detallevta
	/*@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="idVenta", referencedColumnName="idVenta")*/
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "venta", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Detallevta> detallevtas;


}