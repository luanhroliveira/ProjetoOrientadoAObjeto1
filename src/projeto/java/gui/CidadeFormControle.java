package projeto.java.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import projeto.java.db.DbException;
import projeto.java.gui.listeners.DataChangeListener;
import projeto.java.gui.util.Alertas;
import projeto.java.gui.util.Restricoes;
import projeto.java.gui.util.Utils;
import projeto.java.modelo.entidades.Cidade;
import projeto.java.modelo.exceptions.ValidadtionException;
import projeto.java.modelo.servicos.CidadeServico;

public class CidadeFormControle implements Initializable{

	private Cidade entidade;
	
	private CidadeServico cidadeServico;
	
	private List<DataChangeListener> listaDeDadosAlterados = new ArrayList<>();
	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNome;	
	@FXML
	private TextField txtDescricao;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btCancelar;
	@FXML
	private Label labelErroNome;
	@FXML
	private Label labelErroDescricao;
	
	public void setCidade(Cidade entidade) {
		this.entidade = entidade;
	}
	
	public void setCidadeServico(CidadeServico cidadeServico) {
		this.cidadeServico = cidadeServico;
	}
	
	public void subscreveDadosAlterados(DataChangeListener listener) {
		listaDeDadosAlterados.add(listener);
	}
	
	@FXML
	public void onBtSalvarAcao(ActionEvent event) {
		if(entidade == null) {
			throw new IllegalStateException("Entidade estava nulo");
		}
		if(cidadeServico == null) {
			throw new IllegalStateException("Cidade servico estava nulo");
		}
		try {
			entidade = getFormDado();
			cidadeServico.salvaOuEdita(entidade);
			notificaDadosAlterados();
			Utils.estagioAtual(event).close();
		}
		catch(ValidadtionException e) {
			setErroMensagem(e.getErros());
		}
		catch(DbException e){
			Alertas.showAlert("Erro ao salvar objeto", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void notificaDadosAlterados() {
		for(DataChangeListener listener: listaDeDadosAlterados) {
			listener.onDadoAlterado();
		}
	}

	private Cidade getFormDado() {
		Cidade obj = new Cidade();
		
		ValidadtionException exception = new ValidadtionException("Erro de validação.");
		
		obj.setIdCidade(Utils.tryParseToInt(txtId.getText()));
		
		if(txtNome.getText() == null || txtNome.getText().trim().equals("")) {
			exception.addErro("nome", "O campo 'nome' não pode ser vazio.");
		}
		obj.setNome(txtNome.getText());
		obj.setDescricao(txtDescricao.getText());
		
		if(exception.getErros().size() > 0) {
			throw exception;
		}
		return obj;
	}

	@FXML
	public void onBtCancelarAcao(ActionEvent event) {
		Utils.estagioAtual(event).close();	
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {		
		initializeNodes();
	}

	private void initializeNodes() {
		Restricoes.setTextFieldInteger(txtId);
		Restricoes.setTextFieldMaxLength(txtNome, 32);
		Restricoes.setTextFieldMaxLength(txtDescricao, 64);
	}
	
	public void atualizaFormDado() {
		if(entidade == null) {
			throw new IllegalStateException("Entidade é nulo");
		}
		txtId.setText(String.valueOf(entidade.getIdCidade()));
		txtNome.setText(entidade.getNome());
		txtDescricao.setText(entidade.getDescricao());
	}
	
	private void setErroMensagem(Map<String, String> erro) {
		Set<String> fields = erro.keySet();
		
		if(fields.contains("nome")) {
			labelErroNome.setText(erro.get("nome"));
		}
		
	}
	
}