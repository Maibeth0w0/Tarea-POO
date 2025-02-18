package com.poo;

public class Inscripcion {
    private Curso curso;
    private Integer anno;
    private Integer semestre;
    private Estudiante estudiante;
    
    public Inscripcion(Curso curso, Integer anno, Integer semestre, Estudiante estudiante) {
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
