package ru.etu.view.utils;

import javafx.scene.control.Tab;
import ru.etu.launcher.HelloFX;
import ru.etu.view.FileEditorControl;
import ru.etu.viewmodel.FileLoadedVM;
import ru.etu.viewmodel.ManagerVM;

import java.io.IOException;
import java.nio.file.InvalidPathException;

public abstract class TabFactory {
    public static Tab createTab(ManagerVM managerVM, FileLoadedVM fileLoadedVM) {
        var tab = new Tab();
        tab.textProperty().bind(fileLoadedVM.fileNameProperty());
        var fileEditorControl = new FileEditorControl(managerVM, fileLoadedVM);
        tab.setContent(fileEditorControl);

        tab.setOnCloseRequest(__ -> {
            if (!fileLoadedVM.isFileSaved()) {
                boolean mustSave = HelloFX.openNotSavedFileWindow();
                if (mustSave) {
                    try {
                        managerVM.save(fileLoadedVM);
                    } catch (InvalidPathException e) {
                        var file = HelloFX.openSaveFileDialog();
                        if (file != null) {
                            try {
                                managerVM.saveAs(fileLoadedVM, file);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        HelloFX.displayError(e);
                    }
                }
            }
            managerVM.closeFile(fileLoadedVM);
        });

        return tab;
    }
}
