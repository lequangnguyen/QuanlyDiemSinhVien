/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Model.KhoaModel;
import Model.LopHocModel;
import Model.MonHocModel;
import java.sql.*;
import javax.swing.*;
import Utility.*;
import static Utility.MyConnect.closeAll;
import static Utility.MyConnect.getConnect;
import java.util.ArrayList;

/**
 *
 * @author NGUYENIT
 */
public class LopHoc {

    
    public static void InsertLop(String malop, String tenlop, String makhoa) throws ClassNotFoundException, SQLException
    {
        String sql = "INSERT INTO `quanlydiem`.`lophoc` (`malop`, `tenlop`,`makhoa`) VALUES (?, ?,?);";
        try
        {
            Connection conn = getConnect();
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, malop);
            pst.setString(2, tenlop);
            pst.setString(3, makhoa);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Đã thêm lớp "+'"'+tenlop+'"'+" thành công", "Thông báo",1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Mã lớp hoặc tên lớp vừa nhập đã tồn tại, không thể thêm mới", "Thông báo",1);
        }
    }
    
    public static void UpdateLop(String malop1, String malop, String tenlop, String makhoa)
    {
        String sql = "UPDATE `quanlydiem`.`lophoc` SET `malop`='"+malop+"', `tenlop`='"+tenlop+"',`makhoa`='"+makhoa+"'  WHERE `malop`='"+malop1+"';";
        try
        {
            Connection conn = getConnect();
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Đã sửa thành công!", "Thông báo",1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Mã lớp "+'"'+malop+'"'+" đã tồn tại", "Thông báo",1);
        }
    }
    
    public static void DeleteLop(String malop)
    
    {
        String sql = "DELETE FROM `quanlydiem`.`lophoc` WHERE `malop`='"+malop+"';";
        try
        {
            Connection conn = getConnect();
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Đã xóa lớp " +'"'+malop+'"'+ " thành công!", "Thông báo",1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Mã lớp "+'"'+malop+'"'+" có thể đang được sử dụng ở thực thể khác, không thể xóa", "Thông báo",1);
        }
    }
    
    public ArrayList<LopHocModel> getlstLop(KhoaModel Khoa) throws ClassNotFoundException, SQLException{
        ArrayList<LopHocModel> lstLopHoc = new ArrayList<LopHocModel>();
        String sqlquery = "select * from lophoc where makhoa = '"+Khoa.getMaKhoa()+"'";
        Connection conn = getConnect();
        PreparedStatement stm = null;
        ResultSet rs= null;
         try {
            System.err.println(sqlquery);
            stm = conn.prepareStatement(sqlquery);
            rs = stm.executeQuery();
            while (rs.next()) {
                String malop = rs.getString("malop");
                String tenlop = rs.getString("tenlop");
                String makhoa = rs.getString("makhoa");
                LopHocModel lopModel = new LopHocModel(malop, tenlop, makhoa);
                lstLopHoc.add(lopModel);
            }
             
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            closeAll(conn, stm, rs);
        }
        return lstLopHoc;
    }
    
}

