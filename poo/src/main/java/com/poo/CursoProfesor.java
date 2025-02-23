package com.poo;

public class CursoProfesor {
    private Profesor profesor;
    private Integer anno;
    private Integer semestre;
    private Curso curso;
    
    public CursoProfesor(Profesor profesor, Integer anno, Integer semestre, Curso curso) {
        this.profesor = profesor;
        this.anno = anno;
        this.semestre = semestre;
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "CursoProfesor {profesor=" + profesor.getNombres() + " "
        + profesor.getApellidos() +
        ", año=" + anno +
        ", semestre=" + semestre +
        ", curso=" + curso.getNombre() +
        "}";
    }

    
}
