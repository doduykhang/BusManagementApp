/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.ChiTietTuyen;
import Entity.DonViQuanLy;
import Entity.NhanVien;
import Entity.Tuyen;
import Hibernate.DataGetter;
import JavaFX.CustomNode.SearchComboBox;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

/**
 *
 * @author dokha
 */
public class DoanhThuNhanVien extends BasedFrameControllerr{

    private DonViQuanLy donViQuanLy;
    
    @Override
    public void sua(ActionEvent event) {
        super.sua(event); //To change body of generated methods, choose Tools | Templates.
        infoPane.getNode().get(0).setDisable(true);
        infoPane.getNode().get(1).setDisable(true);
    }
    
    
    
    @Override
    public boolean checkConstraint() {
        ComboBox cb1 = (ComboBox)infoPane.getNode().get(0);
        if(cb1.getItems().isEmpty()){
            showThongBao("Không thể thêm vì chưa có  tuyến");
            return false;
        }
        LocalDate ngay = (LocalDate)infoPane.getFieldVale(1);
        Tuyen tuyen = (Tuyen)infoPane.getFieldVale(0);
        LocalDate ngayBatDau = tuyen.getNgayBatDau();
        LocalDate ngayKetThuc = tuyen.getNgayKetThuc();
        ChiTietTuyen chiTietTuyen = tuyen.getChiTietTuyen();
        
        switch(mode){
            case 0:
                
                break;
            case 1:   
                if(!(!ngay.isBefore(ngayBatDau) && !ngay.isAfter(ngayKetThuc))){
                    showThongBao("phải trong thời gian hoạt động");
                    return false;
                }
                if(DataGetter.checkDaCoDoanhThuTrongNgay(ngay, chiTietTuyen)){
                    showThongBao("Tuyến đã có doanh thu trong ngày này");
                    return false;
                }
                if(!DataGetter.checkDoanhThuPhanCong(tuyen)){
                    showThongBao("Tuyến chưa có xe");
                    return false;
                }
                break;
            case 2:
                
                break;
            case 3:
                if(!(!ngay.isBefore(ngayBatDau) && !ngay.isAfter(ngayKetThuc))){
                    showThongBao("phải trong thời gian hoạt động");
                    return false;
                }
                if(DataGetter.checkDaCoDoanhThuTrongNgay(ngay, chiTietTuyen)){
                    showThongBao("Tuyến đã có doanh thu trong ngày này");
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
        super.extraInital(); //To change body of generated methods, choose Tools | Templates.
        xoaButton.setDisable(true);
        SearchComboBox searchComboBox = (SearchComboBox)searchPane.getSearchNodes().get(0);
        List<Tuyen> tuyen = DataGetter.listTuyenCuaDonVi(donViQuanLy);
        searchComboBox.getComboBox().getItems().clear();
        searchComboBox.getComboBox().getItems().addAll(tuyen);
        
        ComboBox comboBox = (ComboBox)infoPane.getNode().get(0);
        comboBox.getItems().clear();
        comboBox.getItems().addAll(tuyen);
        
        updateCondition();
    }

 
    
    
    
    public void setDonVi(NhanVien nhanVien){
        donViQuanLy = nhanVien.getDonViQuanLy();
    }
}
