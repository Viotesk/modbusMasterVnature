package ru.trein.factory.main;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.trein.factory.spring.SpringJavaConfig;

@SpringBootApplication
@ComponentScan("fi.stardex.nova")
@Import(SpringJavaConfig.class)
public abstract class AbstractJavaFxApplicationSupport extends Application {

    private static String[] savedArgs;

    protected ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(AbstractJavaFxApplicationSupport.class, savedArgs);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> appClass, String[] args) {
        savedArgs = args;
        LauncherImpl.launchApplication(appClass, args);
    }
}
