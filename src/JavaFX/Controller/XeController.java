/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.Xe;
import Hibernate.DataGetter;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author dokha
 */
public class XeController extends BasedFrameControllerr{

    @Override
    public void xoa(ActionEvent event) {
        Xe xe = (Xe)table.getSelectionModel().getSelectedItem();
        if(DataGetter.checkForeignKey("PhanCong", "xe", xe)){
            showThongBao("Không thể xóa vì đã được phân công");
            return;
        }
        super.xoa(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sua(ActionEvent event) {
        Xe xe = (Xe)table.getSelectionModel().getSelectedItem();
        if(DataGetter.checkForeignKey("PhanCong", "xe", xe)){
            showThongBao("Không thể sửa vì đã được phân công");
            return;
        }
        super.sua(event); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public boolean checkConstraint() {
        ComboBox cb1 = (ComboBox)infoPane.getNode().get(2);
        ComboBox cb2 = (ComboBox)infoPane.getNode().get(3);
        if(cb1.getItems().isEmpty()){
            disableButton(true ,true, true);
            showThongBao("Không thể thêm vì chưa có nhà sản xuát");
            return false;
        }
        
        if(cb2.getItems().isEmpty()){
            disableButton(true ,true, true);
            showThongBao("Không thể thêm vì chưa có loại xe");
            return false;
        }
            
        return true;
    }

    @Override
    public void extraInital() {
        DatePicker dp = (DatePicker)infoPane.getNode().get(1);
        
        dp.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
    }
    
}
