/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import JavaFX.BooleanColumn;
import JavaFX.CellValueFactorys;
import JavaFX.CustomNode.Constraint;
import JavaFX.CustomNode.SearchField;
import JavaFX.CustomNode.TypeOfConstraint;
import JavaFX.TableViewColumn;
import JavaFX.TableFK;
import JavaFX.Filter.Filters;
import JavaFX.InputField;
import JavaFX.Nodes;
import JavaFX.SearchNodes;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dokha
 */
@Entity
@Table(name = "DonViQuanLy")
public class DonViQuanLy implements JavaFX.Entity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MaDonVi")
    private Integer maDonVi;
    
    @Column(name = "TenDonVi")
    @TableViewColumn(columnName = "Tên Đơn Vị")
    @InputField(fieldName = "Tên Đơn Vị",filters = Filters.Text, limit = 50)
    @Constraint
    @SearchField(name = "Tên Đơn Vị", searchNodes = SearchNodes.TEXT)
    private String tenDonVi;
    
    @Column(name = "SoDienThoai")
    @TableViewColumn(columnName = "Số Điện Thoại")
    @InputField(fieldName = "Số Điện Thoại",filters = Filters.SDT)
    @Constraint(idField = "maDonVi" , message = "đã có số điện thoại", typeOfConstraint = TypeOfConstraint.UNIQUE)
    private String sdt;
    
    @Column(name = "DiaChi")
    @TableViewColumn(columnName = "Địa Chỉ")
    @InputField(fieldName = "Địa Chỉ", nodes = Nodes.TEXTAREA, filters = Filters.Text, limit = 100)
    @Constraint
    @SearchField(name = "Đia Chỉ", searchNodes = SearchNodes.TEXT)
    private String diaChi;
    
    @Column(name = "Email")
    @TableViewColumn(columnName = "Email")
    @InputField(fieldName = "Email",filters = Filters.EMAIL)
    @Constraint(idField = "maDonVi" , message = "đã có số email", typeOfConstraint = TypeOfConstraint.UNIQUE)
    @SearchField(name = "Email", searchNodes = SearchNodes.TEXT)
    private String email;


    public DonViQuanLy() {
    }
    
    public DonViQuanLy(int maDonVi, String tenDonVi, String sdt, String diaChi, String email) {
        this.maDonVi = maDonVi;
        this.tenDonVi = tenDonVi;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.email = email;
    }

    public Integer getMaDonVi() {
        return maDonVi;
    }

    public void setMaDonVi(Integer maLoaiTuyen) {
        this.maDonVi = maLoaiTuyen;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            if(this.getMaDonVi() == null)
                return true;
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final DonViQuanLy other = (DonViQuanLy) obj;
        if (!Objects.equals(this.maDonVi, other.maDonVi)) {
            return false;
        }
        return true;
    }


    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("tenDonVi"))
            tenDonVi = (String)value;
        if(fieldName.equals("sdt"))
            sdt =  (String)value;
        if(fieldName.equals("diaChi"))
            diaChi =  (String)value;
        if(fieldName.equals("email"))
            email =  (String)value;
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("tenDonVi"))
            return tenDonVi;
        if(fieldName.equals("sdt"))
            return sdt;
        if(fieldName.equals("diaChi"))
            return diaChi;
        if(fieldName.equals("email"))
            return email;
        return null;
    }

    @Override
    public Object getId() {
        return maDonVi;
    }

    @Override
    public Object getDisplayValue() {
        return tenDonVi;
    }

    @Override
    public void setId(Object value) {
        this.maDonVi = Integer.parseInt(value + "");
    }

}
