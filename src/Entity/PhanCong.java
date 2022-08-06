/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author dokha
 */
@Entity
@Table(name = "PhanCong")
public class PhanCong implements Serializable {
    @EmbeddedId
    private PhanCongId id = new PhanCongId();
    
    @ManyToOne
    @MapsId("maTuyen") //This is the name of attr in EmployerDeliveryAgentPK class
    @JoinColumn(name = "MaTuyen")
    private Tuyen tuyen;

    @ManyToOne
    @MapsId("maXe")
    @JoinColumn(name = "MaXe")
    private Xe xe;
    
    @Column(name = "chuyen")
    private short chuyen;
    
    @Column(name = "chieu")
    private boolean chieu;

    public PhanCong() {
    }

    public PhanCong(Tuyen tuyen, Xe xe, short chuyen, boolean chieu) {
        this.tuyen = tuyen;
        this.xe = xe;
        this.chuyen = chuyen;
        this.chieu = chieu;
    }

    public PhanCongId getId() {
        return id;
    }

    public void setId(PhanCongId id) {
        this.id = id;
    }

    public Tuyen getTuyen() {
        return tuyen;
    }

    public void setTuyen(Tuyen tuyen) {
        this.tuyen = tuyen;
    }

    public Xe getXe() {
        return xe;
    }

    public void setXe(Xe xe) {
        this.xe = xe;
    }

    public short getChuyen() {
        return chuyen;
    }

    public void setChuyen(short chuyen) {
        this.chuyen = chuyen;
    }

    public boolean isChieu() {
        return chieu;
    }

    public void setChieu(boolean chieu) {
        this.chieu = chieu;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final PhanCong other = (PhanCong) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public void swapPos(PhanCong other){
        short chuyen = other.getChuyen();
        boolean chieu = other.isChieu();
        other.setChieu(this.chieu);
        other.setChuyen(this.chuyen);
        this.chieu = chieu;
        this.chuyen = chuyen;
    }
}
