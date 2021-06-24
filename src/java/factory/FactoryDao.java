package factory;

import tablasDao.UsuarioDao;
import Conexion.Configuracion;
import tablasDao.AnuncioDao;
import tablasDao.ArchivoDao;

public abstract class FactoryDao {

	protected static FactoryDao instancia;
	public static FactoryDao getFactoryInstance(){
		if(instancia == null){
                    Configuracion config = Configuracion.getConfiguracion();
                    if(config.getDbEngine().equals("MySQL"));
                            instancia = FactoryDaoMySQL.getFactoryInstance();
		}
		return instancia;
	}

	public abstract AnuncioDao getNewAnuncioDao();

	public abstract ArchivoDao getNewArchivoDao();

	public abstract UsuarioDao getNewUsuarioDao();

}

