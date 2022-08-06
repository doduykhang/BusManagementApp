/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.BieuDoGio;
import Entity.LoaiTuyen;
import Hibernate.DataGetter;
import JavaFX.CustomNode.TimePicker;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import javafx.event.ActionEvent;

/**
 *
 * @author dokha
 */
public class BieuDoGioConTroller extends BasedFrameControllerr{

    @Override
    public void sua(ActionEvent event) {
        BieuDoGio bieuDoGio = (BieuDoGio)table.getSelectionModel().getSelectedItem();
        if(DataGetter.checkBieuDoGioDoanhThu(bieuDoGio)){
            showThongBao("Không thể sửa vì đã tuyến có trong doanh thu");
            return;
        }
        if(DataGetter.checkBieuDoPhanCong(bieuDoGio)){
            showThongBao("Không thể sửa vì đã tuyến có xe sử dụng biểu đồ giờ này");
            return;
        }
        super.sua(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void xoa(ActionEvent event) {
        BieuDoGio bieuDoGio = (BieuDoGio)table.getSelectionModel().getSelectedItem();
        if(DataGetter.checkForeignKey("Tuyen", "bieuDoGio", bieuDoGio)){
            showThongBao("Không thể xóa vì đang có tuyến với biểu đồ giờ này");
            return;
        }
        super.xoa(event); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean checkConstraint() {
        BieuDoGio bieuDoGio = (BieuDoGio)table.getSelectionModel().getSelectedItem();
        LocalTime t1 = ((TimePicker)infoPane.getNode().get(0)).getValue();
        LocalTime t2 = ((TimePicker)infoPane.getNode().get(1)).getValue();
        short gianCach = Short.parseShort((String)infoPane.getFieldVale(2));
        short thoiGianChuyen = Short.parseShort((String)infoPane.getFieldVale(3));
        long hours = t1.until( t2, ChronoUnit.HOURS );
        switch(mode){
            case 0:
                break;
            case 1:    
                if(hours <= 12){
                    showThongBao("Thời gian hoạt động ít nhất 12 giờ");
                    return false;
                }
                if(DataGetter.checkBieuDoUnique(t1, t2, gianCach, thoiGianChuyen, null)){
                    showThongBao("Đã có biểu đồ giờ này");
                    return false;
                }
                break;
            case 2:
                break;
            case 3:
                if(hours <= 12){
                    showThongBao("Thời gian hoạt động ít nhất 12 giờ");
                    return false;
                }
                if(DataGetter.checkBieuDoUnique(t1, t2, gianCach, thoiGianChuyen, bieuDoGio.getMaBieuDo())){
                    showThongBao("Đã có biểu đồ giờ này");
                    return false;
                }
                break;
            default:
                break;
        }
        
        return true;
    }
    
}
