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
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.*; //thu vien lay tu rs2xml.jar

/**
 *
 * @author NGUYENIT
 */
public class UpdateComboBox {


    public static void LoadComboBox(String sql, JComboBox jcombo) throws ClassNotFoundException {
        try {
            Connection conn = getConnect();
            PreparedStatement pst = null;//biến thực thị sql
            ResultSet rs= null;//kết quả trả về dạng 1 bảng hay một dòng dữ liệu 
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                jcombo.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateComboBox.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
