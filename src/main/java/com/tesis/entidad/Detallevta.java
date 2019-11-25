package com.tesis.entidad;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Detallevta implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idventa_idproducto;
	private int idVenta;
	private int IDproducto;

	private String descripcion;
	private int cant;
	private double precioSIVA;
	private int oferta;
	private double importe;
	private double total;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Productos productos;

	// bi-directional many-to-one association to Venta
	@OneToMany(mappedBy = "detallevta",fetch = FetchType.LAZY)
	private List<Venta> ventas;

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setDetallevta(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setDetallevta(null);

		return venta;
	}

}