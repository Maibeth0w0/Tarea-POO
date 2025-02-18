package com.poo;

import java.util.ArrayList;
import java.util.List;

//Nombre de la clase no revela las intenciones de la misma
// alterno => ListaCursosProfesores

public class CursosProfesores {
    private List<CursoProfesor> listadoCursoProfesores;

    public CursosProfesores() {
        this.listadoCursoProfesores = new ArrayList<>();
    }

    public void inscribirCursoProfesores(CursoProfesor cursoProfesor){
        listadoCursoProfesores.add(cursoProfesor);
        System.out.println("Curso " + cursoProfesor.getCurso().getNombre() + " asignado exitosamente al profesor " + cursoProfesor.getProfesor().getNombres() + ".");
    }
    
    public void guardarInformacion(CursoProfesor cursoProfesor) {
        System.out.println("Guardando información");
    }//método innecesario

    public void cargarDatos() {
        System.out.println("Cargando datos de los profesores asignados...");
        for (CursoProfesor i : listadoCursoProfesores) {
            System.out.println(i);
        }
    }

    @Override
    public String toString() {
        return "CursosProfesores [listadoCursoProfesores=" + listadoCursoProfesores + "]";
    }

}
