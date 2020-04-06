package projeto.java.modelo.servicos;

import java.util.List;

import projeto.java.modelo.dao.CidadeDao;
import projeto.java.modelo.dao.DaoFactory;
import projeto.java.modelo.entidades.Cidade;

public class CidadeServico implements CidadeDao{

	private CidadeDao dao = DaoFactory.createCidadeDao();
	
	public List<Cidade> buscaCompleta(){
		return dao.buscaCompleta();
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