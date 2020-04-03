package projeto.java.modelo.dao.impl;

import java.sql.Connection;

import projeto.java.modelo.dao.CidadeDao;
import projeto.java.modelo.entidades.Cidade;

public class CidadeDaoJDBC implements CidadeDao{

	private Connection conn;
	
	public CidadeDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Cidade obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Cidade obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deletePorId(Integer id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Cidade buscaPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}