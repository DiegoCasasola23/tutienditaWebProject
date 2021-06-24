/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablasDao;
import tablas.Anuncio;
import java.util.ArrayList;

/**
 *
 * @author Diego Casasola
 */
public abstract class AnuncioDao {
        public abstract int insert(Anuncio obj) throws Exception;

    public abstract void update(Anuncio obj) throws Exception;
	
    public abstract ArrayList<Anuncio> getAnunciosByUsuarioId(int usuarioId);
    
    public abstract ArrayList<Anuncio> getAnunciosByCategoria(String categoriaTipo);

    public abstract void delete(int id);

    public abstract ArrayList<Anuncio> getList();

    public abstract Anuncio get(int id);
}
