/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author dokha
 */
@Embeddable
public class DoanThuId implements Serializable {
    @Column(name = "Ngay")
    private LocalDate ngay;

    @Column(name = "MaTuyen")
    private int maTuyen;

    public DoanThuId() {
    }

    public DoanThuId(LocalDate ngay, int maTuyen) {
        this.ngay = ngay;
        this.maTuyen = maTuyen;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(int maTuyen) {
        this.maTuyen = maTuyen;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.ngay);
        hash = 47 * hash + this.maTuyen;
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
        final DoanThuId other = (DoanThuId) obj;
        if (this.maTuyen != other.maTuyen) {
            return false;
        }
        if (!Objects.equals(this.ngay, other.ngay)) {
            return false;
        }
        return true;
    }
}
