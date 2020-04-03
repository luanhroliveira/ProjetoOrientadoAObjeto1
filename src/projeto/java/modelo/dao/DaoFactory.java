package projeto.java.modelo.dao;

import projeto.java.db.DB;
import projeto.java.modelo.dao.impl.CidadeDaoJDBC;

public class DaoFactory {
	public static CidadeDao createCidadeDao() {
		return new CidadeDaoJDBC(DB.getConnection());
	}
}