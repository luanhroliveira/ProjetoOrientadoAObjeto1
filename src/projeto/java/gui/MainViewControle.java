package projeto.java.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		System.out.println("Teste item sobre");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
}