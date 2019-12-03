package com.tesis.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.tesis.entidad.Detallevta;
import com.tesis.entidad.Venta;

import lombok.Getter;
import lombok.Setter;

@Getter
public class VentaDTO {
	private int idVenta;
	private String usuario;
	private int IDproducto;
	private String descripcion;
	private int cant;
	private double precioSIVA;	
	private int oferta;
	private double importe;
	private double total;
	private Date fecha;

	public VentaDTO(int idVenta, String usuario, Detallevta detallevta) {
		this.idVenta = idVenta;
		this.usuario = usuario;
		this.IDproducto = detallevta.getIDproducto();
		this.descripcion = detallevta.getDescripcion();
		this.cant = detallevta.getCant();
		this.precioSIVA = detallevta.getPrecioSIVA();
		this.oferta = detallevta.getOferta();
		this.importe = detallevta.getImporte();
		this.total = detallevta.getTotal();
		this.fecha = detallevta.getFecha();
	}
	
	
	public static List<VentaDTO> builder(List<Venta> ventas) {
		List<VentaDTO> auxList = new ArrayList<VentaDTO>();
		for (Venta v : ventas) {
			for (Detallevta detallevta : v.getDetallevtas()) {
				VentaDTO auxVenta = new VentaDTO(v.getIdVenta(), v.getUsuario(), detallevta);
				auxList.add(auxVenta);
			}	
		}
		return auxList;
	}


	public VentaDTO(int idVenta, String usuario, int iDproducto, String descripcion, int cant, double precioSIVA,
			int oferta, double importe, double total, Date fecha) {
		super();
		this.idVenta = idVenta;
		this.usuario = usuario;
		IDproducto = iDproducto;
		this.descripcion = descripcion;
		this.cant = cant;
		this.precioSIVA = precioSIVA;
		this.oferta = oferta;
		this.importe = importe;
		this.total = total;
		this.fecha = fecha;
	}
	
}
