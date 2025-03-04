package com.poo.modelos;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_programa")
    private Programa programa;

    private Boolean activo;

    public Curso(){
    }

    public Curso(String nombre, Programa programa, Boolean activo){
        this.nombre = nombre;
        this.programa = programa;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Curso{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", programa=" + (programa != null ? programa.getNombre() : "Sin programa") +
            ", activo=" + activo +
            '}';
    }

    
    
}
