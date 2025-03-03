package com.poo.modelos;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Estudiante extends Persona {
   
   @Id
   private Integer codigo;
   private Programa programa;
   private Boolean activo;
   private Double promedio;

   public Estudiante(){}

   public Estudiante(Integer id, String nombres, String apellidos, String email, Integer codigo, Programa programa, Boolean activo, Double promedio){
      super(id, nombres, apellidos, email);
      this.codigo = codigo;
      this.programa = programa;
      this.activo = activo;
      this.promedio = promedio;
   }

   public Integer getCodigo(){
      return codigo;
   }

   public void setCodigo(Integer codigo){
      this.codigo = codigo;
   }

   public Programa getPrograma(){
      return programa;
   }

   public void setPrograma(Programa programa){
      this.programa = programa;
   }

   public Boolean getActivo(){
      return activo;
   }

   public void setActivo(Boolean activo){
      this.activo = activo;
   }

   public Double getPromedio(){
      return promedio;
   }

   public void setPromedio(Double promedio){
      this.promedio = promedio;
   }

   @Override
   public String toString(){
      return "Estudiante{"+
      super.toString()+
      ", codigo="+codigo+
      ", programa="+programa+
      ", activo="+activo+
      ", promedio="+promedio+
      "}";
   }

}
