package Process;

import java.sql.*;
import javax.swing.*;
import Utility.*;
import static Utility.MyConnect.getConnect;

public class Kiemtra_Taikhoan {

    private static ResultSet rs = null;
    private static PreparedStatement pst = null;
    private static Connection conn = null;

    public static String testConnect() {
        try {
            conn = MyConnect.getConnect();
            return "Kết nối cơ sở dữ liệu thành công";
        } catch (Exception e) {
            return "Kết nối cơ sở dữ liệu thất bại";
        }
    }

    public static ResultSet kiemtraDangnhap(String user, String pass) {
        String sql = "SELECT * FROM user where user_name=? and password=?";
        try {
            Connection conn = getConnect();
            pst = conn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            return rs = pst.executeQuery();
        } catch (Exception e) {
            return rs = null;
        }
    }

    public static ResultSet kiemtraDangky(String user) {
        String sql = "SELECT * FROM user where user_name=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user);
            return rs = pst.executeQuery();
        } catch (Exception e) {
            return rs = null;
        }
    }

}
