package dad.javafx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

	private Label introductionLabel;
	private TextField numberField;
	private Button checkButton;
	private int numSecreto;
	private int intentos;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		introductionLabel = new Label("Introduce un n�mero del 1 al 100");

		numberField = new TextField();
		numberField.setMaxWidth(150);

		checkButton = new Button("Comprobar");
		checkButton.setDefaultButton(true);

		checkButton.setOnAction(e -> onCheckNumberButtonAction(e));

		VBox root = new VBox();
		root.setSpacing(15);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(introductionLabel, numberField, checkButton);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		generarNumeroSecreto();
	}

	private void onCheckNumberButtonAction(ActionEvent e) {
		String numberWord = numberField.getText();
		Pattern pattern = Pattern.compile("^([1-9][0-9]?)|100$");
		Matcher matcher = pattern.matcher(numberWord);
		AlertType alertType = null;
		String alertTitle = "";
		String alertMessage = "";
		numberField.setText("");
		intentos++;
		if (matcher.matches()) {
			int number = Integer.parseInt(numberWord);
			if (number == numSecreto) {
				alertType = AlertType.INFORMATION;
				alertTitle = "�Has acertado!";
				alertMessage = "Solo has necesitado " + intentos + " intentos\n\n"
						+ "Puedes volver a jugar";
				generarNumeroSecreto();
			} else {
				alertType = AlertType.WARNING;
				alertTitle = "�Has fallado!";
				alertMessage = "El n�mero a adivinar es " + ((number > numSecreto)?"menor":"mayor") + " que " + number + "\n\n"
						+ "Vuelve a intentarlo";
			}
		} else {
			alertType = AlertType.ERROR;
			alertTitle = "Error";
			alertMessage = "El n�mero introducido no es v�lido";
		}
		Alert alert = new Alert(alertType);
		alert.setTitle("AdivinApp");
		alert.setHeaderText(alertTitle);
		alert.setContentText(alertMessage);

		alert.showAndWait();
	}
	
	private void generarNumeroSecreto() {
		numSecreto = (int) Math.floor(Math.random() * (1 - 100 + 1) + 100);
		intentos = 0;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
