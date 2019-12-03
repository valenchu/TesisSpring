package com.tesis.entidad;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the detallevta database table.
 * 
 */
@Entity
@Table(name = "detallevta")
/**
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Detallevta implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idventa_idproducto;
	private int idVenta;
	
	private int IDproducto;
	@Column(name = "Descripcion")
	private String descripcion;
	@Column(name = "Cant")
	private int cant;
	private double precioSIVA;
	private int oferta;
	@Column(name = "Importe")
	private double importe;
	@Column(name = "Total")
	private double total;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "Fecha")
	private Date fecha;
	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Productos productos;

	// bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="idVenta",insertable = false, updatable = false)
	private Venta venta;


}