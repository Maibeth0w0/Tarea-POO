package com.poo;

public class Programa {

    private Double id;
    private String nombre;
    private Double duracion;
    private String registro;
    private Facultad facultad;

    public Programa(){
    }

    public Programa( Double id, String nombre, Double duracion, String registro, Facultad facultad){
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }

    public Double getId(){
        return id;
    }

    public void setId(Double id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Double getDuracion(){
        return duracion;
    }

    public void setDuracion(Double duracion){
        this.duracion = duracion;
    }

    public String getRegistro(){
        return registro;
    }

    public void setRegistro(String registro){
        this.registro = registro;
    }

    public Facultad getFacultad(){
        return facultad;
    }

    public void setFacultad(Facultad facultad){
        this.facultad=facultad;
    }

    @Override
    public String toString(){
        return "Programa{Id="+id+
        ", nombre="+nombre+
        ", duración="+duracion+
        ", registro="+registro+
        ", facultad="+facultad.getNombre()+
        "}";
    }

}
