package ru.etu.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import org.fxmisc.richtext.CodeArea;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class FileEditorControl extends BorderPane {

    @FXML
    private CodeArea codeArea;
    @FXML
    private WebView webView;

    @FXML
    private Label selectedLineAndColLabel;

    public FileEditorControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/fileEditorControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setLocale(Locale.getDefault().toString());

        codeArea.getStylesheets().add(Objects.requireNonNull(MainApp.class.getResource("/style/codeAreaStyle.css")).toExternalForm());

        initialize();
    }

    private void initialize() {

    }

    public void resetSelection(IndexRange selectedText, int difference) {
        codeArea.selectRange(selectedText.getStart() + difference, selectedText.getEnd() + difference);
    }

    public void resetCaretPosition(int caretPositon) {
        codeArea.displaceCaret(caretPositon);
    }

    public IndexRange getSelection() {
        return codeArea.getSelection();
    }

    public int getCurrentLine() {
        return codeArea.getText(0, codeArea.getCaretPosition()).split("\n").length;
    }

    public int getCaretPosition() {
        return codeArea.getCaretPosition();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        ResourceBundle bundle = ResourceBundle.getBundle("config.lang", locale);
    }
}
