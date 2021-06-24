/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablasDao;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import tablas.Anuncio;

/**
 *
 * @author Diego Casasola
 */
public class AnuncioDaoMySQL extends AnuncioDao {

    @Override
    public int insert(Anuncio obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO anuncio  ( usuario_id_anuncio, titulo, descripcion, precio, telefono_contacto, img_file_id, categoria, estado) VALUES (");

        String strFile = obj.getImg_file_id() == 0 ? "null" : Integer.toString(obj.getImg_file_id());

        query.append(obj.getUsuario_id_anuncio() + ",");
        query.append("'" + obj.getTitulo() + "',");
        query.append("'" + obj.getDescripcion() + "',");
        query.append(obj.getPrecio() + ",");
        query.append(obj.getTelefono_contacto() + ",");
        query.append(strFile + ",");
        query.append("'" + obj.getCategoria() + "',");
        query.append("'" + obj.getEstado() + "'");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El anuncio no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Anuncio obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        String strFile = obj.getImg_file_id() == 0 ? "null" : Integer.toString(obj.getImg_file_id());

        StringBuilder query = new StringBuilder("UPDATE anuncio SET ");
        query.append("usuario_id_anuncio = " + obj.getUsuario_id_anuncio() + ",");
        query.append("titulo = '" + obj.getTitulo() + "',");
        query.append("descripcion = '" + obj.getDescripcion() + "',");
        query.append("precio = '" + obj.getPrecio() + "',");
        query.append("telefono_contacto = " + obj.getTelefono_contacto() + ",");
        query.append("img_file_id = " + strFile + ",");
        query.append("categoria = '" + obj.getCategoria() + "',");
        query.append("estado = '" + obj.getEstado() + "'");

        query.append(" WHERE anuncio_id = " + obj.getAnuncio_id());

        System.out.println(query.toString());
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

    @Override
    public ArrayList<Anuncio> getAnunciosByUsuarioId(int usuarioId) {
        ArrayList<Anuncio> registros = new ArrayList<Anuncio>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM anuncio WHERE usuario_id_anuncio = " + usuarioId;
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Anuncio obj = new Anuncio();
                int _anuncioId = objResultSet.getInt("anuncio_id");
                obj.setAnuncio_id(_anuncioId);

                int _usuarioId = objResultSet.getInt("usuario_id_anuncio");
                obj.setUsuario_id_anuncio(_usuarioId);

                String _titulo = objResultSet.getString("titulo");
                obj.setTitulo(_titulo);

                String _descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_descripcion);

                String _categoria = objResultSet.getString("categoria");
                obj.setCategoria(_categoria);

                String _estado = objResultSet.getString("estado");
                obj.setEstado(_estado);

                int _precio = objResultSet.getInt("precio");
                obj.setPrecio(_precio);

                int _telefonoContacto = objResultSet.getInt("telefono_contacto");
                obj.setTelefono_contacto(_telefonoContacto);

                int _imgFile = objResultSet.getInt("img_file_id");
                obj.setImg_file_id(_imgFile);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public ArrayList<Anuncio> getAnunciosByCategoria(String categoriaTipo) {
        ArrayList<Anuncio> registros = new ArrayList<Anuncio>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM anuncio WHERE estado = 'Publicar' AND categoria = '" + categoriaTipo + "'";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Anuncio obj = new Anuncio();
                int _anuncioId = objResultSet.getInt("anuncio_id");
                obj.setAnuncio_id(_anuncioId);

                int _usuarioId = objResultSet.getInt("usuario_id_anuncio");
                obj.setUsuario_id_anuncio(_usuarioId);

                String _titulo = objResultSet.getString("titulo");
                obj.setTitulo(_titulo);

                String _descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_descripcion);

                String _categoria = objResultSet.getString("categoria");
                obj.setCategoria(_categoria);

                int _precio = objResultSet.getInt("precio");
                obj.setPrecio(_precio);

                int _telefonoContacto = objResultSet.getInt("telefono_contacto");
                obj.setTelefono_contacto(_telefonoContacto);

                int _imgFile = objResultSet.getInt("img_file_id");
                obj.setImg_file_id(_imgFile);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuffer query = new StringBuffer("DELETE FROM anuncio ");
        query.append("WHERE anuncio_id = " + id);
        objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public ArrayList<Anuncio> getList() {
        ArrayList<Anuncio> registros = new ArrayList<Anuncio>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM anuncio WHERE estado = 'Publicar'";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Anuncio obj = new Anuncio();
                int _contactoId = objResultSet.getInt("anuncio_id");
                obj.setAnuncio_id(_contactoId);

                int _usuarioId = objResultSet.getInt("usuario_id_anuncio");
                obj.setUsuario_id_anuncio(_usuarioId);

                String _titulo = objResultSet.getString("titulo");
                obj.setTitulo(_titulo);

                String _descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_descripcion);

                String _categoria = objResultSet.getString("categoria");
                obj.setCategoria(_categoria);

                String _estado = objResultSet.getString("estado");
                obj.setEstado(_estado);

                int _precio = objResultSet.getInt("precio");
                obj.setPrecio(_precio);

                int _telefono = objResultSet.getInt("telefono_contacto");
                obj.setTelefono_contacto(_telefono);

                int _imagenFileId = objResultSet.getInt("img_file_id");
                obj.setImg_file_id(_imagenFileId);

                registros.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return registros;
    }

    @Override
    public Anuncio get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM anuncio WHERE anuncio_id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Anuncio obj = new Anuncio();
                int _anuncioId = objResultSet.getInt("anuncio_id");
                obj.setAnuncio_id(_anuncioId);

                int _usuarioId = objResultSet.getInt("usuario_id_anuncio");
                obj.setUsuario_id_anuncio(_usuarioId);

                String _titulo = objResultSet.getString("titulo");
                obj.setTitulo(_titulo);

                String _descripcion = objResultSet.getString("descripcion");
                obj.setDescripcion(_descripcion);
                
                int _precio = objResultSet.getInt("precio");
                obj.setPrecio(_precio);

                int _celular = objResultSet.getInt("telefono_contacto");
                obj.setTelefono_contacto(_celular);

                int _fileId = objResultSet.getInt("img_file_id");
                obj.setImg_file_id(_fileId);

                String _categoria = objResultSet.getString("categoria");
                obj.setCategoria(_categoria);

                String _estado = objResultSet.getString("estado");
                obj.setEstado(_estado);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

}
