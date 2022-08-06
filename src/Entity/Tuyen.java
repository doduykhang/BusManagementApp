/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Hibernate.DataGetter;
import JavaFX.CellValueFactorys;
import JavaFX.CustomNode.Constraint;
import JavaFX.CustomNode.SearchField;
import JavaFX.InputField;
import JavaFX.Nodes;
import JavaFX.SearchNodes;
import JavaFX.TableViewColumn;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author dokha
 */
@Entity
@Table(name = "Tuyen")
public class Tuyen implements JavaFX.Entity{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MaTuyen")
    private int maTuyen;
    
    @ManyToOne
    @JoinColumn(name = "MaSo")
    @TableViewColumn(columnName = "Mã Số", cellValueFactorys = CellValueFactorys.FK)
    @InputField(fieldName = "Mã Số", nodes = Nodes.COMBOBOX)
    @Constraint
    @SearchField(name = "Mã Số", searchNodes = SearchNodes.COMBOBOX)
    private ChiTietTuyen chiTietTuyen;
    
    @ManyToOne
    @JoinColumn(name = "MaLoaituyen")
    @TableViewColumn(columnName = "Loại Tuyến", cellValueFactorys = CellValueFactorys.FK)
    @InputField(fieldName = "Loại Tuyến", nodes = Nodes.COMBOBOX)
    @Constraint
    @SearchField(name = "Loại Tuyến", searchNodes = SearchNodes.COMBOBOX)
    private LoaiTuyen loaiTuyen;
    
    @ManyToOne
    @JoinColumn(name = "MaDonViQuanLy")
    @TableViewColumn(columnName = "Đơn Vị Quản Lý", cellValueFactorys = CellValueFactorys.FK)
    @InputField(fieldName = "Đơn Vị Quản Lý", nodes = Nodes.COMBOBOX)
    @Constraint
    @SearchField(name = "Đơn Vị Quản Lý", searchNodes = SearchNodes.COMBOBOX)
    private DonViQuanLy donViQuanLy;

    @ManyToOne
    @JoinColumn(name = "MaBieuDoGio")
//    @TableViewColumn(columnName = "Biểu Đồ Giờ", cellValueFactorys = CellValueFactorys.FK)
    @InputField(fieldName = "Biểu Đồ Giờ", nodes = Nodes.COMBOBOX)
    @Constraint
    @SearchField(name = "Biểu Đồ Giờ", searchNodes = SearchNodes.COMBOBOX)
    private BieuDoGio bieuDoGio;
    
    @TableViewColumn(columnName = "Thời Gian Bắt ĐẦu", cellValueFactorys = CellValueFactorys.FK)
    @SearchField(name = "Thời Gian Bắt ĐẦu", searchNodes = SearchNodes.TIME, embeded = "bieuDoGio")
    @Transient 
    private LocalTime thoiGianBatDau;
    
    @TableViewColumn(columnName = "Thời Gian Kết Thúc", cellValueFactorys = CellValueFactorys.FK)
    @SearchField(name = "Thời Gian Kết Thúc", searchNodes = SearchNodes.TIME, embeded = "bieuDoGio")
    @Transient 
    private LocalTime thoiGianKetThuc;
    
    @TableViewColumn(columnName = "Giãn Cách Chuyến", cellValueFactorys = CellValueFactorys.FK)
    @SearchField(name = "Giãn Cách Chuyến", searchNodes = SearchNodes.NUMBER, embeded = "bieuDoGio")
    @Transient 
    private short gianCachChuyen;
    
    @TableViewColumn(columnName = "Thời Gian Chuyến", cellValueFactorys = CellValueFactorys.FK)
    @SearchField(name = "Thời Gian Chuyến", searchNodes = SearchNodes.NUMBER, embeded = "bieuDoGio")
    @Transient 
    private short thoiGianChuyen;
    
    @Column(name = "NgayBatDauHoatDong")
    @TableViewColumn(columnName = "Ngày Bắt Đầu")
    @InputField(fieldName = "Ngày Bắt Đầu", nodes = Nodes.DATEPICKER)
    @Constraint
    @SearchField(name = "Ngày Bắt Đầu", searchNodes = SearchNodes.DATE)
    private LocalDate ngayBatDau;
    
    @Column(name = "NgayNgungHoatDong")
    @TableViewColumn(columnName = "Ngày Kết Thúc")
    @InputField(fieldName = "Ngày Kết Thúc", nodes = Nodes.DATEPICKER)
    @Constraint
    @SearchField(name = "Ngày Kết Thúc", searchNodes = SearchNodes.DATE)
    private LocalDate ngayKetThuc;
    
    @OneToMany(mappedBy = "tuyen")
    private List<PhanCong> xe = new ArrayList<PhanCong>();
    
    public Tuyen() {
    }

    public Tuyen(int maTuyen, ChiTietTuyen chiTietTuyen, LoaiTuyen loaiTuyen, DonViQuanLy donViQuanLy, BieuDoGio bieuDoGio, LocalTime thoiGianBatDau, LocalTime thoiGianKetThuc, short gianCachChuyen, short thoiGianChuyen, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        this.maTuyen = maTuyen;
        this.chiTietTuyen = chiTietTuyen;
        this.loaiTuyen = loaiTuyen;
        this.donViQuanLy = donViQuanLy;
        this.bieuDoGio = bieuDoGio;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.gianCachChuyen = gianCachChuyen;
        this.thoiGianChuyen = thoiGianChuyen;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(int maTuyen) {
        this.maTuyen = maTuyen;
    }

    public ChiTietTuyen getChiTietTuyen() {
        return chiTietTuyen;
    }

    public void setChiTietTuyen(ChiTietTuyen chiTietTuyen) {
        this.chiTietTuyen = chiTietTuyen;
    }

    public LoaiTuyen getLoaiTuyen() {
        return loaiTuyen;
    }

    public void setLoaiTuyen(LoaiTuyen loaiTuyen) {
        this.loaiTuyen = loaiTuyen;
    }

    public DonViQuanLy getDonViQuanLy() {
        return donViQuanLy;
    }

    public void setDonViQuanLy(DonViQuanLy donViQuanLy) {
        this.donViQuanLy = donViQuanLy;
    }

    public BieuDoGio getBieuDoGio() {
        return bieuDoGio;
    }

    public void setBieuDoGio(BieuDoGio bieuDoGio) {
        this.bieuDoGio = bieuDoGio;
    }

    public LocalTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public short getGianCachChuyen() {
        return gianCachChuyen;
    }

    public void setGianCachChuyen(short gianCachChuyen) {
        this.gianCachChuyen = gianCachChuyen;
    }

    public short getThoiGianChuyen() {
        return thoiGianChuyen;
    }

    public void setThoiGianChuyen(short thoiGianChuyen) {
        this.thoiGianChuyen = thoiGianChuyen;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public List<PhanCong> getXe() {
        return xe;
    }

    public void setXe(List<PhanCong> xe) {
        this.xe = xe;
    }

    public void addXe(Xe newXe, short chuyen, boolean chieu){
        for(PhanCong phanCong : xe){
            if(phanCong.getXe().equals(newXe)){
                phanCong.setChieu(chieu);
                phanCong.setChuyen(chuyen);
                DataGetter.updateObject(phanCong);
                return;
            }
        }
        PhanCong phanCong = new PhanCong(this, newXe, chuyen, chieu);
        DataGetter.addObject(phanCong);
        this.getXe().add(phanCong);
        newXe.getTuyen().add(phanCong);
    }
    
    public void removeXe(Xe xe){
        int n = -1;
        for(int i = 0; i < this.xe.size(); i++){
            if(this.xe.get(i).getXe().equals(xe)){
                n = i;
            }
        }
        if(n != -1){
            DataGetter.deleteObject(this.xe.get(n));
            this.xe.remove(n);
        }
//        for(PhanCong i : this.xe){
//            if(i.getXe().equals(xe)){
//               i.getXe().getTuyen().remove(this);
//               xe.removeTuyen(this);
//               i.setTuyen(null);
//               i.setXe(null);
//               this.xe.remove(i);
//               DataGetter.deleteObject(i);
//               break;
//            }
//                
//        }
    }
    
    public void swapXe(Xe xeA, Xe xeB){
        PhanCong phanCongA = null;
        PhanCong phanCongB = null;
        for(PhanCong phanCong : this.xe){
            if(phanCong.getXe().equals(xeA))
                phanCongA = phanCong;
            else if(phanCong.getXe().equals(xeB))
                phanCongB = phanCong;
        }
        if(phanCongA != null && phanCongB != null){
            phanCongA.swapPos(phanCongB);
            DataGetter.swapXe(xeA, xeB, this);
        }
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
        final Tuyen other = (Tuyen) obj;
        if (this.maTuyen != other.maTuyen) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Tuyen{" + "maTuyen=" + maTuyen + ", chiTietTuyen=" + chiTietTuyen.getMaSo() + ", loaiTuyen=" + loaiTuyen.getMaLoaiTuyen() + ", donViQuanLy=" + donViQuanLy.getTenDonVi() + '}';
    }

    @Override
    public void setValue(String fieldName, Object value) {
        if(fieldName.equals("id"))
            maTuyen = Integer.parseInt((String)value);
        if(fieldName.equals("chiTietTuyen"))
            chiTietTuyen = (ChiTietTuyen)value;
        if(fieldName.equals("loaiTuyen"))
            loaiTuyen = (LoaiTuyen)value;
        if(fieldName.equals("donViQuanLy"))
            donViQuanLy = (DonViQuanLy)value;
        if(fieldName.equals("ngayBatDau"))
            ngayBatDau = (LocalDate) value;
        if(fieldName.equals("ngayKetThuc"))
            ngayKetThuc = (LocalDate) value;
        if(fieldName.equals("bieuDoGio"))
            bieuDoGio = (BieuDoGio)value;
        
    }

    @Override
    public Object getValue(String fieldName) {
        if(fieldName.equals("id"))
            return maTuyen;
        if(fieldName.equals("chiTietTuyen"))
            return chiTietTuyen;
        if(fieldName.equals("loaiTuyen"))
            return loaiTuyen;
        if(fieldName.equals("donViQuanLy"))
            return donViQuanLy;
        if(fieldName.equals("bieuDoGio"))
            return bieuDoGio;
        if(fieldName.equals("ngayBatDau"))
            return ngayBatDau;
        if(fieldName.equals("ngayKetThuc"))
            return ngayKetThuc;
        
        if(fieldName.equals("FKchiTietTuyen"))
            return chiTietTuyen.getMaSo() + "";
        if(fieldName.equals("FKloaiTuyen"))
            return loaiTuyen.getTenLoaiTuyen();
        if(fieldName.equals("FKdonViQuanLy"))
            return donViQuanLy.getTenDonVi();
        if(fieldName.equals("FKthoiGianBatDau"))
            return bieuDoGio.getThoiGianBatDau().toString();
        if(fieldName.equals("FKthoiGianKetThuc"))
            return bieuDoGio.getThoiGianKetThuc().toString();
        if(fieldName.equals("FKthoiGianChuyen"))
            return bieuDoGio.getThoiGianChuyen() + "";
        if(fieldName.equals("FKgianCachChuyen"))
            return bieuDoGio.getGianCachChuyen() + "";
        return null;
    }

    @Override
    public Object getId() {
        return maTuyen;
    }

    @Override
    public Object getDisplayValue() {
        return chiTietTuyen.getMaSo() + "/" + ngayBatDau.toString() + "/" + ngayKetThuc.toString();
    }

    @Override
    public void setId(Object value) {
        this.maTuyen = (Integer)value;
    }
}
