/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX.Controller;

import Entity.ChiTietTuyen;
import Entity.DonViQuanLy;
import Entity.NhanVien;
import Entity.TaiKhoan;
import Hibernate.DataGetter;
import JavaFX.CustomNode.InfoPane;
import JavaFX.JavaFXUtil;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

/**
 *
 * @author dokha
 */
public class NhanVienController extends BasedFrameControllerr{

    public NhanVienController() {
        
    }

    InfoPane taiKhoanPane;
    
    @Override
    public void xoa(ActionEvent event) {
        NhanVien nhanVien = (NhanVien)table.getSelectionModel().getSelectedItem(); 
        if(DataGetter.checkForeignKey("TaiKhoan", "nhanVien", nhanVien)){
            showThongBao("Không thể xóa nhân viên vì đã có tài khoản");
            return;
        }
        super.xoa(event); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void select(Object object, InfoPane infoPane) {
        super.select(object, infoPane); //To change body of generated methods, choose Tools | Templates.
        TaiKhoan taiKhoan = ((NhanVien)table.getSelectionModel().getSelectedItem()).getTaiKhoan();
        if(taiKhoan != null){
            ((TextField)taiKhoanPane.getNode().get(0)).setText(taiKhoan.getTenDangNhap());
            ((PasswordField)taiKhoanPane.getNode().get(1)).setText(taiKhoan.getMatKhau());
        }
        else{
            ((TextField)taiKhoanPane.getNode().get(0)).clear();
            ((PasswordField)taiKhoanPane.getNode().get(1)).clear();
        }
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
        taiKhoanPane = JavaFXUtil.makeRightBar(TaiKhoan.class);
        Tab tab = new Tab("Tài khoản", taiKhoanPane);
        tabPane.getTabs().add(tab);
        
        
        Button capNhapButton = new Button("Cập nhật tài khoản");
        capNhapButton.setOnAction((event) -> {
            NhanVien nhanVien = (NhanVien) table.getSelectionModel().getSelectedItem();
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setNhanVien(nhanVien);
            taiKhoan.setId(nhanVien.getMaNhanVien());
            taiKhoan.setTenDangNhap((String)taiKhoanPane.getFieldVale(0));
            taiKhoan.setMatKhau((String)taiKhoanPane.getFieldVale(1));
            nhanVien.setTaiKhoan(taiKhoan);
            nhanVien = (NhanVien)DataGetter.mergeObject(nhanVien);
        });
        taiKhoanPane.add(capNhapButton, 0, 4);
        
        DatePicker dp = (DatePicker)infoPane.getNode().get(4);
        
        dp.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                
                setDisable(empty || date.compareTo(today.minusYears(18)) > 0 );
            }
        });
        
    }
    
}
