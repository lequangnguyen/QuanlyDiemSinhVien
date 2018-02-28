/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.KhoaModel;
import Model.LopHocModel;
import Model.MonHocModel;
import Model.SinhVienModel;
import Process.Khoa;
import Process.LopHoc;
import Process.Sinhvien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author lenovo g4070
 */
public class ChonSVAddBDForm extends javax.swing.JFrame {
    private final JfrmMain jfrmMain;
    Khoa progess = new Khoa();
    LopHoc progesslop = new LopHoc();
    Sinhvien progesssv = new Sinhvien();
    ArrayList<KhoaModel> lstKhoa = new ArrayList<KhoaModel>(); 
    ArrayList<LopHocModel> lstLopHoc = new ArrayList<LopHocModel>();
    ArrayList<SinhVienModel> lstSinhVien = new ArrayList<SinhVienModel>();
    int i = 0;
    /**
     * Creates new form ChonSVAddBDForm
     */
    public ChonSVAddBDForm(JfrmMain home) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChonSVAddBDForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ChonSVAddBDForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ChonSVAddBDForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ChonSVAddBDForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.jfrmMain = home;
        initComponents();
        add_cbb_khoa();
        get_cbb_lop();
        get_jlist_sinhvien();
    }
    public void get_cbb_lop(){
        
        cbb_bd_khoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    i = cbb_bd_khoa.getSelectedIndex();
                    if(i>0){
                        try {
                            KhoaModel model = lstKhoa.get(i-1);
                            lstLopHoc = progesslop.getlstLop(model);
                            cbb_bd_lophoc.removeAllItems();
                            cbb_bd_lophoc.addItem("Chọn lớp học");
                            for (LopHocModel lopHocModel : lstLopHoc) {
                                cbb_bd_lophoc.addItem(lopHocModel.getTenLop());
                            }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ChonSVAddBDForm.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ChonSVAddBDForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
            }
        });
    }
    
    public void add_cbb_khoa(){
        cbb_bd_khoa.addItem("Chọn Khoa");
        try {
            lstKhoa = progess.Getcbbkhoa();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChonSVAddBDForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChonSVAddBDForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (KhoaModel item : lstKhoa) {
            cbb_bd_khoa.addItem(item.getTenKhoa());
        }
        
    }
    
    public ArrayList<SinhVienModel> get_jlist_sinhvien(){
        
        i = 0;
        cbb_bd_lophoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                i = cbb_bd_lophoc.getSelectedIndex();
                if(i >0){
                    LopHocModel lop = lstLopHoc.get(i-1);
                try {
                    lstSinhVien = progesssv.getSinhVien(lop);
                    DefaultListModel jlstmodel = new DefaultListModel();
                    for (SinhVienModel sinhVienModel : lstSinhVien) {
                        jlstmodel.addElement(sinhVienModel.getTenSinhVien());
                    }
                    lst_bd_sinhvien.setModel(jlstmodel);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ChonSVAddBDForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ChonSVAddBDForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
            }
        });
        
        return lstSinhVien;
    }
    
       /*i = 0;
       i = lst_bd_sinhvien.getSelectedIndex();
       SinhVienModel sv = lstSinhVien.get(i);
       ThemMoiBangDiemForm AddBangDiem = new ThemMoiBangDiemForm(JfrmMain,sv.getMaSinhVien(), sv.getTenSinhVien());
        AddBangDiem.setVisible(true);*/
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbb_bd_khoa = new javax.swing.JComboBox<>();
        cbb_bd_lophoc = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst_bd_sinhvien = new javax.swing.JList<>();
        btn_ThemMoiDiem = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CHỌN SINH VIÊN");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Chọn Khoa");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Chọn Lớp Học");

        jScrollPane1.setViewportView(lst_bd_sinhvien);

        btn_ThemMoiDiem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_ThemMoiDiem.setText("Thêm Mới Điểm");
        btn_ThemMoiDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemMoiDiemActionPerformed(evt);
            }
        });

        jLabel3.setText("Danh sách sinh viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_ThemMoiDiem))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbb_bd_khoa, 0, 81, Short.MAX_VALUE)
                            .addComponent(cbb_bd_lophoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbb_bd_khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbb_bd_lophoc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_ThemMoiDiem)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemMoiDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemMoiDiemActionPerformed
        // TODO add your handling code here:
        i = 0;
       i = lst_bd_sinhvien.getSelectedIndex();
       if(i>=0){
           SinhVienModel sv = lstSinhVien.get(i);
       ThemMoiBangDiemForm AddBangDiem = new ThemMoiBangDiemForm(jfrmMain,sv.getMaSinhVien(), sv.getTenSinhVien());
        AddBangDiem.setVisible(true);
        this.dispose();
       }else{
           JOptionPane.showMessageDialog(null, "Bạn chưa chọn sinh viên!", "Thông báo", 1);
       }
       
        
    }//GEN-LAST:event_btn_ThemMoiDiemActionPerformed

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
            java.util.logging.Logger.getLogger(ChonSVAddBDForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChonSVAddBDForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChonSVAddBDForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChonSVAddBDForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new ChonSVAddBDForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ThemMoiDiem;
    private javax.swing.JComboBox<String> cbb_bd_khoa;
    private javax.swing.JComboBox<String> cbb_bd_lophoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lst_bd_sinhvien;
    // End of variables declaration//GEN-END:variables
}
