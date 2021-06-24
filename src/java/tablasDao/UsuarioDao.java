/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablasDao;

import tablas.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Diego Casasola
 */
public abstract class UsuarioDao {

    public abstract int insert(Usuario obj) throws Exception;

    public abstract void update(Usuario obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Usuario> getList();

    public abstract Usuario get(int id);

    public abstract Usuario get(String username);
}
