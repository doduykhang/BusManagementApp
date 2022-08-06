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
import JavaFX.TableViewColumn;
import JavaFX.TableFK;
import JavaFX.Filter.Filters;
import JavaFX.InputField;
import JavaFX.Nodes;
import JavaFX.SearchNodes;
import java.math.BigDecimal;
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
@Table(name = "LoaiTuyen")
public class LoaiTuyen implements JavaFX.Entity, BooleanColumn{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MaLoaiTuyen")
    private int maLoaiTuyen;
    
    @Column(name = "TenLoaiTuyen")
    @TableViewColumn(columnName = "Tên Loại Tuyến")
    @InputField(fieldName = "Tên Loại Tuyến", filters = Filters.Text, limit = 255)
    @Constraint
    @SearchField(name = "Tên Loại Tuyến", searchNodes = SearchNodes.TEXT)
    private String tenLoaiTuyen;
    
    @Column(name = "LoaihinhHoatDong")
    @TableViewColumn(columnName = "Trợ Giá",  cellValueFactorys = CellValueFactorys.BOOLEAN)
    @InputField(fieldName = "Trợ Giá", nodes = Nodes.RADIOBUTTON, trueRadio = "Có trợ giá", falseRadio = "Không trợ giá")
    @Constraint
    @SearchField(name = "Tên Đơn Vị", searchNodes = SearchNodes.CUSTOMRADIO)
    private boolean coTroGia;
    
    @Column(name = "GiaVeThuong", scale = 0)
    @TableViewColumn(columnName = "Giá Vé Thường", cellValueFactorys = CellValueFactorys.MONEY)
    @InputField(fieldName = "Giá Vé Thường", filters = Filters.MONEY, limit = Integer.MAX_VALUE)
    @Constraint
    @SearchField(name = "Giá Vé Thường", searchNodes = SearchNodes.NUMBER)
    private BigDecimal giaVeThuong;
    
    @Column(name = "GiaVeSinhVien", scale= 0)
    @TableViewColumn(columnName = "Giá Vé Sinh Viên", cellValueFactorys = CellValueFactorys.MONEY)
    @InputField(fieldName = "Giá Vé Sinh Viên", filters = Filters.MONEY, limit = Integer.MAX_VALUE)
    @Constraint
    @SearchField(name = "Giá Vé Thường", searchNodes = SearchNodes.NUMBER)
    private BigDecimal giaVeSinhVien;
    
    @Column(name = "GiaVeTap", scale= 0)
    @TableViewColumn(columnName = "Giá Vé Tập", cellValueFactorys = CellValueFactorys.MONEY)
    @InputField(fieldName = "Giá Vé Tập", filters = Filters.MONEY, limit = Integer.MAX_VALUE)
    @Constraint
    @SearchField(name = "Giá Vé Thường", searchNodes = SearchNodes.NUMBER)
    private BigDecimal giaVeTap;

    public LoaiTuyen() {
    }

    public LoaiTuyen(int maLoaiTuyen, String tenLoaiTuyen, boolean coTroGia, BigDecimal giaVeThuong, BigDecimal giaVeSinhVien, BigDecimal giaVeTap) {
        this.maLoaiTuyen = maLoaiTuyen;
        this.tenLoaiTuyen = tenLoaiTuyen;
        this.coTroGia = coTroGia;
        this.giaVeThuong = giaVeThuong;
        this.giaVeSinhVien = giaVeSinhVien;
        this.giaVeTap = giaVeTap;
    }

    public int getMaLoaiTuyen() {
        return maLoaiTuyen;
    }

    public void setMaLoaiTuyen(int maLoaiTuyen) {
        this.maLoaiTuyen = maLoaiTuyen;
    }

    public String getTenLoaiTuyen() {
        return tenLoaiTuyen;
    }

    public void setTenLoaiTuyen(String tenLoaiTuyen) {
        this.tenLoaiTuyen = tenLoaiTuyen;
    }

    public boolean isCoTroGia() {
        return coTroGia;
    }

    public void setCoTroGia(boolean coTroGia) {
        this.coTroGia = coTroGia;
    }

    public BigDecimal getGiaVeSinhVien() {
        return giaVeSinhVien;
    }

    public void setGiaVeSinhVien(BigDecimal giaVeSinhVien) {
        this.giaVeSinhVien = giaVeSinhVien;
    }

    public BigDecimal getGiaVeThuong() {
        return giaVeThuong;
    }

    public void setGiaVeThuong(BigDecimal giaVeThuong) {
        this.giaVeThuong = giaVeThuong;
    }

    public BigDecimal getGiaVeTap() {
        return giaVeTap;
    }

    public void setGiaVeTap(BigDecimal giaVeTap) {
        this.giaVeTap = giaVeTap;
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
        final LoaiTuyen other = (LoaiTuyen) obj;
        if (this.maLoaiTuyen != other.maLoaiTuyen) {
            return false;
        }
        return true;
    }
    
    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("tenLoaiTuyen"))
            tenLoaiTuyen = (String)value;
        if(fieldName.equals("coTroGia"))
            coTroGia = (Boolean)value;
        if(fieldName.equals("giaVeSinhVien")){
            String s = (String)value;
            s = s.replace(".", "");
            giaVeSinhVien =  BigDecimal.valueOf(Double.parseDouble(s));
        }
        if(fieldName.equals("giaVeThuong")){
            String s = (String)value;
            s = s.replace(".", "");
            giaVeThuong =  BigDecimal.valueOf(Double.parseDouble(s));
        }
        if(fieldName.equals("giaVeTap")){
            String s = (String)value;
            s = s.replace(".", "");
            giaVeTap =  BigDecimal.valueOf(Double.parseDouble(s));
        }
           
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("tenLoaiTuyen"))
            return tenLoaiTuyen;
        if(fieldName.equals("coTroGia"))
            return coTroGia;
        if(fieldName.equals("giaVeSinhVien"))
            return giaVeSinhVien;
        if(fieldName.equals("giaVeThuong"))
            return giaVeThuong;
        if(fieldName.equals("giaVeTap"))
            return giaVeTap;
        return null;
    }

    @Override
    public String boolColumnValue(String field) {
        if(field.equals("coTroGia"))
            return coTroGia ? "có trợ giá" : "không có trợ giá";
        return "";
    }

    @Override
    public Object getId() {
        return maLoaiTuyen;
    }

    @Override
    public Object getDisplayValue() {
        return tenLoaiTuyen;
    }

    @Override
    public void setId(Object value) {
        this.maLoaiTuyen = Integer.parseInt(value + "");
    }
}
