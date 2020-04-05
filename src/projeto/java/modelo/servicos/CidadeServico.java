package projeto.java.modelo.servicos;

import java.util.ArrayList;
import java.util.List;

import projeto.java.modelo.entidades.Cidade;

public class CidadeServico {

	public List<Cidade> buscaCompleta(){
		List<Cidade> cidadeList = new ArrayList<>();
	
		cidadeList.add(new Cidade(1, "Araruna", "Não sei a utilidade"));
		cidadeList.add(new Cidade(2, "Cianorte", "Cia"));
		cidadeList.add(new Cidade(3, "Campo Mourão", "Camourão"));
		
		return cidadeList;
	}
}