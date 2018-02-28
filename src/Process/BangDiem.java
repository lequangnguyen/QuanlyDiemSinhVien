/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Model.BangDiemModel;
import Model.MonHocModel;
import static Utility.MyConnect.closeAll;
import static Utility.MyConnect.getConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo g4070
 */
public class BangDiem {
    public ArrayList<BangDiemModel> SearchBangDiem(String MaSinhVienSearch ,String TenSinhVienSearch, String TenMonHocSearch ,String MaMonHocSearch, String XepLoai) throws ClassNotFoundException, SQLException{
        ArrayList<BangDiemModel> list = new ArrayList<>();
        Connection con = getConnect();
        PreparedStatement stm = null;
        ResultSet rs= null;
        String sqlquery = "";
        sqlquery = "select * from bangdiem join sinhvien on bangdiem.masinhvien = sinhvien.masinhvien join monhoc on bangdiem.mamonhoc = monhoc.mamonhoc where 1=1";
        if(!TenSinhVienSearch.equals("")){
            sqlquery += " and sinhvien.tensinhvien like "+"'%"+TenSinhVienSearch+"%'";
        }
        if(!MaSinhVienSearch.equals("")){
            sqlquery += " and sinhvien.masinhvien like "+"'%"+MaSinhVienSearch+"%'";
        }
        if(!TenMonHocSearch.equals("")){
            sqlquery += " and monhoc.tenmonhoc like "+"'%"+TenMonHocSearch+"%'";
        }
        if(!MaMonHocSearch.equals("")){
            sqlquery += " and monhoc.id_mon like "+"'%"+MaMonHocSearch+"%'";
        }
        if(XepLoai.equals("Tất cả")){
            
        }
        if(XepLoai.equals("Distinction")){
            sqlquery+= " and diemtongket >= 8";
        }
        if(XepLoai.equals("Credit")){
            sqlquery+= "  and diemtongket >= 6 and diemtongket <8";
        }
        if(XepLoai.equals("Pass")){
            sqlquery+= "  and diemtongket >= 4 and diemtongket <6";
        }
        if(XepLoai.equals("Fail")){
            sqlquery+= "  and diemtongket < 4";
        }
        try {
             System.err.println(sqlquery);
             stm = con.prepareStatement(sqlquery); 
             rs = stm.executeQuery();
             while(rs.next()){
             String masinhvien = rs.getString("masinhvien");
             String tensinhvien = rs.getString("tensinhvien");
             String mamonhoc = rs.getString("mamonhoc");
             String id_mon = rs.getString("id_mon");
             String tenmonhoc = rs.getString("tenmonhoc");
             String xeploai = rs.getString("xeploai");
             float diemkiemtra1 = rs.getFloat("diemkiemtra1");
             float diemkiemtra2 = rs.getFloat("diemkiemtra2");
             float diemthi = rs.getFloat("diemthi");
             float diemtongket = rs.getFloat("diemtongket");
             BangDiemModel BangDiemModel = new BangDiemModel(masinhvien,tensinhvien,tenmonhoc,mamonhoc,diemkiemtra1,diemkiemtra2,diemthi,diemtongket,xeploai,id_mon);
             list.add(BangDiemModel);
             }
             
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            closeAll(con, stm, rs);
        }
        return list;
    }
    public BangDiemModel GetBangDiemById (String MaSinhVien, String MaMonHoc) throws ClassNotFoundException, SQLException{
        BangDiemModel Bangdiemget = new BangDiemModel();
        ResultSet rs= null;
        Connection con = getConnect();
        boolean kq = false;
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("select * from bangdiem join sinhvien on bangdiem.masinhvien = sinhvien.masinhvien join monhoc on bangdiem.mamonhoc = monhoc.mamonhoc where bangdiem.masinhvien = ? and bangdiem.mamonhoc = ? limit 1");
            stm.setString(1, MaSinhVien);
            stm.setString(2, MaMonHoc);
            System.out.println(stm);
            rs = stm.executeQuery();
            //kq = stm.executeUpdate() >0;
            while(rs.next()){
                Bangdiemget.setMasinhvien(rs.getString("masinhvien"));
                Bangdiemget.setTensinhvien(rs.getString("tensinhvien"));
                Bangdiemget.setMamonhoc(rs.getString("mamonhoc"));
                Bangdiemget.setId_mon(rs.getString("id_mon"));
                Bangdiemget.setTenmonhoc(rs.getString("tenmonhoc"));
                Bangdiemget.setDiemkiemtra_1(rs.getFloat("diemkiemtra1"));
                Bangdiemget.setDiemkiemtra_2(rs.getFloat("diemkiemtra2"));
                Bangdiemget.setDiemthicuoiky(rs.getFloat("diemthi"));
                Bangdiemget.setDiemtongket(rs.getFloat("diemtongket"));
                Bangdiemget.setXeploai(rs.getString("xeploai"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            closeAll(con, stm, null);
        }
       return Bangdiemget;
    }
    public void EditBangDiem(BangDiemModel model) throws ClassNotFoundException, SQLException{       
        Connection con = getConnect();
        boolean kq = false;
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("update bangdiem set diemkiemtra1 = ?, diemkiemtra2 = ?, diemthi = ?, diemtongket = ?, xeploai = ?  where masinhvien = ? and mamonhoc = ?");
            if(model.getDiemkiemtra_1() == -1f){
                 stm.setObject(1, null);
            }else{
                stm.setFloat(1, model.getDiemkiemtra_1());
            }
            if(model.getDiemkiemtra_2()==-1f){
                stm.setObject(2, null);
            }else{
                stm.setFloat(2, model.getDiemkiemtra_2());
            }
            if(model.getDiemthicuoiky()==-1f){
                stm.setObject(3, null);
            }else{
                stm.setFloat(3, model.getDiemthicuoiky());
            }
            if(model.getDiemtongket()==-1f){
                stm.setObject(4, null);
            }else{
                stm.setFloat(4, model.getDiemtongket());
            }
            stm.setString(5, model.getXeploai());
            stm.setString(6, model.getMasinhvien());
            stm.setString(7, model.getMamonhoc());
            System.out.println(stm);
            kq = stm.executeUpdate() >0;
            if (kq){
                System.out.println("Updated Succeed !");
                //JOptionPane.showMessageDialog(null, "Cập nhật bảng điểm thành công !", "Succeed Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                            "Cập nhật không thành công !", "Error Massage",
                            JOptionPane.ERROR_MESSAGE);
        }finally{
            closeAll(con, stm, null);
        }
        
        //model.setMasinhvien("");
    }
    public void AddBangDiem (BangDiemModel model) throws ClassNotFoundException, SQLException{
        BangDiemModel BangdiemAdd = new BangDiemModel();
        
        Connection con = getConnect();
        boolean kq = false;
        ResultSet rs= null;
        boolean checkadd = false;
        PreparedStatement stm = null;
        
        //Check kiểm tra tồn tại mã môn
        try{
            stm = con.prepareStatement("select * from bangdiem where masinhvien = ? and mamonhoc = ?");
        stm.setString(1,model.getMasinhvien());
        stm.setString(2,model.getMamonhoc());
        rs = stm.executeQuery();
            while(rs.next()){
                checkadd = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            closeAll(con, stm, null);
        }
        if (checkadd){
             JOptionPane.showMessageDialog(null,
                            "Điểm Môn học đã tồn tại !", "Succeed Message",
                            JOptionPane.ERROR_MESSAGE);
        }else{
        con = getConnect();
            //insert bảng điểm
        try {
            stm = con.prepareStatement("insert into bangdiem values (?,?,?,?,?,?,?)");
            stm.setString(1,model.getMasinhvien());
            stm.setString(2,model.getMamonhoc());
            
            if(model.getDiemkiemtra_1() == -1f){
                 stm.setObject(3, null);
            }else{
                stm.setFloat(3, model.getDiemkiemtra_1());
            }
            
            if(model.getDiemkiemtra_2()==-1f){
                stm.setObject(4, null);
            }else{
                stm.setFloat(4, model.getDiemkiemtra_2());
            }
            if(model.getDiemthicuoiky()==-1f){
                stm.setObject(5, null);
            }else{
                stm.setFloat(5, model.getDiemthicuoiky());
            }
            if(model.getDiemtongket()==-1f){
                stm.setObject(6, null);
            }else{
                stm.setFloat(6, model.getDiemtongket());
            }
            stm.setString(7, model.getXeploai());
            System.out.println(stm);
            kq = stm.executeUpdate() >0;
            if (kq){
                System.out.println("Updated Succeed !");
                //JOptionPane.showMessageDialog(null,"Thêm mới bảng điểm thành công !", "Succeed Message",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                            "Thêm mới không thành công !", "Error Massage",
                            JOptionPane.ERROR_MESSAGE);
        }finally{
            closeAll(con, stm, null);
        }
        }
    }
    public void DelBangDiem (BangDiemModel model) throws ClassNotFoundException, SQLException{
        Connection conn = getConnect();
        boolean kq = false;
        PreparedStatement stm = null;
        try{
            stm = conn.prepareStatement("delete from bangdiem where masinhvien = ? and mamonhoc = ?");
        stm.setString(1,model.getMasinhvien());
        stm.setString(2,model.getMamonhoc());
        kq = stm.executeUpdate()>0;
            if(kq){
                JOptionPane.showMessageDialog(null, "Xóa bảng điểm thành công !", "Succeed Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Xóa bảng điểm không thành công !", "Error Message", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }finally{
            closeAll(conn, stm, null);
        }
    }
    public ArrayList<MonHocModel> cbbmonhoc () throws ClassNotFoundException, SQLException{
        ArrayList<MonHocModel> list = new ArrayList<>();
        Connection conn = getConnect();
        PreparedStatement stm = null;
        ResultSet rs= null;
        String sqlquery = "";
        sqlquery = "select * from monhoc";
        try {
             System.err.println(sqlquery);
             stm = conn.prepareStatement(sqlquery); 
             rs = stm.executeQuery();
             while(rs.next()){
                 String mamon = rs.getString("mamonhoc");
                 String tenmon = rs.getString("tenmonhoc");
                 String idmon = rs.getString("id_mon");
                 MonHocModel MonModel = new MonHocModel(mamon, tenmon,idmon);
                 list.add(MonModel);
             }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            closeAll(conn, stm, rs);
        }
        return list;
    }
}
