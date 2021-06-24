/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablasDao;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import tablas.Archivo;

/**
 *
 * @author Diego Casasola
 */
public class ArchivoDaoMySQL extends ArchivoDao{

    @Override
    public int insert(Archivo obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        int id = 0;
        StringBuilder query = new StringBuilder("INSERT INTO archivo (nombre_archivo, ruta, temporal) VALUES (");
        query.append("'" + obj.getNombre_archivo()+ "',");
        query.append("'" + obj.getRuta()+ "',");
        query.append("" + obj.getTemporal()+ " ");
        query.append(")");
        id = objConexion.ejecutarInsert(query.toString());
        if (id == 0) {
            throw new Exception("El registro no pudo ser insertado");
        }
        objConexion.desconectar();
        return id;
    }

    @Override
    public void update(Archivo obj) throws Exception {
        Conexion objConexion = Conexion.getOrCreate();

        StringBuilder query = new StringBuilder("UPDATE archivo SET ");
        query.append("nombre_archivo = '" + obj.getNombre_archivo()+ "',");
        query.append("rute = '" + obj.getRuta()+ "',");
        query.append("temporal = '" + obj.getTemporal() + "' ");
        query.append("WHERE file_id = " + obj.getFile_id());
        int upd = objConexion.ejecutarSimple(query.toString());
        if (upd == 0) {
            throw new Exception("El registro no pudo ser actualizado");
        }

        objConexion.desconectar();
    }

    @Override
    public void delete(int id) {
        Conexion objConexion = Conexion.getOrCreate();
        StringBuffer query = new StringBuffer("DELETE FROM archivo ");
        query.append("WHERE file_id = " + id);
        objConexion.ejecutarSimple(query.toString());
        objConexion.desconectar();
    }

    @Override
    public ArrayList<Archivo> getList() {
         ArrayList<Archivo> archivos = new ArrayList<Archivo>();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM archivo";
            ResultSet objResultSet = objConexion.ejecutar(query);
            while (objResultSet.next()) {
                Archivo obj = new Archivo();
                int _fileId = objResultSet.getInt("file_id");
                obj.setFile_id(_fileId);

                String _fileName = objResultSet.getString("nombre_archivo");
                obj.setNombre_archivo(_fileName);

                String _path = objResultSet.getString("ruta");
                obj.setRuta(_path);

                int _temporal = objResultSet.getInt("temporal");
                obj.setTemporal(_temporal);

                archivos.add(obj);
            }
        } catch (Exception ex) {
            ;
        }
        return archivos;
    }

    @Override
    public Archivo get(int id) {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            String query = "SELECT * FROM archivo WHERE file_id = " + id;
            ResultSet objResultSet = objConexion.ejecutar(query);
            if (objResultSet.next()) {
                Archivo obj = new Archivo();
                int _fileId = objResultSet.getInt("file_id");
                obj.setFile_id(_fileId);

                String _fileName = objResultSet.getString("nombre_archivo");
                obj.setNombre_archivo(_fileName);

                String _path = objResultSet.getString("ruta");
                obj.setRuta(_path);

                int _temporal = objResultSet.getInt("temporal");
                obj.setTemporal(_temporal);

                return obj;
            }
        } catch (Exception ex) {
            ;
        }
        return null;
    }

   
    
}
