/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Hibernate.DataGetter;
import JavaFX.CustomNode.Constraint;
import JavaFX.CustomNode.SearchField;
import JavaFX.CustomNode.TypeOfConstraint;
import JavaFX.Filter.Filters;
import JavaFX.InputField;
import JavaFX.SearchNodes;
import JavaFX.TableViewColumn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author dokha
 */
@Entity
@Table(name = "ChiTietTuyen")
public class ChiTietTuyen implements JavaFX.Entity{
    @Id 
    @Column(name = "MaSo")
    @TableViewColumn(columnName = "Mã Số")
    @InputField(fieldName = "Mã Số", filters = Filters.Number)
    @Constraint(idField = "maSo" , message = "đã mã số này", typeOfConstraint = TypeOfConstraint.UNIQUE)
    @SearchField(name = "Tên Đơn Vị", searchNodes = SearchNodes.NUMBER)
    private int maSo;
    
    @Column(name = "TenTuyen")
    @TableViewColumn(columnName = "Tên Tuyến")
    @InputField(fieldName = "Tên Tuyến", filters = Filters.Text)
    @SearchField(name = "Tên Đơn Vị", searchNodes = SearchNodes.TEXT)
    @Constraint
    private String tenTuyen;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "chiTietTuyen", fetch = FetchType.LAZY)
    private List<LoTrinh> tram = new ArrayList<LoTrinh>();
 
    
    
    public ChiTietTuyen() {
    }

    public ChiTietTuyen(int maSo, String tenTuyen) {
        this.maSo = maSo;
        this.tenTuyen = tenTuyen;
    }

    public int getMaSo() {
        return maSo;
    }

    public void setMaSo(int maSo) {
        this.maSo = maSo;
    }

    public String getTenTuyen() {
        return tenTuyen;
    }

    public void setTenTuyen(String tenTuyen) {
        this.tenTuyen = tenTuyen;
    }

    public List<LoTrinh> getTram() {
        return tram;
    }

    public void setTram(ArrayList<LoTrinh> tram) {
        this.tram = tram;
    }
    
    public void addTram(Tram tram, short soThuTu) {
        LoTrinh loTrinh = new LoTrinh(tram, this, soThuTu);
        DataGetter.addObject(loTrinh);
        this.tram.add(loTrinh);
        tram.getTuyen().add(loTrinh);
    }
 
    public void removeTram(Tram tram) {
        for(LoTrinh i : this.tram){
            if(i.getChiTietTuyen().equals(this) && i.getTram().equals(tram)){
               //i.getTram().getTuyen().remove(this);
               tram.removeTuyen(this);
               i.setChiTietTuyen(null);
               i.setTram(null);
               this.tram.remove(i);
               DataGetter.deleteObject(i);
               break;
            }
                
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.maSo;
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
        final ChiTietTuyen other = (ChiTietTuyen) obj;
        if (this.maSo != other.maSo) {
            return false;
        }
        return true;
    }
    

    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("maSo"))
            this.maSo = Integer.parseInt((String)value);
        if(fieldName.equals("tenTuyen"))
            this.tenTuyen = (String)value;
        
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("maSo"))
            return this.maSo;
        if(fieldName.equals("tenTuyen"))
            return this.tenTuyen;
        
        if(fieldName.equals("displayValue"))
            return maSo + "";
        return null;
    }

    @Override
    public Object getId() {
        return maSo;
    }

    @Override
    public Object getDisplayValue() {
        return maSo + "-" +tenTuyen;
    }

    @Override
    public void setId(Object value) {
        this.maSo = (Integer)value;
    }
 
     public double getDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
     
    public boolean canUseTram(Tram newT){
        if(tram.size() == 0)
            return true;
        Tram last = tram.get(tram.size()-1).getTram();
        if(getDistance(newT.getX(),newT.getY(),last.getX(),last.getY()) > 100)
            return false;
        else 
            return true;
    }
    public double getTotalDistance(){
        double totalDistance = 0;
        Tram t1;
        Tram t2;
        if(tram.size() < 2)
            return totalDistance;
        for(int i = 0; i < tram.size() -1; i++){
            t1 = tram.get(i).getTram();
            t2 = tram.get(i+1).getTram();
            totalDistance += getDistance(t1.getX(), t1.getY(), t2.getX(), t2.getY());
        }
        return totalDistance;
    }
}
