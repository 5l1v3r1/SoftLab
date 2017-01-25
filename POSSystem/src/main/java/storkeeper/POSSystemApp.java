package storkeeper;

import database.DBModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author HikmatD
 */
public class POSSystemApp extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        DBModel model = new DBModel();
        model.createDataBase();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome to POS System -Login");
        primaryStage.getIcons().add(new Image("/image/icon.png"));
        primaryStage.setMaximized(false);
        primaryStage.setMinHeight(500.0);
        primaryStage.setMinWidth(850.0);

        primaryStage.show();
    }

}
