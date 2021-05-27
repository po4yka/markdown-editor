package ru.etu.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.etu.utils.FileResourcesUtils;
import ru.etu.view.ImageLinkPicker;
import ru.etu.view.MainApp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main extends Application {
    private static Class<?> classValue;
    private static Stage stage;
    private final FileResourcesUtils fileResourcesUtils = new FileResourcesUtils();

    public static Stage getParentStage() {
        return stage;
    }

    private static void openWindow() throws IOException {
        var stage = new Stage();

        URL res = classValue.getResource("/fxml/helpWindow.fxml");
        assert (res != null) : "res in Main openWindow is null";
        Parent root = FXMLLoader.load(res);
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(getParentStage());

        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void openHelpWindow() throws IOException {
        openWindow();
    }

    public static boolean openNotSavedFileWindow() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("titre");
        alert.setContentText("Save ?");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        URL stylesheet = MainApp.class.getResource("/styles/darkTheme.css");
        assert (stylesheet != null) : "stylesheet in Main openNotSavedFileWindow is null";
        alert.getDialogPane().getStylesheets().add(stylesheet.toExternalForm());
        alert.showAndWait();
        return alert.getResult() == yesButton;
    }

    public static String openImageFileDialog() {
        var file = openExistingFile(
                new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png"));
        if (file == null) {
            return "";
        }

        return file.getAbsolutePath();
    }

    public static void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getMessage());

        StringBuilder stackTrace = new StringBuilder();
        for (var trace : e.getStackTrace()) {
            stackTrace.append(trace);
            stackTrace.append('\n');
        }

        alert.setContentText(stackTrace.toString());
        URL stylesheet = MainApp.class.getResource("/styles/darkTheme.css");
        assert (stylesheet != null) : "stylesheet in Main displayError is null";
        alert.getDialogPane().getStylesheets().add(stylesheet.toExternalForm());
        alert.show();
    }

    public static File openSaveFileDialog() {
        return openFileDialog("Save as..",
                new FileChooser.ExtensionFilter("Markdown Files", "*.md", "*.markdown"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Others", "*.*"));
    }

    public static File openExportFileDialog() {
        return openFileDialog("Export as..",
                new FileChooser.ExtensionFilter("Export as html", "*.pdf"),
                new FileChooser.ExtensionFilter("Export as pdf", "*.pdf"));
    }

    public static File openExportFileDialog(String exportFormat) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(String.format("Export as %s", exportFormat));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(exportFormat.toUpperCase(), String.format("*.%s", exportFormat)));

        return fileChooser.showSaveDialog(Main.getParentStage());
    }

    public static File openSavePdfDialog() {
        return openFileDialog("Export as pdf",
                new FileChooser.ExtensionFilter("Pdf File", "*.pdf"));
    }

    public static String[] openLinkImagePicker(String text, boolean mustBeImage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL res = classValue.getResource("/fxml/imageLinkPicker.fxml");
        assert (res != null) : "res in Main openLinkImagePicker is null";
        loader.setLocation(res);
        final Parent rootPane = loader.load();
        Scene scene = new Scene(rootPane);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initOwner(getParentStage());
        ImageLinkPicker controller = loader.getController();
        controller.initialize(text, mustBeImage);
        stage.setResizable(false);

        stage.showAndWait();

        return controller.getValues();
    }

    private static File openFileDialog(String title, FileChooser.ExtensionFilter... filters) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(filters);

        return fileChooser.showSaveDialog(Main.getParentStage());
    }

    private static File openExistingFile(FileChooser.ExtensionFilter... extensionFilters) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(extensionFilters);

        return fileChooser.showOpenDialog(Main.getParentStage());
    }

    public static File openExistingMarkdownFile() {
        return openExistingFile(
                new FileChooser.ExtensionFilter("Markdown, Text and Html Files", "*.md", "*.markdown", "*.txt", "*.html"),
                new FileChooser.ExtensionFilter("Markdown Files", "*.md", "*.markdown"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Html Files", "*.html"),
                new FileChooser.ExtensionFilter("Others", "*.*"));
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            classValue = getClass();
            InputStream str = fileResourcesUtils.getFileFromResourceAsStream("fxml/mainApp.fxml");
            URL res = getClass().getResource("text.txt");
            assert (res != null) : "res in Main start is null";
            Parent root = FXMLLoader.load(res);
            primaryStage.setTitle("MD Editor");

            var scene = new Scene(root);
            primaryStage.setScene(scene);
            stage = primaryStage;

            primaryStage.show();
        } catch (Exception e) {
            displayError(e);
        }
    }
}
