package com.poo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Nombre de la clase no revela las intenciones de la misma
// alterno => ListaCursosProfesores

public class CursosProfesores implements Servicios {
    private List<CursoProfesor> listadoCursoProfesores;

    public CursosProfesores() {
        this.listadoCursoProfesores = new ArrayList<>();
    }

    public void inscribirCursoProfesores(CursoProfesor cursoProfesor){
        listadoCursoProfesores.add(cursoProfesor);
        System.out.println("Curso " + cursoProfesor.getCurso().getNombre() + " asignado exitosamente al profesor " + cursoProfesor.getProfesor().getNombres() + ".");
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

                sheet = workbook.getSheet("CursosProfesores");

                if (sheet == null) {
                    sheet = workbook.createSheet("CursosProfesores");
                }
            } else {
                System.out.println("El archivo no existe. Creando...");
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("CursosProfesores");
            }

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Curso");
            headerRow.createCell(1).setCellValue("Programa");
            headerRow.createCell(2).setCellValue("Profesor");
            headerRow.createCell(3).setCellValue("Año");
            headerRow.createCell(4).setCellValue("Período");

            int rowNum = 1;
            for (CursoProfesor cp : listadoCursoProfesores) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(cp.getCurso().getNombre());
                row.createCell(1).setCellValue(cp.getCurso().getPrograma().getNombre());
                row.createCell(2).setCellValue(cp.getProfesor().getNombres() + " " + cp.getProfesor().getApellidos());
                row.createCell(3).setCellValue(cp.getAnno());
                row.createCell(4).setCellValue(cp.getSemestre());
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

            Sheet sheet = workbook.getSheet("CursosProfesores");
            if (sheet == null) {
                System.out.println("La hoja 'CursosProfesores' no existe en el archivo.");
                return;
            }
            
            listadoCursoProfesores.clear();

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {

                    String cursoNombre = row.getCell(0).getStringCellValue();

                    String programaNombre = row.getCell(1).getStringCellValue();

                    String profesorNombreCompleto = row.getCell(2).getStringCellValue();

                    int anno = (int) row.getCell(3).getNumericCellValue();
    
                    int semestre = (int) row.getCell(4).getNumericCellValue();

                    Programa programa = new Programa();
                    programa.setNombre(programaNombre);
                    
                    Curso curso = new Curso();
                    curso.setNombre(cursoNombre);
                    curso.setPrograma(programa);
                    
                    Profesor profesor = new Profesor();
                    String[] partes = profesorNombreCompleto.split(" ", 2);
                    if (partes.length == 2) {
                        profesor.setNombres(partes[0]);
                        profesor.setApellidos(partes[1]);
                    } else {
                        profesor.setNombres(profesorNombreCompleto);
                        profesor.setApellidos("");
                    }
                    
                    CursoProfesor cp = new CursoProfesor(profesor, anno, semestre, curso);
                    listadoCursoProfesores.add(cp);
                }
            }
            System.out.println("Datos cargados desde la hoja 'CursosProfesores'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    @Override
    public String toString() {
        return "CursosProfesores [listadoCursoProfesores=" + listadoCursoProfesores + "]";
    }
     

    @Override
    public String imprimirPosicion(int posicion) {
        if (posicion >= 0 && posicion < listadoCursoProfesores.size()) {
            return listadoCursoProfesores.get(posicion).toString();
        }
        return "Posición fuera de rango.";
    }

    @Override
    public Integer cantidadActual() {
        return listadoCursoProfesores.size();
    }

    @Override
    public List<String> imprimirListado() {
        List<String> nombres = new ArrayList<>();
        for (CursoProfesor cursoProfesor : listadoCursoProfesores) {
            nombres.add(cursoProfesor.getProfesor().getNombres());
        }
        return nombres;
    }



}
