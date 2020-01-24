package dad.contactos.nuevo;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class NuevoView extends BorderPane {
	private TextField nombreText, telefonoText;
	private DatePicker nacimientoDatePicker;
	private Button crearButton, cancelarButton;

	public NuevoView() {
		super();
		nombreText = new TextField();

		nacimientoDatePicker = new DatePicker();

		telefonoText = new TextField();
		GridPane.setFillWidth(telefonoText, false);

		GridPane formPane = new GridPane();
		formPane.setHgap(5);
		formPane.setVgap(5);
		formPane.addRow(0, new Label("Nombre:"), nombreText);
		formPane.addRow(1, new Label("Fecha nacimiento:"), nacimientoDatePicker);
		formPane.addRow(2, new Label("Teléfono:"), telefonoText);

		ColumnConstraints[] cols = { 
				new ColumnConstraints(), 
				new ColumnConstraints(), 
		};
		cols[0].setHalignment(HPos.RIGHT);
		cols[1].setHgrow(Priority.ALWAYS);
		formPane.getColumnConstraints().setAll(cols);

		crearButton = new Button("Crear");
		crearButton.setDefaultButton(true);
		crearButton.setMaxWidth(Double.MAX_VALUE); // ancho máximo = infinito

		cancelarButton = new Button("Cancelar");
		cancelarButton.setCancelButton(true);
		cancelarButton.setMaxWidth(Double.MAX_VALUE); // ancho máximo = infinito

		VBox botonesPane = new VBox(5, crearButton, cancelarButton);
		botonesPane.setPadding(new Insets(0, 0, 0, 5));

		setCenter(formPane);
		setRight(botonesPane);
		setPadding(new Insets(5));
	}

	public TextField getNombreText() {
		return nombreText;
	}

	public TextField getTelefonoText() {
		return telefonoText;
	}

	public DatePicker getNacimientoDatePicker() {
		return nacimientoDatePicker;
	}

	public Button getCrearButton() {
		return crearButton;
	}

	public Button getCancelarButton() {
		return cancelarButton;
	}

}
