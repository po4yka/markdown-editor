module MarkdownEditor {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.web;
    requires javafx.media;
    requires io;
    requires kernel;
    requires layout;
    requires html2pdf;
    requires java.desktop;
    requires org.fxmisc.richtext;
    requires reactfx;
    requires junit;

    opens ru.etu.launcher to javafx.fxml;
    exports ru.etu.launcher;
}