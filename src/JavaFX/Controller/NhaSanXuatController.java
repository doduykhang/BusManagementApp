/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.LoaiXe;
import Entity.NhaSanXuat;
import Hibernate.DataGetter;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

/**
 *
 * @author dokha
 */
public class NhaSanXuatController extends BasedFrameControllerr{
    @Override
    public void xoa(ActionEvent event) {
        NhaSanXuat nhaSanXuat = (NhaSanXuat)table.getSelectionModel().getSelectedItem(); 
        if(DataGetter.checkForeignKey("Xe", "nhaSanXuat", nhaSanXuat)){
            showThongBao("Không thể xóa vì đang có xe với nhà sản xuất này");
            return;
        }
        super.xoa(event); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean checkConstraint() {
        
        switch(mode){
            case 0:
                break;
            case 1: 
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void extraInital() {
    }
    
}
