package com.tesis.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "productos")
@Getter @Setter
public class Productos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDproducto")
	private int idProducto;
	@Column(name = "Descripcio")
	private String descripcio;
	@Column(name = "PrecioSinIVA")
	private double preciosiniva;
	@Column(name = "PrecioConIVA")
	private double precioconiva;
	@Column(name = "Cantidad")
	private int cant;
	@Column(name = "Oferta")
	private int oferta;
	@Column(name = "altabajaproductos")
	private int alta_baja_productos;
	@Column(name = "altaBajaPedidos")
	private int alta_baja_pedidos;


	// bi-directional many-to-one association to Detallevta
	@OneToMany(mappedBy = "productos")
	private List<Detallevta> detallevtas;

	public Productos(int idProducto, String descripcio, double preciosiniva, double precioconiva, int cant, int oferta,
			int alta_baja_productos, int alta_baja_pedidos) {

		this.idProducto = idProducto;
		this.descripcio = descripcio;
		this.preciosiniva = preciosiniva;
		this.precioconiva = precioconiva;
		this.cant = cant;
		this.oferta = oferta;
		this.alta_baja_productos = alta_baja_productos;
		this.alta_baja_pedidos = alta_baja_pedidos;
	}

	public Productos() {

	}

}
