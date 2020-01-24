package dad.contactos;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ContactosView<T> extends BorderPane {

	private TableView<T> contactosTable;
	private TableColumn<T, String> nombreColumn;
	private TableColumn<T, LocalDate> nacimientoColumn;
	private TableColumn<T, Number> telefonoColumn;

	private Button nuevoButton, editarButton, eliminarButton;

	public ContactosView() {
		super();

		nombreColumn = new TableColumn<>("Nombre");
		nombreColumn.setEditable(true);
		nombreColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		nacimientoColumn = new TableColumn<>("Fecha nacimiento");

		telefonoColumn = new TableColumn<>("Teléfono");

		contactosTable = new TableView<>();
		contactosTable.setEditable(true);
		contactosTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		contactosTable.getColumns().add(nombreColumn);
		contactosTable.getColumns().add(nacimientoColumn);
		contactosTable.getColumns().add(telefonoColumn);

		nuevoButton = new Button("Nuevo");

		editarButton = new Button("Editar");

		eliminarButton = new Button("Eliminar");

		HBox bottomPane = new HBox(5, nuevoButton, editarButton, eliminarButton);
		bottomPane.setAlignment(Pos.BASELINE_CENTER);
		bottomPane.setPadding(new Insets(5, 0, 0, 0));

		setPadding(new Insets(5));
		setCenter(contactosTable);
		setBottom(bottomPane);
	}

	public TableView<T> getContactosTable() {
		return contactosTable;
	}

	public TableColumn<T, String> getNombreColumn() {
		return nombreColumn;
	}

	public TableColumn<T, LocalDate> getNacimientoColumn() {
		return nacimientoColumn;
	}

	public TableColumn<T, Number> getTelefonoColumn() {
		return telefonoColumn;
	}

	public Button getNuevoButton() {
		return nuevoButton;
	}

	public Button getEditarButton() {
		return editarButton;
	}

	public Button getEliminarButton() {
		return eliminarButton;
	}

}
