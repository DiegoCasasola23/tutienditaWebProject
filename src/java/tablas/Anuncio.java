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
public class Anuncio {

    private int anuncio_id;
    private int usuario_id_anuncio;
    private String titulo;
    private String descripcion;
    private int precio;
    private int telefono_contacto;
    private int img_file_id;
    private String categoria;
    private String estado;

    public Anuncio() {
    }

    public int getAnuncio_id() {
        return anuncio_id;
    }

    public void setAnuncio_id(int anuncio_id) {
        this.anuncio_id = anuncio_id;
    }

    public int getUsuario_id_anuncio() {
        return usuario_id_anuncio;
    }

    public void setUsuario_id_anuncio(int usuario_id_anuncio) {
        this.usuario_id_anuncio = usuario_id_anuncio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(int telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    public int getImg_file_id() {
        return img_file_id;
    }

    public void setImg_file_id(int img_file_id) {
        this.img_file_id = img_file_id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

}
