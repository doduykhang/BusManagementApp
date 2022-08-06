/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import JavaFX.CellValueFactorys;
import JavaFX.CustomNode.Constraint;
import JavaFX.CustomNode.SearchField;
import JavaFX.Filter.Filters;
import JavaFX.InputField;
import JavaFX.Nodes;
import JavaFX.SearchNodes;
import JavaFX.TableViewColumn;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author dokha
 */
@Entity
@Table(name = "DoanhThu")
public class DoanhThu implements JavaFX.Entity{
    @EmbeddedId
    private DoanThuId id = new DoanThuId();
     
    @ManyToOne
    @MapsId("tuyen") //This is the name of attr in EmployerDeliveryAgentPK class
    @JoinColumn(name = "MaTuyen")
    @TableViewColumn(columnName = "Tuyến", cellValueFactorys = CellValueFactorys.FK)
    @InputField(fieldName = "Tuyến", nodes = Nodes.COMBOBOX)
    @Constraint
    @SearchField(name = "Tuyến", searchNodes = SearchNodes.COMBOBOX)
    private Tuyen tuyen; 
    
    
    @TableViewColumn(columnName = "Ngày", cellValueFactorys = CellValueFactorys.FK)
    @InputField(fieldName = "Ngày", nodes = Nodes.DATEPICKER)
    @Constraint
    @SearchField(name = "Ngày", searchNodes = SearchNodes.DATE, embeded = "id")
    @Transient 
    private LocalDate ngay;
    
    @Column(name = "SoVeThuong")
    @TableViewColumn(columnName = "Số Vé Thường")
    @InputField(fieldName = "Số Vé Thường", nodes = Nodes.TEXTFILED, filters = Filters.Number, limit = Short.MAX_VALUE)
    @Constraint
    @SearchField(name = "Số Vé Thường", searchNodes = SearchNodes.NUMBER)
    private int soVeThuong;
    
    @Column(name = "SoVeSinhVien")
    @TableViewColumn(columnName = "Số Vé Sinh Viên")
    @InputField(fieldName = "Số Vé Sinh Viên", nodes = Nodes.TEXTFILED, filters = Filters.Number, limit = Short.MAX_VALUE)
    @Constraint
    @SearchField(name = "Số Vé Sinh Viên", searchNodes = SearchNodes.NUMBER)
    private int soVeSinhVien;
    
    @Column(name = "SoVeTap")
    @TableViewColumn(columnName = "Số Vé Tập")
    @InputField(fieldName = "Số Vé Tập", nodes = Nodes.TEXTFILED, filters = Filters.Number, limit = Short.MAX_VALUE)
    @Constraint
    @SearchField(name = "Số Vé Tập", searchNodes = SearchNodes.NUMBER)
    private int soVeTap;

    
    
    public DoanhThu(Tuyen tuyen, int soVeThuong, int soVeSinhVien, int soVeTap) {
        this.ngay = id.getNgay();
        this.tuyen = tuyen;
        this.soVeThuong = soVeThuong;
        this.soVeSinhVien = soVeSinhVien;
        this.soVeTap = soVeTap;
    }

    public DoanhThu() {
        this.ngay = id.getNgay();
    }

    public DoanThuId getId() {
        return id;
    }

    public void setId(DoanThuId id) {
        this.id = id;
    }

    public Tuyen getTuyen() {
        return tuyen;
    }

    public void setTuyen(Tuyen tuyen) {
        this.tuyen = tuyen;
    }

    public int getSoVeThuong() {
        return soVeThuong;
    }

    public void setSoVeThuong(int soVeThuong) {
        this.soVeThuong = soVeThuong;
    }

    public int getSoVeSinhVien() {
        return soVeSinhVien;
    }

    public void setSoVeSinhVien(int soVeSinhVien) {
        this.soVeSinhVien = soVeSinhVien;
    }

    public int getSoVeTap() {
        return soVeTap;
    }

    public void setSoVeTap(int soVeTap) {
        this.soVeTap = soVeTap;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final DoanhThu other = (DoanhThu) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("tuyen"))
            return tuyen;
        if(fieldName.equals("ngay"))
            return id.getNgay();
        if(fieldName.equals("soVeThuong"))
            return soVeThuong;
        if(fieldName.equals("soVeSinhVien"))
            return soVeSinhVien;
        if(fieldName.equals("soVeTap"))
            return soVeTap;
        if(fieldName.equals("FKtuyen"))
            return tuyen.getChiTietTuyen().getMaSo() + "";
        if(fieldName.equals("FKngay"))
            return id.getNgay().toString();
        return null;
    }

    @Override
    public String toString() {
        return "DoanhThu{" + "id=" + id + ", tuyen=" + tuyen.getChiTietTuyen().getMaSo() + ", ngay=" + ngay + ", soVeThuong=" + soVeThuong + ", soVeSinhVien=" + soVeSinhVien + ", soVeTap=" + soVeTap + '}';
    }

    
    
    @Override
    public Object getDisplayValue() {
        return id.getNgay().toString() + "-" + tuyen.getMaTuyen();
    }

    @Override
    public void setId(Object value) {
    }

    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("tuyen")){
            tuyen = (Tuyen)value;
            id.setMaTuyen(tuyen.getMaTuyen());
        }
        if(fieldName.equals("ngay")){
            ngay = (LocalDate)value;
            id.setNgay(ngay);
        }
        if(fieldName.equals("soVeThuong"))
            soVeThuong = Integer.parseInt((String)value);
        if(fieldName.equals("soVeSinhVien"))
            soVeSinhVien = Integer.parseInt((String)value);;
        if(fieldName.equals("soVeTap"))
            soVeTap = Integer.parseInt((String)value);;
        
    }    
}
