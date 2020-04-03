package projeto.java.Programa;

import projeto.java.modelo.dao.CidadeDao;
import projeto.java.modelo.dao.DaoFactory;
import projeto.java.modelo.entidades.Cidade;

public class Programa {

	public static void main(String[] args) {
		
		CidadeDao cidadeDao = DaoFactory.createCidadeDao();
		
		Cidade cidade = new Cidade(1, "Araruna", "Testando classe cidade");
		
		System.out.println(cidade);
		
		System.out.println("*****Testando insert*****");
		cidade = new Cidade(null, "Araruna", "Teste 1");
		cidadeDao.insert(cidade);
		System.out.println("Insert executado!");
		
	}
}