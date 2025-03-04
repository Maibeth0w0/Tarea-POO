package com.poo.modelos;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("PROFESOR")
public class Profesor extends Persona {
    
    @Column(name = "tipo_contrato")
    private String tipoContrato;
    
    @ManyToOne
    @JoinColumn(name = "id_facultad")
    private Facultad facultad;
    
    public Profesor() {
    }
    
    public Profesor(String nombres, String apellidos, String email, String tipoContrato, Facultad facultad) {
        super(nombres, apellidos, email);
        this.tipoContrato = tipoContrato;
        this.facultad = facultad;
    }
    
    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    
    @Override
    public String toString() {
        return "Profesor{" +
            "tipoContrato='" + tipoContrato + '\'' +
            ", " + super.toString() +
            '}';
    }
}
