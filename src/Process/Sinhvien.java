/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;
import Model.LopHocModel;
import Model.SinhVienModel;
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
public class Sinhvien {
    public static void InsertSinhvien(String masinhvien, String tensinhvien, String malop)
    {
        String sql = "INSERT INTO `quanlydiem`.`sinhvien` (`masinhvien`, `tensinhvien`,`malop`) VALUES (?, ?,?);";
        try
        {
            Connection conn = getConnect();
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, masinhvien);
            pst.setString(2, tensinhvien);
            pst.setString(3, malop);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Đã thêm sinh viên "+'"'+tensinhvien+'"'+" thành công", "Thông báo",1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Mã sinh viên "+'"'+masinhvien+'"'+" đã tồn tại, không thể thêm", "Thông báo",1);
        }
    }
    
    public static void UpdateSinhvien(String masinhvien1, String masinhvien, String tensinhvien, String malop)
    {
        String sql = "UPDATE `quanlydiem`.`sinhvien` SET `masinhvien`='"+masinhvien+"', `tensinhvien`='"+tensinhvien+"',`malop`='"+malop+"'  WHERE `masinhvien`='"+masinhvien1+"';";
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
            JOptionPane.showMessageDialog(null, "Mã sinh viên "+'"'+masinhvien+'"'+" đã tồn tại", "Thông báo",1);
        }
    }
    
    public static void DeleteSinhvien(String masinhvien)
    
    {
        String sql = "DELETE FROM `quanlydiem`.`sinhvien` WHERE `masinhvien`='"+masinhvien+"';";
        try
        {
            Connection conn = getConnect();
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Đã xóa sinh viên " +'"'+masinhvien+'"'+ " thành công!", "Thông báo",1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Mã snh viên "+'"'+masinhvien+'"'+" có thể đang được sử dụng ở thực thể khác, không thể xóa", "Thông báo",1);
        }
    }
    
    public ArrayList<SinhVienModel> getSinhVien(LopHocModel lop) throws ClassNotFoundException, SQLException{
        ArrayList<SinhVienModel> lstSinhvien = new ArrayList<SinhVienModel>();
        String sqlquery = "select * from sinhvien where malop = '"+lop.getMaLop()+"'";
        Connection con = getConnect();
        PreparedStatement stm = null;
        ResultSet rs= null;
         try {
            System.err.println(sqlquery);
            stm = con.prepareStatement(sqlquery);
            rs = stm.executeQuery();
            while (rs.next()) {
                String masinhvien = rs.getString("masinhvien");
                String tensinhvien = rs.getString("tensinhvien");
                String malop = rs.getString("malop");
                SinhVienModel sinhvien = new SinhVienModel(masinhvien, tensinhvien, malop);
                lstSinhvien.add(sinhvien);
            }
             
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            closeAll(con, stm, rs);
        }
        return lstSinhvien;
    }
    
}
