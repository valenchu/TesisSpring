package com.tesis.servicio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.tesis.entidad.Venta;
import com.tesis.modelo.Login;
import com.tesis.repo.DetallevtaRepository;
import com.tesis.repo.VentasRepository;

@Service
@Transactional
public class MovimientoServicio {

	@Autowired
	public VentasRepository ventasRepository;
	@Autowired
	public DetallevtaRepository detallevtaRepository;

	// Metodo que retorna la busqueda completa de venta detalle
	public List<Venta> findAll() {

		return ventasRepository.findAll();
	}

	// Metodo de paginado movimiento
	public ModelMap pag(int cantPagina, String ordenarPor, ModelMap m, Map<String, Object> param) {
		Integer pagina = param.get("nume") != null ? (Integer.valueOf(param.get("nume").toString())-1) : 0;
		PageRequest page = PageRequest.of(pagina, cantPagina, Sort.by(ordenarPor));
		Page<Venta> pa = ventasRepository.findAll(page);

		int totalPage = pa.getTotalPages();
		if (totalPage > 0) {
			// IntStream.rangeClosed(1,totalPage) = el gango sercano entre un comienzo 1 y
			// un final que es el total page ejemplo = 1 y 10
			// De boxed en adelante se encarga de transformar los num en una lista
			List<Integer> listPag = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			m.addAttribute("nume", listPag);
		}

		// paso rol para mostar el rol del que se logueo
		m.addAttribute("rol", Login.rol);
		// Paso tabla con todas las filas para paginarlas
		m.addAttribute("filTab", pa.getContent());
		// Paso los 3 botones para siguiente y anterior
		m.addAttribute("rec", pagina + 1);
		m.addAttribute("Sigue", pagina + 2);
		m.addAttribute("Anter", pagina);
		// Variable que devuelve el total de pag
		m.addAttribute("Ult", totalPage);
		return m;
	}
	// Metodo para buscar por fecha en movimiento

	public ModelMap buscarFecha(String desde, String hasta, int cantPagina, String ordenarPor,
			ModelMap m, Map<String, Object> param) throws ParseException {
		boolean errorMensaje= false,noElement = false;
		// Comienzo funcion para paginar. El parametro recibido lo paso a iteger
		Integer pagina = param.get("nume") != null ? (Integer.valueOf(param.get("nume").toString())-1) : 0;
		
		PageRequest page = null;
		// Creo el page request para paginar
		if(pagina != null) {
		 page = PageRequest.of(pagina, cantPagina, Sort.by(ordenarPor));
		}
		Page<Venta> vs = null;

		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date desdee = null;
		Date hastaa = null;
		//Verifico fechas string para formatearlas
		if (desde != null && hasta != null && desde != "" && hasta != "") {
			desdee = formater.parse(desde);
			hastaa = formater.parse(hasta);
		}
	
		//Verifico fechas data
		if (desdee != null && hastaa != null) {
			m.addAttribute("data1", desdee);
			m.addAttribute("data2", hastaa);
		}
		//Verifico fechas String
		if (desde != null && hasta != null) {
			m.addAttribute("desde", desde);
			m.addAttribute("hasta", hasta);
		}
		long totalElement = 0;
		//Verifico q nada sea null para consultar a la BD
		if (ventasRepository.findByAllData(m.get("data1"), m.get("data2"), page) != null) {
			 vs  = ventasRepository.findByAllData(m.get("data1"), m.get("data2"), page);
			 //obtengo los elementos para saber cuantos hay y mostrar mensaje en pantalla
			  totalElement = ventasRepository.findByAllData(m.get("data1"), m.get("data2"), page).getTotalElements();
		}
		
		// Obtengo el total de paginas con las filas buscadas
		int totalPage = vs.getTotalPages();
		if (totalPage > 0) {
			// IntStream.rangeClosed(1,totalPage) = el gango sercano entre un comienzo 1 y
			// un final que es el total page ejemplo = 1 y 10
			// De boxed en adelante se encarga de transformar los num en una lista
			List<Integer> listPag = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			m.addAttribute("nume", listPag);
		}
		// paso rol para mostar el rol del que se logueo
		m.addAttribute("rol", Login.rol);
		// Paso tabla con todas las filas para paginarlas
		m.addAttribute("fecha", vs.getContent());
		// Paso los 3 botones para siguiente y anterior
		if(pagina != null) {
		m.addAttribute("rec", pagina + 1);
		m.addAttribute("Sigue", pagina + 2);
		m.addAttribute("Anter", pagina);
		}
		// Variable que devuelve el total de pag
		m.addAttribute("Ult", totalPage);
		//Creo objeto booleano dentro del model map
		//Verifico las fechas para pasar un boolean y dar el verificado
		if (desde != null && hasta != null && desde != "" && hasta != "") {
		if(desdee.compareTo(hastaa) == -1) {
			errorMensaje = true;
		}else if(desdee.compareTo(hastaa)== 0){
			errorMensaje = true;
		} 
		}else {
			errorMensaje = false ;
		}
		if(totalElement != 0) {
			noElement = true;
		}
		m.addAttribute("noReg", noElement);
		m.addAttribute("error", errorMensaje);
		return m;

	}

	public String fechaActual() {

		LocalDate ahora = LocalDate.now();

		return ahora.toString();
	}
	

}
