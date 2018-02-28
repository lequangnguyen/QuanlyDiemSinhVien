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
public class SinhVienModel {
    public String MaSinhVien;
    public String TenSinhVien;
    public String MaLop;

    public SinhVienModel() {
    }

    public SinhVienModel(String MaSinhVien, String TenSinhVien, String MaLop) {
        this.MaSinhVien = MaSinhVien;
        this.TenSinhVien = TenSinhVien;
        this.MaLop = MaLop;
    }

    public String getMaSinhVien() {
        return MaSinhVien;
    }

    public void setMaSinhVien(String MaSinhVien) {
        this.MaSinhVien = MaSinhVien;
    }

    public String getTenSinhVien() {
        return TenSinhVien;
    }

    public void setTenSinhVien(String TenSinhVien) {
        this.TenSinhVien = TenSinhVien;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    @Override
    public String toString() {
        return "SinhVienModel{" + "MaSinhVien=" + MaSinhVien + ", TenSinhVien=" + TenSinhVien + ", MaLop=" + MaLop + '}';
    }
    
}
