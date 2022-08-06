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
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author dokha
 */
@Entity
@Table(name = "NhanVien")
public class NhanVien implements BooleanColumn,JavaFX.Entity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MaNhanVien")
    private int maNhanVien;
    
    @Column(name = "CMND")
    @TableViewColumn(columnName = "CMND")
    @InputField(fieldName = "CMND", filters = Filters.Text, limit = 10)
    @Constraint(idField = "maNhanVien" , message = "đã có số CMND", typeOfConstraint = TypeOfConstraint.UNIQUE)
    @SearchField(name = "CMND", searchNodes = SearchNodes.TEXT)
    private String cmnd;
    
    @Column(name = "Ho")
    @TableViewColumn(columnName = "Họ")
    @InputField(fieldName = "Họ", filters = Filters.Text, limit = 10)
    @Constraint
    @SearchField(name = "Họ", searchNodes = SearchNodes.TEXT)
    private String ho;
    
    @Column(name = "Ten")
    @TableViewColumn(columnName = "Tên")
    @InputField(fieldName = "Tên", filters = Filters.Text, limit = 20)
    @Constraint
    @SearchField(name = "Tên", searchNodes = SearchNodes.TEXT)
    private String ten;
    
    @Column(name = "GioiTinh")
    @TableViewColumn(columnName = "Giới Tính", cellValueFactorys = CellValueFactorys.BOOLEAN)
    @InputField(fieldName = "Giới Tính", nodes = Nodes.RADIOBUTTON, trueRadio = "Nam", falseRadio = "Nữ")
    @Constraint
    @SearchField(name = "Giới Tính", searchNodes = SearchNodes.CUSTOMRADIO)
    private boolean gioiTinh;
    
    @Column(name = "NgaySinh")
    @TableViewColumn(columnName = "Ngày Sinh")
    @InputField(fieldName = "Ngày Sinh", nodes = Nodes.DATEPICKER)
    @Constraint
    @SearchField(name = "Ngày Sinh", searchNodes = SearchNodes.DATE)
    private LocalDate ngaySinh;
    
    @Column(name = "SoDienThoai")
    @TableViewColumn(columnName = "Số Điện Thoại")
    @InputField(fieldName = "Số Điện Thoại", filters = Filters.SDT)
    @Constraint
    @SearchField(name = "Số Điện Thoại", searchNodes = SearchNodes.TEXT)
    private String soDienThoai;
    
    @Column(name = "DiaChi")
    @TableViewColumn(columnName = "Địa Chỉ")
    @InputField(fieldName = "Địa Chỉ", filters = Filters.Text, limit = 100, nodes = Nodes.TEXTAREA)
    @Constraint
    @SearchField(name = "Địa Chỉ", searchNodes = SearchNodes.TEXT)
    private String diaChi;
    
    @Column(name = "Email")
    @TableViewColumn(columnName = "Email")
    @InputField(fieldName = "Email", filters = Filters.EMAIL, limit = 50)
    @Constraint(idField = "maNhanVien" , message = "đã có số email", typeOfConstraint = TypeOfConstraint.UNIQUE)
    @SearchField(name = "Email", searchNodes = SearchNodes.TEXT)
    private String email;
    
    @ManyToOne()
    @JoinColumn(name = "MaDonVi", nullable = true)
    @TableViewColumn(columnName = "Đơn Vị Quản Lý", cellValueFactorys = CellValueFactorys.FK)
    @InputField(fieldName = "Đơn Vị Quản Lý", nodes = Nodes.CUSTOMBOX)
    @Constraint
    @SearchField(name = "Đơn Vị Quản Lý", searchNodes = SearchNodes.DONVIBOX)
    private DonViQuanLy donViQuanLy;

    
    @OneToOne(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    //@PrimaryKeyJoinColumn
    private TaiKhoan taiKhoan;
    
    
    public NhanVien() {
    }

    public NhanVien(int maNhanVien, String cmnd, String ho, String ten, boolean gioiTinh, LocalDate ngaySinh, String soDienThoai, String diaChi, String email, DonViQuanLy donViQuanLy, TaiKhoan taiKhoan) {
        this.maNhanVien = maNhanVien;
        this.cmnd = cmnd;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.email = email;
        this.donViQuanLy = donViQuanLy;
        this.taiKhoan = taiKhoan;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
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

    public DonViQuanLy getDonViQuanLy() {
        return donViQuanLy;
    }

    public void setDonViQuanLy(DonViQuanLy donViQuanLy) {
        this.donViQuanLy = donViQuanLy;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNhanVien=" + maNhanVien + ", cmnd=" + cmnd + ", ho=" + ho + ", ten=" + ten + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + ", email=" + email + ", donViQuanLy=" + donViQuanLy + '}';
    }

    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("id"))
            maNhanVien = Integer.parseInt((String)value);
        if(fieldName.equals("cmnd"))
            cmnd = (String)value;
        if(fieldName.equals("ho"))
            ho = (String)value;
        if(fieldName.equals("ten"))
            ten = (String)value;
        if(fieldName.equals("gioiTinh"))
            gioiTinh = (Boolean)value;
        if(fieldName.equals("ngaySinh"))
            ngaySinh = (LocalDate)value;
        if(fieldName.equals("soDienThoai"))
            soDienThoai = (String)value;
        if(fieldName.equals("diaChi"))
            diaChi = (String)value;
        if(fieldName.equals("email"))
            email = (String)value;
        if(fieldName.equals("donViQuanLy")){
            DonViQuanLy dv = (DonViQuanLy)value;
            donViQuanLy = (dv.getMaDonVi() == null) ? null:dv;
            //donViQuanLy = dv;
        }
            
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("id"))
            return maNhanVien;
        if(fieldName.equals("cmnd"))
            return cmnd;
        if(fieldName.equals("ho"))
            return ho;
        if(fieldName.equals("ten"))
            return ten;
        if(fieldName.equals("gioiTinh"))
            return gioiTinh;
        if(fieldName.equals("ngaySinh"))
            return ngaySinh;
        if(fieldName.equals("soDienThoai"))
            return soDienThoai;
        if(fieldName.equals("diaChi"))
            return diaChi;
        if(fieldName.equals("email"))
            return email;
        if(fieldName.equals("donViQuanLy"))
            return donViQuanLy;
        if(fieldName.equals("FKdonViQuanLy")){
            return (donViQuanLy != null) ? donViQuanLy.getTenDonVi():"Bộ GTVT";
        }
            
        return null;
    }

    @Override
    public String boolColumnValue(String field) {
        if(field.equals("gioiTinh"))
            return gioiTinh ? "Nam" : "Nữ";
        return "";
    }

    @Override
    public Object getId() {
        return maNhanVien;
    }

    @Override
    public Object getDisplayValue() {
        return ho + ten;
    }

    @Override
    public void setId(Object value) {
        this.maNhanVien = Integer.parseInt(value + "");
    }
    
}
