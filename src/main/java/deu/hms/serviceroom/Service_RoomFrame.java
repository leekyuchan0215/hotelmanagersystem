package deu.hms.serviceroom;

import deu.hms.login.MainFrame_Master;
import deu.hms.login.MainFrame_Staff;
import deu.hms.management.ManagementFrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Service_RoomFrame extends javax.swing.JFrame {

    private String userType;  // "manager" 또는 "staff"를 저장하는 변수

    public Service_RoomFrame(String userType) {
        this.userType = userType;
        System.out.println("User type in constructor: " + userType);
        initComponents();
        loadMenuList();
        loadCheckedInRooms();  // 체크인한 객실 번호 로드
    }


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
        yearText = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        monthText = new javax.swing.JComboBox<>();
        dayText = new javax.swing.JComboBox<>();
        hourCombo = new javax.swing.JComboBox<>();
        minuteCombo = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        reservationDialogBtn = new javax.swing.JButton();
        howpayDialog = new javax.swing.JComboBox<>();
        reservationCheckDialog = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reservationCheckTable = new javax.swing.JTable();
        backDialogBtn = new javax.swing.JButton();
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
        reservationCheck = new javax.swing.JButton();
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
        roomListCombo = new javax.swing.JComboBox<>();

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

        yearText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearTextActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel20.setText("년");

        jLabel21.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel21.setText("월");

        jLabel22.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel22.setText("일");

        monthText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        monthText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthTextActionPerformed(evt);
            }
        });

        dayText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        hourCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        hourCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourComboActionPerformed(evt);
            }
        });

        minuteCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "10", "20", "30", "40", "50" }));

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
                        .addComponent(hourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yearText)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(monthText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dayText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(minuteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(yearText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(monthText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(hourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minuteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel25.setText("룸서비스 예약");

        jButton3.setText("취소");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        reservationDialogBtn.setText("예약");
        reservationDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservationDialogBtnActionPerformed(evt);
            }
        });

        howpayDialog.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "객실 등록", "카드", "현금" }));
        howpayDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                howpayDialogActionPerformed(evt);
            }
        });

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
                        .addGap(182, 182, 182)
                        .addComponent(howpayDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(reservationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reservationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(howpayDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel3.setText("예약 확인");

        reservationCheckTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "서비스", "호수", "날짜", "시간", "메뉴", "총 금액", "결제유형"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(reservationCheckTable);
        if (reservationCheckTable.getColumnModel().getColumnCount() > 0) {
            reservationCheckTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            reservationCheckTable.getColumnModel().getColumn(1).setPreferredWidth(80);
            reservationCheckTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            reservationCheckTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            reservationCheckTable.getColumnModel().getColumn(4).setPreferredWidth(300);
        }

        backDialogBtn.setText("취소");
        backDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backDialogBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reservationCheckDialogLayout = new javax.swing.GroupLayout(reservationCheckDialog.getContentPane());
        reservationCheckDialog.getContentPane().setLayout(reservationCheckDialogLayout);
        reservationCheckDialogLayout.setHorizontalGroup(
            reservationCheckDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationCheckDialogLayout.createSequentialGroup()
                .addGroup(reservationCheckDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationCheckDialogLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1))
                    .addGroup(reservationCheckDialogLayout.createSequentialGroup()
                        .addGroup(reservationCheckDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reservationCheckDialogLayout.createSequentialGroup()
                                .addGap(351, 351, 351)
                                .addComponent(backDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reservationCheckDialogLayout.createSequentialGroup()
                                .addGap(352, 352, 352)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 346, Short.MAX_VALUE)))
                .addContainerGap())
        );
        reservationCheckDialogLayout.setVerticalGroup(
            reservationCheckDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationCheckDialogLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(backDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
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

        reservationCheck.setText("예약확인");
        reservationCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservationCheckActionPerformed(evt);
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
                    .addComponent(reservationBtn)
                    .addComponent(reservationCheck))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payBtn)
                    .addComponent(reservationCheck))
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

        roomListCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roomListCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomListComboActionPerformed(evt);
            }
        });

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
                        .addGap(70, 70, 70)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(101, 101, 101))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(roomListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                                .addComponent(roomListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void loadCheckedInRooms() {
        String filePath = "checked_in_customers.txt"; // 파일 경로
        Set<String> roomNumbers = new HashSet<>(); // 중복 제거를 위한 Set 사용

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // ','로 분리
                if (data.length >= 4) {
                    String roomNumber = data[2].split(":")[1].trim(); // 객실 번호 가져오기 (예: "객실 번호: 103"에서 103 추출)
                    roomNumbers.add(roomNumber); // 중복 없이 객실 번호 추가
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // 파일 읽기 오류 처리
        }

        // roomListCombo에 객실 번호 추가
        roomListCombo.removeAllItems(); // 기존 아이템 제거
        for (String roomNumber : roomNumbers) {
            roomListCombo.addItem(roomNumber); // ComboBox에 객실 번호 추가
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
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();

        // orderListTable이 비어있는지 확인
        if (model.getRowCount() == 0) {
            // 테이블에 항목이 없을 경우 경고 메시지 표시
            JOptionPane.showMessageDialog(this, "메뉴를 담아야합니다.", "경고", JOptionPane.WARNING_MESSAGE);
            return; // 메서드 종료
        }

        // 결제 진행
        String howpay = (String) howPayComboBox.getSelectedItem();
        int totalprice = calculateTotalPrice();
        String selectedRoomNumber = (String) roomListCombo.getSelectedItem();

        if (selectedRoomNumber == null) {
            JOptionPane.showMessageDialog(this, "객실 번호를 선택해 주세요.", "오류", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // "룸서비스", roomListCombo 값, orderListTable 메뉴/수량, 총 금액, 결제 방식
        StringBuilder serviceData = new StringBuilder("룸서비스");
        serviceData.append(", ").append(selectedRoomNumber);

        // orderListTable에 있는 메뉴/수량 정보를 추가 (각 메뉴 사이에 슬래시로 구분)
        StringBuilder menuList = new StringBuilder();
        for (int i = 0; i < model.getRowCount(); i++) {
            String menu = (String) model.getValueAt(i, 0);
            int quantity = (int) model.getValueAt(i, 2);
            if (i > 0) {
                menuList.append("/"); // 각 메뉴 사이에 슬래시 추가
            }
            menuList.append(menu).append("/").append(quantity);
        }

        // 메뉴 정보 추가 (쉼표로 구분)
        serviceData.append(", ").append(menuList.toString());

        // 총 금액과 결제 방식 추가
        serviceData.append(", ").append(totalprice);
        serviceData.append(", ").append(howpay);

        // 파일에 저장
        saveToFile("use_service.txt", serviceData.toString());

        // 결제 완료 메시지
        JOptionPane.showMessageDialog(this, totalprice + "원 결제되었습니다.");

        // 주문 목록 초기화
        model.setRowCount(0);
        priceText.setText("0");
    }

// 총 금액 계산 메서드
    private int calculateTotalPrice() {
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();
        int totalprice = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            String money = (String) model.getValueAt(i, 1);
            int count = (int) model.getValueAt(i, 2);
            int a = Integer.parseInt(money);
            totalprice += a * count;
        }

        return totalprice;
    }

// 파일에 데이터 저장 메서드
    private void saveToFile(String filePath, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace(); // 파일 쓰기 오류 처리
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
        // orderListTable의 모델 가져오기
        DefaultTableModel orderModel = (DefaultTableModel) orderListTable.getModel();

        // orderListTable이 비어 있는지 확인
        if (orderModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "메뉴를 담아주세요.", "오류", JOptionPane.WARNING_MESSAGE);
        } else {
            // 다이얼로그 설정
            reservationDialog.setSize(700, 500);  // 다이얼로그 크기 설정
            reservationDialog.setLocationRelativeTo(this);  // 부모 컴포넌트를 기준으로 중앙에 배치
            reservationDialog.setTitle("룸 서비스 예약");  // 다이얼로그 제목 설정
            reservationDialog.setModal(false);  // 비모달로 설정 (부모 창과 상호작용 가능)
            reservationDialog.setVisible(true);  // 다이얼로그 표시
            reservationDialog.toFront();  // 다이얼로그를 화면 최상위로 가져오기

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
        }
    }//GEN-LAST:event_reservationBtnActionPerformed

    private void yearTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearTextActionPerformed

    private void roomListComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomListComboActionPerformed
        // TODO add your handling code here:
        // roomListCombo에서 선택된 방 번호 가져오기
        String selectedRoomNumber = (String) roomListCombo.getSelectedItem();

        if (selectedRoomNumber == null) {
            return;  // 선택된 방 번호가 없으면 메서드 종료
        }

        String filePath = "reservations.txt"; // 예약자 정보가 담긴 파일 경로

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // ','로 데이터를 분리
                if (data.length >= 7) {
                    String roomNumber = data[5].trim(); // 6번째 값인 방 번호 가져오기

                    // 선택된 방 번호와 파일의 방 번호가 일치하는지 확인
                    if (selectedRoomNumber.equals(roomNumber)) {
                        String dateValue = data[6].trim(); // 7번째 값인 날짜 가져오기 (예: 2024-01-01)
                        String year = dateValue.split("-")[0]; // '-'로 분리하여 연도 추출
                        yearText.setText(year); // 연도를 yearText에 설정
                        break; // 해당 방 번호에 대해 첫 번째로 일치하는 항목만 처리
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // 파일 읽기 오류 처리
        }
    }//GEN-LAST:event_roomListComboActionPerformed

    private void monthTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthTextActionPerformed

    private void reservationDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationDialogBtnActionPerformed
        // "룸서비스"라는 고정 문자열 추가
        StringBuilder reservationData = new StringBuilder("룸서비스");

        // yearText, monthText, dayText, hourCombo, minuteCombo, roomListCombo 값 가져오기
        reservationData.append(", ").append((String) roomListCombo.getSelectedItem());
        reservationData.append(", ").append(yearText.getText());
        reservationData.append(", ").append((String) monthText.getSelectedItem());
        reservationData.append(", ").append((String) dayText.getSelectedItem());
        reservationData.append(", ").append((String) hourCombo.getSelectedItem()); // hourCombo에 해당하는 콤보박스
        reservationData.append(", ").append((String) minuteCombo.getSelectedItem()); // minuteCombo에 해당하는 콤보박스

        // reservationTable의 메뉴와 수량을 문자열로 생성
        DefaultTableModel reservationModel = (DefaultTableModel) reservationTable.getModel();
        StringBuilder menuList = new StringBuilder();
        for (int i = 0; i < reservationModel.getRowCount(); i++) {
            String menu = (String) reservationModel.getValueAt(i, 0); // 메뉴
            int quantity = (int) reservationModel.getValueAt(i, 2); // 수량
            if (i > 0) {
                menuList.append("/"); // 각 메뉴 사이에 슬래시 추가
            }
            menuList.append(menu).append("/").append(quantity);
        }
        reservationData.append(", ").append(menuList.toString());

        // priceText에서 총 금액을 가져와 추가
        reservationData.append(", ").append(priceText.getText());

        // howPayDialog에서 선택한 결제 방식 추가
        String howPay = (String) howpayDialog.getSelectedItem(); // howPayComboBox에서 결제 방식을 가져옴
        reservationData.append(", ").append(howPay);

        // 파일에 저장
        String filePath = "service_reservation_list.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(reservationData.toString());
            bw.newLine(); // 줄 바꿈 추가 -> 기존 값 아래에 새 값을 저장
            JOptionPane.showMessageDialog(this, "예약 정보가 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace(); // 파일 쓰기 오류 처리
        }
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();
        model.setRowCount(0);
        priceText.setText("0");
        reservationDialog.dispose();
    }//GEN-LAST:event_reservationDialogBtnActionPerformed

    private void hourComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourComboActionPerformed

    private void howpayDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howpayDialogActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_howpayDialogActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        reservationDialog.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void reservationCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationCheckActionPerformed
        // 다이얼로그 설정
        reservationCheckDialog.setSize(800, 500);  // 다이얼로그 크기 설정
        reservationCheckDialog.setLocationRelativeTo(this);  // 부모 컴포넌트를 기준으로 중앙에 배치
        reservationCheckDialog.setTitle("예약 확인");  // 다이얼로그 제목 설정
        reservationCheckDialog.setModal(false);  // 비모달로 설정 (부모 창과 상호작용 가능)
        reservationCheckDialog.setVisible(true);  // 다이얼로그 표시
        reservationCheckDialog.toFront();  // 다이얼로그를 화면 최상위로 가져오기

        // 테이블 모델 초기화
        DefaultTableModel reservationModel = (DefaultTableModel) reservationCheckTable.getModel();
        reservationModel.setRowCount(0); // 기존 데이터 초기화

        // 파일 경로 설정
        String filePath = "service_reservation_list.txt";

        // 파일 읽기 및 테이블에 추가
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // 쉼표로 구분된 데이터 분리

                if (data.length >= 10) {
                    // 날짜 생성: 3번째, 4번째, 5번째 값 합쳐서 "2024년 1월 1일" 형식으로 생성
                    String date = data[2] + "년 " + data[3] + "월 " + data[4] + "일";

                    // 시간 생성: 6번째, 7번째 값 합쳐서 "07시 00분" 형식으로 생성
                    String time = data[5] + "시 " + data[6] + "분";

                    // 서비스 종류 (예: "룸서비스" 또는 기타)
                    String serviceType = data[0];

                    // 객실 번호
                    String roomNumber = data[1];
                    
                    //메뉴
                    String menuRaw = data[7];
                    String[] menuItems=menuRaw.split("/");
                    StringBuilder menuFormatted = new StringBuilder();
                    
                    for(int i=0; i<menuItems.length; i+=2){
                        String itemName = menuItems[i];
                        String itemQuantity = menuItems[i+1];
                        menuFormatted.append(itemName).append(" ").append(itemQuantity).append("개");
                        if(i+2 < menuItems.length){
                            menuFormatted.append(" / ");  // 다음  항목이 있다면 구분자 추가
                        }
                    }

                    // 총 금액: 9번째 값
                    String totalAmount = data[8];

                    // 결제 방식: 10번째 값
                    String paymentMethod = data[9];

                    // 테이블에 추가할 데이터 배열 생성
                    Object[] rowData = {
                        serviceType,  //1번째 열: 서비스 종류
                        roomNumber, // 2번째 열: 객실번호
                        date, // 3번째 열: 날짜
                        time, // 4번째 열: 시간
                        menuFormatted.toString(), // 5번째 열: 메뉴
                        totalAmount, // 6번째 열: 총 금액
                        paymentMethod // 7번째 열: 결제 방식
                    };

                    // 테이블 모델에 행 추가
                    reservationModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // 파일 읽기 오류 처리
        }
    }//GEN-LAST:event_reservationCheckActionPerformed

    private void backDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backDialogBtnActionPerformed
        // TODO add your handling code here:
        reservationCheckDialog.dispose();
    }//GEN-LAST:event_backDialogBtnActionPerformed

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
    private javax.swing.JButton backDialogBtn;
    private javax.swing.JComboBox<String> dayText;
    private javax.swing.JComboBox<String> hourCombo;
    private javax.swing.JComboBox<String> howPayComboBox;
    private javax.swing.JComboBox<String> howpayDialog;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable menuListTable;
    private javax.swing.JButton minusBtn;
    private javax.swing.JComboBox<String> minuteCombo;
    private javax.swing.JComboBox<String> monthText;
    private javax.swing.JTable orderListTable;
    private javax.swing.JButton payBtn;
    private javax.swing.JTextField priceText;
    private javax.swing.JButton reservationBtn;
    private javax.swing.JButton reservationCheck;
    private javax.swing.JDialog reservationCheckDialog;
    private javax.swing.JTable reservationCheckTable;
    private javax.swing.JDialog reservationDialog;
    private javax.swing.JButton reservationDialogBtn;
    private javax.swing.JTable reservationTable;
    private javax.swing.JButton resetBtn;
    private javax.swing.JComboBox<String> roomListCombo;
    private javax.swing.JTextField yearText;
    // End of variables declaration//GEN-END:variables
}
