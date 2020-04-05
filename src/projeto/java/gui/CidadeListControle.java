package projeto.java.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import projeto.java.Programa.Main;
import projeto.java.modelo.entidades.Cidade;
import projeto.java.modelo.servicos.CidadeServico;

public class CidadeListControle implements Initializable{

	private CidadeServico servico;
	
	@FXML
	private TableView<Cidade> tableViewCidade;
	
	@FXML
	private TableColumn<Cidade, Integer> tableColumnId;
	@FXML
	private TableColumn<Cidade, String> tableColumnNome;
	@FXML
	private TableColumn<Cidade, String> tableColumnDescricao;
	@FXML
	private Button btAdicionar;
	
	private ObservableList<Cidade> obsList;
	
	@FXML
	public void onBtAdicionarAcao() {
		System.out.println("Adicionado.");
	}
	
	public void setCidadeServico(CidadeServico servico) {
		this.servico = servico;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initialieNodes();
	}

	private void initialieNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		Stage stage =(Stage) Main.getMainScene().getWindow();
		
		tableViewCidade.prefHeightProperty().bind(stage.heightProperty());
		
	}
	
	public void atualizaTableView() {
		if(servico == null) {
			throw new IllegalStateException("Serviço nulo.");
		}
		
		List<Cidade> cidadeList = servico.buscaCompleta();
		obsList = FXCollections.observableArrayList(cidadeList);
		tableViewCidade.setItems(obsList);
	}
}