/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus2;

import Entity.ChiTietTuyen;
import Entity.DonViQuanLy;
import Entity.LoTrinh;
import Entity.LoaiXe;
import Entity.NhanVien;
import Entity.TinhTrang;
import Entity.Tram;
import Entity.Tuyen;
import Entity.Xe;
import Hibernate.DataGetter;
import JavaFX.CustomNode.SearchComboBox;
import JavaFX.CustomNode.SearchNum;
import JavaFX.CustomNode.SearchNode;
import JavaFX.CustomNode.SearchPane;
import JavaFX.Filter.Filters;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dokha
 */
public class test extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
       
        
        SearchComboBox searchComboBox = new SearchComboBox();
        DonViQuanLy donViQuanLy = new DonViQuanLy();
        donViQuanLy.setMaDonVi(1);
        searchComboBox.getComboBox().getItems().add(donViQuanLy);
        searchComboBox.getComboBox().getSelectionModel().select(0);
        
        SearchPane searchPane = new SearchPane();
        searchPane.add(searchComboBox, 0, 0);
        
        searchPane.getSearchNodes().add(searchComboBox);
        searchPane.getFieldName().add("donViQuanLy:tuyen");
        Button button = new Button();
        
        
        button.setOnAction((event) -> {
            List<Xe> list = DataGetter.listObject(Xe.class, searchPane);
                for(Xe x : list)
                    System.out.println(x.toString());
        });
        searchPane.add(button, 0, 1);
        
        StackPane root = new StackPane();
        root.getChildren().add(searchPane);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
