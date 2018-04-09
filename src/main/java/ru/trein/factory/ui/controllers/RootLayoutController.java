package ru.trein.factory.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import net.wimpi.modbus.ModbusException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.trein.factory.ui.connect.ModbusConnect;
import ru.trein.factory.work.demo.Test;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class RootLayoutController {

    @Autowired
    ModbusConnect modbusConnect;
    Test test = new Test();

    @FXML
    private Button connectBtn;

    @FXML
    private TextField ipTF;
    @FXML
    private TextField portTF;
    @FXML
    private Label statusLabel;

    @FXML
    private Button startBtn;

    @PostConstruct
    private void init() {
        ipTF.setTextFormatter(new TextFormatter<>(getFilter()));

        Pattern p = Pattern.compile("(\\d+\\.?\\d*)?");
        portTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!p.matcher(newValue).matches()) {
                portTF.setText(oldValue);
                return;
            }
            if (!newValue.isEmpty() && Integer.parseInt(newValue) > 65535) {
                portTF.setText("65535");
            }
        });

        connectBtn.setOnMouseClicked(event -> {
                    if (modbusConnect.connect(ipTF.getText(), portTF.getText()))
                        statusLabel.setText("Connected");
                    else
                        statusLabel.setText("Disconnected");
                }
        );

        startBtn.setOnMouseClicked(event -> {
            try {
                test.start();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ModbusException e) {
                e.printStackTrace();
            }

        });
    }


    private UnaryOperator<TextFormatter.Change> getFilter() {
        return c -> {
            String text = c.getControlNewText();
            if (text.matches(makePartialRegex())) {
                return c;
            } else {
                return null;
            }
        };
    }

    private String makePartialRegex() {

        String partialBlock = "(([01]?[0-9]{0,2})|(2[0-4][0-9])|(25[0-5]))";
        String subsequentPartialBlock = "(\\." + partialBlock + ")";
        String ipAddress = partialBlock + "?" + subsequentPartialBlock + "{0,3}";

        return "^" + ipAddress;
    }
}
