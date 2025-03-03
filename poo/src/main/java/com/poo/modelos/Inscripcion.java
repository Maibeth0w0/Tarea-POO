package com.poo.modelos;
import java.io.Serializable;

public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;

    private Curso curso;
    private Integer anno;
    private Integer semestre;
    private Estudiante estudiante;
    
    public Inscripcion(Curso curso, Integer anno, Integer semestre, Estudiante estudiante) {
       
            if (curso == null) {
                throw new IllegalArgumentException("El curso no puede ser null");
            }
            if (estudiante == null) {
                throw new IllegalArgumentException("El estudiante no puede ser null");
            }
        this.curso = curso;
        this.anno = anno;
        this.semestre = semestre;
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "Inscripcion {curso=" + curso.toString() +
        ", año=" + anno +
        ", semestre=" + semestre +
        ", estudiante=" + estudiante.toString() +
        "}";
    }    
}
