package com.gdse.serenity;

import com.gdse.serenity.config.FactoryConfiguration;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

public class AppInitializer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/loadingFx.fxml"));
        stage.setScene(new Scene(load));
        stage.show();

        Task<Scene> loadingTask = new Task<Scene>() {
            @Override
            protected Scene call() throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/loginFx.fxml"));
//                FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/adminDashboardFx.fxml"));
                return new Scene(fxmlLoader.load());
            }
        };

        loadingTask.setOnSucceeded(event -> {
            Scene value = loadingTask.getValue();

            stage.setTitle("Serenity");
            Image image = new Image(getClass().getResourceAsStream("/assests/images/logo.png"));
            stage.getIcons().add(image);

//            stage.setResizable(false);

            stage.setScene(value);
            stage.centerOnScreen();

        });

        new Thread(loadingTask).start();
    }

    public static void main(String[] args) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.close();
        launch();
    }
}
