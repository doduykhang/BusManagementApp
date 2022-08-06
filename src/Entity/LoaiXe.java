/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import JavaFX.BooleanColumn;
import JavaFX.TableViewColumn;
import JavaFX.CellValueFactorys;
import JavaFX.CustomNode.Constraint;
import JavaFX.CustomNode.SearchField;
import JavaFX.Filter.Filters;
import JavaFX.InputField;
import JavaFX.Nodes;
import JavaFX.SearchNodes;
import JavaFX.TableFK;
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
@Table(name = "LoaiXe")
public class LoaiXe implements BooleanColumn,JavaFX.Entity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MaLoaiXe")
    private int maLoaiXe;
    
    @Column(name = "TenLoaiXe")
    @TableViewColumn(columnName = "Tên Loại Xe")
    @InputField(fieldName = "Tên Loại Xe", filters = Filters.Text, limit = 50)
    @Constraint
    @SearchField(name = "Tên Loại Xe", searchNodes = SearchNodes.TEXT)
    private String tenLoaiXe;
    
    @Column(name = "SoChoDung")
    @InputField(fieldName = "Số Chỗ Đứng", filters = Filters.Number, limit = 255)
    @Constraint
    @TableViewColumn(columnName = "Số chỗ đứng")
    @SearchField(name = "Số chỗ đứng", searchNodes = SearchNodes.NUMBER)
    private short soChoDung;
    
    @Column(name = "SoChoNgoi")
    @InputField(fieldName = "Số Chỗ Ngồi", filters = Filters.Number, limit = 255)
    @Constraint
    @TableViewColumn(columnName = "Số chỗ ngồi")
    @SearchField(name = "Số chỗ ngồi", searchNodes = SearchNodes.NUMBER)
    private short soChoNgoi;
    
    @Column(name = "MayBanVe")
    @InputField(fieldName = "Máy Bán Vé", nodes = Nodes.RADIOBUTTON, trueRadio = "có", falseRadio = "không có")
    @Constraint
    @TableViewColumn(columnName = "Máy bán vé", cellValueFactorys = CellValueFactorys.BOOLEAN)
    @SearchField(name = "Máy bán vé", searchNodes = SearchNodes.CUSTOMRADIO)
    private boolean mayBanVe;
    
    @Column(name = "KhoangCachDiChuyen")
    @InputField(fieldName = "Khoảng Cách Di Chuyển", filters = Filters.Number, limit = Short.MAX_VALUE)
    @Constraint
    @TableViewColumn(columnName = "Khoảng Cách Di Chuyển")
    @SearchField(name = "Khoảng Cách Di Chuyển", searchNodes = SearchNodes.NUMBER)
    private short khoangCachDiChuyen;
    
    @Column(name = "TocDoToiDa")
    @InputField(fieldName = "Tốc Độ Di Chuyển",  filters = Filters.Number, limit = Short.MAX_VALUE)
    @Constraint
    @TableViewColumn(columnName = "Tốc độ tối đa")
    @SearchField(name = "Tốc độ tối đa", searchNodes = SearchNodes.NUMBER)
    private short tocDoToiDa;

    public LoaiXe() {
    }

    public LoaiXe(int maLoaiXe, String tenLoaiXe, short soChoDung, short soChoNgoi, boolean mayBanVe, short khoangCachDiChuyen, short tocDoToiDa) {
        this.maLoaiXe = maLoaiXe;
        this.tenLoaiXe = tenLoaiXe;
        this.soChoDung = soChoDung;
        this.soChoNgoi = soChoNgoi;
        this.mayBanVe = mayBanVe;
        this.khoangCachDiChuyen = khoangCachDiChuyen;
        this.tocDoToiDa = tocDoToiDa;
    }

    public int getMaLoaiXe() {
        return maLoaiXe;
    }

    public void setMaLoaiXe(int maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }

    public String getTenLoaiXe() {
        return tenLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
    }
    
    public short getSoChoDung() {
        return soChoDung;
    }

    public void setSoChoDung(short soChoDung) {
        this.soChoDung = soChoDung;
    }

    public short getSoChoNgoi() {
        return soChoNgoi;
    }

    public void setSoChoNgoi(short soChoNgoi) {
        this.soChoNgoi = soChoNgoi;
    }

    public boolean isMayBanVe() {
        return mayBanVe;
    }

    public void setMayBanVe(boolean mayBanVe) {
        this.mayBanVe = mayBanVe;
    }

    public short getKhoangCachDiChuyen() {
        return khoangCachDiChuyen;
    }

    public void setKhoangCachDiChuyen(short khoangCachDiChuyen) {
        this.khoangCachDiChuyen = khoangCachDiChuyen;
    }

    public short getTocDoToiDa() {
        return tocDoToiDa;
    }

    public void setTocDoToiDa(short TocDoToiDa) {
        this.tocDoToiDa = TocDoToiDa;
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
        final LoaiXe other = (LoaiXe) obj;
        if (this.maLoaiXe != other.maLoaiXe) {
            return false;
        }
        return true;
    }

    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("tenLoaiXe"))
            tenLoaiXe = (String)value;
        if(fieldName.equals("soChoDung"))
            soChoDung = Short.parseShort((String)value);
        if(fieldName.equals("soChoNgoi"))
            soChoNgoi =  Short.parseShort((String)value);
        if(fieldName.equals("mayBanVe"))
            mayBanVe =  (Boolean)value;
        if(fieldName.equals("khoangCachDiChuyen"))
            khoangCachDiChuyen =  Short.parseShort((String)value);
        if(fieldName.equals("tocDoToiDa"))
            tocDoToiDa =  Short.parseShort((String)value);
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("tenLoaiXe"))
            return tenLoaiXe;
        if(fieldName.equals("soChoDung"))
            return soChoDung;
        if(fieldName.equals("soChoNgoi"))
            return soChoNgoi;
        if(fieldName.equals("mayBanVe"))
            return mayBanVe;
        if(fieldName.equals("khoangCachDiChuyen"))
            return khoangCachDiChuyen;
        if(fieldName.equals("tocDoToiDa"))
            return tocDoToiDa;
        return null;
    }
    
    @Override
    public String boolColumnValue(String field) {
        if(field.equals("mayBanVe"))
            return mayBanVe ? "có" : "không có";
        return "";
    }

    @Override
    public String toString() {
        return "LoaiXe{" + "maLoaiXe=" + maLoaiXe + ", soChoDung=" + soChoDung + ", soChoNgoi=" + soChoNgoi + ", mayBanVe=" + mayBanVe + ", khoangCachDiChuyen=" + khoangCachDiChuyen + ", tocDoToiDa=" + tocDoToiDa + '}';
    }

    @Override
    public Object getId() {
        return maLoaiXe;
    }

    @Override
    public Object getDisplayValue() {
        return tenLoaiXe;
    }

    @Override
    public void setId(Object value) {
        this.maLoaiXe = Integer.parseInt(value + "");
    }
}

