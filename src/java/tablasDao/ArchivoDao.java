/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablasDao;

import java.util.ArrayList;
import tablas.Archivo;

/**
 *
 * @author Diego Casasola
 */
public abstract class ArchivoDao {
    
    	public abstract int insert(Archivo obj) throws Exception;

	public abstract void update(Archivo obj) throws Exception;

	public abstract void delete(int id);

	public abstract ArrayList<Archivo> getList();

	public abstract Archivo get(int id);
    
}
