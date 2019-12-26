package org.openjfx.CssAppfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class CssApp extends Application {

	@Override
	public void start(Stage stage) {
		var javaVersion = SystemInfo.javaVersion();
		var javafxVersion = SystemInfo.javafxVersion();

		var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
		var scene = new Scene(new Group(),640,480);
	
		// scene.getStylesheets().add("test.css");
		scene.getStylesheets().add("test2.css");
		Rectangle rect = new Rectangle(100,100);
		rect.setLayoutX(50);
		rect.setLayoutY(50);
		rect.getStyleClass().add("my-rect");
		((Group)scene.getRoot()).getChildren().add(label); 
		((Group)scene.getRoot()).getChildren().add(rect);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}