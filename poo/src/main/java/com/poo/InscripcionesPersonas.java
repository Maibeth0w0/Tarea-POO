package com.poo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class InscripcionesPersonas {

    private List<Persona> listadoPersonas;

    public InscripcionesPersonas() {
        this.listadoPersonas = new ArrayList<>();
    }

    public void inscribirPersona(Persona persona) {
        listadoPersonas.add(persona);
        System.out.println("Persona inscrita: " + persona.getNombres());
    }

    public void eliminarPersona(Persona persona) {
        if (listadoPersonas.remove(persona)) {
            System.out.println("Persona eliminada: " + persona.getNombres());
        } else {
            System.out.println("Persona no encontrada en el listado.");
        }
    }

    public void actualizarPersona(Persona personaActualizada) {
        for (int i = 0; i < listadoPersonas.size(); i++) {
            if (listadoPersonas.get(i).getId().equals(personaActualizada.getId())) {
                listadoPersonas.set(i, personaActualizada);
                System.out.println("Datos actualizados para: " + personaActualizada.getNombres());
                return;
            }
        }
        System.out.println("Persona no encontrada para actualizar.");
    }

    public void guardarInformacion() {
        String filePath = "cursos_profesores.xlsx";
        File file = new File(filePath);
        Workbook workbook;
        Sheet sheet;

        try {
            if (file.exists()) {
                System.out.println("El archivo ya existe. Abriendo...");
                FileInputStream fileInputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(fileInputStream);
                fileInputStream.close();

                sheet = workbook.getSheet("Personas");

                if (sheet == null) {
                    sheet = workbook.createSheet("Personas");
                }
            } else {
                System.out.println("El archivo no existe. Creando...");
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Personas");
            }

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nombre");
            headerRow.createCell(2).setCellValue("Apellido");
            headerRow.createCell(3).setCellValue("Email");

            int rowNum = 1;
            for (Persona p : listadoPersonas) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(p.getId());
                row.createCell(1).setCellValue(p.getNombres());
                row.createCell(2).setCellValue(p.getApellidos());
                row.createCell(3).setCellValue(p.getEmail());
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            workbook.close();
            System.out.println("Datos guardados en la hoja 'Personas'.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void cargarDatos() {
        String filePath = "cursos_profesores.xlsx";
        try (FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Personas");
            if (sheet == null) {
                System.out.println("La hoja 'Personas' no existe en el archivo.");
                return;
            }

            listadoPersonas.clear();

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    Cell cellId = row.getCell(0);
                    Cell cellNombre = row.getCell(1);
                    Cell cellApellido = row.getCell(2);
                    Cell cellEmail = row.getCell(3);

                    Double id = cellId.getNumericCellValue();
                    String nombre = cellNombre.getStringCellValue();
                    String apellido = cellApellido.getStringCellValue();
                    String email = cellEmail.getStringCellValue();

                    Persona persona = new Persona(id, nombre, apellido, email);
                    listadoPersonas.add(persona);
                }
            }

            System.out.println("Datos cargados desde la hoja 'Personas'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
