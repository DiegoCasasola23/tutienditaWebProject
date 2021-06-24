/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablasDao;

import Conexion.Conexion;
import tablas.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Diego Casasola
 */
public class UsuarioDaoMySQL extends UsuarioDao {

    @Override
    public int insert(Usuario obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO usuario  ( usuario_id, email, nombre_completo, contraseña, username) VALUES (");

        query.append(obj.getUsuario_id()+ ",");
        query.append("'" + obj.getEmail()+ "',");
        query.append("'" + obj.getNombre_completo()+ "',");
        query.append("'" + obj.getContrasena()+ "',");
        query.append("'" + obj.getUsername()+ "'");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Usuario obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM usuario WHERE usuario_id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Usuario obj = new Usuario();
                int _usuarioId = objResultSet.getInt("usuario_id");
                obj.setUsuario_id(_usuarioId);

                String _email = objResultSet.getString("email");
                obj.setEmail(_email);

                String _nombreCompleto = objResultSet.getString("nombre_Completo");
                obj.setNombre_completo(_nombreCompleto);

                String _password = objResultSet.getString("contraseña");
                System.out.println(_password);
                obj.setContrasena(_password);

                String _username = objResultSet.getString("username");
                obj.setUsername(_username);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    @Override
    public Usuario get(String username) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM usuario WHERE username = '" + username + "'";
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Usuario obj = new Usuario();
                int _usuarioId = objResultSet.getInt("usuario_id");
                obj.setUsuario_id(_usuarioId);

                String _email = objResultSet.getString("email");
                obj.setEmail(_email);

                String _nombreCompleto = objResultSet.getString("nombre_Completo");
                obj.setNombre_completo(_nombreCompleto);

                String _password = objResultSet.getString("contraseña");
                obj.setContrasena(_password);

                String _username = objResultSet.getString("username");
                obj.setUsername(_username);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

}
