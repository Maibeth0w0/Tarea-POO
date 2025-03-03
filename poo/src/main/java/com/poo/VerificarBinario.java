package com.poo;

import java.util.List;

import com.poo.modelos.Persona;
import com.poo.persistencia.InscripcionesPersonas;

public class VerificarBinario {
    public static void main(String[] args) {
        InscripcionesPersonas inscripciones = new InscripcionesPersonas();
        inscripciones.cargarDatos(); // Cargar desde binario
        List<Persona> personas = inscripciones.getListadoPersonas();

        if (personas.isEmpty()) {
            System.out.println("⚠️ El archivo binario está vacío o no se está cargando correctamente.");
        } else {
            System.out.println("✅ Personas guardadas en el archivo binario:");
            for (Persona p : personas) {
                System.out.println(p);
            }
        }
    }
}