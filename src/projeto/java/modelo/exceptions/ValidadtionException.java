package projeto.java.modelo.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidadtionException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private Map<String, String> erros = new HashMap<>();
	
	public ValidadtionException(String str) {
		super(str);
	}
	
	public Map<String, String> getErros(){
		return erros;
	}

	public void addErro(String fildNome, String erroMensagem) {
		erros.put(fildNome, erroMensagem);
	}
}