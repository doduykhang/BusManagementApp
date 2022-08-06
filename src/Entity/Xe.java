/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import JavaFX.CellValueFactorys;
import JavaFX.CustomNode.Constraint;
import JavaFX.CustomNode.SearchField;
import JavaFX.CustomNode.TypeOfConstraint;
import JavaFX.TableViewColumn;
import JavaFX.Filter.Filters;
import JavaFX.InputField;
import JavaFX.Nodes;
import JavaFX.SearchNodes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dokha
 */

@Entity
@Table(name = "Xe")
public class Xe implements JavaFX.Entity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MaXe")
    private int maXe;
    
    @Column(name = "BienSoXe", unique = true)
    @TableViewColumn(columnName = "Biển Số Xe")
    @InputField(fieldName = "Biển Số Xe", filters = Filters.BIENSOXE) 
    @Constraint(idField = "maXe" , message = "đã có biển số xe", typeOfConstraint = TypeOfConstraint.UNIQUE)
    @SearchField(name = "Biển Số Xe", searchNodes = SearchNodes.TEXT)
    private String bienSoXe;
    
    @Column(name = "HanBaoHanh")
    @TableViewColumn(columnName = "Hạn Bảo Hành")
    @InputField(fieldName = "Hạn Bảo Hành",nodes = Nodes.DATEPICKER)
    @Constraint
    @SearchField(name = "Hạn Bảo Hành", searchNodes = SearchNodes.DATE)
    private LocalDate hanBaoHanh;
    
    @ManyToOne
    @JoinColumn(name = "MaNhaSanXuat")
    @TableViewColumn(columnName = "Nhà Sản Xuất", cellValueFactorys = CellValueFactorys.FK)
    @InputField(fieldName = "Nhà Sản Xuất", nodes = Nodes.COMBOBOX)
    @Constraint
    @SearchField(name = "Nhà Sản Xuất", searchNodes = SearchNodes.COMBOBOX)
    private NhaSanXuat nhaSanXuat;
    
    @ManyToOne
    @JoinColumn(name = "MaLoaiXe")
    @TableViewColumn(columnName = "Loại Xe", cellValueFactorys = CellValueFactorys.FK)
    @InputField(fieldName = "Loại Xe", nodes = Nodes.COMBOBOX)
    @Constraint
    @SearchField(name = "Loại Xe", searchNodes = SearchNodes.COMBOBOX)
    private LoaiXe loaiXe;

    @OneToMany(mappedBy = "xe")
    private List<PhanCong> tuyen = new ArrayList<PhanCong>();
    
    public Xe() {
    }

    public Xe(int maXe, String bienSoXe, LocalDate hanBaoHanh, NhaSanXuat nhaSanXuat, LoaiXe loaiXe) {
        this.maXe = maXe;
        this.bienSoXe = bienSoXe;
        this.hanBaoHanh = hanBaoHanh;
        this.nhaSanXuat = nhaSanXuat;
        this.loaiXe = loaiXe;
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public LocalDate getHanBaoHanh() {
        return hanBaoHanh;
    }

    public void setHanBaoHanh(LocalDate hanBaoHanh) {
        this.hanBaoHanh = hanBaoHanh;
    }

    public NhaSanXuat getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public LoaiXe getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(LoaiXe loaiXe) {
        this.loaiXe = loaiXe;
    }

    public List<PhanCong> getTuyen() {
        return tuyen;
    }

    public void setTuyen(List<PhanCong> tuyen) {
        this.tuyen = tuyen;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.maXe;
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
        final Xe other = (Xe) obj;
        if (this.maXe != other.maXe) {
            return false;
        }
        return true;
    }
    
    public void removeTuyen(Tuyen tuyen){
        for(int i = 0; i < this.tuyen.size(); i++){
            PhanCong phanCong = this.tuyen.get(i);
            if(phanCong.getTuyen().equals(tuyen)){
                this.tuyen.remove(i);
                return;
            }
        }
    }
    
//    public void swapPos(Xe other){
//        short chuyen = other.getChuyen();
//        boolean chieu = other.isChieu();
//        Tuyen tuyen = other.getTuyen();
//        other.setChieu(this.isChieu());
//        other.setChuyen(this.getChuyen());
//        other.setTuyen(this.getTuyen());
//        setChieu(chieu);
//        setChuyen(chuyen);
//        setTuyen(tuyen);
//    }
    
    public boolean checkOverlapTime(Tuyen tuyen){
        LocalDate s1;
        LocalDate e1;
        LocalDate s2;
        LocalDate e2;
        for(PhanCong phanCong : this.tuyen){
            s1 = tuyen.getNgayBatDau();
            e1 = tuyen.getNgayKetThuc();
            s2 = phanCong.getTuyen().getNgayBatDau();
            e2 = phanCong.getTuyen().getNgayKetThuc();
            if(s1.isBefore(e2) && e1.isAfter(s2)){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Xe{" + "maXe=" + maXe + ", bienSoXe=" + bienSoXe + ", hanBaoHanh=" + hanBaoHanh + ", nhaSanXuat=" + nhaSanXuat.getTenNhaSanXuat() + ", loaiXe=" + loaiXe.getMaLoaiXe() + '}';
    }

    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("bienSoXe"))
            bienSoXe = (String)value;
        if(fieldName.equals("hanBaoHanh"))
            hanBaoHanh = (LocalDate)value;
        if(fieldName.equals("nhaSanXuat"))
            nhaSanXuat = (NhaSanXuat)value;
        if(fieldName.equals("loaiXe"))
            loaiXe = (LoaiXe)value;
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("id"))
            return maXe;
        if(fieldName.equals("bienSoXe"))
            return bienSoXe;
        if(fieldName.equals("hanBaoHanh"))
             return hanBaoHanh;
        if(fieldName.equals("nhaSanXuat"))
            return nhaSanXuat;
        if(fieldName.equals("loaiXe"))
            return loaiXe;
        
        if(fieldName.equals("FKnhaSanXuat"))
            return nhaSanXuat.getTenNhaSanXuat();
        if(fieldName.equals("FKloaiXe"))
            return loaiXe.getTenLoaiXe();
        
        return null;
    }

    @Override
    public Object getId() {
        return maXe;
    }

    @Override
    public Object getDisplayValue() {
        return bienSoXe;
    }

    @Override
    public void setId(Object value) {
        this.maXe = (Integer)value;
    }
}
