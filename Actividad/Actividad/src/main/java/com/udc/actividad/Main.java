package com.udc.actividad;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();


        try {
            Image backgroundImage = new Image(new FileInputStream("src/main/resources/Login_Mockup.jpg"));
            ImageView backgroundView = new ImageView(backgroundImage);

            backgroundView.fitWidthProperty().bind(primaryStage.widthProperty());
            backgroundView.fitHeightProperty().bind(primaryStage.heightProperty());
            backgroundView.setPreserveRatio(false);

            root.getChildren().add(backgroundView);
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró la imagen. Asegúrate de que la ruta es correcta.");
        }


        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(100, 50, 50, 50));


        Label titleLabel = new Label("Bienvenido a\nAgencia Sky");
        titleLabel.setFont(Font.font("Arial", 40));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-alignment: center;");
        contentBox.getChildren().add(titleLabel);


        TextField userField = new TextField();
        userField.setPromptText("Usuario");

        HBox userBox = createInputField(userField);


        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");

        HBox passwordBox = createInputField(passwordField);

        contentBox.getChildren().addAll(userBox, passwordBox);


        Button loginButton = createStyledButton("Iniciar Sesion");
        Button registerButton = createStyledButton("Registrarse");

        contentBox.getChildren().addAll(loginButton, registerButton);

        root.getChildren().add(contentBox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Agencia Sky - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para crear los contenedores de los campos de texto
    private HBox createInputField(javafx.scene.control.TextInputControl textField) {
        HBox container = new HBox(10);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPadding(new Insets(10, 10, 10, 10));
        container.setPrefWidth(300);
        container.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 20;");

        textField.setStyle("-fx-background-color: transparent;");
        textField.setPrefWidth(250);

        ImageView iconView = null;
        try {
            if (textField.getPromptText().equals("Usuario")) {
                iconView = new ImageView(new Image(new FileInputStream("src/main/resources/icons/user.png")));
            } else if (textField.getPromptText().equals("Contraseña")) {
                iconView = new ImageView(new Image(new FileInputStream("src/main/resources/icons/lock.png")));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró un ícono. Asegúrate de que los archivos user.png y lock.png existan en la carpeta icons.");
        }

        if (iconView != null) {
            container.getChildren().addAll(iconView, textField);
        } else {
            container.getChildren().add(textField);
        }

        return container;
    }

    // Método para crear botones con estilo
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(40);
        button.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 20; -fx-border-color: #6495ED; -fx-border-width: 2; -fx-border-radius: 20;");
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}