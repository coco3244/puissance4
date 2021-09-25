package View;

import java.net.URL;

import Controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
	private Parent root;
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource("/MainWindow.fxml");
        if (fxmlFileUrl == null) {
                System.out.println("Impossible de charger le fichier fxml");
                System.exit(-1);
        }
		loader.setLocation(fxmlFileUrl);
		root = loader.load();
		MainController MainControl = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Puissance 4");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
