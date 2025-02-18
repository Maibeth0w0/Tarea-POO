package com.poo;

public class Curso {
    private Integer id;
    private String nombre;
    private Programa programa;
    private Boolean activo;

    public Curso(){
    }

    public Curso(Integer id,String nombre, Programa programa, Boolean activo){
        this.id = id;
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
    public String toString(){
        return "Curso{Id=" + id +
        ", nombre=" + nombre +
        ", programa=" + programa +
        ", activo=" + activo +
        "}";
    }

    
    
}
