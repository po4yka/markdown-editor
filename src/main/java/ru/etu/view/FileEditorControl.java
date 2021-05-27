package ru.etu.view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import org.fxmisc.richtext.CodeArea;
import ru.etu.view.utils.CodeAreaInitializer;
import ru.etu.viewmodel.FileLoadedVM;
import ru.etu.viewmodel.ManagerVM;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;

public class FileEditorControl extends BorderPane {
    private final FileLoadedVM fileLoadedVM;

    @FXML
    private Label selectedLineAndColLabel;

    @FXML
    private CodeArea codeArea;
    @FXML
    private WebView webView;

    public FileEditorControl(ManagerVM managerVM, FileLoadedVM fileLoadedVM) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/fileEditorControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        URL stylesheet = MainApp.class.getResource("/styles/darkTheme.css");
        if (stylesheet != null) codeArea.getStylesheets().add(stylesheet.toExternalForm());

        this.fileLoadedVM = fileLoadedVM;
        initialize();
    }

    public FileLoadedVM getViewModel() {
        return fileLoadedVM;
    }

    private void initialize() {
        webView.getEngine().loadContent(fileLoadedVM.getHtmlText(), "text/html");

        fileLoadedVM.htmlTextProperty().addListener((___) -> {
            webView.getEngine().loadContent(fileLoadedVM.getHtmlText(), "text/html");
        });

        codeArea.textProperty().addListener((___, oldText, newVText) -> {
            fileLoadedVM.markDownTextProperty().setValue(newVText);
        });

        fileLoadedVM.markDownTextProperty().addListener((___, oldText, newVText) -> {
            codeArea.replaceText(fileLoadedVM.getMarkDownText());
        });

        CodeAreaInitializer.initialize(codeArea);

        selectedLineAndColLabel.textProperty().bind(
                Bindings.createStringBinding(() -> MessageFormat.format(
                        "{0} {1}",
                        codeArea.getCaretColumn(),
                        codeArea.getText(0, codeArea.getCaretPosition()).split("\n").length),
                        codeArea.caretPositionProperty()));

        codeArea.replaceText(fileLoadedVM.getMarkDownText());
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
}
