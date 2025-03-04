package com.poo.modelos;

import jakarta.persistence.*;

@Entity
public class CursoProfesor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;
    
    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;
    
    private Integer anno;
    private Integer semestre;

    public CursoProfesor() {}

    public CursoProfesor(Profesor profesor, Curso curso, Integer anno, Integer semestre) {
        this.profesor = profesor;
        this.curso = curso;
        this.anno = anno;
        this.semestre = semestre;
    }

    public Integer getId() {
        return id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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

    @Override
    public String toString() {
        return "CursoProfesor{" +
            "id=" + id +
            ", profesor=" + (profesor != null ? profesor.getNombres() + " " + profesor.getApellidos() : "null") +
            ", curso=" + (curso != null ? curso.getNombre() : "null") +
            ", anno=" + anno +
            ", semestre=" + semestre +
            '}';
    }
}