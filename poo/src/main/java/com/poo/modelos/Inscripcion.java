package com.poo.modelos;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;
    
    private Integer anno;
    private Integer semestre;
    
    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;
    
    public Inscripcion(){
    }

    public Inscripcion(Curso curso, Integer anno, Integer semestre, Estudiante estudiante) {
        if (curso == null || estudiante == null) {
            throw new IllegalArgumentException("Curso y Estudiante no pueden ser nulos");
        }
        this.curso = curso;
        this.anno = anno;
        this.semestre = semestre;
        this.estudiante = estudiante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "Inscripcion{" +
            "id=" + id +
            ", curso=" + (curso != null ? curso.getNombre() : "Sin curso") +
            ", año=" + anno +
            ", semestre=" + semestre +
            ", estudiante=" + (estudiante != null ? estudiante.getNombres() : "Sin estudiante") +
            '}';
    }
}
