/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import JavaFX.CustomNode.Constraint;
import JavaFX.CustomNode.SearchField;
import JavaFX.Filter.Filters;
import JavaFX.InputField;
import JavaFX.Nodes;
import JavaFX.SearchNodes;
import JavaFX.TableViewColumn;
import java.time.LocalTime;
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
@Table(name = "BieuDoGio")
public class BieuDoGio implements JavaFX.Entity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MaBieuDo")
    private int maBieuDo;
    
    @Column(name = "ThoiGianBatDau")
    @TableViewColumn(columnName = "Thời Gian Bắt ĐẦu")
    @InputField(fieldName = "Thời Gian Bắt ĐẦu", nodes = Nodes.TIMEPICKER)
    @Constraint
    @SearchField(name = "Thời Gian Bắt ĐẦu", searchNodes = SearchNodes.TIME)
    private LocalTime thoiGianBatDau;
    
    @Column(name = "ThoiGianKetThuc")
    @TableViewColumn(columnName = "Thời Gian Kết Thúc")
    @InputField(fieldName = "Thời Gian Kết Thúc",  nodes = Nodes.TIMEPICKER)
    @Constraint
    @SearchField(name = "Thời Gian Kết Thúc", searchNodes = SearchNodes.TIME)
    private LocalTime thoiGianKetThuc;
    
    @Column(name = "GianCachChuyen")
    @TableViewColumn(columnName = "Giãn Cách Chuyến")
    @InputField(fieldName = "Giãn Cách Chuyến", filters = Filters.Number, limit = 30)
    @Constraint
    @SearchField(name = "Giãn Cách Chuyến", searchNodes = SearchNodes.NUMBER)
    private short gianCachChuyen;
    
    @Column(name = "ThoiGianChuyen")
    @TableViewColumn(columnName = "Thời Gian Chuyến")
    @InputField(fieldName = "Thời Gian Chuyến", filters = Filters.Number)
    @Constraint
    @SearchField(name = "Thời Gian Chuyến", searchNodes = SearchNodes.NUMBER)
    private short thoiGianChuyen;

    public BieuDoGio() {
    }

    public BieuDoGio(int maBieuDo, LocalTime thoiGianBatDau, LocalTime thoiGianKetThuc, short gianCachChuyen, short thoiGianChuyen) {
        this.maBieuDo = maBieuDo;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.gianCachChuyen = gianCachChuyen;
        this.thoiGianChuyen = thoiGianChuyen;
    }
    
    public int getMaBieuDo() {
        return maBieuDo;
    }

    public void setMaBieuDo(int maBieuDo) {
        this.maBieuDo = maBieuDo;
    }

    public LocalTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public short getGianCachChuyen() {
        return gianCachChuyen;
    }

    public void setGianCachChuyen(short gianCachChuyen) {
        this.gianCachChuyen = gianCachChuyen;
    }

    public short getThoiGianChuyen() {
        return thoiGianChuyen;
    }

    public void setThoiGianChuyen(short thoiGianChuyen) {
        this.thoiGianChuyen = thoiGianChuyen;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.maBieuDo;
        return hash;
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
        final BieuDoGio other = (BieuDoGio) obj;
        if (this.maBieuDo != other.maBieuDo) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public Object getId() {
        return maBieuDo;
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("thoiGianBatDau"))
            return this.thoiGianBatDau;
        if(fieldName.equals("thoiGianKetThuc"))
            return this.thoiGianKetThuc;
        if(fieldName.equals("thoiGianChuyen"))
            return this.thoiGianChuyen;
        if(fieldName.equals("gianCachChuyen"))
            return this.gianCachChuyen;
        return null;
    }

    @Override
    public Object getDisplayValue() {
        return thoiGianBatDau.toString() + "-" + thoiGianKetThuc.toString() + "-" +
                thoiGianChuyen + "-" + gianCachChuyen;
    }

    @Override
    public void setId(Object value) {
        maBieuDo = (Integer)value;
    }

    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("thoiGianBatDau"))
            this.thoiGianBatDau = (LocalTime) value;
        if(fieldName.equals("thoiGianKetThuc"))
            this.thoiGianKetThuc = (LocalTime) value;
        if(fieldName.equals("thoiGianChuyen"))
            this.thoiGianChuyen = Short.parseShort((String)value);
        if(fieldName.equals("gianCachChuyen"))
            this.gianCachChuyen = Short.parseShort((String)value);
    }
    
    
}
