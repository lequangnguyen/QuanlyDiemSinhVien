/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author lenovo g4070
 */
public class LopHocModel {
    public String MaLop;
    public String TenLop;
    public String MaKhoa;

    public LopHocModel() {
    }

    public LopHocModel(String MaLop, String TenLop, String MaKhoa) {
        this.MaLop = MaLop;
        this.TenLop = TenLop;
        this.MaKhoa = MaKhoa;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String TenLop) {
        this.TenLop = TenLop;
    }

    public String getMaKhoa() {
        return MaKhoa;
    }

    public void setMaKhoa(String MaKhoa) {
        this.MaKhoa = MaKhoa;
    }

    @Override
    public String toString() {
        return "LopHocModel{" + "MaLop=" + MaLop + ", TenLop=" + TenLop + ", MaKhoa=" + MaKhoa + '}';
    }
    
    
    
}
