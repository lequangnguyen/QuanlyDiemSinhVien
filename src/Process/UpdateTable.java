/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import java.sql.*;
import javax.swing.*;
import Utility.*;
import static Utility.MyConnect.getConnect;
import net.proteanit.sql.*; //thu vien lay tu rs2xml.jar

/**
 *
 * @author NGUYENIT
 */
public class UpdateTable {
    private static PreparedStatement pst = null; //biến thực thị sql
    private static ResultSet rs = null; //kết quả trả về dạng 1 bảng hay một dòng dữ liệu        
    private static Connection conn = null;
    
    //Hàm nạp dữ liệu cho bảng
    public static void LoadData (String sql, JTable tb)
    {
        try
        {
            Connection conn = getConnect();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(DbUtils.resultSetToTableModel(rs));//nạp dữ liệu lên bảng truyền vào "tb"
            
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
        }
    }
    
    //Hàm đổ một dòng dữ liệu từ bảng lên textfield
    public static ResultSet showTextField(String sql)
    {
        try
        {
            Connection conn = getConnect();
            pst = conn.prepareStatement(sql);
            return pst.executeQuery();//Trả về một dòng dữ liệu đọc được
            
        }
        catch(Exception e)
        {
            
            JOptionPane.showMessageDialog(null, e, "Thông báo lỗi", 1);
            return null;
        }
        
    }
}
