/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.NhanVien;
import Hibernate.DataGetter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author dokha
 */
public class DangNhapPaneController implements Initializable {

//    @FXML
//    private GridPane gPane;
    @FXML
    private TextField tenDangNhapField;
    @FXML
    private PasswordField matKhauField;
    @FXML
    private Button dangNhapButton;
    @FXML
    private Label errorLabel;

    
    public Label getErrorLabel() {
        return errorLabel;
        // TODO
    }    

    /**
     * Initializes the controller class.
     */
    public void setErrorLabel(Label errorLabel) {
        this.errorLabel = errorLabel;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void dangNhapEnter(KeyEvent event) {
        //danhNhap();
    }

    @FXML
    private void dangNhap(ActionEvent event) {
        danhNhap();
    }
    
    public NhanVien danhNhap(){
        return DataGetter.dangNhap(tenDangNhapField.getText(), matKhauField.getText());
    }
    
}
