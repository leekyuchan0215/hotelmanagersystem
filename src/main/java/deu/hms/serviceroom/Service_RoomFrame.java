package deu.hms.serviceroom;

import deu.hms.login.MainFrame_Master;
import deu.hms.login.MainFrame_Staff;
import deu.hms.management.ManagementFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Service_RoomFrame extends javax.swing.JFrame {

    private String userType;  // "manager" 또는 "staff"를 저장하는 변수

    public Service_RoomFrame(String userType) {
        this.userType = userType;
        System.out.println("User type in constructor: " + userType);
        initComponents();
        loadMenuList(); // 메뉴 데이터 로드
    }

    //Warnings( "unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reservationDialog = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        reservationTable = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        priceText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        reservationBtn = new javax.swing.JButton();
        payBtn = new javax.swing.JButton();
        howPayComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        menuListTable = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        orderListTable = new javax.swing.JTable();
        minusBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        roomComboBox = new javax.swing.JComboBox<>();

        reservationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "메뉴", "가격", "수량"
            }
        ));
        jScrollPane2.setViewportView(reservationTable);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel18.setText("날짜 : ");

        jLabel19.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel19.setText("시간 : ");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel20.setText("년");

        jLabel21.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel21.setText("월");

        jLabel22.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel22.setText("일");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "10", "20", "30", "40", "50" }));

        jLabel23.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel23.setText("시");

        jLabel24.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel24.setText("분");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)))
                .addGap(7, 7, 7)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel25.setText("룸서비스 예약");

        jButton3.setText("취소");

        jButton4.setText("예약");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(287, 287, 287)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel17))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout reservationDialogLayout = new javax.swing.GroupLayout(reservationDialog.getContentPane());
        reservationDialog.getContentPane().setLayout(reservationDialogLayout);
        reservationDialogLayout.setHorizontalGroup(
            reservationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
            .addGroup(reservationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(reservationDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        reservationDialogLayout.setVerticalGroup(
            reservationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 422, Short.MAX_VALUE)
            .addGroup(reservationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(reservationDialogLayout.createSequentialGroup()
                    .addGap(0, 17, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 17, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel1.setText("룸서비스 이용");

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel2.setText("호수");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setText("금액");

        priceText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTextActionPerformed(evt);
            }
        });

        jLabel5.setText("결제");

        jLabel6.setText("예약");

        reservationBtn.setText("예약하기");
        reservationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservationBtnActionPerformed(evt);
            }
        });

        payBtn.setText("결제");
        payBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payBtnActionPerformed(evt);
            }
        });

        howPayComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "객실 등록", "카드", "현금" }));
        howPayComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                howPayComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(payBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(howPayComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(reservationBtn))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(reservationBtn)
                        .addComponent(howPayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(payBtn)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel7.setText("메뉴판");

        menuListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "메뉴", "가격"
            }
        ));
        jScrollPane3.setViewportView(menuListTable);

        addBtn.setText("담기");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        orderListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "메뉴", "가격", "수량"
            }
        ));
        jScrollPane4.setViewportView(orderListTable);
        if (orderListTable.getColumnModel().getColumnCount() > 0) {
            orderListTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        minusBtn.setText("빼기");
        minusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusBtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel8.setText("주문 확인");

        backBtn.setText("이전");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        resetBtn.setText("초기화");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        roomComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "101 ", "102 ", "103 ", "104 ", "105", "201 ", "202 ", "203 ", "204 ", "205", "301 ", "302 ", "303 ", "304 ", "305", "401 ", "402 ", "403 ", "404 ", "405", "501 ", "502 ", "503 ", "504 ", "505", "601 ", "602 ", "603 ", "604 ", "605", "701 ", "702 ", "703 ", "704 ", "705", "801 ", "802 ", "803 ", "804 ", "805", "901 ", "902 ", "903 ", "904 ", "905", "1001 ", "1002 ", "1003 ", "1004 ", "1005", "1101 ", "1102 ", "1103 ", "1104 ", "1105", "1201 ", "1202 ", "1203 ", "1204 ", "1205", "1301 ", "1302 ", "1303 ", "1304 ", "1305", "1401 ", "1402 ", "1403 ", "1404 ", "1405", "1501 ", "1502 ", "1503", "1504 ", "1505", "1601 ", "1602 ", "1603 ", "1604 ", "1605", "1701 ", "1702 ", "1703 ", "1704 ", "1705", "1801 ", "1802 ", "1803 ", "1804 ", "1805", "1901 ", "1902 ", "1903 ", "1904 ", "1905", "2001 ", "2002 ", "2003 ", "2004 ", "2005" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addBtn)
                                    .addComponent(minusBtn))
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(resetBtn)))
                        .addGap(0, 46, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(roomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(101, 101, 101)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(roomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel7))
                    .addComponent(jLabel8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(addBtn)
                        .addGap(44, 44, 44)
                        .addComponent(minusBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadMenuList() {
        String filePath = "menu_list.txt"; // 파일 경로
        DefaultTableModel model = (DefaultTableModel) menuListTable.getModel();
        model.setRowCount(0); // 기존 데이터 초기화

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // ','로 분리
                if (data.length >= 3 && "룸서비스".equals(data[0])) { // 첫 번째 값이 "룸서비스"인지 확인
                    String menu = data[1]; // 두 번째 값
                    String price = data[2]; // 세 번째 값
                    model.addRow(new Object[]{menu, price}); // 테이블에 추가
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // 파일 읽기 오류 처리
        }
    }

    private void priceTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTextActionPerformed
        // TODO add your handling code here:     
    }//GEN-LAST:event_priceTextActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        // 메뉴 테이블에서 선택된 행 가져오기
        int selectedRow = menuListTable.getSelectedRow();
        if (selectedRow == -1) {
            // 선택된 행이 없을 경우 경고 메시지
            javax.swing.JOptionPane.showMessageDialog(this, "추가할 메뉴를 선택하세요!", "알림", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 메뉴와 가격 데이터 읽기
        String menu = (String) menuListTable.getValueAt(selectedRow, 0); // 메뉴 이름
        String price = (String) menuListTable.getValueAt(selectedRow, 1); // 메뉴 가격

        // 주문 목록 테이블의 모델 가져오기
        DefaultTableModel orderModel = (DefaultTableModel) orderListTable.getModel();

        // 주문 목록에서 이미 존재하는 메뉴인지 확인
        boolean menuExists = false;
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            String existingMenu = (String) orderModel.getValueAt(i, 0); // 주문 테이블의 메뉴 이름
            if (menu.equals(existingMenu)) {
                // 이미 존재하면 수량 증가
                int quantity = (int) orderModel.getValueAt(i, 2); // 수량
                orderModel.setValueAt(quantity + 1, i, 2); // 수량 +1
                menuExists = true;
                break;
            }
        }

        // 새로운 메뉴라면 추가
        if (!menuExists) {
            orderModel.addRow(new Object[]{menu, price, 1}); // 메뉴, 가격, 초기 수량 1
        }

        // 총 금액 계산하여 priceText에 설정
        int totalprice = 0;
        for (int i = 0; i < orderListTable.getRowCount(); i++) {
            String money = (String) orderListTable.getValueAt(i, 1);  // orderTableList에 2번째 열 값 money에 저장
            int count = (int) orderListTable.getValueAt(i, 2);  // 수량 값 가져오기

            int a = Integer.parseInt(money);   // String을 int로 변경
            totalprice += a * count;  // 총 금액 계산
        }

        priceText.setText(String.valueOf(totalprice)); // 계산된 총 금액을 priceText에 설정
    }//GEN-LAST:event_addBtnActionPerformed

    private void minusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusBtnActionPerformed
        // 선택된 행의 인덱스를 가져오기
        int selectedRow = orderListTable.getSelectedRow();

        // 선택된 행이 없으면 경고 메시지 표시 후 종료
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "삭제할 행을 선택하세요!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // JTable의 모델 가져오기
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();

        // 3번째 열 (수량) 값 가져오기 (인덱스는 0부터 시작하므로 열 인덱스는 2)
        int quantity = (int) model.getValueAt(selectedRow, 2);

        if (quantity == 1) {
            // 수량이 1이면 행 삭제
            model.removeRow(selectedRow);
        } else if (quantity > 1) {
            // 수량이 2 이상이면 1 감소
            model.setValueAt(quantity - 1, selectedRow, 2);
        }

        // 총 금액 계산하여 priceText에 설정
        int totalprice = 0;
        for (int i = 0; i < orderListTable.getRowCount(); i++) {
            String money = (String) orderListTable.getValueAt(i, 1);  // orderTableList에 2번째 열 값 money에 저장
            int count = (int) orderListTable.getValueAt(i, 2);  // 수량 값 가져오기

            int a = Integer.parseInt(money);   // String을 int로 변경
            totalprice += a * count;  // 총 금액 계산
        }

        priceText.setText(String.valueOf(totalprice)); // 계산된 총 금액을 priceText에 설정
    }//GEN-LAST:event_minusBtnActionPerformed

    private void howPayComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howPayComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_howPayComboBoxActionPerformed

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBtnActionPerformed
        // TODO add your handling code here:
        String howpay = (String) howPayComboBox.getSelectedItem();
        if (howpay == "카드" || howpay == "현금") {
            DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();
            int totalprice = 0;
            for (int i = 0; i < orderListTable.getRowCount(); i++) {
                String money = (String) orderListTable.getValueAt(i, 1);  // orderTableList에 2번째 열 값 money에 저장
                int count = (int) orderListTable.getValueAt(i, 2);  // 수량 값 가져오기

                int a = Integer.parseInt(money);   // String을 int로 변경
                totalprice += a * count;  // 총 금액 계산
            }
            JOptionPane.showMessageDialog(this, totalprice + "원 결제가 완료되었습니다.");
            model.setRowCount(0);
            priceText.setText("0");
        } else {
            JOptionPane.showMessageDialog(this, "객실 등록하셨습니다.", "오류", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_payBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();
        model.setRowCount(0);
        priceText.setText("0");
    }//GEN-LAST:event_resetBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        System.out.println("User Type :  " + userType);
        if ("master".equals(userType)) {
            // 관리자 페이지로 이동
            MainFrame_Master mframe = new MainFrame_Master();
            mframe.setVisible(true);
        } else if ("staff".equals(userType)) {
            // 일반 직원 페이지로 이동
            MainFrame_Staff sframe = new MainFrame_Staff();
            sframe.setVisible(true);
        }
        this.dispose();  // 현재 창 닫기
    }//GEN-LAST:event_backBtnActionPerformed

    private void reservationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationBtnActionPerformed
        // TODO add your handling code here:

        // 다이얼로그 설정
        reservationDialog.setSize(700, 500);  // 다이얼로그 크기 설정
        reservationDialog.setLocationRelativeTo(this);  // 부모 컴포넌트를 기준으로 중앙에 배치
        reservationDialog.setTitle("룸 서비스 예약");  // 다이얼로그 제목 설정
        reservationDialog.setModal(false);  // 비모달로 설정 (부모 창과 상호작용 가능)
        reservationDialog.setVisible(true);  // 다이얼로그 표시
        reservationDialog.toFront();  // 다이얼로그를 화면 최상위로 가져오기

        // orderListTable의 모델 가져오기
        DefaultTableModel orderModel = (DefaultTableModel) orderListTable.getModel();

        // reservationTable의 모델 가져오기
        DefaultTableModel reservationModel = (DefaultTableModel) reservationTable.getModel();

        // 기존 데이터 제거 (필요한 경우)
        reservationModel.setRowCount(0);

        // orderListTable의 데이터를 reservationTable로 복사
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            Object[] rowData = new Object[orderModel.getColumnCount()];
            for (int j = 0; j < orderModel.getColumnCount(); j++) {
                rowData[j] = orderModel.getValueAt(i, j);
            }
            reservationModel.addRow(rowData);
        }
    }//GEN-LAST:event_reservationBtnActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Service_RoomFrame("master").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JComboBox<String> howPayComboBox;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable menuListTable;
    private javax.swing.JButton minusBtn;
    private javax.swing.JTable orderListTable;
    private javax.swing.JButton payBtn;
    private javax.swing.JTextField priceText;
    private javax.swing.JButton reservationBtn;
    private javax.swing.JDialog reservationDialog;
    private javax.swing.JTable reservationTable;
    private javax.swing.JButton resetBtn;
    private javax.swing.JComboBox<String> roomComboBox;
    // End of variables declaration//GEN-END:variables
}
