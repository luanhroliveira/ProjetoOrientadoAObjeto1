package projeto.java.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import projeto.java.Programa.Main;
import projeto.java.gui.util.Alertas;
import projeto.java.modelo.servicos.CidadeServico;

public class MainViewControle implements Initializable{

	@FXML
	private MenuItem menuItemCidade;
	@FXML
	private MenuItem menuItemEstado;
	@FXML
	private MenuItem menuItemSobre;
	
	@FXML
	public void onMenuItemCidadeAcao() {
		loadView("/projeto/java/gui/CidadeList.fxml", (CidadeListControle controle) -> {
			controle.setCidadeServico(new CidadeServico());
			controle.atualizaTableView();
		});
	}
	
	@FXML
	public void onMenuItemEstadoAcao() {
		System.out.println("Teste item estado");
	}
	
	@FXML
	public void onMenuItemSobreAcao() {
		loadView("/projeto/java/gui/Sobre.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	private <T> void loadView(String nomeAbsoluto, Consumer<T> AcaoDeInicializacao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeAbsoluto));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controle = loader.getController();
			AcaoDeInicializacao.accept(controle);
			
		} catch (IOException e) {
			Alertas.showAlert("IO Exception", "Erro ao carregar view", e.getMessage(), AlertType.ERROR);
		}
	}
}