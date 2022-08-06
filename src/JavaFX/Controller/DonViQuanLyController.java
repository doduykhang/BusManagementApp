/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.ChiTietTuyen;
import Entity.DonViQuanLy;
import Entity.NhaSanXuat;
import Hibernate.DataGetter;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author dokha
 */
public class DonViQuanLyController extends BasedFrameControllerr{
    @Override
    public void xoa(ActionEvent event) {
        DonViQuanLy donViQuanLy = (DonViQuanLy)table.getSelectionModel().getSelectedItem(); 
        if(DataGetter.checkForeignKey("Tuyen", "donViQuanLy", donViQuanLy)){
            showThongBao("Không thể xóa vì đang có tuyến với đơn vị quản lý này");
            return ;
        }
        if(DataGetter.checkForeignKey("NhanVien", "donViQuanLy", donViQuanLy)){
            showThongBao("Không thể xóa vì đang có nhân viên với đơn vị quản lý này");
            return ;
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
//        Button button = new Button("test");
//        button.setOnAction((event) -> {
//            checkConstraint();
//        });
//        infoPane.add(button, 10, 10);
    }
    
}
