/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dokha
 */
@Embeddable
public class PhanCongId implements Serializable {
    
    @Column(name = "MaTuyen")
    private int maTuyen;

    @Column(name = "MaXe")
    private int maXe;

    public PhanCongId() {
    }

    public PhanCongId(int maTuyen, int maXe) {
        this.maTuyen = maTuyen;
        this.maXe = maXe;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(int maTuyen) {
        this.maTuyen = maTuyen;
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.maTuyen;
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
        final PhanCongId other = (PhanCongId) obj;
        if (this.maTuyen != other.maTuyen) {
            return false;
        }
        return true;
    }

}
