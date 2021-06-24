package factory;

import tablasDao.UsuarioDaoMySQL;
import tablasDao.UsuarioDao;
import Conexion.Configuracion;
import tablasDao.AnuncioDao;
import tablasDao.AnuncioDaoMySQL;
import tablasDao.ArchivoDao;
import tablasDao.ArchivoDaoMySQL;

public class FactoryDaoMySQL extends FactoryDao{

	private FactoryDaoMySQL(){
		;
	}

	public static FactoryDao getFactoryInstance(){
		if(instancia==null)
			instancia = new FactoryDaoMySQL();
		return instancia;
	}

	@Override
	public AnuncioDao getNewAnuncioDao(){
		return new AnuncioDaoMySQL();
	}

	@Override
	public ArchivoDao getNewArchivoDao(){
		return new ArchivoDaoMySQL();
	}
 
	@Override
	public UsuarioDao getNewUsuarioDao(){
		return new UsuarioDaoMySQL();
	}

}

