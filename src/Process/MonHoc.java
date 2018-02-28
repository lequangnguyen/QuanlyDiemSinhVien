/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Model.MonHocModel;
import static Utility.MyConnect.closeAll;
import static Utility.MyConnect.getConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MonHoc {
    public ArrayList<MonHocModel> SearchMonHoc(String TenMonHoc,String id_mon ) throws ClassNotFoundException, SQLException{
        ArrayList<MonHocModel> list = new ArrayList<>();
        Connection con = getConnect();
        PreparedStatement stm = null;
        ResultSet rs= null;
        String sqlquery = "";
        sqlquery = "select * from monhoc  where 1=1 ";
        if(!TenMonHoc.equals(""))
        {
            sqlquery += "and tenmonhoc like "+"'%"+TenMonHoc+"%'";
        }
        if(!id_mon.equals("")){
            sqlquery += " and id_mon like "+"'%"+id_mon+"%'";
        }
        
        try {
            System.err.println(sqlquery);
            stm = con.prepareStatement(sqlquery);
            rs = stm.executeQuery();
            while (rs.next()) {
                String mamon = rs.getString("mamonhoc");
                String tenmon = rs.getString("tenmonhoc");
                String idmon = rs.getString("id_mon");
                MonHocModel monModel = new MonHocModel(mamon, tenmon, idmon);
                list.add(monModel);
            }
             
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            closeAll(con, stm, rs);
        }
        return list;
    }
    public Boolean AddMonHoc(MonHocModel model) throws ClassNotFoundException, SQLException{
       
        boolean kq = false;
        boolean checkadd = true;
        //check mã môn trùng trước khi add
        
        Connection con = getConnect();
        
        PreparedStatement stm = null;
        ResultSet rs= null;
        try {
            stm = con.prepareStatement("select * from monhoc where id_mon = ?");
            stm.setString(1, model.getId_mon());
            rs = stm.executeQuery();
            while(rs.next()){
                checkadd = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            closeAll(con, stm, null);
        }
        con = getConnect();
        
        stm = null;
        if (checkadd){
            try {
            stm = con.prepareStatement("insert into monhoc (mamonhoc,tenmonhoc,id_mon) values (?,?,?) ");
            stm.setString(1, model.getMamonhoc());
            stm.setString(2, model.getTenmonhoc());
            stm.setString(3,model.getId_mon());
            kq = stm.executeUpdate() >0;
            if (kq){
                System.out.println("Added Succeed !");
            }
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }finally{
            closeAll(con, stm, null);
        }
        }else{
            JOptionPane.showMessageDialog(null,
                            "Mã môn học đã tồn tại !", "Succeed Message",
                            JOptionPane.ERROR_MESSAGE);
        }
        
        
        return kq;
    }
    
    public String EditMonHoc(MonHocModel model) throws ClassNotFoundException, SQLException{
        boolean kq = false;
        Connection con = getConnect();
        
        PreparedStatement stm = null;
        try {
             stm = con.prepareStatement("update monhoc set tenmonhoc = ?,id_mon = ? where mamonhoc = ?");
            stm.setString(1, model.getTenmonhoc());
            stm.setString(2, model.getId_mon());
            stm.setString(3, model.getMamonhoc());
            kq = stm.executeUpdate() >0;
            if (kq){
                System.out.println("Updated Succeed !");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            closeAll(con, stm, null);
        }
        
        return model.getMamonhoc();
    }
    
    public Boolean DeleteMonHoc(String MaMonHoc) throws ClassNotFoundException, SQLException {
        Connection con = getConnect();
        boolean kq = false;
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("delete from monhoc where mamonhoc = ?");
            stm.setString(1, MaMonHoc);
            kq = stm.executeUpdate() > 0;
            if (kq) {
                System.out.println("Deleted Succeed !");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không xóa được môn học vì có chứa bảng điểm !", "Không xóa được môn học vì có chứa bảng điểm !", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            closeAll(con, stm, null);
        }

        return kq;
    }
}
