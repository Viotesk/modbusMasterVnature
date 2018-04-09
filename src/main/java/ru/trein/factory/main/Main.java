package ru.trein.factory.main;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.trein.factory.ui.ViewHolder;

import javax.swing.*;

public class Main extends AbstractJavaFxApplicationSupport {

    private static final Logger logger = LoggerFactory.getLogger(AbstractJavaFxApplicationSupport.class);


    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewHolder rootLayoutControllerViewHolder = context.getBean("rootLayout", ViewHolder.class);
        Parent rootLayout = rootLayoutControllerViewHolder.getView();

        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setTitle("qModBusMasterVNature");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        String appId = "FactoryTest";

        try {
            JUnique.acquireLock(appId);
            launchApp(Main.class, args);
        } catch (AlreadyLockedException e) {
            JOptionPane.showMessageDialog(null, "Another instance of application is already running. Exiting.", "Warning", JOptionPane.WARNING_MESSAGE);
            System.exit(10);
        }
    }

}
