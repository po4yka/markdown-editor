package markdowneditor.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import markdowneditor.model.FileLoaded;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FileLoadedVM implements PropertyChangeListener {
    private final FileLoaded model;

    public FileLoaded getModel() {
        return model;
    }

    private final StringProperty fileName = new SimpleStringProperty();

    public String fileName() {
        return fileName.get();
    }

    private void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    private final StringProperty markDownText = new SimpleStringProperty();

    public StringProperty fileNameProperty() {
        return fileName;
    }

    private final StringProperty htmlText = new SimpleStringProperty();

    public String markDownText() {
        return markDownText.get();
    }

    private void setMarkDownText(String markDownText) {
        this.markDownText.set(markDownText);
    }

    public String getFileName() {
        return this.fileName.get();
    }

    public StringProperty markDownTextProperty() {
        return markDownText;
    }

    public String getMarkDownText() {
        return this.markDownText.get();
    }

    public String getHtmlText() {
        return htmlText.get();
    }

    private void setHtmlText(String htmlText) {
        this.htmlText.set(htmlText);
    }

    public StringProperty htmlTextProperty() {
        return htmlText;
    }

    public FileLoadedVM(FileLoaded fileLoaded) {
        model = fileLoaded;
        setFileName(model.getFileName());
        setMarkDownText(model.getMarkDownText());
        setHtmlText(model.getHtmlText());
        markDownTextProperty().addListener((__) -> {
            model.setMarkDownText(getMarkDownText());
        });
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(model.PROP_HTML_TEXT)) {
            if (evt.getNewValue() != null) {
                if (!evt.getOldValue().equals(evt.getNewValue())) {
                    setHtmlText((String) evt.getNewValue());
                }
            }
        } else if (evt.getPropertyName().equals(model.PROP_MARKDOWN_TEXT)) {
            if (evt.getNewValue() != null) {
                if (!evt.getOldValue().equals(evt.getNewValue())) {
                    setMarkDownText((String) evt.getNewValue());
                }
            }
        } else if (evt.getPropertyName().equals(model.PROP_FILE_NAME)) {
            if (evt.getNewValue() != null) {
                if (evt.getOldValue() != evt.getNewValue()) {
                    setFileName((String) evt.getNewValue());
                }
            }
        }
    }

    public int setBold(int start, int end) {
        return model.setBold(start, end);
    }

    public int setHighlight(int start, int end) {
        return model.setHighlight(start, end);
    }

    public int setCode(int start, int end) {
        return model.setCode(start, end);
    }

    public void setQuote(int numberLine) {
        model.setQuote(numberLine);
    }

    public int setItalic(int start, int end) {
        return model.setItalic(start, end);
    }

    public int setStrikeThrough(int start, int end) {
        return model.setStrikeThrough(start, end);
    }

    public boolean isFileSaved() {
        return model.isFileSaved();
    }

    public void setTitle(int numberLine, int titleNumber) {
        model.setTitle(numberLine, titleNumber);
    }

    public int addOlListLine(int numberLine) {
        return model.addOlListLine(numberLine);
    }

    public int addUlListLine(int numberLine) {
        return model.addUlListLine(numberLine);
    }

    public void setImage(int caretPosition, String text, String path) {
        model.setImage(caretPosition, text, path);
    }

    public void setLink(int startPosition, int endPosition, String text, String link) {
        model.setLink(startPosition, endPosition, text, link);
    }
}
