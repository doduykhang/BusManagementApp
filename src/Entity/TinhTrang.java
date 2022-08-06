/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
@Table(name = "TinhTrangXe")
public class TinhTrang implements TableFK,JavaFX.Entity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MaTinhTrang")
    private int maTinhTrang;
    
    @Column(name = "TenTinhTrang")
    private String tenTinhTrang;

    public TinhTrang() {
    }

    public TinhTrang(int maTinhTrang, String tenTinhTrang) {
        this.maTinhTrang = maTinhTrang;
        this.tenTinhTrang = tenTinhTrang;
    }

    public int getMaTinhTrang() {
        return maTinhTrang;
    }

    public void setMaTinhTrang(int maTinhTrang) {
        this.maTinhTrang = maTinhTrang;
    }

    public String getTenTinhTrang() {
        return tenTinhTrang;
    }

    public void setTenTinhTrang(String tenTinhTrang) {
        this.tenTinhTrang = tenTinhTrang;
    }

    @Override
    public String getFK(String fieldName) {
        return tenTinhTrang;
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
        final TinhTrang other = (TinhTrang) obj;
        if (this.maTinhTrang != other.maTinhTrang) {
            return false;
        }
        return true;
    }

    @Override
    public void setValue(String fieldName, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("displayValue"))
            return tenTinhTrang;
        return null;
    }

    @Override
    public Object getId() {
        return maTinhTrang;
    }

    @Override
    public Object getDisplayValue() {
        return tenTinhTrang;
    }

    @Override
    public void setId(Object value) {
        this.maTinhTrang = Integer.parseInt(value + "");
    }
    
}
