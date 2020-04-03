package projeto.java.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import projeto.java.db.DB;
import projeto.java.db.DbException;
import projeto.java.modelo.dao.CidadeDao;
import projeto.java.modelo.entidades.Cidade;

public class CidadeDaoJDBC implements CidadeDao{

	private Connection conn;
	
	public CidadeDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Cidade obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO Cidade "
					+ "(nome, descricao) "
					+ "VALUES "
					+ "(?,?)",
					Statement.RETURN_GENERATED_KEYS
					);
		
			st.setString(1, obj.getNome());
			st.setString(2, obj.getDescricao());
			
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdCidade(id);
				}
				DB.closeResultSet(rs);
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
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