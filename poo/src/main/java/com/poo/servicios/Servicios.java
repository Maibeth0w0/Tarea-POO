package com.poo.servicios;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface Servicios {
    @Autowired
    String imprimirPosicion(int posicion);

    Integer cantidadActual();

    List<String> imprimirListado();
}
