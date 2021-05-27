package ru.etu.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Help {
    @FXML
    private Label helpLabel;

    @FXML
    private void initialize() {
        helpLabel.setText("Markdown Editor");
    }
}
