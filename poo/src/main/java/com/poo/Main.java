package com.poo;

public class Main {
    public static void main(String[] args) {
        InscripcionesPersonas inscripciones = new InscripcionesPersonas();

        Persona p1 = new Persona(1.0, "Luis", "Martínez", "luis@email.com");
        Persona p2 = new Persona(2.0, "Ana", "Gómez", "ana@email.com");

        inscripciones.inscribirPersona(p1);
        inscripciones.inscribirPersona(p2);

        inscripciones.cargarDatos();

        Persona p1Actualizado = new Persona(1.0, "Luis", "Martínez", "luis.nuevo@email.com");
        inscripciones.actualizarPersona(p1Actualizado);

        inscripciones.eliminarPersona(p2);

        inscripciones.cargarDatos();
    }
}