package projeto.java.modelo.dao;

import java.util.List;

import projeto.java.modelo.entidades.Cidade;

public interface CidadeDao {
	void insert(Cidade obj);
	void update(Cidade obj);
	void deletePorId(Integer id);
	Cidade buscaPorId(Integer id);
	List<Cidade> buscaCompleta();
}