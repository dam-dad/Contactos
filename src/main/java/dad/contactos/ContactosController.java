package dad.contactos;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.contactos.editar.EditarController;
import dad.contactos.model.Agenda;
import dad.contactos.model.Contacto;
import dad.contactos.nuevo.NuevoController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ContactosController implements Initializable {
	
	// modelo
	private Agenda agenda;
	private ObjectProperty<Contacto> seleccionado;
	
	// vista
	private ContactosView<Contacto> view;
	
	public ContactosController() {
		view = new ContactosView<>();
		agenda = new Agenda();
		seleccionado = new SimpleObjectProperty<>(this, "seleccionado");
		initialize(null, null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// bindings
		
		seleccionado.bind(view.getContactosTable().getSelectionModel().selectedItemProperty());
		
		view.getContactosTable().itemsProperty().bind(agenda.contactosProperty());
		
		view.getEditarButton().disableProperty().bind(seleccionado.isNull());
		view.getEliminarButton().disableProperty().bind(seleccionado.isNull());
		
		// factories 

		view.getNombreColumn().setCellValueFactory(value -> value.getValue().nombreProperty());
		view.getNacimientoColumn().setCellValueFactory(value -> value.getValue().fechaNacimientoProperty());
		view.getTelefonoColumn().setCellValueFactory(value -> value.getValue().telefonoProperty());
		
		// listeners
		view.getNuevoButton().setOnAction(e -> onNuevoButtonAction(e));
		view.getEditarButton().setOnAction(e -> onEditarButtonAction(e));
		view.getEliminarButton().setOnAction(e -> onEliminarButtonAction(e));
		
	}

	private void onEliminarButtonAction(ActionEvent e) {
		String nombre = seleccionado.get().getNombre();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar contacto");
		alert.setHeaderText("Se dispone a eliminar al contacto '" + nombre + "'.");
		alert.setContentText("¿Desea eliminar el contacto?");
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> resultado = alert.showAndWait();
		if (ButtonType.YES.equals(resultado.get())) {
			agenda.getContactos().remove(seleccionado.get());
		}
	}

	private void onNuevoButtonAction(ActionEvent e) {
		NuevoController controller = new NuevoController();
		Contacto nuevo = controller.show(ContactosApp.getPrimaryStage());
		if (nuevo != null) {
			agenda.getContactos().add(nuevo);
		}
	}
	
	private void onEditarButtonAction(ActionEvent e) {
		EditarController controller = new EditarController();
		controller.show(ContactosApp.getPrimaryStage(), seleccionado.get());
	}
	
	public ContactosView<Contacto> getView() {
		return view;
	}

}
