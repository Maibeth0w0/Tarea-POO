package com.poo;
import java.util.List;
import java.util.ArrayList;

public class InscripcionesPersonas {

    private List<Persona> listadoPersonas;

    public InscripcionesPersonas() {
        this.listadoPersonas = new ArrayList<>();
    }

    public void inscribirPersona(Persona persona) {
        listadoPersonas.add(persona);
        System.out.println("Persona inscrita: " + persona.getNombres());
    }

    public void eliminarPersona(Persona persona) {
        if (listadoPersonas.remove(persona)) {
            System.out.println("Persona eliminada: " + persona.getNombres());
        } else {
            System.out.println("Persona no encontrada en el listado.");
        }
    }

    public void actualizarPersona(Persona personaActualizada) {
        for (int i = 0; i < listadoPersonas.size(); i++) {
            if (listadoPersonas.get(i).getId().equals(personaActualizada.getId())) {
                listadoPersonas.set(i, personaActualizada);
                System.out.println("Datos actualizados para: " + personaActualizada.getNombres());
                return;
            }
        }
        System.out.println("Persona no encontrada para actualizar.");
    }

    public void guardarInformacion(Persona persona) {
        System.out.println("Guardando información de: " + persona);
    } //Método innecesario

    public void cargarDatos() {
        System.out.println("Cargando datos de personas inscritas...");
        for (Persona p : listadoPersonas) {
            System.out.println(p);
        }
    }
}
