package com.poo.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.poo.modelos.Persona;

public class InscripcionesPersonas {
    private List<Persona> listadoPersonas;
    private static final String FILE_PATH = "inscripcionPersonas.bin";
    private static final String KEY_PATH = "inscripcionPersonas.key";
    private static final String ALGORITHM = "AES";
    private Key secretKey;

    public InscripcionesPersonas() {
        this.listadoPersonas = new ArrayList<>();
        cargarClave();
    }
    
    private void generateAndSaveKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(128);
            secretKey = keyGenerator.generateKey();
    
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(KEY_PATH))) {
                oos.writeObject(secretKey);
            }
            System.out.println("Clave AES generada y guardada correctamente.");
        } catch (Exception e) {
            throw new RuntimeException("Error al generar y guardar la clave AES", e);
        }
    }
    
    private void cargarClave() {
        File keyFile = new File(KEY_PATH);
        if (keyFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(KEY_PATH))) {
                secretKey = (SecretKey) ois.readObject();
                System.out.println("Clave AES cargada desde archivo.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error cargando la clave de cifrado: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró clave guardada. Generando una nueva...");
            generateAndSaveKey();
        }
    }
    
  
    public void inscribirPersona(Persona persona) {
        if (existePersona(persona)) {
            System.out.println("Error: La persona con ID " + persona.getId() + " ya está inscrita.");
            return;
        }
        listadoPersonas.add(persona);
        guardarInformacion();
        System.out.println("✅ Persona inscrita y guardada: " + persona);
    }

    private boolean existePersona(Persona persona) {
        for (Persona p : listadoPersonas) {
            if (p.getId().equals(persona.getId())) {
                return true;
            }
        }
        return false;
    }


    public void actualizarPersona(Persona personaActualizada) {
        for (int i = 0; i < listadoPersonas.size(); i++) {
            if (listadoPersonas.get(i).getId().equals(personaActualizada.getId())) {
                listadoPersonas.set(i, personaActualizada);
                guardarInformacion();
                System.out.println("Persona actualizada: " + personaActualizada.getNombres());
                return;
            }
        }
        System.out.println("Persona no encontrada para actualizar.");
    }

    public void eliminarPersonaPorId(Double id) {
        listadoPersonas.removeIf(persona -> persona.getId().equals(id));
        guardarInformacion();
        System.out.println("Persona con ID " + id + " eliminada.");
    }

    public void guardarInformacion() {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             CipherOutputStream cos = new CipherOutputStream(fos, getCipher(Cipher.ENCRYPT_MODE));
             ObjectOutputStream oos = new ObjectOutputStream(cos)) {
            
            oos.writeObject(listadoPersonas);
            System.out.println("Datos guardados encriptados en 'inscripcionPersonas.bin'.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al guardar los datos en 'inscripcionPersonas.bin'");
        }
    }

    public void cargarDatos() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No hay datos previos guardados.");
            return;
        }
        
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             CipherInputStream cis = new CipherInputStream(fis, getCipher(Cipher.DECRYPT_MODE));
             ObjectInputStream ois = new ObjectInputStream(cis)) {
            
            listadoPersonas = (List<Persona>) ois.readObject();
            System.out.println("Datos cargados correctamente desde 'inscripcionPersonas.bin'.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar los datos desde 'inscripcionPersonas.bin'");
        }
    }

    private Cipher getCipher(int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(mode, secretKey);
        return cipher;
    }

    public List<Persona> getListadoPersonas() {
        return listadoPersonas;
    }

    public void setListadoPersonas(List<Persona> listadoPersonas) {
        this.listadoPersonas = listadoPersonas;
    }
}
