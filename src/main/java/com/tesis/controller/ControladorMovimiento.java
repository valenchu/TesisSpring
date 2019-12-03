package com.tesis.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tesis.dto.VentaDTO;
import com.tesis.entidad.Venta;
import com.tesis.modelo.Login;
import com.tesis.servicio.MovimientoServicio;
import com.tesis.servicio.ProductoServicio;

@Controller
public class ControladorMovimiento {

	@Autowired
	private MovimientoServicio movimientoServicio;
	@Autowired
	private ProductoServicio productoServicio;

	// Metodo que define la busqueda de movimientos y los pagina
	@GetMapping("/mov")
	public String listMov(@RequestParam Map<String, Object> param, ModelMap m) {
		m = movimientoServicio.pag(20, "idVenta", m, param);

		// Variable ocultar para ocultar la pestaña welcome
		m.addAttribute("ocultar", 1);
		return productoServicio.retornarVista();
	}

	@GetMapping("/fecha{desde}{hasta}")

	// Usos response para ver resultado de consultas rapido
	public String mostrar(@RequestParam(value = "desde", required = false) String desde,
			@RequestParam(value = "hasta", required = false) String hasta, ModelMap mp,
			@RequestParam Map<String, Object> param, RedirectAttributes mensajeError) throws ParseException {
		// Desde, hasta variables de fechas - MP = variable de model map donde guardo
		// datos para pasar pantalla-
		// Param variable la cual recibo parametros enviados por el paginador -
		// mensajeError = Variable que devueleve un mensaje para error fechas
		String desdee = desde, hastaa = hasta;

		// Paso fechas a servicio
		mp = movimientoServicio.buscarFecha(desdee, hastaa, 20, "idVenta", mp, param);

		System.out.println(desde + "    " + hasta);
		mp.addAttribute("ocultar", 1);
		if (mp.get("error").equals(false)) {
			mensajeError.addFlashAttribute("ERROR", "Ha ingresado erroneamente las fechas de busqueda");
			return "redirect:/mov";
		} else if (mp.get("noReg").equals(false)) {
			mensajeError.addFlashAttribute("noRegg", "No se encontraron datos en la BD de la busqueda");
			return "redirect:/mov";
		} else {
			return productoServicio.retornarVista();
		}
	}
}
