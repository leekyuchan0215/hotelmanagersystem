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
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class Service_RoomFrame extends javax.swing.JFrame {

    private String userType;  // "manager" 또는 "staff"를 저장하는 변수

    public Service_RoomFrame(String userType) {
        // 생성자의 매개변수로 전달된 사용자 유형(userType)을 클래스 필드에 저장
        this.userType = userType;
        // GUI 컴포넌트 초기화 메서드 호출 (버튼, 라벨, 텍스트 필드 등 생성 및 배치)
        initComponents();
        // 메뉴 리스트를 로드하여 메뉴 테이블에 표시
        loadMenuList();
        // 체크인된 객실 번호 목록을 로드하여 콤보박스에 표시
        loadCheckedInRooms();
    }

    // 메뉴 리스트 파일을 로드하여 테이블에 표시
    private void loadMenuList() {
        loadTableData("menu_list.txt", menuListTable, "룸서비스", 1, 2);
    }

    // 체크인된 객실 번호를 파일에서 불러와 콤보박스에 추가하는 메소드
    private void loadCheckedInRooms() {
        String filePath = "checked_in_customers.txt";  // 파일 경로
        Set<String> roomNumbers = new HashSet<>();  // 중복 방지를 위해 Set 사용

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    String roomNumber = data[2].split(":")[1].trim();  // 객실 번호 추출
                    roomNumbers.add(roomNumber);
                }
            }
        } catch (IOException e) {
        }

        roomListCombo.removeAllItems();  // 콤보박스 초기화
        for (String roomNumber : roomNumbers) {
            roomListCombo.addItem(roomNumber);   // 객실 번호 추가
        }
    }

    // 파일 데이터를 테이블에 로드하는 메소드
    private void loadTableData(String filePath, javax.swing.JTable table, String requiredType, int... columns) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();   // JTable의 테이블 모델을 가져옴
        model.setRowCount(0);  // 기존 테이블 데이터를 모두 삭제하여 초기화

        // 파일을 읽기 위해 BufferedReader 사용 (try-with-resources를 사용해 자동으로 닫히도록 설정)
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;  // 파일에서 읽어온 각 줄을 저장할 변수
            // 파일의 끝까지 한 줄씩 읽음
            while ((line = br.readLine()) != null) {
                // 읽어온 줄을 쉼표로 분리하여 문자열 배열로 저장
                String[] data = line.split(",");

                // 배열의 길이가 충분한지 확인하고, 필요한 서비스 유형(requiredType)과 일치하는지 확인
                if (data.length >= columns.length + 1 && requiredType.equals(data[0])) {
                    // 데이터를 저장할 행 배열 생성 (필요한 열 수 만큼)
                    Object[] rowData = new Object[columns.length];

                    // 필요한 열 데이터를 행 배열에 저장
                    for (int i = 0; i < columns.length; i++) {
                        rowData[i] = data[columns[i]];
                    }
                    model.addRow(rowData);  // 테이블 모델에 행을 추가하여 테이블에 표시
                }
            }
        } catch (IOException e) {
        }
    }

    private void showDialog(javax.swing.JDialog dialog, String title, int width, int height) {
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(this);  // 현재 프레임을 기준으로 위치 설정
        dialog.setTitle(title);
        dialog.setModal(false);
        dialog.setVisible(true);
        dialog.toFront();  // 다이얼로그를 맨 앞으로 가져옴
    }

    // 총 가격 계산 메소드
    private int calculateTotalPrice() {
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();
        int totalprice = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            int price = Integer.parseInt((String) model.getValueAt(i, 1));  // 가격 가져오기
            int count = (int) model.getValueAt(i, 2);  // 수량 가져오기
            totalprice += price * count;  // 가격 * 수량 계산
        }
        return totalprice;
    }

    // 파일에 데이터 저장 메소드
    private void saveToFile(String filePath, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(data);
            bw.newLine();  // 줄 바꿈 추가
        } catch (IOException e) {
        }
    }

    // 가격 텍스트 업데이트 메소드
    private void updatePriceText() {
        int totalprice = calculateTotalPrice();  // 총 가격 계산
        priceText.setText(String.valueOf(totalprice));  // 텍스트 필드에 설정
    }

    // 메인 프레임으로 돌아가는 메소드
    private void navigateToMainFrame() {
        if ("master".equals(userType)) {
            new MainFrame_Master().setVisible(true);
        } else if ("staff".equals(userType)) {
            new MainFrame_Staff().setVisible(true);
        }
        this.dispose();
    }

    // 주문 리스트의 데이터를 예약 테이블에 복사하는 메소드
    private void copyOrderListToReservationTable() {
        DefaultTableModel orderModel = (DefaultTableModel) orderListTable.getModel();
        DefaultTableModel reservationModel = (DefaultTableModel) reservationTable.getModel();
        reservationModel.setRowCount(0);  // 예약 테이블 초기화
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            reservationModel.addRow(new Object[]{
                orderModel.getValueAt(i, 0), // 메뉴
                orderModel.getValueAt(i, 1), // 가격
                orderModel.getValueAt(i, 2) // 수량
            });
        }
    }

    // 선택된 객실 번호와 주문 내역을 바탕으로 서비스 데이터를 생성하는 메소드
    private StringBuilder createServiceData(String selectedRoomNumber) {
        StringBuilder serviceData = new StringBuilder("룸서비스, ").append(selectedRoomNumber).append(",");
        for (int i = 0; i < orderListTable.getRowCount(); i++) {
            if (i > 0) {
                serviceData.append("/");
            }
            serviceData.append(orderListTable.getValueAt(i, 0)).append("/").append(orderListTable.getValueAt(i, 2));
        }
        serviceData.append(",").append(calculateTotalPrice()).append(",").append(howPayComboBox.getSelectedItem());
        return serviceData;
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
        cancelBtn = new javax.swing.JButton();
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

        cancelBtn.setText("취소");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
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
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roomListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(101, 101, 101)))
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

    private void priceTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTextActionPerformed
        // TODO add your handling code here:     
    }//GEN-LAST:event_priceTextActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        addMenuToOrderList();
    }//GEN-LAST:event_addBtnActionPerformed

    // '추가' 버튼 이벤트 핸들러 메소드
    private void addMenuToOrderList() {
        // 메뉴 리스트 테이블에서 선택된 행의 인덱스를 가져옴
        int selectedRow = menuListTable.getSelectedRow();

        // 선택된 행이 없는 경우 경고 메시지를 표시하고 메소드를 종료
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "추가할 메뉴를 선택하세요!", "알림", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 선택된 행에서 메뉴 이름과 가격을 가져옴
        String menu = (String) menuListTable.getValueAt(selectedRow, 0);  // 첫 번째 열의 메뉴 이름
        String price = (String) menuListTable.getValueAt(selectedRow, 1);  // 두 번째 열의 메뉴 가격

        // 주문 리스트 테이블의 모델을 가져옴
        DefaultTableModel orderModel = (DefaultTableModel) orderListTable.getModel();

        // 주문 리스트에서 선택된 메뉴가 이미 있는지 확인하기 위한 변수
        boolean menuExists = false;

        // 주문 리스트의 모든 행을 반복하면서 이미 있는 메뉴인지 확인
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            if (menu.equals(orderModel.getValueAt(i, 0))) {
                // 이미 존재하는 메뉴일 경우 수량을 증가시킴
                int quantity = (int) orderModel.getValueAt(i, 2);
                orderModel.setValueAt(quantity + 1, i, 2);
                menuExists = true;  // 메뉴가 이미 존재함을 표시
                break;  // 메뉴를 찾았으므로 반복문 종료
            }
        }

        // 만약 주문 리스트에 같은 메뉴가 없을 경우 새 행을 추가
        if (!menuExists) {
            orderModel.addRow(new Object[]{menu, price, 1});  // 메뉴 이름, 가격, 수량(1)으로 행 추가
        }

        // 총 가격을 업데이트
        updatePriceText();
    }


    private void minusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusBtnActionPerformed
        removeMenuFromOrderList();
    }//GEN-LAST:event_minusBtnActionPerformed

    // '빼기' 버튼 이벤트 핸들러
    private void removeMenuFromOrderList() {
        int selectedRow = orderListTable.getSelectedRow();  // 주문 테이블에서 선택된 행의 인덱스를 가져옴
        if (selectedRow == -1) {  // 선택된 메뉴가 없을 경우 오류 메시지 표시
            JOptionPane.showMessageDialog(this, "삭제할 행을 선택하세요!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();
        int quantity = (int) model.getValueAt(selectedRow, 2);
        if (quantity == 1) {
            model.removeRow(selectedRow);  // 수량이 1인 경우 행 삭제
        } else {
            model.setValueAt(quantity - 1, selectedRow, 2);  // 수량 감소
        }
        updatePriceText();   // 총 가격 업데이트
    }

    private void howPayComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howPayComboBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_howPayComboBoxActionPerformed

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBtnActionPerformed
        handlePayButton();
    }//GEN-LAST:event_payBtnActionPerformed

    // '결제' 버튼 이벤트 핸들러
    private void handlePayButton() {
        if (orderListTable.getRowCount() == 0) {  // 주문 목록이 비어 있을 경우 경고 메시지 표시
            JOptionPane.showMessageDialog(this, "메뉴를 담아야합니다.", "경고", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String selectedRoomNumber = (String) roomListCombo.getSelectedItem();
        if (selectedRoomNumber == null) {  // 객실 번호가 선택되지 않았을 경우 경고 메시지 표시
            JOptionPane.showMessageDialog(this, "객실 번호를 선택해 주세요.", "오류", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StringBuilder serviceData = createServiceData(selectedRoomNumber);
        saveToFile("use_service.txt", serviceData.toString());  // 서비스 데이터를 파일에 저장
        JOptionPane.showMessageDialog(this, calculateTotalPrice() + "원 결제되었습니다.");
        ((DefaultTableModel) orderListTable.getModel()).setRowCount(0);  // 주문 테이블 초기화
        updatePriceText();  // 가격 정보 업데이트
    }


    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        resetOrderList();
    }//GEN-LAST:event_resetBtnActionPerformed

    private void resetOrderList() {
        ((DefaultTableModel) orderListTable.getModel()).setRowCount(0);
        updatePriceText();
    }

    // 메인으로 돌아가기 버튼 이벤트 핸들러
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        JOptionPane.showMessageDialog(this, "이전 페이지로 이동합니다.");
        navigateToMainFrame();  // 메인 화면으로 이동
    }//GEN-LAST:event_backBtnActionPerformed

    private void reservationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationBtnActionPerformed
        handleReservationButton();
    }//GEN-LAST:event_reservationBtnActionPerformed

    // 예약 버튼 클릭 시 예약 다이얼로그 표시
    private void handleReservationButton() {
        if (orderListTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "메뉴를 담아주세요.", "오류", JOptionPane.WARNING_MESSAGE);
            return;
        }
        copyOrderListToReservationTable();
        showDialog(reservationDialog, "룸서비스 예약", 700, 500);
    }


    private void yearTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearTextActionPerformed

    private void roomListComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomListComboActionPerformed
        String selectedRoomNumber = getSelectedRoomNumber();
        if (selectedRoomNumber == null) {
            return;
        }

        String reservationDate = findReservationDate(selectedRoomNumber);
        if (reservationDate != null) {
            yearText.setText(reservationDate.split("-")[0]);
        }
    }//GEN-LAST:event_roomListComboActionPerformed

    private String getSelectedRoomNumber() {
        return (String) roomListCombo.getSelectedItem();
    }

    // 선택된 객실 번호에 해당하는 예약 날짜를 찾는 메소드
    private String findReservationDate(String selectedRoomNumber) {
        // 예약 정보가 저장된 파일 경로를 지정
        String filePath = "reservations.txt";

        // 파일을 읽기 위해 BufferedReader 생성 (try-with-resources 사용으로 자동 닫힘)
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line; // 파일에서 읽어올 각 줄을 저장할 변수

            // 파일의 끝까지 한 줄씩 읽기
            while ((line = br.readLine()) != null) {
                // 쉼표를 기준으로 줄을 나누어 문자열 배열에 저장
                String[] data = line.split(",");

                // 배열 길이가 최소한 7 이상인지 확인하고 선택된 객실 번호와 일치하는지 확인
                if (data.length >= 7 && selectedRoomNumber.equals(data[5].trim())) {
                    // 일치하는 객실 번호가 있는 경우 예약 날짜 반환 (6번째 요소)
                    return data[6].trim();
                }
            }
        } catch (IOException e) {
        }

        // 예약 날짜를 찾지 못한 경우 null 반환
        return null;
    }


    private void monthTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthTextActionPerformed

    // 예약 다이얼로그 버튼 클릭 시 수행되는 이벤트 핸들러 메소드
    private void reservationDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationDialogBtnActionPerformed
        // 예약 데이터를 생성하는 메소드 호출
        String reservationData = generateReservationData();

        // 생성된 예약 데이터를 파일에 저장하는 메소드 호출
        saveReservationToFile(reservationData);

        // 예약 다이얼로그를 초기화하는 메소드 호출
        resetReservationDialog();
    }//GEN-LAST:event_reservationDialogBtnActionPerformed

    // 예약 데이터를 생성하는 메소드
    private String generateReservationData() {
        // 예약 데이터 문자열을 생성하기 위한 StringBuilder 객체 생성
        StringBuilder reservationData = new StringBuilder("룸서비스");

        // 객실 번호, 연도, 월, 일, 시간, 분 데이터를 예약 데이터에 추가
        reservationData.append(", ").append((String) roomListCombo.getSelectedItem()); // 선택된 객실 번호 추가
        reservationData.append(", ").append(yearText.getText()); // 연도 텍스트 필드에서 값을 가져와 추가
        reservationData.append(", ").append((String) monthText.getSelectedItem()); // 선택된 월 추가
        reservationData.append(", ").append((String) dayText.getSelectedItem()); // 선택된 일 추가
        reservationData.append(", ").append((String) hourCombo.getSelectedItem()); // 선택된 시간 추가
        reservationData.append(", ").append((String) minuteCombo.getSelectedItem()); // 선택된 분 추가

        // reservationTable에 저장된 메뉴와 수량을 문자열로 추가
        reservationData.append(", ").append(getMenuList());

        // priceText 텍스트 필드에서 총 금액을 가져와 예약 데이터에 추가
        reservationData.append(", ").append(priceText.getText());

        // howpayDialog에서 선택한 결제 방식 추가
        String howPay = (String) howpayDialog.getSelectedItem(); // 선택된 결제 방식 가져오기
        reservationData.append(", ").append(howPay);

        // 생성된 예약 데이터를 문자열로 반환
        return reservationData.toString();
    }

    // 예약 테이블에 있는 메뉴 목록을 문자열 형태로 반환하는 메소드
    private String getMenuList() {
        // 예약 테이블의 테이블 모델을 가져옴
        DefaultTableModel reservationModel = (DefaultTableModel) reservationTable.getModel();
        // 메뉴 목록을 저장할 StringBuilder 객체 생성
        StringBuilder menuList = new StringBuilder();

        // 예약 테이블의 모든 행을 반복
        for (int i = 0; i < reservationModel.getRowCount(); i++) {
            // 현재 행의 첫 번째 열에서 메뉴 이름을 가져옴
            String menu = (String) reservationModel.getValueAt(i, 0);

            // 현재 행의 세 번째 열에서 수량을 가져옴
            int quantity = (int) reservationModel.getValueAt(i, 2);

            // 첫 번째 메뉴가 아닌 경우 메뉴 사이에 슬래시를 추가
            if (i > 0) {
                menuList.append("/"); // 메뉴와 수량 사이에 구분자 추가
            }

            // 메뉴 이름과 수량을 문자열에 추가
            menuList.append(menu).append("/").append(quantity);
        }

        // 생성된 메뉴 목록을 문자열로 반환
        return menuList.toString();
    }

    // 예약 정보를 파일에 저장하는 메소드
    private void saveReservationToFile(String reservationData) {
        // 예약 정보를 저장할 파일 경로 설정
        String filePath = "service_reservation_list.txt";

        // 파일에 데이터를 쓰기 위해 BufferedWriter 생성 (try-with-resources로 자동 닫기 설정)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            // 예약 데이터를 파일에 기록
            bw.write(reservationData);
            // 새로운 줄로 이동하여 이후의 데이터를 다음 줄에 기록
            bw.newLine(); // 줄 바꿈 추가 -> 기존 데이터 아래에 새 값을 저장

            // 사용자에게 예약 정보가 저장되었음을 알리는 메시지 표시
            JOptionPane.showMessageDialog(this, "예약 정보가 저장되었습니다.");
        } catch (IOException e) {
            // 파일 쓰기 도중 발생할 수 있는 예외를 처리하고 오류 메시지를 출력
        }
    }

// 예약 다이얼로그를 초기화하는 메소드
    private void resetReservationDialog() {
        // 주문 리스트 테이블의 모델을 가져옴
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();

        // 주문 리스트를 초기화 (모든 행을 삭제)
        model.setRowCount(0);

        // 총 금액을 표시하는 텍스트 필드를 0으로 초기화
        priceText.setText("0");

        // 예약 다이얼로그를 닫음
        reservationDialog.dispose();
    }

    private void hourComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourComboActionPerformed

    private void howpayDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howpayDialogActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_howpayDialogActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        reservationDialog.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void reservationCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationCheckActionPerformed
        handleReservationCheck();
    }//GEN-LAST:event_reservationCheckActionPerformed

// 예약 확인 처리 메소드
    private void handleReservationCheck() {
        // 예약 확인 다이얼로그를 표시하는 메소드 호출
        showDialog(reservationCheckDialog, "예약 확인", 800, 500);

        // 예약 확인 테이블의 모델을 가져옴
        DefaultTableModel reservationModel = (DefaultTableModel) reservationCheckTable.getModel();

        // 예약 확인 테이블의 모든 행을 삭제하여 초기화
        reservationModel.setRowCount(0);

        // 예약 정보가 저장된 파일 경로 설정
        String filePath = "service_reservation_list.txt";

        // 파일을 읽기 위해 BufferedReader 생성 (try-with-resources 사용으로 자동 닫힘)
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line; // 파일에서 읽어올 각 줄을 저장할 변수

            // 파일의 끝까지 한 줄씩 읽기
            while ((line = br.readLine()) != null) {
                // 쉼표를 기준으로 줄을 나누어 문자열 배열로 저장
                String[] data = line.split(",");

                // 배열 길이가 최소한 10 이상이고, 서비스 유형이 "룸서비스"인지 확인
                if (data.length >= 10 && "룸서비스".equals(data[0])) {
                    // 날짜와 시간을 지정된 형식으로 포맷팅하여 저장
                    String date = data[2] + "년 " + data[3] + "월 " + data[4] + "일";
                    String time = data[5] + "시 " + data[6] + "분";

                    // 서비스 유형, 객실 번호, 메뉴 목록 등의 정보 가져오기
                    String serviceType = data[0];
                    String roomNumber = data[1];
                    String menuRaw = data[7];

                    // 메뉴 목록을 슬래시(/)로 분리하여 배열로 저장
                    String[] menuItems = menuRaw.split("/");
                    StringBuilder menuFormatted = new StringBuilder();

                    // 메뉴 아이템을 반복하면서 이름과 수량을 형식에 맞게 추가
                    for (int i = 0; i < menuItems.length; i += 2) {
                        String itemName = menuItems[i]; // 메뉴 이름
                        String itemQuantity = menuItems[i + 1]; // 메뉴 수량
                        menuFormatted.append(itemName).append(" ").append(itemQuantity).append("개");

                        // 다음 아이템이 있을 경우 슬래시 추가
                        if (i + 2 < menuItems.length) {
                            menuFormatted.append(" / ");
                        }
                    }

                    // 총 금액 및 결제 유형 가져오기
                    String totalAmount = data[8];
                    String paymentMethod = data[9];

                    // 예약 정보를 테이블에 추가할 행 데이터로 저장
                    Object[] rowData = {
                        serviceType, roomNumber, date, time, menuFormatted.toString(), totalAmount, paymentMethod};

                    // 예약 확인 테이블에 행 추가
                    reservationModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
        }
    }


    private void backDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backDialogBtnActionPerformed
        // TODO add your handling code here:
        reservationCheckDialog.dispose();
    }//GEN-LAST:event_backDialogBtnActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Service_RoomFrame("master").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton backDialogBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox<String> dayText;
    private javax.swing.JComboBox<String> hourCombo;
    private javax.swing.JComboBox<String> howPayComboBox;
    private javax.swing.JComboBox<String> howpayDialog;
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
