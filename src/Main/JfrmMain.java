/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.BangDiemModel;
import Model.MonHocModel;
import java.sql.*;
import javax.swing.*;
import Process.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author NGUYENIT
 */
public class JfrmMain extends javax.swing.JFrame {

    public static String sql1 = "SELECT makhoa as 'MÃ KHOA',tenkhoa as 'TÊN KHOA' FROM quanlydiem.khoa";
    public static String sql2 = "SELECT malop as 'MÃ LỚP',tenlop as 'TÊN LỚP',makhoa as 'MÃ KHOA' FROM quanlydiem.lophoc order by malop";
    public static String sql3 = "SELECT makhoa FROM quanlydiem.khoa order by makhoa";
    public static String sql4 = "SELECT masinhvien as 'MÃ SINH VIÊN',tensinhvien as 'TÊN SINH VIÊN',malop as 'MÃ LỚP' FROM quanlydiem.sinhvien order by masinhvien";
    public static String sql5 = "SELECT malop FROM quanlydiem.lophoc order by malop";
    public static String makhoa; //biến này lưu tạm mã khi nhấn lên 1 dòng trong bảng phục vụ cho hàm Update, Delete
    public static String malop;  //biến này lưu tạm mã khi nhấn lên 1 dòng trong bảng phục vụ cho hàm Update, Delete
    public static String masinhvien;  //biến này lưu tạm mã khi nhấn lên 1 dòng trong bảng phục vụ cho hàm Update, Delete
    public DefaultTableModel tableModel; // biến table model
    public DefaultTableModel tableBangDiemModel;
    MonHocModel mon = new MonHocModel();

    public JfrmMain() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
        ProcessCtr_Khoa(false);
        ProcessCtr_Lop(false);
        ProcessCtr_Sinhvien(false);
        this.btnAdd_Khoa.setEnabled(true);
        this.btnAdd_Lop.setEnabled(true);
        this.btnAdd_Sinhvien.setEnabled(true);
        tbKhoa_Display();
        tbLop_Display();
        tbSinhVien_Display();

        try {
            UpdateComboBox.LoadComboBox(sql3, jComboBox_Khoa);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            UpdateComboBox.LoadComboBox(sql5, jComboBox_Lop);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Toàn viết hàm cho môn học
        //Hàm set lại giá trị cho table Toàn viết
        String[] colsMonHoc = "ID MÔN,STT,TÊN MÔN HỌC,MÃ MÔN HỌC".split(",");
        tableModel = new DefaultTableModel(colsMonHoc, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tbl_MonHoc.setModel(tableModel);

        tbl_MonHoc.getColumnModel().getColumn(0).setWidth(0);
        tbl_MonHoc.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_MonHoc.getColumnModel().getColumn(0).setMaxWidth(0);

        String[] colsBangDiem = "TÊN SINH VIÊN,TÊN MÔN HỌC,ĐIỂM THÀNH PHẦN,ĐIỂM THI, ĐIỂM TỔNG KẾT,XẾP LOẠI,MÃ SINH VIÊN,MÃ MÔN HỌC,MÃ MÔN HỌC".split(",");
        tableBangDiemModel = new DefaultTableModel(colsBangDiem, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }

        };
        tbl_BangDiem.setModel(tableBangDiemModel);
        tbl_BangDiem.getColumnModel().getColumn(8).setWidth(0);
        tbl_BangDiem.getColumnModel().getColumn(8).setMinWidth(0);
        tbl_BangDiem.getColumnModel().getColumn(8).setMaxWidth(0);

        // Kết thúc Toàn viết hàm cho môn học
        // Gọi hàm đưa dữ liệu vào jtable môn
        fillDataMonHoc2Table();
        // Gọi hàm đưa dữ liệu vào jtable bảng điểm
        fillDataBangDiem2Table();
    }

    public void tbKhoa_Display() {
        UpdateTable.LoadData(sql1, tbKhoa);
        this.lbTongKhoa.setText("Tổng số có " + this.tbKhoa.getRowCount() + " khoa");

    }

    public void tbLop_Display() {
        UpdateTable.LoadData(sql2, tbLop);
        this.lbTongLop.setText("Tổng số có " + this.tbLop.getRowCount() + " lớp học");

    }

    public void tbSinhVien_Display() {
        UpdateTable.LoadData(sql4, tbSinhvien);
        this.lbTongSV.setText("Tổng số có " + this.tbSinhvien.getRowCount() + " sinh viên");
    }

    public void ProcessCtr_Khoa(boolean b) {
        this.btnEdit_Khoa.setEnabled(b);
        this.btnDel_Khoa.setEnabled(b);
    }

    public void ProcessCtr_Lop(boolean b) {
        this.btnEdit_Lop.setEnabled(b);
        this.btnDel_Lop.setEnabled(b);
    }

    public void ProcessCtr_Sinhvien(boolean b) {
        this.btnEdit_Sinhvien.setEnabled(b);
        this.btnDel_Sinhvien.setEnabled(b);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newtab1 = new Utility.newtab();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txttenkhoa = new javax.swing.JTextField();
        txtmakhoa = new javax.swing.JTextField();
        btnNew_Khoa = new javax.swing.JButton();
        txttimkiem_khoa = new javax.swing.JTextField();
        btnLook_Khoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhoa = new javax.swing.JTable();
        btnAdd_Khoa = new javax.swing.JButton();
        btnEdit_Khoa = new javax.swing.JButton();
        btnDel_Khoa = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnrefresh_Khoa = new javax.swing.JButton();
        lbTongKhoa = new javax.swing.JLabel();
        newtab2 = new Utility.newtab();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnNew_Lop = new javax.swing.JButton();
        txttimkiem_lop = new javax.swing.JTextField();
        btnLook_Lop = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbLop = new javax.swing.JTable();
        btnAdd_Lop = new javax.swing.JButton();
        btnEdit_Lop = new javax.swing.JButton();
        btnDel_Lop = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnrefresh_Lop = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtmalop = new javax.swing.JTextField();
        txttenlop = new javax.swing.JTextField();
        jComboBox_Khoa = new javax.swing.JComboBox<>();
        lbTongLop = new javax.swing.JLabel();
        newtab4 = new Utility.newtab();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnNew_Sinhvien = new javax.swing.JButton();
        txttimkiem_sinhvien = new javax.swing.JTextField();
        btnLook_Sinhvien = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbSinhvien = new javax.swing.JTable();
        btnAdd_Sinhvien = new javax.swing.JButton();
        btnEdit_Sinhvien = new javax.swing.JButton();
        btnDel_Sinhvien = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        btnrefresh_Sinhvien = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtmasinhvien = new javax.swing.JTextField();
        txttensinhvien = new javax.swing.JTextField();
        jComboBox_Lop = new javax.swing.JComboBox<>();
        lbTongSV = new javax.swing.JLabel();
        newtab3 = new Utility.newtab();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_tenmonsearch = new javax.swing.JTextField();
        txt_mamonsearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_MonHoc = new javax.swing.JTable();
        btn_TimKiemMonHoc = new javax.swing.JButton();
        lbl_tongmonhoc = new javax.swing.JLabel();
        btn_CapNhatMonHoc = new javax.swing.JButton();
        btn_XoaMonHoc = new javax.swing.JButton();
        btn_ThemMonHoc = new javax.swing.JButton();
        btn_lammoitimkiem = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jlabel16 = new javax.swing.JLabel();
        txt_bd_TenSinhVienSearch = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_bd_TenMonHocSearch = new javax.swing.JTextField();
        btn_bd_TimKiem = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cbb_bd_XepLoai = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_BangDiem = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        lbl_bd_Status = new javax.swing.JLabel();
        jlabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_bd_MaMonHocSearch = new javax.swing.JTextField();
        txt_bd_MaSinhVienSearch = new javax.swing.JTextField();
        btn_bd_ThemMoi = new javax.swing.JButton();
        btn_bd_Xoa = new javax.swing.JButton();
        btn_bd_CapNhat = new javax.swing.JButton();
        btn_LamMoiBangDiem = new javax.swing.JButton();

        javax.swing.GroupLayout newtab1Layout = new javax.swing.GroupLayout(newtab1);
        newtab1.setLayout(newtab1Layout);
        newtab1Layout.setHorizontalGroup(
            newtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        newtab1Layout.setVerticalGroup(
            newtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CHƯƠNG TRÌNH QUẢN LÝ ĐIỂM");
        setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Mã khoa:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Tên khoa:");

        txtmakhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmakhoaActionPerformed(evt);
            }
        });

        btnNew_Khoa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNew_Khoa.setText("Nhập mới");
        btnNew_Khoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew_KhoaActionPerformed(evt);
            }
        });

        btnLook_Khoa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLook_Khoa.setText("Tìm kiếm");
        btnLook_Khoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLook_KhoaActionPerformed(evt);
            }
        });

        tbKhoa.setAutoCreateRowSorter(true);
        tbKhoa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbKhoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "              Mã khoa", "              Tên khoa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbKhoa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbKhoa.setEditingColumn(0);
        tbKhoa.setEditingRow(0);
        tbKhoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhoaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhoa);

        btnAdd_Khoa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAdd_Khoa.setText("Thêm");
        btnAdd_Khoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_KhoaActionPerformed(evt);
            }
        });

        btnEdit_Khoa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEdit_Khoa.setText("Sửa");
        btnEdit_Khoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit_KhoaActionPerformed(evt);
            }
        });

        btnDel_Khoa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDel_Khoa.setText("Xóa");
        btnDel_Khoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel_KhoaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setText("THÔNG TIN KHOA");

        btnrefresh_Khoa.setText("Hủy tìm kiếm");
        btnrefresh_Khoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefresh_KhoaActionPerformed(evt);
            }
        });

        lbTongKhoa.setText("Tổng số khoa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(lbTongKhoa)
                .addGap(0, 674, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txttenkhoa, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addComponent(txtmakhoa))
                        .addGap(38, 38, 38)
                        .addComponent(btnNew_Khoa))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnrefresh_Khoa)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAdd_Khoa)
                                .addGap(18, 18, 18)
                                .addComponent(btnEdit_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDel_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138)
                                .addComponent(btnLook_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttimkiem_khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmakhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenkhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd_Khoa)
                    .addComponent(btnEdit_Khoa)
                    .addComponent(btnDel_Khoa)
                    .addComponent(btnLook_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimkiem_khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(btnrefresh_Khoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTongKhoa)
                .addGap(69, 69, 69))
        );

        jTabbedPane2.addTab(" THÔNG TIN KHOA  ", null, jPanel2, "");
        jPanel2.getAccessibleContext().setAccessibleName("");

        newtab2.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("  Mã lớp:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText(" Tên lớp:");

        btnNew_Lop.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNew_Lop.setText("Nhập mới");
        btnNew_Lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew_LopActionPerformed(evt);
            }
        });

        btnLook_Lop.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLook_Lop.setText("Tìm kiếm");
        btnLook_Lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLook_LopActionPerformed(evt);
            }
        });

        tbLop.setAutoCreateRowSorter(true);
        tbLop.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã lớp", "Tên lớp", "Tên khoa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLopMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbLop);

        btnAdd_Lop.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAdd_Lop.setText("Thêm");
        btnAdd_Lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_LopActionPerformed(evt);
            }
        });

        btnEdit_Lop.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEdit_Lop.setText("Sửa");
        btnEdit_Lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit_LopActionPerformed(evt);
            }
        });

        btnDel_Lop.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDel_Lop.setText("Xóa");
        btnDel_Lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel_LopActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setText("THÔNG TIN LỚP HỌC");

        btnrefresh_Lop.setText("Hủy tìm kiếm");
        btnrefresh_Lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefresh_LopActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("     Khoa:");

        jComboBox_Khoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn mã khoa..." }));
        jComboBox_Khoa.setToolTipText("Chọn mã khoa...");

        lbTongLop.setText("Tổng số lớp");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtmalop, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNew_Lop))
                                .addComponent(txttenlop, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(lbTongLop)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnrefresh_Lop)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(btnAdd_Lop)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnEdit_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDel_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLook_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(txttimkiem_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(167, 167, 167))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmalop, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenlop, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd_Lop)
                    .addComponent(btnEdit_Lop)
                    .addComponent(btnDel_Lop)
                    .addComponent(btnLook_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimkiem_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(btnrefresh_Lop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongLop)
                .addContainerGap())
        );

        javax.swing.GroupLayout newtab2Layout = new javax.swing.GroupLayout(newtab2);
        newtab2.setLayout(newtab2Layout);
        newtab2Layout.setHorizontalGroup(
            newtab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newtab2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        newtab2Layout.setVerticalGroup(
            newtab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane2.addTab("           THÔNG TIN LỚP HỌC           ", newtab2);

        newtab4.setPreferredSize(new java.awt.Dimension(900, 600));

        jPanel4.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText(" Mã sinh viên:");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Tên sinh viên:");

        btnNew_Sinhvien.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNew_Sinhvien.setText("Nhập mới");
        btnNew_Sinhvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew_SinhvienActionPerformed(evt);
            }
        });

        btnLook_Sinhvien.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLook_Sinhvien.setText("Tìm kiếm");
        btnLook_Sinhvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLook_SinhvienActionPerformed(evt);
            }
        });

        tbSinhvien.setAutoCreateRowSorter(true);
        tbSinhvien.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbSinhvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sinh viên", "Tên sinh viên", "Tên lớp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSinhvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSinhvienMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbSinhvien);

        btnAdd_Sinhvien.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAdd_Sinhvien.setText("Thêm");
        btnAdd_Sinhvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd_SinhvienActionPerformed(evt);
            }
        });

        btnEdit_Sinhvien.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEdit_Sinhvien.setText("Sửa");
        btnEdit_Sinhvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit_SinhvienActionPerformed(evt);
            }
        });

        btnDel_Sinhvien.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDel_Sinhvien.setText("Xóa");
        btnDel_Sinhvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel_SinhvienActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel21.setText("THÔNG TIN SINH VIÊN");

        btnrefresh_Sinhvien.setText("Hủy tìm kiếm");
        btnrefresh_Sinhvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefresh_SinhvienActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("         Lớp học:");

        jComboBox_Lop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn mã lớp..." }));

        lbTongSV.setText("Tổng số sinh viên");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnrefresh_Sinhvien, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(btnAdd_Sinhvien)
                            .addGap(18, 18, 18)
                            .addComponent(btnEdit_Sinhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnDel_Sinhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLook_Sinhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txttimkiem_sinhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(167, 167, 167))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtmasinhvien)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnNew_Sinhvien))
                                .addComponent(txttensinhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(lbTongSV)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel21)
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew_Sinhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmasinhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txttensinhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd_Sinhvien)
                    .addComponent(btnEdit_Sinhvien)
                    .addComponent(btnDel_Sinhvien)
                    .addComponent(btnLook_Sinhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttimkiem_sinhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnrefresh_Sinhvien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTongSV)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout newtab4Layout = new javax.swing.GroupLayout(newtab4);
        newtab4.setLayout(newtab4Layout);
        newtab4Layout.setHorizontalGroup(
            newtab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newtab4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        newtab4Layout.setVerticalGroup(
            newtab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newtab4Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("                THÔNG TIN SINH VIÊN                ", newtab4);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel11.setText("QUẢN LÝ MÔN HỌC");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Tên môn học:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Mã môn học:");

        tbl_MonHoc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_MonHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_MonHoc.setName(""); // NOI18N
        tbl_MonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_MonHocMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_MonHoc);

        btn_TimKiemMonHoc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_TimKiemMonHoc.setText("Tìm kiếm");
        btn_TimKiemMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemMonHocActionPerformed(evt);
            }
        });

        lbl_tongmonhoc.setText("Kết quả tìm kiếm: Tổng số 0 bản ghi");

        btn_CapNhatMonHoc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_CapNhatMonHoc.setText("Cập nhật");
        btn_CapNhatMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatMonHocActionPerformed(evt);
            }
        });

        btn_XoaMonHoc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_XoaMonHoc.setText("Xóa");
        btn_XoaMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaMonHocActionPerformed(evt);
            }
        });

        btn_ThemMonHoc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_ThemMonHoc.setText("Thêm mới");
        btn_ThemMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemMonHocActionPerformed(evt);
            }
        });

        btn_lammoitimkiem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_lammoitimkiem.setText("Làm Mới");
        btn_lammoitimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoitimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newtab3Layout = new javax.swing.GroupLayout(newtab3);
        newtab3.setLayout(newtab3Layout);
        newtab3Layout.setHorizontalGroup(
            newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newtab3Layout.createSequentialGroup()
                .addGap(0, 160, Short.MAX_VALUE)
                .addGroup(newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(newtab3Layout.createSequentialGroup()
                        .addGroup(newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(newtab3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btn_ThemMonHoc)
                                .addGap(18, 18, 18)
                                .addComponent(btn_CapNhatMonHoc)
                                .addGap(18, 18, 18)
                                .addComponent(btn_XoaMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(newtab3Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tenmonsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_mamonsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbl_tongmonhoc, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_lammoitimkiem, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newtab3Layout.createSequentialGroup()
                                .addComponent(btn_TimKiemMonHoc)
                                .addGap(68, 68, 68)))))
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(newtab3Layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        newtab3Layout.setVerticalGroup(
            newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newtab3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addGroup(newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenmonsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newtab3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_mamonsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(51, 51, 51))
                    .addGroup(newtab3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btn_TimKiemMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(newtab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhatMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XoaMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_lammoitimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(lbl_tongmonhoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jTabbedPane2.addTab("       THÔNG TIN MÔN HỌC      ", newtab3);

        jPanel1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setText("QUẢN LÝ BẢNG ĐIỂM");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Tìm kiếm điểm theo:");

        jlabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlabel16.setText("Tên Sinh Viên:");

        jLabel17.setToolTipText("");

        btn_bd_TimKiem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_bd_TimKiem.setText("Tìm Kiếm");
        btn_bd_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bd_TimKiemActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setText("          Xếp loại:");

        cbb_bd_XepLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Distinction", "Credit", "Pass", "Fail" }));

        tbl_BangDiem.setAutoCreateRowSorter(true);
        tbl_BangDiem.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbl_BangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tbl_BangDiem);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Tên Môn Học:");

        lbl_bd_Status.setText("Kết quả tìm kiếm: Tổng số 0 bản ghi");

        jlabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlabel14.setText("Mã Môn Học:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Mã Sinh Viên:");

        btn_bd_ThemMoi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_bd_ThemMoi.setText("THÊM MỚI");
        btn_bd_ThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bd_ThemMoiActionPerformed(evt);
            }
        });

        btn_bd_Xoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_bd_Xoa.setText("XÓA");
        btn_bd_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bd_XoaActionPerformed(evt);
            }
        });

        btn_bd_CapNhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_bd_CapNhat.setText("CẬP NHẬT");
        btn_bd_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bd_CapNhatActionPerformed(evt);
            }
        });

        btn_LamMoiBangDiem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_LamMoiBangDiem.setText("Làm Mới");
        btn_LamMoiBangDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiBangDiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jLabel17))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_bd_ThemMoi)
                                .addGap(18, 18, 18)
                                .addComponent(btn_bd_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_bd_Xoa))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jlabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbb_bd_XepLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(319, 319, 319)
                                        .addComponent(btn_LamMoiBangDiem))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_bd_TenSinhVienSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_bd_TenMonHocSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_bd_TimKiem))
                                        .addGap(53, 53, 53)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlabel14)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_bd_MaMonHocSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_bd_MaSinhVienSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbl_bd_Status))
                        .addGap(113, 113, 113))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_bd_TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_bd_TenSinhVienSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlabel14)
                                .addComponent(txt_bd_MaMonHocSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_bd_TenMonHocSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bd_MaSinhVienSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_bd_XepLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_bd_ThemMoi)
                    .addComponent(btn_bd_CapNhat)
                    .addComponent(btn_bd_Xoa)
                    .addComponent(btn_LamMoiBangDiem))
                .addGap(9, 9, 9)
                .addComponent(lbl_bd_Status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );

        jLabel14.getAccessibleContext().setAccessibleName("");

        jTabbedPane2.addTab("         QUẢN LÝ BẢNG ĐIỂM SINH VIÊN         ", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_bd_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bd_CapNhatActionPerformed
        Object objGetBangDiem = new Object();
        BangDiem progess = new BangDiem();
        //Check xem đã chọn row trong jtable chưa
        try {
            objGetBangDiem = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 0);
        } catch (Exception e) {
            e.getStackTrace();
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn Thông tin sinh viên !", "Bạn chưa chọn Thông tin sinh viên !", JOptionPane.ERROR_MESSAGE);
        }
        Object objMaSinhVien = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 6);
        String MaSinhVien = objMaSinhVien.toString();
        Object objMaMonHoc = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 8);
        String MaMonHoc = objMaMonHoc.toString();
        // Gọi hàm lấy thông tin bảng điểm từ db
        BangDiemModel updateModel = new BangDiemModel();
        try {
            updateModel = progess.GetBangDiemById(MaSinhVien, MaMonHoc);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChiTietCapNhatBangDiemForm EditBangdiem = new ChiTietCapNhatBangDiemForm(this, updateModel);
        EditBangdiem.setVisible(true);
    }//GEN-LAST:event_btn_bd_CapNhatActionPerformed

    private void btn_bd_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bd_XoaActionPerformed
        Object objGetBangDiem = new Object();
        BangDiem progess = new BangDiem();
        BangDiemModel BangDiemDel = new BangDiemModel();
        //Check xem đã chọn row trong jtable chưa
        try {
            objGetBangDiem = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 0);
        } catch (Exception e) {
            e.getStackTrace();
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn sinh viên để xóa !", "Bạn chưa chọn sinh viên để xóa !", JOptionPane.ERROR_MESSAGE);
        }
        Object objMaSinhVien = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 6);
        BangDiemDel.setMasinhvien(objMaSinhVien.toString());
        Object objMaMonHoc = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 8);
        BangDiemDel.setMamonhoc(objMaMonHoc.toString());
        Object objTenMonHoc = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 1);
        BangDiemDel.setTenmonhoc(objTenMonHoc.toString());
        Object objTenSinhVien = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 0);
        BangDiemDel.setTensinhvien(objTenSinhVien.toString());
        String[] options = new String[2];
        options[0] = new String("Có");
        options[1] = new String("Không");
        int n = JOptionPane.showOptionDialog(this.getContentPane(), "Bạn thực sự muốn xóa bảng điểm môn " + BangDiemDel.getTenmonhoc() + " của sinh viên " + BangDiemDel.getTensinhvien() + "?", "Thông báo xác nhận xóa khoa", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if (n == JOptionPane.YES_OPTION) {
            try {
                progess.DelBangDiem(BangDiemDel);
                fillDataBangDiem2Table();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (n == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Xóa môn học không thành công !", "Xóa môn học không thành công !", JOptionPane.ERROR_MESSAGE);

        } else {
            fillDataBangDiem2Table();
            JOptionPane.showMessageDialog(null, "Xóa môn học không thành công !", "Xóa môn học không thành công !", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_bd_XoaActionPerformed

    private void btn_bd_ThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bd_ThemMoiActionPerformed
        Object objGetBangDiem = new Object();
        BangDiem progess = new BangDiem();
        //Check xem đã chọn row trong jtable chưa
        try {
            objGetBangDiem = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 0);
            Object objMaSinhVien = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 6);
            String MaSinhVien = objMaSinhVien.toString();
            Object objTenSinhVien = tbl_BangDiem.getModel().getValueAt(tbl_BangDiem.getSelectedRow(), 0);
            String TenSinhVien = objTenSinhVien.toString();
            // Gọi Form thêm mới
            ThemMoiBangDiemForm AddBangDiem = new ThemMoiBangDiemForm(this, MaSinhVien, TenSinhVien);
            AddBangDiem.setVisible(true);
        } catch (Exception e) {
            e.getStackTrace();
            ChonSVAddBDForm ThemSVForm = new ChonSVAddBDForm(this);
            ThemSVForm.setVisible(true);
            //JOptionPane.showMessageDialog(null, "Bạn chưa chọn sinh viên để thêm mới !", "Bạn chưa chọn Thông tin sinh viên !", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btn_bd_ThemMoiActionPerformed

    private void btn_bd_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bd_TimKiemActionPerformed
        fillDataBangDiem2Table();
    }//GEN-LAST:event_btn_bd_TimKiemActionPerformed

    private void btn_ThemMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemMonHocActionPerformed
        //ThemMonHocForm addMonHoc = new ThemMonHocForm(this);
        //addMonHoc.setVisible(true);
        if (!txt_tenmonsearch.getText().equals("") && !txt_mamonsearch.getText().equals("")) {
            // Khởi tạo progess
            MonHoc progess = new MonHoc();
            // Khởi tạo model
            MonHocModel modeladd = new MonHocModel();
            // Tạo id môn học mới
            Long timestamp = System.currentTimeMillis();
            String genid = Long.toString(timestamp);
            genid = "Mon-" + genid;
            modeladd.setMamonhoc(genid);
            modeladd.setId_mon(txt_mamonsearch.getText());
            modeladd.setTenmonhoc(txt_tenmonsearch.getText());
            try {
                progess.AddMonHoc(modeladd);
                txt_tenmonsearch.setText("");
                txt_mamonsearch.setText("");
                fillDataMonHoc2Table();
                //JOptionPane.showMessageDialog(null, "Thêm mới môn học thành công !", "Thêm mới môn học thành công !", JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ tên môn và mã môn !", "Vui lòng nhập đủ tên môn và mã môn !", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_ThemMonHocActionPerformed

    private void btn_XoaMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaMonHocActionPerformed
        Object objMaMonHoc = new Object();
        try {
            objMaMonHoc = tbl_MonHoc.getModel().getValueAt(tbl_MonHoc.getSelectedRow(), 0);
        } catch (Exception e) {
            e.getStackTrace();
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn môn học !", "Bạn chưa chọn môn học !", JOptionPane.ERROR_MESSAGE);
        }
        MonHoc pst = new MonHoc();
        String MaMonHoc = objMaMonHoc.toString();
        Object objTenMonHoc = tbl_MonHoc.getModel().getValueAt(tbl_MonHoc.getSelectedRow(), 2);
        String TenMonHoc = objTenMonHoc.toString();
        boolean kq = false;
        String[] options = new String[2];
        options[0] = new String("Có");
        options[1] = new String("Không");
        int n = JOptionPane.showOptionDialog(this.getContentPane(), "Bạn thực sự muốn xóa môn học " + TenMonHoc + " ?", "Thông báo xác nhận xóa khoa", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if (n == JOptionPane.YES_OPTION) {
            try {
                kq = pst.DeleteMonHoc(MaMonHoc);
                clearSearchMonHoc();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (kq) {
                fillDataMonHoc2Table();
                JOptionPane.showMessageDialog(null, "Xóa môn học thành công !", "Xóa môn học thành công !", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (n == JOptionPane.NO_OPTION) {
            clearSearchMonHoc();
            JOptionPane.showMessageDialog(null, "Xóa môn học không thành công !", "Xóa môn học không thành công !", JOptionPane.ERROR_MESSAGE);

        } else {
            clearSearchMonHoc();
            fillDataMonHoc2Table();
            JOptionPane.showMessageDialog(null, "Xóa môn học không thành công !", "Xóa môn học không thành công !", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_XoaMonHocActionPerformed

    private void btn_CapNhatMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatMonHocActionPerformed
        Object objMaMonHoc = new Object();
        MonHocModel model = new MonHocModel();
        try {
            objMaMonHoc = tbl_MonHoc.getModel().getValueAt(tbl_MonHoc.getSelectedRow(), 0);
        } catch (Exception e) {
            e.getStackTrace();
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn môn học !", "Bạn chưa chọn môn học !", JOptionPane.ERROR_MESSAGE);
        }
        String mamonhoc = objMaMonHoc.toString();
        model.setId_mon(txt_mamonsearch.getText());
        model.setMamonhoc(mamonhoc);
        model.setTenmonhoc(txt_tenmonsearch.getText());
        System.out.println(model.getId_mon());
        MonHoc progess = new MonHoc();
        try {
            progess.EditMonHoc(model);
            clearSearchMonHoc();
            fillDataMonHoc2Table();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_CapNhatMonHocActionPerformed

    private void btn_TimKiemMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemMonHocActionPerformed
        fillDataMonHoc2Table();
    }//GEN-LAST:event_btn_TimKiemMonHocActionPerformed

    private void btnNew_SinhvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew_SinhvienActionPerformed
        // TODO add your handling code here:
        ProcessCtr_Sinhvien(false);
        this.btnAdd_Sinhvien.setEnabled(true);
        this.txtmasinhvien.setText(null);
        this.txttensinhvien.setText(null);
    }//GEN-LAST:event_btnNew_SinhvienActionPerformed

    private void btnLook_SinhvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLook_SinhvienActionPerformed
        // TODO add your handling code here:
        if (this.txttimkiem_sinhvien.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập từ khóa muốn tìm kiếm!", "Thông báo", 1);
        } else {
            String sql = "SELECT masinhvien as 'MÃ SINH VIÊN',tensinhvien as 'TÊN SINH VIÊN',malop as 'MÃ LỚP' FROM quanlydiem.sinhvien where masinhvien like '%" + this.txttimkiem_sinhvien.getText() + "%' or tensinhvien like '%" + this.txttimkiem_sinhvien.getText() + "%' or malop like '%" + this.txttimkiem_sinhvien.getText() + "%'";
            UpdateTable.LoadData(sql, tbSinhvien);
            this.lbTongSV.setText("Tổng số có " + this.tbSinhvien.getRowCount() + " sinh viên");
        }
    }//GEN-LAST:event_btnLook_SinhvienActionPerformed

    private void tbSinhvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSinhvienMouseClicked
        // TODO add your handling code here:
        ProcessCtr_Sinhvien(true);
        try {
            int row = this.tbSinhvien.getSelectedRow();// lấy dòng hiên tại đang nhấn chuột
            //Lấy giá trị ở dòng vừa nhấn chuột và chuyển sang dạng String
            String IDrow = (this.tbSinhvien.getModel().getValueAt(row, 0)).toString();
            String sql = "SELECT * FROM quanlydiem.sinhvien where masinhvien = '" + IDrow + "'";
            //đọc dữ liệu tại dòng được chọn
            ResultSet rs = UpdateTable.showTextField(sql);
            if (rs.next())//kiểm tra nếu có dữ liệu
            {
                masinhvien = rs.getString("masinhvien");
                this.txtmasinhvien.setText(rs.getString("masinhvien"));
                this.txttensinhvien.setText(rs.getString("tensinhvien"));
                this.jComboBox_Lop.setSelectedItem(rs.getString("malop"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tbSinhvienMouseClicked

    private void btnAdd_SinhvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_SinhvienActionPerformed
        // TODO add your handling code here:
        if (this.txtmasinhvien.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên không được để trống!", "Thông báo", 1);
        } else if (this.txtmasinhvien.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên không được vượt quá 20 ký tự!", "Thông báo", 1);
        } else if (this.txttensinhvien.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Tên sinh viên không được để trống!", "Thông báo", 1);
        } else if (this.txttensinhvien.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên không được vượt quá 100 ký tự!", "Thông báo", 1);
        } else if ((String) jComboBox_Lop.getSelectedItem() == "Chọn mã lớp...") {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn mã lớp!", "Thông báo", 1);
        } else {
            Sinhvien.InsertSinhvien(this.txtmasinhvien.getText().trim(), this.txttensinhvien.getText(), (String) jComboBox_Lop.getSelectedItem());
            tbSinhVien_Display();
            this.txtmasinhvien.setText(null);
            this.txttensinhvien.setText(null);
        }
    }//GEN-LAST:event_btnAdd_SinhvienActionPerformed

    private void btnEdit_SinhvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit_SinhvienActionPerformed
        // TODO add your handling code here:
        if (this.txtmasinhvien.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên không được để trống!", "Thông báo", 1);
        } else if (this.txtmasinhvien.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên không được vượt quá 20 ký tự!", "Thông báo", 1);
        } else if (this.txttensinhvien.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Tên sinh viên không được để trống!", "Thông báo", 1);
        } else if (this.txttensinhvien.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Mã sinh viên không được vượt quá 100 ký tự!", "Thông báo", 1);
        } else if ((String) jComboBox_Lop.getSelectedItem() == "Chọn mã lớp...") {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn mã lớp!", "Thông báo", 1);
        } else {
            LopHoc.UpdateLop(masinhvien, this.txtmasinhvien.getText().trim(), this.txttensinhvien.getText(), (String) jComboBox_Lop.getSelectedItem());
            tbSinhVien_Display();
            ProcessCtr_Sinhvien(false);
            this.txtmasinhvien.setText(null);
            this.txttensinhvien.setText(null);
            this.btnAdd_Sinhvien.setEnabled(true);
        }
    }//GEN-LAST:event_btnEdit_SinhvienActionPerformed

    private void btnDel_SinhvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel_SinhvienActionPerformed
        // TODO add your handling code here:
        if (this.txtmasinhvien.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Bạn cần chọn sinh viên muốn xóa!", "Thông báo", 1);
        } else {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sinh viên " + masinhvien + " hay không?", "Thông báo", 2) == 0) {
                Sinhvien.DeleteSinhvien(masinhvien);
            }
            tbSinhVien_Display();
            ProcessCtr_Sinhvien(false);
            this.txttensinhvien.setText(null);
            this.txtmasinhvien.setText(null);
            this.btnAdd_Sinhvien.setEnabled(true);

        }
    }//GEN-LAST:event_btnDel_SinhvienActionPerformed

    private void btnrefresh_SinhvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefresh_SinhvienActionPerformed
        // TODO add your handling code here:
        this.txttimkiem_sinhvien.setText(null);
        tbSinhVien_Display();
        ProcessCtr_Sinhvien(false);
        this.btnAdd_Sinhvien.setEnabled(true);
    }//GEN-LAST:event_btnrefresh_SinhvienActionPerformed

    private void btn_LamMoiBangDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiBangDiemActionPerformed
        txt_bd_MaSinhVienSearch.setText("");
        txt_bd_MaMonHocSearch.setText("");
        txt_bd_TenMonHocSearch.setText("");
        txt_bd_TenSinhVienSearch.setText("");
        cbb_bd_XepLoai.setSelectedIndex(0);
        fillDataBangDiem2Table();
    }//GEN-LAST:event_btn_LamMoiBangDiemActionPerformed

    private void tbl_MonHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_MonHocMouseClicked

        int row = this.tbl_MonHoc.getSelectedRow();// lấy dòng hiên tại đang nhấn chuột
        //Lấy giá trị ở dòng vừa nhấn chuột và chuyển sang dạng String
        String mamonhoc = (this.tbl_MonHoc.getModel().getValueAt(row, 3)).toString();
        mon.setMamonhoc(mamonhoc);
        txt_mamonsearch.setText(mamonhoc);

        String tenmonhoc = (this.tbl_MonHoc.getModel().getValueAt(row, 2)).toString();
        mon.setTenmonhoc(tenmonhoc);
        txt_tenmonsearch.setText(tenmonhoc);
        btn_ThemMonHoc.setEnabled(false);
    }//GEN-LAST:event_tbl_MonHocMouseClicked

    private void btn_lammoitimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoitimkiemActionPerformed
        // TODO add your handling code here:
        clearSearchMonHoc();
    }//GEN-LAST:event_btn_lammoitimkiemActionPerformed

    private void btnrefresh_KhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefresh_KhoaActionPerformed
        // TODO add your handling code here:
        this.txttimkiem_khoa.setText(null);
        tbKhoa_Display();
        ProcessCtr_Khoa(false);
        this.btnAdd_Khoa.setEnabled(true);
    }//GEN-LAST:event_btnrefresh_KhoaActionPerformed

    private void btnDel_KhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel_KhoaActionPerformed
        // TODO add your handling code here:
        if (this.txtmakhoa.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Bạn cần chọn Khoa muốn xóa!", "Thông báo", 1);
        } else {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa khoa " + makhoa + " hay không?", "Thông báo", 2) == 0) {
                Khoa.DeleteKhoa(makhoa);
            }
            tbKhoa_Display();
            ProcessCtr_Khoa(false);
            this.txttenkhoa.setText(null);
            this.txtmakhoa.setText(null);
            this.btnAdd_Khoa.setEnabled(true);
            jComboBox_Khoa.removeAllItems();
            try {
                UpdateComboBox.LoadComboBox(sql3, jComboBox_Khoa);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDel_KhoaActionPerformed

    private void btnEdit_KhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit_KhoaActionPerformed
        // TODO add your handling code here:
        if (this.txtmakhoa.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Mã khoa không được để trống!", "Thông báo", 1);
        } else if (this.txtmakhoa.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Mã khoa không được vượt quá 20 ký tự!", "Thông báo", 1);
        } else if (this.txttenkhoa.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Tên khoa không được để trống!", "Thông báo", 1);
        } else if (this.txttenkhoa.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Mã khoa không được vượt quá 100 ký tự!", "Thông báo", 1);
        } else {
            Khoa.UpdateKhoa(makhoa, this.txtmakhoa.getText().trim(), this.txttenkhoa.getText());
            tbKhoa_Display();
            ProcessCtr_Khoa(false);
            this.txttenkhoa.setText(null);
            this.txtmakhoa.setText(null);
            this.btnAdd_Khoa.setEnabled(true);
            jComboBox_Khoa.removeAllItems();
            try {
                UpdateComboBox.LoadComboBox(sql3, jComboBox_Khoa);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEdit_KhoaActionPerformed

    private void btnAdd_KhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_KhoaActionPerformed
        // TODO add your handling code here:
        if (this.txtmakhoa.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Mã khoa không được để trống!", "Thông báo", 1);
        } else if (this.txtmakhoa.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Mã khoa không được vượt quá 20 ký tự!", "Thông báo", 1);
        } else if (this.txttenkhoa.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Tên khoa không được để trống!", "Thông báo", 1);
        } else if (this.txttenkhoa.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Mã khoa không được vượt quá 100 ký tự!", "Thông báo", 1);
        } else {
            Khoa.InsertKhoa(this.txtmakhoa.getText().trim(), this.txttenkhoa.getText());
            tbKhoa_Display();
            ProcessCtr_Khoa(false);
            this.txttenkhoa.setText(null);
            this.txtmakhoa.setText(null);
            this.btnAdd_Khoa.setEnabled(true);
            jComboBox_Khoa.removeAllItems();
            try {
                UpdateComboBox.LoadComboBox(sql3, jComboBox_Khoa);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnAdd_KhoaActionPerformed

    private void tbKhoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhoaMouseClicked
        // TODO add your handling code here:
        ProcessCtr_Khoa(true);
        try {
            int row = this.tbKhoa.getSelectedRow();// lấy dòng hiên tại đang nhấn chuột
            //Lấy giá trị ở dòng vừa nhấn chuột và chuyển sang dạng String
            String IDrow = (this.tbKhoa.getModel().getValueAt(row, 0)).toString();
            String sql1 = "SELECT * FROM quanlydiem.khoa where makhoa = '" + IDrow + "'";
            //đọc dữ liệu tại dòng được chọn
            ResultSet rs = UpdateTable.showTextField(sql1);
            if (rs.next())//kiểm tra nếu có dữ liệu
            {
                makhoa = rs.getString("makhoa");
                this.txtmakhoa.setText(rs.getString("makhoa"));
                this.txttenkhoa.setText(rs.getString("tenkhoa"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tbKhoaMouseClicked

    private void btnLook_KhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLook_KhoaActionPerformed
        // TODO add your handling code here:
        if (this.txttimkiem_khoa.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập từ khóa muốn tìm kiếm!", "Thông báo", 1);
        } else {
            String sql = "SELECT makhoa as 'MÃ KHOA',tenkhoa as 'TÊN KHOA' FROM quanlydiem.khoa where makhoa like '%" + this.txttimkiem_khoa.getText() + "%' or tenkhoa like '%" + this.txttimkiem_khoa.getText() + "%'";
            UpdateTable.LoadData(sql, tbKhoa);
            this.lbTongKhoa.setText("Tổng số có " + this.tbKhoa.getRowCount() + " khoa");
        }
    }//GEN-LAST:event_btnLook_KhoaActionPerformed

    private void btnNew_KhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew_KhoaActionPerformed
        // TODO add your handling code here:
        ProcessCtr_Khoa(false);
        this.btnAdd_Khoa.setEnabled(true);
        this.txttenkhoa.setText(null);
        this.txtmakhoa.setText(null);
    }//GEN-LAST:event_btnNew_KhoaActionPerformed

    private void btnrefresh_LopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefresh_LopActionPerformed
        // TODO add your handling code here:
        this.txttimkiem_lop.setText(null);
        tbLop_Display();
        ProcessCtr_Lop(false);
        this.btnAdd_Lop.setEnabled(true);
    }//GEN-LAST:event_btnrefresh_LopActionPerformed

    private void btnDel_LopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel_LopActionPerformed
        // TODO add your handling code here:
        if (this.txtmalop.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Bạn cần chọn Lớp muốn xóa!", "Thông báo", 1);
        } else {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa lớp " + malop + " hay không?", "Thông báo", 2) == 0) {
                LopHoc.DeleteLop(malop);
            }
            tbLop_Display();
            ProcessCtr_Lop(false);
            this.txttenlop.setText(null);
            this.txtmalop.setText(null);
            this.btnAdd_Lop.setEnabled(true);
            jComboBox_Lop.removeAllItems();
            try {
                UpdateComboBox.LoadComboBox(sql5, jComboBox_Lop);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnDel_LopActionPerformed

    private void btnEdit_LopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit_LopActionPerformed
        // TODO add your handling code here:
        if (this.txtmalop.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Mã lớp không được để trống!", "Thông báo", 1);
        } else if (this.txtmalop.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Mã lớp không được vượt quá 20 ký tự!", "Thông báo", 1);
        } else if (this.txttenlop.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Tên lớp không được để trống!", "Thông báo", 1);
        } else if (this.txttenlop.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Mã lớp không được vượt quá 100 ký tự!", "Thông báo", 1);
        } else if ((String) jComboBox_Khoa.getSelectedItem() == "Chọn mã khoa...") {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn mã khoa!", "Thông báo", 1);
        } else {
            LopHoc.UpdateLop(malop, this.txtmalop.getText().trim(), this.txttenlop.getText(), (String) jComboBox_Khoa.getSelectedItem());
            tbLop_Display();
            ProcessCtr_Lop(false);
            this.txtmalop.setText(null);
            this.txttenlop.setText(null);
            this.btnAdd_Lop.setEnabled(true);
            jComboBox_Lop.removeAllItems();
            try {
                UpdateComboBox.LoadComboBox(sql5, jComboBox_Lop);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEdit_LopActionPerformed

    private void btnAdd_LopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd_LopActionPerformed
        // TODO add your handling code here:

        if (this.txtmalop.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Mã lớp không được để trống!", "Thông báo", 1);
        } else if (this.txtmalop.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Mã lớp không được vượt quá 20 ký tự!", "Thông báo", 1);
        } else if (this.txttenlop.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Tên lớp không được để trống!", "Thông báo", 1);
        } else if (this.txttenlop.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Mã lớp không được vượt quá 100 ký tự!", "Thông báo", 1);
        } else if ((String) jComboBox_Khoa.getSelectedItem() == "Chọn mã khoa...") {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn mã khoa!", "Thông báo", 1);
        } else {
            try {
                LopHoc.InsertLop(this.txtmalop.getText().trim(), this.txttenlop.getText(), (String) jComboBox_Khoa.getSelectedItem());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            tbLop_Display();
            ProcessCtr_Lop(false);
            this.txtmalop.setText(null);
            this.txttenlop.setText(null);
            this.btnAdd_Lop.setEnabled(true);
            jComboBox_Lop.removeAllItems();
            try {
                UpdateComboBox.LoadComboBox(sql5, jComboBox_Lop);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAdd_LopActionPerformed

    private void tbLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLopMouseClicked
        // TODO add your handling code here:
        ProcessCtr_Lop(true);
        try {
            int row = this.tbLop.getSelectedRow();// lấy dòng hiên tại đang nhấn chuột
            //Lấy giá trị ở dòng vừa nhấn chuột và chuyển sang dạng String
            String IDrow = (this.tbLop.getModel().getValueAt(row, 0)).toString();
            String sql1 = "SELECT * FROM quanlydiem.lophoc where malop = '" + IDrow + "'";
            //đọc dữ liệu tại dòng được chọn
            ResultSet rs = UpdateTable.showTextField(sql1);
            if (rs.next())//kiểm tra nếu có dữ liệu
            {
                malop = rs.getString("malop");
                this.txtmalop.setText(rs.getString("malop"));
                this.txttenlop.setText(rs.getString("tenlop"));
                this.jComboBox_Khoa.setSelectedItem(rs.getString("makhoa"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tbLopMouseClicked

    private void btnLook_LopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLook_LopActionPerformed
        // TODO add your handling code here:
        if (this.txttimkiem_lop.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập từ khóa muốn tìm kiếm!", "Thông báo", 1);
        } else {
            String sql = "SELECT malop as 'MÃ LỚP',tenlop as 'TÊN LỚP',makhoa as 'MÃ KHOA' FROM quanlydiem.lophoc where malop like '%" + this.txttimkiem_lop.getText() + "%' or tenlop like '%" + this.txttimkiem_lop.getText() + "%' or makhoa like '%" + this.txttimkiem_lop.getText() + "%'";
            UpdateTable.LoadData(sql, tbLop);
            this.lbTongLop.setText("Tổng số có " + this.tbLop.getRowCount() + " lớp học");
        }
    }//GEN-LAST:event_btnLook_LopActionPerformed

    private void btnNew_LopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew_LopActionPerformed
        // TODO add your handling code here:
        ProcessCtr_Lop(false);
        this.btnAdd_Lop.setEnabled(true);
        this.txtmalop.setText(null);
        this.txttenlop.setText(null);
    }//GEN-LAST:event_btnNew_LopActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát chương trình hay không?", "Thông báo", 2) == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void txtmakhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmakhoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmakhoaActionPerformed

    //Hàm Làm Mới đk tìm kiếm
    public void clearSearchMonHoc() {
        txt_tenmonsearch.setText("");
        txt_mamonsearch.setText("");
        btn_ThemMonHoc.setEnabled(true);
        fillDataMonHoc2Table();
    }

    // Hàm Fill bảng điểm ra jtable
    public void fillDataBangDiem2Table() {
        ArrayList<BangDiemModel> lstBangDiem = new ArrayList<BangDiemModel>();
        BangDiem progess = new BangDiem();
        // Lấy thông tin bảng điểm
        try {
            lstBangDiem = progess.SearchBangDiem(txt_bd_MaSinhVienSearch.getText(), txt_bd_TenSinhVienSearch.getText(), txt_bd_TenMonHocSearch.getText(), txt_bd_MaMonHocSearch.getText(), cbb_bd_XepLoai.getSelectedItem().toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        lbl_bd_Status.setText("Kết quả tổng số: " + lstBangDiem.size() + " bản ghi");
        tableBangDiemModel.setRowCount(0);
        Vector newRow;
        for (BangDiemModel item : lstBangDiem) {
            newRow = new Vector();
            //newRow.add(lstBangDiem.indexOf(item) + 1);
            newRow.add(item.getTensinhvien());
            newRow.add(item.getTenmonhoc());
            newRow.add(item.getDiemkiemtra_1() + " , " + item.getDiemkiemtra_2());
            newRow.add(item.getDiemthicuoiky());
            newRow.add(item.getDiemtongket());
            newRow.add(item.getXeploai());
            newRow.add(item.getMasinhvien());
            newRow.add(item.getId_mon());
            newRow.add(item.getMamonhoc());
            tableBangDiemModel.addRow(newRow);
        }
    }

    // Hàm fill data môn học ra table toàn viết
    public void fillDataMonHoc2Table() {
        String tenmonhoc = txt_tenmonsearch.getText();
        String idmon = txt_mamonsearch.getText();
        ArrayList<MonHocModel> lstMonHoc = new ArrayList<MonHocModel>();
        MonHoc pst = new MonHoc();
        try {
            lstMonHoc = pst.SearchMonHoc(tenmonhoc, idmon);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JfrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        lbl_tongmonhoc.setText("Kết quả tìm kiếm: Tổng số " + lstMonHoc.size() + " bản ghi");
        //truoc khi insert vao thi clear tableModel di
        tableModel.setRowCount(0);
        Vector newRow;
        for (MonHocModel item : lstMonHoc) {
            newRow = new Vector();
            newRow.add(item.getMamonhoc());
            newRow.add(lstMonHoc.indexOf(item) + 1);
            newRow.add(item.getTenmonhoc());
            newRow.add(item.getId_mon());
            tableModel.addRow(newRow);
        }
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
            java.util.logging.Logger.getLogger(JfrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd_Khoa;
    private javax.swing.JButton btnAdd_Lop;
    private javax.swing.JButton btnAdd_Sinhvien;
    private javax.swing.JButton btnDel_Khoa;
    private javax.swing.JButton btnDel_Lop;
    private javax.swing.JButton btnDel_Sinhvien;
    private javax.swing.JButton btnEdit_Khoa;
    private javax.swing.JButton btnEdit_Lop;
    private javax.swing.JButton btnEdit_Sinhvien;
    private javax.swing.JButton btnLook_Khoa;
    private javax.swing.JButton btnLook_Lop;
    private javax.swing.JButton btnLook_Sinhvien;
    private javax.swing.JButton btnNew_Khoa;
    private javax.swing.JButton btnNew_Lop;
    private javax.swing.JButton btnNew_Sinhvien;
    private javax.swing.JButton btn_CapNhatMonHoc;
    private javax.swing.JButton btn_LamMoiBangDiem;
    private javax.swing.JButton btn_ThemMonHoc;
    private javax.swing.JButton btn_TimKiemMonHoc;
    private javax.swing.JButton btn_XoaMonHoc;
    private javax.swing.JButton btn_bd_CapNhat;
    private javax.swing.JButton btn_bd_ThemMoi;
    private javax.swing.JButton btn_bd_TimKiem;
    private javax.swing.JButton btn_bd_Xoa;
    private javax.swing.JButton btn_lammoitimkiem;
    private javax.swing.JButton btnrefresh_Khoa;
    private javax.swing.JButton btnrefresh_Lop;
    private javax.swing.JButton btnrefresh_Sinhvien;
    private javax.swing.JComboBox<String> cbb_bd_XepLoai;
    private javax.swing.JComboBox<String> jComboBox_Khoa;
    private javax.swing.JComboBox<String> jComboBox_Lop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel jlabel14;
    private javax.swing.JLabel jlabel16;
    private javax.swing.JLabel lbTongKhoa;
    private javax.swing.JLabel lbTongLop;
    private javax.swing.JLabel lbTongSV;
    private javax.swing.JLabel lbl_bd_Status;
    private javax.swing.JLabel lbl_tongmonhoc;
    private Utility.newtab newtab1;
    private Utility.newtab newtab2;
    private Utility.newtab newtab3;
    private Utility.newtab newtab4;
    private javax.swing.JTable tbKhoa;
    private javax.swing.JTable tbLop;
    private javax.swing.JTable tbSinhvien;
    private javax.swing.JTable tbl_BangDiem;
    private javax.swing.JTable tbl_MonHoc;
    private javax.swing.JTextField txt_bd_MaMonHocSearch;
    private javax.swing.JTextField txt_bd_MaSinhVienSearch;
    private javax.swing.JTextField txt_bd_TenMonHocSearch;
    private javax.swing.JTextField txt_bd_TenSinhVienSearch;
    private javax.swing.JTextField txt_mamonsearch;
    private javax.swing.JTextField txt_tenmonsearch;
    private javax.swing.JTextField txtmakhoa;
    private javax.swing.JTextField txtmalop;
    private javax.swing.JTextField txtmasinhvien;
    private javax.swing.JTextField txttenkhoa;
    private javax.swing.JTextField txttenlop;
    private javax.swing.JTextField txttensinhvien;
    private javax.swing.JTextField txttimkiem_khoa;
    private javax.swing.JTextField txttimkiem_lop;
    private javax.swing.JTextField txttimkiem_sinhvien;
    // End of variables declaration//GEN-END:variables
}
