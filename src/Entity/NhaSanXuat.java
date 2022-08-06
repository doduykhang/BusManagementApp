/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import JavaFX.CustomNode.Constraint;
import JavaFX.CustomNode.SearchField;
import JavaFX.CustomNode.TypeOfConstraint;
import JavaFX.TableViewColumn;
import JavaFX.TableFK;
import JavaFX.Filter.Filters;
import JavaFX.InputField;
import JavaFX.Nodes;
import JavaFX.SearchNodes;
import java.time.LocalDate;
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
@Table(name = "NhaSanXuatXe")
public class NhaSanXuat implements JavaFX.Entity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MaNhaSanXuat")
    private int maNhaSanXuat;
    
    @Column(name = "TenNhaSanXuat")
    @TableViewColumn(columnName = "Tên")
    @InputField(fieldName = "Tên", filters = Filters.Text, limit = 50)
    @Constraint
    @SearchField(name = "Tên", searchNodes = SearchNodes.TEXT)
    private String tenNhaSanXuat;
    
    @Column(name = "SoDienThoai")
    @TableViewColumn(columnName = "Số Điện Thoại")
    @InputField(fieldName = "Số Điện Thoại",filters = Filters.SDT)
    @Constraint(idField = "maNhaSanXuat" , message = "đã có số điện thoại", typeOfConstraint = TypeOfConstraint.UNIQUE)
    @SearchField(name = "Số Điện Thoại", searchNodes = SearchNodes.TEXT)
    private String soDienThoai;
    
    @Column(name = "Email")
    @TableViewColumn(columnName = "Email")
    @InputField(fieldName = "Email", filters = Filters.EMAIL, limit = 50)
    @Constraint(idField = "maNhaSanXuat" , message = "đã có số email", typeOfConstraint = TypeOfConstraint.UNIQUE)
    @SearchField(name = "Email", searchNodes = SearchNodes.TEXT)
    private String email;
    
    @Column(name = "DiaChi")
    @TableViewColumn(columnName = "Địa Chỉ")
    @InputField(fieldName = "Địa Chỉ", nodes = Nodes.TEXTAREA, filters = Filters.Text, limit = 100)
    @Constraint
    @SearchField(name = "Địa Chỉ", searchNodes = SearchNodes.TEXT)
    private String diaChi;

    
    public NhaSanXuat() {
    }

    public NhaSanXuat(int maNhaSanXuat, String tenNhaSanXuat, String soDienThoai, String email, String diaChi) {
        this.maNhaSanXuat = maNhaSanXuat;
        this.tenNhaSanXuat = tenNhaSanXuat;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
    }

    public int getMaNhaSanXuat() {
        return maNhaSanXuat;
    }

    public void setMaNhaSanXuat(int maNhaSanXuat) {
        this.maNhaSanXuat = maNhaSanXuat;
    }

    public String getTenNhaSanXuat() {
        return tenNhaSanXuat;
    }

    public void setTenNhaSanXuat(String tenNhaSanXuat) {
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhaSanXuat{" + "maNhaSanXuat=" + maNhaSanXuat + ", tenNhaSanXuat=" + tenNhaSanXuat + ", soDienThoai=" + soDienThoai + ", email=" + email + ", diaChi=" + diaChi + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhaSanXuat other = (NhaSanXuat) obj;
        if (this.maNhaSanXuat != other.maNhaSanXuat) {
            return false;
        }
        return true;
    }

    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("id"))
            maNhaSanXuat = Integer.parseInt((String)value);
        if(fieldName.equals("maNhaSanXuat"))
            maNhaSanXuat = Integer.parseInt((String)value);
        if(fieldName.equals("tenNhaSanXuat"))
            tenNhaSanXuat = (String)value;
        if(fieldName.equals("soDienThoai"))
            soDienThoai = (String)value;
        if(fieldName.equals("diaChi"))
            diaChi = (String)value;
        if(fieldName.equals("email"))
            email = (String)value;
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("id"))
            return maNhaSanXuat;
        if(fieldName.equals("maNhaSanXuat"))
            return maNhaSanXuat;
        if(fieldName.equals("tenNhaSanXuat") || fieldName.equals("displayValue"))
            return tenNhaSanXuat;
        if(fieldName.equals("soDienThoai"))
            return soDienThoai;
        if(fieldName.equals("diaChi"))
            return diaChi;
        if(fieldName.equals("email"))
            return email;
        return null;
    }

    @Override
    public Object getId() {
        return maNhaSanXuat;
    }

    @Override
    public Object getDisplayValue() {
        return tenNhaSanXuat;
    }

    @Override
    public void setId(Object value) {
        this.maNhaSanXuat = Integer.parseInt(value + "");
    }
}
