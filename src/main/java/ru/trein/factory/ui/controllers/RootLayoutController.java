package ru.trein.factory.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.annotation.PostConstruct;

public class RootLayoutController {
    @FXML
    private Button connectBtn;

    @PostConstruct
    private void init() {
        connectBtn.setOnMouseClicked(event -> System.err.println("allo"));
    }
}
