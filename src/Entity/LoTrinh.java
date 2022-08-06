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
@Table(name = "LoTrinh")
public class LoTrinh implements Serializable {
    @EmbeddedId
    private LoTrinhId id = new LoTrinhId();
    
    @ManyToOne
    @MapsId("tram") //This is the name of attr in EmployerDeliveryAgentPK class
    @JoinColumn(name = "MaTram")
    private Tram tram;

    @ManyToOne
    @MapsId("chiTietTuyen")
    @JoinColumn(name = "MaSo")
    private ChiTietTuyen chiTietTuyen;

    @Column(name = "SoThuTu")
    private short soThuTu; 
    
    public LoTrinh() {
    }

    public LoTrinh(Tram tram, ChiTietTuyen chiTietTuyen, short soThuTu) {
        this.tram = tram;
        this.chiTietTuyen = chiTietTuyen;
        this.soThuTu = soThuTu;
    }
    
    public LoTrinhId getId() {
        return id;
    }

    public void setId(LoTrinhId id) {
        this.id = id;
    }

    public Tram getTram() {
        return tram;
    }

    public void setTram(Tram tram) {
        this.tram = tram;
    }

    public ChiTietTuyen getChiTietTuyen() {
        return chiTietTuyen;
    }

    public void setChiTietTuyen(ChiTietTuyen chiTietTuyen) {
        this.chiTietTuyen = chiTietTuyen;
    }

    public short getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(short soThuTu) {
        this.soThuTu = soThuTu;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final LoTrinh other = (LoTrinh) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LoTrinh{" + "id=" + id.toString() + ", tram=" + tram + ", chiTietTuyen=" + chiTietTuyen + '}';
    }
    
}
