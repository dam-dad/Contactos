package dad.contactos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContactosApp extends Application {
	
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		ContactosApp.primaryStage = primaryStage;
		
		ContactosController controller = new ContactosController();
		
		primaryStage.setTitle("Contactos");
		primaryStage.setScene(new Scene(controller.getView(), 640, 480));
		primaryStage.show();
		
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
