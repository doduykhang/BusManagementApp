/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.BieuDoGio;
import Entity.ChiTietTuyen;
import Entity.DonViQuanLy;
import Entity.LoaiTuyen;
import Entity.Tuyen;
import Hibernate.DataGetter;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 *
 * @author dokha
 */
public class TuyenController extends BasedFrameControllerr{

    @Override
    public void xoa(ActionEvent event) {
        Tuyen tuyen = (Tuyen)table.getSelectionModel().getSelectedItem(); 
        if(DataGetter.checkForeignKey("DoanhThu", "tuyen", tuyen)){
            showThongBao("Không thể xóa vì đã có trong doanh thu");
            return;
        }
        if(DataGetter.checkForeignKey("PhanCong", "tuyen", tuyen)){
            showThongBao("Không thể xóa vì đã có xe trong tuyen");
            return;
        }
        super.xoa(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sua(ActionEvent event) {
        Tuyen tuyen = (Tuyen)table.getSelectionModel().getSelectedItem(); 
        if(DataGetter.checkForeignKey("DoanhThu", "tuyen", tuyen)){
            showThongBao("Không thể sửa vì đã có trong doanh thu");
            return;
        }
        if(DataGetter.checkForeignKey("PhanCong", "tuyen", tuyen)){
            showThongBao("Không thể xóa vì đã có xe trong tuyen");
            return;
        }
        super.sua(event); //To change body of generated methods, choose Tools | Templates.
    }    
    
    @Override
    public boolean checkConstraint() {
        ComboBox cb1 = (ComboBox)infoPane.getNode().get(0);
        ComboBox cb2 = (ComboBox)infoPane.getNode().get(1);
        ComboBox cb3 = (ComboBox)infoPane.getNode().get(2);
        ComboBox cb4 = (ComboBox)infoPane.getNode().get(3);
        if(cb1.getItems().isEmpty()){
            showThongBao("Không thể thêm vì chưa có chi tiết tuyến");
            return false;
        }
        
        if(cb2.getItems().isEmpty()){
            showThongBao("Không thể thêm vì chua có loại tuyến");
            return false;
        }
        
        if(cb3.getItems().isEmpty()){
            showThongBao("Không thể thêm vì chưa có đơn vị quản lý");
            return false;
        }
        
        if(cb4.getItems().isEmpty()){
            showThongBao("Không thể thêm vì chưa có biểu đồ giờ");
            return false;
        }
        
        Tuyen tuyen = (Tuyen)table.getSelectionModel().getSelectedItem(); 
        LocalDate ngayBatDau = (LocalDate)infoPane.getFieldVale(4);
        LocalDate ngayKetthuc = (LocalDate)infoPane.getFieldVale(5);
        ChiTietTuyen chiTietTuyen = (ChiTietTuyen)cb1.getSelectionModel().getSelectedItem();
        switch(mode){
            case 0:
                
                break;
            case 1:   
                if(ngayBatDau.isAfter(ngayKetthuc)){
                    showThongBao("ngày bắt đầu phải lớn hơn ngày kết thúc");
                    return false;
                }
                if(chiTietTuyen.getTotalDistance() < 500.0){
                    showThongBao("lộ trình của tuyến này quá ngắn");
                    return false;
                }
                if(DataGetter.checkTuyenTrungLap(chiTietTuyen, ngayBatDau, ngayKetthuc, null)){
                    showThongBao("Trùng khoảng thời gian hoạt động");
                    return false;
                }
                
                break;
            case 2:
                
                break;
            case 3:
                if(ngayBatDau.isAfter(ngayKetthuc)){
                    showThongBao("ngày bắt đầu phải lớn hơn ngày kết thúc");
                    return false;
                }
                
                if(DataGetter.checkTuyenTrungLap(chiTietTuyen, ngayBatDau, ngayKetthuc, tuyen.getMaTuyen())){
                    showThongBao("Trùng khoảng thời gian hoạt động");
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void afterUpdate(Object object) {
        super.afterUpdate(object); //To change body of generated methods, choose Tools | Templates.
        Tuyen selectedTuyen = (Tuyen)table.getSelectionModel().getSelectedItem();
        
    }
    
    
    
}
