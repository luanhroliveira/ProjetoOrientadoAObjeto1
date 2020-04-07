package projeto.java.modelo.servicos;

import java.util.List;

import projeto.java.modelo.dao.CidadeDao;
import projeto.java.modelo.dao.DaoFactory;
import projeto.java.modelo.entidades.Cidade;

public class CidadeServico {

	private CidadeDao dao = DaoFactory.createCidadeDao();
	
	public List<Cidade> buscaCompleta(){
		return dao.buscaCompleta();
	}
	
	public void salvaOuEdita(Cidade obj) {
		if(obj.getIdCidade() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
}