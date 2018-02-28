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
public class MonHocModel {
    private String  mamonhoc;
    private String tenmonhoc;
    private String id_mon;

    public MonHocModel() {
    }
    public MonHocModel(String mamonhoc, String tenmonhoc) {
        this.mamonhoc = mamonhoc;
        this.tenmonhoc = tenmonhoc;
        
    }

    public MonHocModel(String mamonhoc, String tenmonhoc, String id_mon) {
        this.mamonhoc = mamonhoc;
        this.tenmonhoc = tenmonhoc;
        this.id_mon = id_mon;
        
    }

    public String getId_mon() {
        return id_mon;
    }

    public void setId_mon(String id_mon) {
        this.id_mon = id_mon;
    }

    public String getMamonhoc() {
        return mamonhoc;
    }

    public void setMamonhoc(String mamonhoc) {
        this.mamonhoc = mamonhoc;
    }

    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }

    @Override
    public String toString() {
        return "MonHocModel{" + "mamonhoc=" + mamonhoc + ", tenmonhoc=" + tenmonhoc + ", id_mon=" + id_mon + '}';
    }

}
