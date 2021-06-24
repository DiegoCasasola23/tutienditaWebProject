/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

/**
 *
 * @author Diego Casasola
 */
public class Archivo {
    private int file_id;
    private String nombre_archivo;
    private String ruta;
    private int temporal;

    public Archivo() {
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getTemporal() {
        return temporal;
    }

    public void setTemporal(int temporal) {
        this.temporal = temporal;
    }
    
    
    
}
