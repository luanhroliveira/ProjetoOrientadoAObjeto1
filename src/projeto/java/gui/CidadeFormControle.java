package projeto.java.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import projeto.java.db.DbException;
import projeto.java.gui.util.Alertas;
import projeto.java.gui.util.Restricoes;
import projeto.java.gui.util.Utils;
import projeto.java.modelo.entidades.Cidade;
import projeto.java.modelo.servicos.CidadeServico;

public class CidadeFormControle implements Initializable{

	private Cidade entidade;
	
	private CidadeServico cidadeServico;
	
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
			Utils.estagioAtual(event).close();
		}
		catch(DbException e){
			Alertas.showAlert("Erro ao salvar objeto", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private Cidade getFormDado() {
		Cidade obj = new Cidade();
		
		obj.setIdCidade(Utils.tryParseToInt(txtId.getText()));
		obj.setNome(txtNome.getText());
		obj.setDescricao(txtDescricao.getText());
		
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
}