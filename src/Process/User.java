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


public class User {

    public static void InsertUser(String user_name, String password) {
        String sql = "INSERT INTO `quanlydiem`.`user` (`user_name`, `password`) VALUES (?, ?);";
        try {
            Connection conn = getConnect();
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, user_name);
            pst.setString(2, password);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Đăng ký tài khoản " + '"'+user_name +'"'+ " thành công", "Thông báo", 1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tên tài khoản " + '"'+user_name +'"' + " đã tồn tại, đề nghị đổi tên khác", "Thông báo", 1);
        }
    }

}
