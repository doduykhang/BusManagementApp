/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.PhanCong;
import Entity.Tuyen;
import Entity.Xe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author dokha
 */
public class PhanCongPaneController implements Initializable {

    @FXML
    private TableView<Tuyen> tuyenTable;
    @FXML
    private TableColumn<Tuyen, String> tuyenColumn;
    @FXML
    private TableView<PhanCong> bieuDoGioTable;
    @FXML
    private TableColumn<PhanCong, String> gioColumn;
    @FXML
    private TableColumn<PhanCong, String> xeAColumn;
    @FXML
    private TableColumn<PhanCong, String> xeBColumn;
    @FXML
    private TableView<Xe> xeTable;
    @FXML
    private TableColumn<Xe, String> maXeColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chonTuyen(MouseEvent event) {
    }

    @FXML
    private void test(MouseEvent event) {
    }
    
}
