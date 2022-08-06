/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.DonViQuanLy;
import Entity.LoaiXe;
import Hibernate.DataGetter;
import javafx.event.ActionEvent;

/**
 *
 * @author dokha
 */
public class LoaiXeController extends BasedFrameControllerr{

    @Override
    public void xoa(ActionEvent event) {
        LoaiXe loaiXe = (LoaiXe)table.getSelectionModel().getSelectedItem();
        if(DataGetter.checkForeignKey("Xe", "loaiXe", loaiXe)){
            showThongBao("Không thể xóa vì đang có xe với loại xe này");
            return;
        }
        super.xoa(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sua(ActionEvent event) {
        LoaiXe loaiXe = (LoaiXe)table.getSelectionModel().getSelectedItem();
        if(DataGetter.checkLoaiXePhanCong(loaiXe)){
            showThongBao("Không thể sửa vì đang có xe với loại xe này trong phân công");
            return;
        }
        super.sua(event); //To change body of generated methods, choose Tools | Templates.
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
