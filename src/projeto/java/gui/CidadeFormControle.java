package projeto.java.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import projeto.java.gui.util.Restricoes;

public class CidadeFormControle implements Initializable{

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
	
	@FXML
	public void onBtSalvarAcao() {
		System.out.println("Salvo");
	}
	
	@FXML
	public void onBtCancelarAcao() {
		System.out.println("Cancelado");
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
	
	
}
