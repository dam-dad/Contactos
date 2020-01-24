package dad.contactos.nuevo;

import java.net.URL;
import java.util.ResourceBundle;

import dad.contactos.model.Contacto;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class NuevoController implements Initializable {
	
	// model
	private Contacto nuevo, devuelto;
	
	// view
	private NuevoView view;
	private Stage stage;
	
	public NuevoController() {
		nuevo = new Contacto();
		view = new NuevoView();
		initialize(null, null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nuevo.nombreProperty().bind(view.getNombreText().textProperty());
		nuevo.fechaNacimientoProperty().bind(view.getNacimientoDatePicker().valueProperty());
		Bindings.bindBidirectional(
				view.getTelefonoText().textProperty(), 
				nuevo.telefonoProperty(),
				new NumberStringConverter()
			);
		
		view.getCrearButton().setOnAction(e -> onCrearButtonAction(e));
		view.getCancelarButton().setOnAction(e -> onCancelarButtonAction(e));
	}

	private void onCrearButtonAction(ActionEvent e) {
		devuelto = new Contacto();
		Contacto.copiar(nuevo, devuelto);
		stage.close();
	}

	private void onCancelarButtonAction(ActionEvent e) {
		devuelto = null;
		stage.close();
	}
	
	public Contacto show(Stage parentStage) {
		stage = new Stage();
		if (parentStage != null) {
			stage.initOwner(parentStage);
			stage.getIcons().setAll(parentStage.getIcons());
		}
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setTitle("Nuevo contacto");
		stage.setResizable(false);
		stage.setScene(new Scene(view, 320, 200));
		stage.showAndWait();
		return devuelto;
	}
	
	public NuevoView getView() {
		return view;
	}

}

