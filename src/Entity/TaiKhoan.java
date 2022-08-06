/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import JavaFX.CustomNode.Constraint;
import JavaFX.Filter.Filters;
import JavaFX.InputField;
import JavaFX.Nodes;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author dokha
 */
@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements Serializable,JavaFX.Entity{
    @Id
    @Column(name = "MaNhanVien")
    private int maNhanVien;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;
    
    @Column(name = "TenDangNhap")
    @InputField(fieldName = "tên đăng nhập", limit = 20 , filters = Filters.NOTBLANK)
    @Constraint
    private String tenDangNhap;
    
    @Column(name = "MatKhau")
    @InputField(fieldName = "mật khẩu", limit = 10, nodes = Nodes.Password ,filters = Filters.NOTBLANK)
    @Constraint
    private String matKhau;

    
    
    public TaiKhoan() {
    }


    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "nhanVien=" + nhanVien + ", tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + '}';
    }

    @Override
    public Object getId() {
        return maNhanVien;
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("tenDangNhap"))
            return tenDangNhap;
        if(fieldName.equals("matKhau"))
            return matKhau;
        return null;
    }

    @Override
    public Object getDisplayValue() {
        return "";
    }

    @Override
    public void setId(Object value) {
        maNhanVien = (Integer)value;
    }

    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("tenDangNhap"))
            tenDangNhap = (String) value;
        if(fieldName.equals("matKhau"))
            matKhau = (String) value;
    }

}
