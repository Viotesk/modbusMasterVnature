package ru.trein.factory.spring;

import javafx.fxml.FXMLLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.trein.factory.ui.ViewHolder;
import ru.trein.factory.ui.controllers.RootLayoutController;

import java.io.IOException;

@Configuration
public class SpringJavaConfig {

    @Bean
    public ViewHolder rootLayout() throws IOException {
        return loadView("/fxml/RootLayout.fxml");
    }

    @Bean
    public RootLayoutController rootLayoutController() throws IOException {
        return (RootLayoutController) rootLayout().getController();
    }

    protected ViewHolder loadView(String url) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        ViewHolder view = new ViewHolder();
        view.setView(fxmlLoader.load());    //first we have to load obj hierarchy
        view.setController(fxmlLoader.getController());
        return view;
    }
}
