package dad.contactos.editar;

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

public class EditarController implements Initializable {
	
	// model
	private Contacto editado, original;
	
	// view
	private EditarView view;
	private Stage stage;
	
	public EditarController() {
		editado = new Contacto();
		view = new EditarView();
		initialize(null, null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		editado.nombreProperty().bindBidirectional(view.getNombreText().textProperty());
		editado.fechaNacimientoProperty().bindBidirectional(view.getNacimientoDatePicker().valueProperty());
		Bindings.bindBidirectional(
				view.getTelefonoText().textProperty(), 
				editado.telefonoProperty(),
				new NumberStringConverter()
			);
		
		view.getGuardarButton().setOnAction(e -> onGuardarButtonAction(e));
		view.getCancelarButton().setOnAction(e -> onCancelarButtonAction(e));
	}

	private void onGuardarButtonAction(ActionEvent e) {
		Contacto.copiar(editado, original);
		stage.close();
	}

	private void onCancelarButtonAction(ActionEvent e) {
		stage.close();
	}
	
	public void show(Stage parentStage, Contacto original) {
		this.original = original;
		Contacto.copiar(original, editado);		
		stage = new Stage();
		if (parentStage != null) {
			stage.initOwner(parentStage);
			stage.getIcons().setAll(parentStage.getIcons());
		}
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setTitle("Editar contacto");
		stage.setResizable(false);
		stage.setScene(new Scene(view, 320, 200));
		stage.showAndWait();
	}
	
	public EditarView getView() {
		return view;
	}

}

