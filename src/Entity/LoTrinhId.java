/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 *
 * @author dokha
 */
@Embeddable
public class LoTrinhId implements Serializable {
    
    @Column(name = "MaTram")
    private int tram;

    @Column(name = "MaSo")
    private int chiTietTuyen;

    
    public LoTrinhId() {
    }

    public LoTrinhId(int tram, int chiTietTuyen) {
        this.tram = tram;
        this.chiTietTuyen = chiTietTuyen;
    }

    public int getTram() {
        return tram;
    }

    public void setTram(int tram) {
        this.tram = tram;
    }

    public int getChiTietTuyen() {
        return chiTietTuyen;
    }

    public void setChiTietTuyen(int chiTietTuyen) {
        this.chiTietTuyen = chiTietTuyen;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.tram;
        hash = 97 * hash + this.chiTietTuyen;
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
        final LoTrinhId other = (LoTrinhId) obj;
        if (this.tram != other.tram) {
            return false;
        }
        if (this.chiTietTuyen != other.chiTietTuyen) {
            return false;
        }
        return true;
    }
    
}
