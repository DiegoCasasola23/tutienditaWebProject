/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import factory.FactoryDao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import tablas.Anuncio;
import tablas.Usuario;
import tablasDao.AnuncioDao;
import tablasDao.ArchivoDao;
import tablasDao.UsuarioDao;

/**
 *
 * @author Diego Casasola
 */
@Path("/anuncio")
public class AnuncioService {

    // api/contacto/usuario/todos
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Anuncio> getAnuncios() {
        AnuncioDao dao = FactoryDao.getFactoryInstance().getNewAnuncioDao();
        List<Anuncio> obj = null;

        try {
            obj = dao.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    @GET
    @Path("/usuario/{usuarioId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Anuncio> getAnuncios(@PathParam("usuarioId") int usuarioId) {
        if (usuarioId <= 0) {
            return null;
        }

        UsuarioDao daoUsuario = FactoryDao.getFactoryInstance().getNewUsuarioDao();
        Usuario obj = daoUsuario.get(usuarioId);

        if (obj == null) {
            return null;
        }

        AnuncioDao dao = FactoryDao.getFactoryInstance().getNewAnuncioDao();
        List<Anuncio> list = null;

        try {
            list = dao.getAnunciosByUsuarioId(usuarioId);
        } catch (Exception e) {
            list = new ArrayList<>();
            e.printStackTrace();
        }

        return list;
    }
    
        @GET
    @Path("/categoria/{categoriaTipo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Anuncio> getAnunciosCategoria(@PathParam("categoriaTipo") String categoriaTipo) {
        if (categoriaTipo == null) {
            return null;
        }

        AnuncioDao dao = FactoryDao.getFactoryInstance().getNewAnuncioDao();
        List<Anuncio> list = null;

        try {
            list = dao.getAnunciosByCategoria(categoriaTipo);
        } catch (Exception e) {
            list = new ArrayList<>();
            e.printStackTrace();
        }

        return list;
    }
    
    @GET
    @Path("/{anuncioId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Anuncio getAnuncio(@PathParam("anuncioId") int anuncioId ){
        AnuncioDao dao = FactoryDao.getFactoryInstance().getNewAnuncioDao();
        Anuncio obj= null;
        
        try {
            obj = dao.get(anuncioId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return obj;
    }
    
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta insertContacto(Anuncio obj) {
        AnuncioDao dao = FactoryDao.getFactoryInstance().getNewAnuncioDao();

        try {
            int id = dao.insert(obj);

            return new Respuesta(true, Integer.toString(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Respuesta(false, "Ocurrió un error al guardar el contacto");

    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta updateContacto(Anuncio obj) {
        AnuncioDao dao = FactoryDao.getFactoryInstance().getNewAnuncioDao();

        try {
            dao.update(obj);

            return new Respuesta(true, "Anuncio actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Respuesta(false, "Ocurrió un error al guardar el contacto");
    }
    
    @DELETE
    @Path("/{anuncio_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta eliminarContacto(@PathParam("anuncio_id") int contactoId){
        AnuncioDao dao = FactoryDao.getFactoryInstance().getNewAnuncioDao();
        
        try {
            dao.delete(contactoId);
            return new Respuesta(true, "El contacto fue eliminado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return new Respuesta(false, "No se pudo eliminar el contacto");
    }
    
     @POST
    @Path("/upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("image") InputStream uploadedInputStream,  
            @FormDataParam("image") FormDataContentDisposition fileDetail) {

        String fileName = java.util.UUID.randomUUID().toString();
        String uploadedFileName = fileDetail.getFileName();
        String extension = uploadedFileName.contains(".") ?
            uploadedFileName.substring(uploadedFileName.lastIndexOf(".")) : ".png";
                
        
        boolean sucess = false;
               
        int fileId = 0;
        String fileLocation = "f://Fotos/" + fileName + extension;
        //saving file  
        try {
            FileOutputStream out = new FileOutputStream(new File(fileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            out = new FileOutputStream(new File(fileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            sucess = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(sucess){
            
            tablas.Archivo fileToSave = new tablas.Archivo();
            fileToSave.setNombre_archivo(fileDetail.getFileName());
            fileToSave.setRuta(fileLocation);
            fileToSave.setTemporal(1);
            
            ArchivoDao dao = FactoryDao.getFactoryInstance().getNewArchivoDao();
            try {
                fileId = dao.insert(fileToSave);
            } catch (Exception ex) {
                
                sucess = false;
            }
        }
        Respuesta respuesta = new Respuesta(sucess, Integer.toString(fileId));
        return Response.status(200).entity(respuesta).build();
    }
    
    @GET
    @Path("/image/{archivoId}")
    @Produces("image/png")
    public Response  getImage(@PathParam("archivoId") int archivoId) {
        
        tablas.Archivo obj = null;

        try {
            ArchivoDao dao = FactoryDao.getFactoryInstance().getNewArchivoDao();
            obj = dao.get(archivoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        File file = new File(obj.getRuta());  
        Response.ResponseBuilder response = Response.ok((Object) file);  
        response.header("Content-Disposition","attachment; filename=\"" + obj.getNombre_archivo()+ "\"");  

        return response.build(); 
    }
}
