/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.BangDiemModel;
import Process.BangDiem;
import Utility.EventTextNumber;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author lenovo g4070
 */
public class ChiTietCapNhatBangDiemForm extends javax.swing.JFrame {

    private final JfrmMain jfrmMain;
    public BangDiemModel BangDiemEdit = new BangDiemModel();
    Float diemtongket = 0.0f;

    /**
     * Creates new form ThemSuaBangDiemForm
     */
    public ChiTietCapNhatBangDiemForm(JfrmMain home) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietCapNhatBangDiemForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ChiTietCapNhatBangDiemForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ChiTietCapNhatBangDiemForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ChiTietCapNhatBangDiemForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.jfrmMain = home;
        initComponents();
    }

    public ChiTietCapNhatBangDiemForm(JfrmMain home, BangDiemModel model) {
        this.jfrmMain = home;
        //BangDiemModel model = new BangDiemModel();
        initComponents();
        BangDiemEdit = model;
        txt_bd_masinhvienupdate.setText(model.getMasinhvien());
        txt_bd_tensinhvienupdate.setText(model.getTensinhvien());
        txt_bd_mamonhocupdate.setText(model.getId_mon());
        txt_bd_tenmonhocupdate.setText(model.getTenmonhoc());
        txt_bd_diemkiemtra1update.setText(String.valueOf(model.getDiemkiemtra_1()));
        txt_bd_diemkiemtra2update.setText(String.valueOf(model.getDiemkiemtra_2()));
        txt_bd_diemthicuoikiupdate.setText(String.valueOf(model.getDiemthicuoiky()));
        if (model.getDiemtongket() == -1f) {
            txt_bd_diemtongketupdate.setText("None");
        } else {
            txt_bd_diemtongketupdate.setText(String.valueOf(model.getDiemtongket()));
        }
        txt_bd_Xeploai.setText(model.getXeploai());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_bd_masinhvienupdate = new javax.swing.JTextField();
        txt_bd_mamonhocupdate = new javax.swing.JTextField();
        txt_bd_diemkiemtra1update = new javax.swing.JTextField();
        txt_bd_diemtongketupdate = new javax.swing.JTextField();
        txt_bd_tensinhvienupdate = new javax.swing.JTextField();
        txt_bd_tenmonhocupdate = new javax.swing.JTextField();
        txt_bd_diemkiemtra2update = new javax.swing.JTextField();
        btn_bd_CapNhatBangDiem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_bd_diemthicuoikiupdate = new javax.swing.JTextField();
        btn_bd_TinhDiemTKBangDiem = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txt_bd_Xeploai = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CẬP NHẬT BẢNG ĐIỂM");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Mã Sinh Viên:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Tên Sinh Viên:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Mã Môn Học:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Tên Môn Học:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Điểm kiểm tra 1:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Điểm kiểm tra 2:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Điểm tổng kết:");

        txt_bd_masinhvienupdate.setEditable(false);
        txt_bd_masinhvienupdate.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txt_bd_masinhvienupdate.setEnabled(false);
        txt_bd_masinhvienupdate.setMinimumSize(new java.awt.Dimension(154, 30));

        txt_bd_mamonhocupdate.setEditable(false);
        txt_bd_mamonhocupdate.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txt_bd_mamonhocupdate.setEnabled(false);

        txt_bd_diemtongketupdate.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txt_bd_diemtongketupdate.setEnabled(false);

        txt_bd_tensinhvienupdate.setEditable(false);
        txt_bd_tensinhvienupdate.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txt_bd_tensinhvienupdate.setEnabled(false);
        txt_bd_tensinhvienupdate.setPreferredSize(new java.awt.Dimension(154, 30));

        txt_bd_tenmonhocupdate.setEditable(false);
        txt_bd_tenmonhocupdate.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txt_bd_tenmonhocupdate.setEnabled(false);
        txt_bd_tenmonhocupdate.setPreferredSize(new java.awt.Dimension(154, 30));

        btn_bd_CapNhatBangDiem.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_bd_CapNhatBangDiem.setText("Cập Nhật");
        btn_bd_CapNhatBangDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bd_CapNhatBangDiemActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Điểm thi cuối kì:");

        btn_bd_TinhDiemTKBangDiem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_bd_TinhDiemTKBangDiem.setLabel("TÍNH ĐIỂM TỔNG KẾT");
        btn_bd_TinhDiemTKBangDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bd_TinhDiemTKBangDiemActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Xếp loại:");

        txt_bd_Xeploai.setEditable(false);
        txt_bd_Xeploai.setDisabledTextColor(new java.awt.Color(255, 51, 51));
        txt_bd_Xeploai.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_bd_diemtongketupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(254, 254, 254))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_bd_TinhDiemTKBangDiem, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_bd_Xeploai, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGap(55, 55, 55)
                .addComponent(btn_bd_CapNhatBangDiem)
                .addGap(84, 84, 84))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_bd_tenmonhocupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_bd_mamonhocupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_bd_tensinhvienupdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_bd_masinhvienupdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_bd_diemkiemtra2update, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bd_diemthicuoikiupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bd_diemkiemtra1update, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(143, 143, 143))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_bd_masinhvienupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_bd_tensinhvienupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txt_bd_diemkiemtra1update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_bd_diemkiemtra2update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_bd_tenmonhocupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_bd_mamonhocupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_bd_diemthicuoikiupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(18, 18, 18)
                .addComponent(btn_bd_TinhDiemTKBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txt_bd_diemtongketupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_bd_CapNhatBangDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_bd_Xeploai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_bd_CapNhatBangDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bd_CapNhatBangDiemActionPerformed
        boolean checkforupdate = true;
        try {
            if (txt_bd_diemkiemtra1update.getText().equals("")) {
                BangDiemEdit.setDiemkiemtra_1(-1f);
            } else {
                BangDiemEdit.setDiemkiemtra_1(Float.parseFloat(txt_bd_diemkiemtra1update.getText()));
                if (BangDiemEdit.getDiemkiemtra_1() >10 || BangDiemEdit.getDiemkiemtra_1() <0){
                    checkforupdate = false;
                }
            }
        } catch (Exception ex) {
            checkforupdate = false;
            
        }

        try {
            if (txt_bd_diemkiemtra2update.getText().equals("")) {
                BangDiemEdit.setDiemkiemtra_2(-1f);
            } else {
                BangDiemEdit.setDiemkiemtra_2(Float.parseFloat(txt_bd_diemkiemtra2update.getText()));
                if (BangDiemEdit.getDiemkiemtra_2()>10 || BangDiemEdit.getDiemkiemtra_2()<0){
                    checkforupdate = false;
                }
            }
        } catch (Exception ex) {
            checkforupdate = false;
            //JOptionPane.showMessageDialog(null, "Vui lòng nhập số cho các trường !", "Vui lòng nhập số cho các trường !", JOptionPane.ERROR_MESSAGE);
        }
        try {
            if (txt_bd_diemthicuoikiupdate.getText().equals("")) {
                BangDiemEdit.setDiemthicuoiky(-1f);
            } else {
                BangDiemEdit.setDiemthicuoiky(Float.parseFloat(txt_bd_diemthicuoikiupdate.getText()));
                if (BangDiemEdit.getDiemthicuoiky()>10 || BangDiemEdit.getDiemthicuoiky()<0){
                    checkforupdate = false;
                }
            }

        } catch (Exception ex) {
            checkforupdate = false;
            //JOptionPane.showMessageDialog(null, "Vui lòng nhập số cho các trường !", "Vui lòng nhập số cho các trường !", JOptionPane.ERROR_MESSAGE);
        }
        if (BangDiemEdit.getDiemkiemtra_1() == -1f || BangDiemEdit.getDiemkiemtra_2() == -1f || BangDiemEdit.getDiemthicuoiky() == -1f) {
            BangDiemEdit.setDiemtongket(-1f);
        } else {
            BangDiemEdit.setDiemtongket((BangDiemEdit.getDiemkiemtra_1() + BangDiemEdit.getDiemkiemtra_2() + BangDiemEdit.getDiemthicuoiky() * 2) / 4);
        }
        BangDiemEdit.setXeploai(checkxeploai(BangDiemEdit.getDiemtongket()));
        BangDiem progess = new BangDiem();
        if(checkforupdate){
            try {
            progess.EditBangDiem(BangDiemEdit);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietCapNhatBangDiemForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietCapNhatBangDiemForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
        this.jfrmMain.setVisible(true);
        this.jfrmMain.fillDataBangDiem2Table();
        }
        else{
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị điểm từ 0 đến 10 !", "Vui lòng nhập giá trị điểm từ 0 đến 10 !", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_bd_CapNhatBangDiemActionPerformed

    private void btn_bd_TinhDiemTKBangDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bd_TinhDiemTKBangDiemActionPerformed
        try {
            diemtongket = (Float.parseFloat(txt_bd_diemkiemtra1update.getText()) + Float.parseFloat(txt_bd_diemkiemtra2update.getText()) + Float.parseFloat(txt_bd_diemthicuoikiupdate.getText()) * 2) / 4;
            txt_bd_diemtongketupdate.setText(String.valueOf(diemtongket));
        } catch (Exception ex) {
            diemtongket = -1f;
            txt_bd_diemtongketupdate.setText("None");
        }
        txt_bd_Xeploai.setText(checkxeploai(diemtongket));
    }//GEN-LAST:event_btn_bd_TinhDiemTKBangDiemActionPerformed

    public String checkxeploai(float diem) {
        String xeploai = "";
        if (diem >= 8) {
            xeploai = "Distinction";
        }
        if (diem < 8 && diem >= 6) {
            xeploai = "Credit";
        }
        if (diem < 6 && diem >= 4) {
            xeploai = "Pass";
        }
        if (diem >= 0 && diem < 4) {
            xeploai = "Fail";
        }
        if (diem < 0) {
            xeploai = "None";
        }
        return xeploai;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChiTietCapNhatBangDiemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietCapNhatBangDiemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietCapNhatBangDiemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietCapNhatBangDiemForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ChiTietCapNhatBangDiemForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bd_CapNhatBangDiem;
    private javax.swing.JButton btn_bd_TinhDiemTKBangDiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txt_bd_Xeploai;
    private javax.swing.JTextField txt_bd_diemkiemtra1update;
    private javax.swing.JTextField txt_bd_diemkiemtra2update;
    private javax.swing.JTextField txt_bd_diemthicuoikiupdate;
    private javax.swing.JTextField txt_bd_diemtongketupdate;
    private javax.swing.JTextField txt_bd_mamonhocupdate;
    private javax.swing.JTextField txt_bd_masinhvienupdate;
    private javax.swing.JTextField txt_bd_tenmonhocupdate;
    private javax.swing.JTextField txt_bd_tensinhvienupdate;
    // End of variables declaration//GEN-END:variables
}
