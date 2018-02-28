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
public class BangDiemModel {
    private String masinhvien;
    private String tensinhvien;
    private String tenmonhoc;
    private String mamonhoc;
    private String id_mon;
    private String xeploai;
    private float diemkiemtra_1;
    private float diemkiemtra_2;
    private float diemthicuoiky;
    private float diemtongket;
    
    
    public BangDiemModel() {
    }

    public BangDiemModel(String masinhvien,String tensinhvien,String tenmonhoc, String mamonhoc, float diemkiemtra_1, float diemkiemtra_2, float diemthicuoiky, float diemtongket, String xeploai, String id_mon) {
        this.masinhvien = masinhvien;
        this.tensinhvien = tensinhvien;
        this.tenmonhoc = tenmonhoc;
        this.mamonhoc = mamonhoc;
        this.diemkiemtra_1 = diemkiemtra_1;
        this.diemkiemtra_2 = diemkiemtra_2;
        this.diemthicuoiky = diemthicuoiky;
        this.diemtongket = diemtongket;
        this.xeploai = xeploai;
        this.id_mon = id_mon;
    }


    public String getMasinhvien() {
        return masinhvien;
    }

    public void setMasinhvien(String masinhvien) {
        this.masinhvien = masinhvien;
    }

    public String getMamonhoc() {
        return mamonhoc;
    }

    public void setMamonhoc(String mamonhoc) {
        this.mamonhoc = mamonhoc;
    }

    public float getDiemkiemtra_1() {
        return diemkiemtra_1;
    }

    public void setDiemkiemtra_1(float diemkiemtra_1) {
        this.diemkiemtra_1 = diemkiemtra_1;
    }

    public float getDiemkiemtra_2() {
        return diemkiemtra_2;
    }

    public void setDiemkiemtra_2(float diemkiemtra_2) {
        this.diemkiemtra_2 = diemkiemtra_2;
    }

    public float getDiemthicuoiky() {
        return diemthicuoiky;
    }

    public void setDiemthicuoiky(float diemthicuoiky) {
        this.diemthicuoiky = diemthicuoiky;
    }

    public float getDiemtongket() {
        return diemtongket;
    }

    public void setDiemtongket(float diemtongket) {
        this.diemtongket = diemtongket;
    }

    public String getTensinhvien() {
        return tensinhvien;
    }

    public void setTensinhvien(String tensinhvien) {
        this.tensinhvien = tensinhvien;
    }

    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }

    public String getXeploai() {
        return xeploai;
    }

    public void setXeploai(String xeploai) {
        this.xeploai = xeploai;
    }

    public String getId_mon() {
        return id_mon;
    }

    public void setId_mon(String id_mon) {
        this.id_mon = id_mon;
    }

    @Override
    public String toString() {
        return "BangDiemModel{" + "masinhvien=" + masinhvien + ", tensinhvien=" + tensinhvien + ", tenmonhoc=" + tenmonhoc + ", mamonhoc=" + mamonhoc + ", xeploai=" + xeploai + ", diemkiemtra_1=" + diemkiemtra_1 + ", diemkiemtra_2=" + diemkiemtra_2 + ", diemthicuoiky=" + diemthicuoiky + ", diemtongket=" + diemtongket + '}';
    }

    
}
