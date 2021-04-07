package ru.etu.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import ru.etu.launcher.Main;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApp {
    @FXML
    private GridPane rootVBox;

    @FXML
    private TabPane filesTabPane;

    @FXML
    private ComboBox<String> styleComboBox;

    @FXML
    private Menu menuFile, menuOptions;
    @FXML
    private MenuItem menuItemNewFile, menuItemOpen, menuItemSave, menuItemSaveAs, menuItemHelp, menuItemExportAs;
    @FXML
    private Button boldBtn, italicBtn, strikeThroughBtn, codeBtn, quoteBtn;

    private FileEditorControl getSelectedFileControl() {
        var selectedTab = filesTabPane.getSelectionModel().getSelectedItem();
        return (FileEditorControl) selectedTab.getContent();
    }

    @FXML
    private void initialize() {
        setLocale(Locale.ENGLISH.toString());
    }

    private void updateTabPane() {
    }

    /*** MENU ***/
    @FXML
    private void newFile() {
    }

    @FXML
    private void help() throws IOException {
        Main.openHelpWindow();
    }


    @FXML
    private void openFile() throws IOException {
    }

    @FXML
    private void save() throws IOException {
    }

    @FXML
    private void saveAs() throws IOException {

    }

    @FXML
    private void exportAs() throws IOException {

    }

    @FXML
    private void bold() {
    }

    @FXML
    private void strikeThrough() {
    }

    @FXML
    private void italic() {
    }

    @FXML
    private void highlight() {
    }

    @FXML
    private void code() {
    }

    @FXML
    private void quote() {
    }

    @FXML
    private void image() {

    }

    @FXML
    private void link() {
    }

    @FXML
    private void olList() {
    }

    @FXML
    private void ulList() {
    }

    @FXML
    private void title(ActionEvent event) {
    }

    @FXML
    private void export(ActionEvent event) {
    }

    private int getCaretPosition() {
        return getSelectedFileControl().getCaretPosition();
    }

    private int getCurrentLine() {
        return getSelectedFileControl().getCurrentLine();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        ResourceBundle bundle = ResourceBundle.getBundle("config.lang", locale);

        menuFile.setText(String.join(" ", "//", bundle.getString("menuFile")));
        menuItemHelp.setText(String.join(" ", "//", bundle.getString("menuHelp")));
        menuOptions.setText(String.join(" ", "//", bundle.getString("menuOptions")));
        menuItemNewFile.setText(String.join(" ", "//", bundle.getString("menuItemNewFile")));
        menuItemOpen.setText(String.join(" ", "//", bundle.getString("menuItemOpen")));
        menuItemSave.setText(String.join(" ", "//", bundle.getString("menuItemSave")));
        menuItemSaveAs.setText(String.join(" ", "//", bundle.getString("menuItemSaveAs")));
        menuItemExportAs.setText(String.join(" ", "//", bundle.getString("menuItemExportAs")));

        boldBtn.setText(bundle.getString("boldBtn"));
        italicBtn.setText(bundle.getString("italicBtn"));
        strikeThroughBtn.setText(bundle.getString("strikeThrough"));
        codeBtn.setText(bundle.getString("codeBtn"));
        quoteBtn.setText(bundle.getString("quoteBtn"));
    }
}
