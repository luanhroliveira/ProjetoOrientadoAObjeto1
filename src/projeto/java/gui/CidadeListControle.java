package projeto.java.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projeto.java.Programa.Main;
import projeto.java.db.DbIntegrityException;
import projeto.java.gui.listeners.DataChangeListener;
import projeto.java.gui.util.Alertas;
import projeto.java.gui.util.Utils;
import projeto.java.modelo.entidades.Cidade;
import projeto.java.modelo.servicos.CidadeServico;

public class CidadeListControle implements Initializable, DataChangeListener{

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
	private TableColumn<Cidade, Cidade> tableColumnEDITAR;
	@FXML
	private TableColumn<Cidade, Cidade> tableColumnREMOVER;
	@FXML
	private Button btAdicionar;
	@FXML
	private Button btPesquisar;
	
	private ObservableList<Cidade> obsList;
	
	@FXML
	public void onBtAdicionarAcao(ActionEvent event) {
		Stage parentStage = projeto.java.gui.util.Utils.estagioAtual(event);
		Cidade obj = new Cidade();
		createDialogoForm(obj, "/projeto/java/gui/CidadeForm.fxml", parentStage);	
	}
	
	@FXML
	public void onBtPesquisarAcao() {
		if(servico == null) {
			throw new IllegalStateException("Servi�o nulo.");
		}
		List<Cidade> cidadeList = servico.buscaCompleta();
		obsList = FXCollections.observableArrayList(cidadeList);
		tableViewCidade.setItems(obsList);
	}
	
	public void setCidadeServico(CidadeServico servico) {
		this.servico = servico;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initialieNodes();
	}

	private void initialieNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("idCidade"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		Stage stage =(Stage) Main.getMainScene().getWindow();
		
		tableViewCidade.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void atualizaTableView() {
		if(servico == null) {
			throw new IllegalStateException("Servi�o nulo.");
		}
		List<Cidade> cidadeList = servico.buscaCompleta();
		obsList = FXCollections.observableArrayList(cidadeList);
		tableViewCidade.setItems(obsList);
		initEditarBotao();
		initRemoverBotao();
	}
	
	private void createDialogoForm(Cidade obj, String nomeAbsoluto, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeAbsoluto));
			Pane pane = loader.load();
			
			CidadeFormControle controle = loader.getController();
			controle.setCidade(obj);
			controle.setCidadeServico(new CidadeServico());
			controle.subscreveDadosAlterados(this);
			controle.atualizaFormDado();
			
			Stage dialogoStage = new Stage();
			dialogoStage.setTitle("Entre com os dados da cidade");
			dialogoStage.setScene(new Scene(pane));
			dialogoStage.setResizable(false);
			dialogoStage.initOwner(parentStage);
			dialogoStage.initModality(Modality.WINDOW_MODAL);
			
			dialogoStage.showAndWait();
		}
		catch(IOException e) {
			Alertas.showAlert("IO Exception", "Erro ao carregar view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDadoAlterado() {
		atualizaTableView();
	}
	
	private void initEditarBotao() {
		tableColumnEDITAR.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDITAR.setCellFactory(param -> new TableCell<Cidade, Cidade>() {
			private final Button button = new Button("editar");

			@Override
			protected void updateItem(Cidade obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogoForm(obj, "/projeto/java/gui/CidadeForm.fxml", Utils.estagioAtual(event)));
			}
		});
	}
	
	private void initRemoverBotao() {
		tableColumnREMOVER.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVER.setCellFactory(param -> new TableCell<Cidade, Cidade>() {
			private final Button button = new Button("remove");

			@Override
			protected void updateItem(Cidade obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntidade(obj));
			}
		});
	}

	private void removeEntidade(Cidade obj) {
		Optional<ButtonType> result = Alertas.showConfirmation("Confirma��o", "Deseja remover esta cidade?");
		
		if(result.get() == ButtonType.OK) {
			
			if(servico == null) {
				throw new IllegalStateException("Servi�o n�o pode ser nulo");
			}
			try {
				servico.remove(obj);	
				atualizaTableView();
			}
			catch(DbIntegrityException e) {
				Alertas.showAlert("Erro em remover cidade", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}
}