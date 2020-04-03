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
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE "
					+ "Cidade "
					+ "SET "
					+ "Nome = ?, "
					+ "Descricao = ? "
					+ "WHERE "
					+ "idCidade = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getDescricao());
			st.setInt(3, obj.getIdCidade());
			
			st.executeUpdate();
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deletePorId(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"DELETE FROM "
					+ "Cidade "
					+ "WHERE "
					+ "idCidade = ?"
					);	
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Cidade buscaPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT " 
					+ "c.idCidade as 'Cód. Cidade', "
					+ "c.nome as 'Nome', "
					+ "c.descricao as 'Descrição' "
					+ "FROM "
					+ "CIDADE c "
					+ "WHERE idCidade = ?"
					);
		
			st.setInt(1, id);
		
			rs = st.executeQuery();
			
			if(rs.next()) {
				Cidade obj = instanciarCidade(rs);
				return obj;
			}else {
				return null;
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Cidade instanciarCidade(ResultSet rs) throws SQLException {
		Cidade obj = new Cidade();
		obj.setIdCidade(rs.getInt("Cód. Cidade"));
		obj.setNome(rs.getString("Nome"));
		obj.setDescricao(rs.getString("Descrição"));
		
		return obj;
	}
}