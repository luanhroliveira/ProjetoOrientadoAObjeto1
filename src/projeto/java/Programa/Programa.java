package projeto.java.Programa;

import java.util.List;

import projeto.java.modelo.dao.CidadeDao;
import projeto.java.modelo.dao.DaoFactory;
import projeto.java.modelo.entidades.Cidade;

public class Programa {

	public static void main(String[] args) {
		
		CidadeDao cidadeDao = DaoFactory.createCidadeDao();
		
		Cidade cidade = new Cidade(1, "Araruna", "Testando classe cidade");
		
		System.out.println(cidade);
		
		System.out.println("*****Testando insert*****");
		Cidade newCidade = new Cidade(null, "Araruna", "Teste 1");
		cidadeDao.insert(newCidade);
		System.out.println("Insert executado!");
		
		System.out.println("*****Testando busca por Id*****");
		cidade = cidadeDao.buscaPorId(1);
	    System.out.println(cidade);
	    
	    System.out.println("*****Testando delete por Id*****");
		cidadeDao.deletePorId(1);
		System.out.println("Delete executado!");
		
		System.out.println("*****Testando update*****");
		cidadeDao.buscaPorId(1);
		cidade.setDescricao("Teste UPDATE");
		cidadeDao.update(cidade);
		System.out.println("Update executado!");
		
		System.out.println("*****Testando busca completa*****");
		List<Cidade> cidadeList = cidadeDao.buscaCompleta();
		for(Cidade cid : cidadeList) {
			System.out.println(cid);
		}
	}
}