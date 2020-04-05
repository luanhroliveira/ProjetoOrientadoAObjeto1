package projeto.java.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class MainViewControle implements Initializable{

	@FXML
	private MenuItem menuItemCidade;
	@FXML
	private MenuItem menuItemEstado;
	@FXML
	private MenuItem menuItemSobre;
	
	@FXML
	public void onMenuItemCidadeAcao() {
		System.out.println("Teste item cidade");
	}
	
	@FXML
	public void onMenuItemEstadoAcao() {
		System.out.println("Teste item estado");
	}
	
	@FXML
	public void onMenuItemSobreAcao() {
		loadView("/projeto/java/gui/Sobre.fxml");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	private void loadView(String nomeAbsoluto) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(nomeAbsoluto));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
		} catch (IOException e) {
			Alertas.showAlert("IO Exception", "Erro ao carregar view", e.getMessage(), AlertType.ERROR);
		}
	}
}