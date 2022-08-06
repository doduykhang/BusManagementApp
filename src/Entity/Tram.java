/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import JavaFX.CustomNode.Constraint;
import JavaFX.Filter.Filters;
import JavaFX.InputField;
import JavaFX.TableViewColumn;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author dokha
 */
@Entity
@Table(name = "Tram")
public class Tram implements JavaFX.Entity, Serializable{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MaTram")
    private int maTram;
    
    @Column(name = "TenTram")
    @TableViewColumn(columnName = "Tên Trạm")
    @InputField(fieldName = "Tên Trạm", filters = Filters.Text, limit = 20)
    @Constraint
    private String tenTram;
    
    @Column(name = "DiaChi")
    @TableViewColumn(columnName = "Địa Chỉ")
    @InputField(fieldName = "Địa Chỉ", filters = Filters.Text, limit = 100)
    @Constraint
    private String diaChi;
    
    @Column(name = "X")
    @InputField(fieldName = "")
    @Constraint
    private double x;
    
    @Column(name = "Y")
    @InputField(fieldName = "")
    @Constraint
    private double y;

    @OneToMany(mappedBy = "tram")
    private List<LoTrinh> tuyen = new ArrayList<LoTrinh>();
    
    public Tram() {
    }

    public Tram(int maTram, String tenTram, String diaChi, double x, double y) {
        this.maTram = maTram;
        this.tenTram = tenTram;
        this.diaChi = diaChi;
        this.x = x;
        this.y = y;
    }

    public int getMaTram() {
        return maTram;
    }

    public void setMaTram(int maTram) {
        this.maTram = maTram;
    }

    public String getTenTram() {
        return tenTram;
    }

    public void setTenTram(String tenTram) {
        this.tenTram = tenTram;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public List<LoTrinh> getTuyen() {
        return tuyen;
    }

    public void setTuyen(ArrayList<LoTrinh> tuyen) {
        this.tuyen = tuyen;
    }
    
    public void removeTuyen(ChiTietTuyen tuyen){
        for(int i = 0; i < this.tuyen.size(); i++){
            LoTrinh loTrinh = this.tuyen.get(i);
            if(loTrinh.getChiTietTuyen().equals(tuyen)){
                this.tuyen.remove(i);
                return;
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.maTram;
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
        final Tram other = (Tram) obj;
        if (this.maTram != other.maTram) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tram{" + "maTram=" + maTram + ", tenTram=" + tenTram + ", diaChi=" + diaChi + ", x=" + x + ", y=" + y + '}';
    }

    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("id"))
            maTram = Integer.parseInt((String)value);
        if(fieldName.equals("tenTram"))
            tenTram = (String)value;
        if(fieldName.equals("diaChi"))
            diaChi = (String)value;
        if(fieldName.equals("x"))
            x = Double.parseDouble((String)value);
        if(fieldName.equals("y"))
            y = Double.parseDouble((String)value);
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("id"))
            return maTram;
        if(fieldName.equals("tenTram"))
            return tenTram;
        if(fieldName.equals("diaChi"))
            return diaChi;
        if(fieldName.equals("x"))
            return x;
        if(fieldName.equals("y"))
            return y;
        return null;
    }

    @Override
    public Object getId() {
        return maTram;
    }

    @Override
    public Object getDisplayValue() {
        return tenTram;
    }

    @Override
    public void setId(Object value) {
        this.maTram = Integer.parseInt(value + "");
    }
}
