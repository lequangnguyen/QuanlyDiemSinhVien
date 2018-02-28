/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Model.KhoaModel;
import Model.MonHocModel;
import java.sql.*;
import javax.swing.*;
import static Utility.MyConnect.closeAll;
import static Utility.MyConnect.getConnect;
import java.util.ArrayList;

/**
 *
 * @author NGUYENIT
 */
public class Khoa {

    
    public static void InsertKhoa(String makhoa, String tenkhoa)
    {
        String sql = "INSERT INTO `quanlydiem`.`khoa` (`makhoa`, `tenkhoa`) VALUES (?, ?);";
        try
        {
            Connection conn = getConnect();
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, makhoa);
            pst.setString(2, tenkhoa);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Đã thêm khoa "+'"'+tenkhoa+'"'+" thành công", "Thông báo",1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Mã khoa hoặc tên khoa vừa nhập đã tồn tại, không thể thêm mới", "Thông báo",1);
        }
    }
    
    public static void UpdateKhoa(String makhoa1, String makhoa, String tenkhoa)
    {
        String sql = "UPDATE `quanlydiem`.`khoa` SET `makhoa`='"+makhoa+"', `tenkhoa`='"+tenkhoa+"' WHERE `makhoa`='"+makhoa1+"';";
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
            JOptionPane.showMessageDialog(null, "Mã khoa "+'"'+makhoa+'"'+" đã tồn tại", "Thông báo",1);
        }
    }
    
    public static void DeleteKhoa(String makhoa)
    
    {
        String sql = "DELETE FROM `quanlydiem`.`khoa` WHERE `makhoa`='"+makhoa+"';";
        try
        {
            Connection conn = getConnect();
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Đã xóa khoa " +'"'+makhoa+'"'+ " thành công!", "Thông báo",1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Mã khoa "+'"'+makhoa+'"'+" có thể đang được sử dụng ở thực thể khác, không thể xóa", "Thông báo",1);
        }
    }
    // Hàm lấy lst khoa toàn viết
    public ArrayList<KhoaModel> Getcbbkhoa() throws ClassNotFoundException, SQLException
    {
        ArrayList<KhoaModel> lstKhoa = new ArrayList<KhoaModel>();
        Connection conn = getConnect();
        PreparedStatement stm = null;
        ResultSet rs= null;
        String sqlquery = "";
        sqlquery = "select * from khoa  where 1=1 ";
        
         try {
            System.err.println(sqlquery);
            stm = conn.prepareStatement(sqlquery);
            rs = stm.executeQuery();
            while (rs.next()) {
                String MaKhoa = rs.getString("makhoa");
                String TenKhoa = rs.getString("tenkhoa");
                KhoaModel Model = new KhoaModel(MaKhoa,TenKhoa);
                lstKhoa.add(Model);
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            closeAll(conn, stm, rs);
        }
        return lstKhoa;
    }
    
}
