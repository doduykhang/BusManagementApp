/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.BieuDoGio;
import Entity.DonViQuanLy;
import Entity.LoaiTuyen;
import Entity.LoaiXe;
import Hibernate.DataGetter;
import java.math.BigDecimal;
import javafx.event.ActionEvent;

/**
 *
 * @author dokha
 */
public class LoaiTuyenController extends BasedFrameControllerr{

    @Override
    public void sua(ActionEvent event) {
        LoaiTuyen loaiTuyen = (LoaiTuyen)table.getSelectionModel().getSelectedItem(); 
        if(DataGetter.checkLoaiTuyenDoanhThu(loaiTuyen)){
            showThongBao("Không thể sửa vì đã tuyến có trong doanh thu");
            return;
        }
        super.sua(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void xoa(ActionEvent event) {
        LoaiTuyen loaiTuyen = (LoaiTuyen)table.getSelectionModel().getSelectedItem(); 
        if(DataGetter.checkForeignKey("Tuyen", "loaiTuyen", loaiTuyen)){
            showThongBao("Không thể xóa vì đang có tuyến với loại tuyến này");
            return;
        }
        super.xoa(event); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public boolean checkConstraint() {
        String [] giaVe = {((String)infoPane.getFieldVale(2)).replace(".", ""),
                            ((String)infoPane.getFieldVale(3)).replace(".", ""),
                            ((String)infoPane.getFieldVale(4)).replace(".", "")};
        
       
        boolean b1 = checkValidMoney(giaVe[0]);
        boolean b2 = checkValidMoney(giaVe[1]);
        boolean b3 = checkValidMoney(giaVe[2]);
        LoaiTuyen loaiTuyen = (LoaiTuyen)table.getSelectionModel().getSelectedItem();
        switch(mode){
            case 0:
                break;
            case 1:    
                if(!(b1 && b2 && b3)){
                    showThongBao("Số tiền phải chia hết cho 500");
                    return false;
                }
                if(DataGetter.checkGiaLoaiTuyen(giaVe, null)){
                    showThongBao("Đã có loại tuyến với giá vé này");
                    return false;
                }
                break;
            case 2:
                
                break;
            case 3:
                if(!(b1 && b2 && b3)){
                    showThongBao("Số tiền phải chia hết cho 500");
                    return false;
                }
                if(DataGetter.checkGiaLoaiTuyen(giaVe, loaiTuyen.getMaLoaiTuyen())){
                    showThongBao("Đã có loại tuyến với giá vés này");
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void extraInital() {
    }
    
    public boolean checkValidMoney(String money){
        BigDecimal value = new BigDecimal(Double.parseDouble(money));
        BigDecimal value500 = new BigDecimal(Double.parseDouble("500"));
        BigDecimal value0 = new BigDecimal(Double.parseDouble("0"));
        return value.divideAndRemainder(value500)[1].equals(value0);
    }
    
}
