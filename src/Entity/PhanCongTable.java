/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author dokha
 */
public class PhanCongTable {
    private LocalTime thoiGian;
    private Xe xeA;
    private Xe xeB;
    private int chuyen;
    
    public PhanCongTable() {
    }

    public PhanCongTable(LocalTime thoiGian, Xe xeA, Xe xeB, int chuyen) {
        this.thoiGian = thoiGian;
        this.xeA = xeA;
        this.xeB = xeB;
        this.chuyen = chuyen;
    }

    public LocalTime getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(LocalTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Xe getXeA() {
        return xeA;
    }

    public void setXeA(Xe xeA) {
        this.xeA = xeA;
    }

    public Xe getXeB() {
        return xeB;
    }

    public void setXeB(Xe xeB) {
        this.xeB = xeB;
    }

    public int getChuyen() {
        return chuyen;
    }

    public void setChuyen(int chuyen) {
        this.chuyen = chuyen;
    }

}
