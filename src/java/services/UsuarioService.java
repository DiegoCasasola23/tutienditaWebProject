/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import factory.FactoryDao;
import tablasDao.UsuarioDao;
import tablas.Usuario;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Diego Casasola
 */

@Path("/usuario")
public class UsuarioService {
    
    
    // /api/usuario/login
    @Path("/login" )
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta login(Usuario datosLogin){
        try {
            UsuarioDao dao = factory.FactoryDao.getFactoryInstance().getNewUsuarioDao();
            Usuario obj = dao.get(datosLogin.getUsername());
            
//            System.out.println(datosLogin.getUsername() + " usuarioentrada");
//            System.out.println(datosLogin.getContrasena() + " contraseñaentrada");
//            System.out.println(obj.getContrasena() + "verifi");
            
            if(obj == null){
                return new Respuesta(false, "Nombre de Usuario y/o Contraseña incorrectos");
            }
            if(obj.getContrasena().equals(datosLogin.getContrasena())){
                String json = " { " +
                        "\"nombreCompleto\" : \"" + obj.getNombre_completo()+ "\"," +
                        "\"username\" : \"" + obj.getUsername()+ "\"," +
                        "\"usuarioId\" : " + obj.getUsuario_id()+ "" +
                        "}";               
                
                return new Respuesta(true, json);
            }else{
                return new Respuesta(false, "Nombre de Usuario y/o Contraseña incorrectos"); 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new Respuesta(false, "Ocurrió un error al verificar el usuario"); 
    }
    
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta insertContacto(Usuario obj){
        UsuarioDao dao = FactoryDao.getFactoryInstance().getNewUsuarioDao();
        
        try {
            int id = dao.insert(obj);
            
            return new Respuesta(true, Integer.toString(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Respuesta(false, "El Usuario ya existe");
    
    }
    
    
}


            /*System.out.println(datosLogin.getUsername() + " usuarioentrada");
            System.out.println(datosLogin.getContraseña() + " contraseñaentrada");
            System.out.println(obj.getContraseña() + "verifi"); */