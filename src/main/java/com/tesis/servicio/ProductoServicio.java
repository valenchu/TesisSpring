package com.tesis.servicio;

import org.springframework.data.domain.PageRequest;

public interface ProductoServicio {
   public String fastCom(String com);
   public PageRequest paginado(int numPag, int tamanoPag, String ordenarPor);
   public String retornarVista();
}
