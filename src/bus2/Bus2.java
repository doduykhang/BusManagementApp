/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus2;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 *
 * @author dokha
 */
public class Bus2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quản lý xe bus");
        FXMLLoader loader = new FXMLLoader(Bus2.class.getResource("/JavaFX/FXML/Home.fxml"));
        HBox root;
        try {
            root = loader.load();
            primaryStage.setScene(new Scene(root, 300, 250));
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Bus2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
