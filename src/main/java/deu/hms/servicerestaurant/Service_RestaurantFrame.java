package deu.hms.servicerestaurant;

import deu.hms.login.MainFrame_Master;
import deu.hms.login.MainFrame_Staff;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class Service_RestaurantFrame extends javax.swing.JFrame {

    // 사용자 유형 ("manager" 또는 "staff")을 저장하는 변수
    private String userType;  // "manager" 또는 "staff"를 저장하는 변수

    // 생성자: userType을 받아 저장하고 컴포넌트를 초기화하고 메뉴와 체크인한 객실 데이터를 로드
    public Service_RestaurantFrame(String userType) {
        this.userType = userType;
        initComponents();
        loadMenuList(); // 메뉴 데이터 로드
        loadCheckedInRooms();  // 체크인한 객실 번호 로드
    }

    // 메뉴 리스트를 파일에서 불러와 테이블에 로드하는 메소드
    private void loadMenuList() {
        loadTableData("menu_list.txt", menuListTable, "식당", 1, 2);
    }

// 체크인된 객실 번호를 파일에서 불러와 콤보박스에 추가하는 메소드
    private void loadCheckedInRooms() {
        // 파일 경로를 지정하는 문자열 변수 선언
        String filePath = "checked_in_customers.txt";  // 체크인된 고객 정보가 저장된 파일의 경로

        // 중복된 객실 번호를 피하기 위해 Set을 사용하여 객실 번호를 저장
        Set<String> roomNumbers = new HashSet<>();

        // 파일을 읽기 위한 BufferedReader를 생성하고 파일을 읽음
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line; // 파일의 각 줄을 저장할 변수

            // 파일 끝까지 한 줄씩 읽음
            while ((line = br.readLine()) != null) {
                // 각 줄의 데이터를 쉼표(,)로 분리하여 배열로 저장
                String[] data = line.split(",");

                // 데이터 배열의 길이가 4 이상일 때만 처리 (잘못된 데이터 방지)
                if (data.length >= 4) {
                    // 데이터 배열에서 세 번째 항목(객실 번호 부분)을 콜론(:)으로 분리하여 실제 객실 번호 추출
                    String roomNumber = data[2].split(":")[1].trim(); // "roomNumber:101" 형식에서 "101"만 추출
                    roomNumbers.add(roomNumber); // 추출한 객실 번호를 Set에 추가
                }
            }
        } catch (IOException e) { // 파일 입출력 도중 발생할 수 있는 예외 처리
            e.printStackTrace(); // 오류 메시지를 출력하여 문제 원인을 파악할 수 있도록 함
        }

        // 콤보박스를 초기화하여 기존 항목을 모두 삭제
        roomListCombo.removeAllItems();

        // Set에 저장된 모든 객실 번호를 콤보박스에 추가
        for (String roomNumber : roomNumbers) {
            roomListCombo.addItem(roomNumber); // 각 객실 번호를 콤보박스의 항목으로 추가
        }
    }

    // 테이블 데이터를 파일에서 불러와 특정 테이블에 추가하는 메소드
    private void loadTableData(String filePath, javax.swing.JTable table, String requiredType, int... columns) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();  // 테이블의 모델을 가져옴
        model.setRowCount(0);  // 테이블을 초기화하여 기존 데이터 삭제

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {  // 파일 읽기를 위한 BufferedReader 생성
            String line;
            while ((line = br.readLine()) != null) {  // 파일의 각 줄을 읽음
                String[] data = line.split(",");  // 쉼표로 구분하여 데이터 분리
                if (data.length >= columns.length + 1 && requiredType.equals(data[0])) {  // 데이터 길이 및 타입 확인
                    Object[] rowData = new Object[columns.length];  // 테이블에 추가할 행 데이터 배열 생성
                    for (int i = 0; i < columns.length; i++) {
                        rowData[i] = data[columns[i]];  // 각 열 데이터를 행 배열에 저장
                    }
                    model.addRow(rowData);  // 테이블 모델에 행 추가
                }
            }
        } catch (IOException e) {
            // 파일 입출력 예외 처리 (오류 무시)
        }
    }

    // 다이얼로그를 표시하는 메소드
    private void showDialog(javax.swing.JDialog dialog, String title, int width, int height) {
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(this);  // 현재 프레임 중앙에 다이얼로그 배치
        dialog.setTitle(title);
        dialog.setModal(false);  // 모달 설정 해제
        dialog.setVisible(true);
        dialog.toFront();  // 다이얼로그를 화면의 최상단에 보이도록 설정
    }

    // 주문된 모든 항목의 총 가격을 계산하는 메소드
    private int calculateTotalPrice() {
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();
        int totalprice = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            int price = Integer.parseInt((String) model.getValueAt(i, 1));  // 가격을 정수로 변환
            int count = (int) model.getValueAt(i, 2);  // 수량 가져오기
            totalprice += price * count;  // 총 금액 계산
        }
        return totalprice;
    }

    // 데이터를 지정된 파일에 저장하는 메소드
    private void saveToFile(String filePath, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
        }
    }

    // 총 가격 텍스트 필드를 업데이트하는 메소드
    private void updatePriceText() {
        int totalprice = calculateTotalPrice();
        priceText.setText(String.valueOf(totalprice));  // 총 가격을 문자열로 변환하여 텍스트 필드에 설정
    }

    // 사용자 유형에 따라 메인 화면으로 이동하는 메소드
    private void navigateToMainFrame() {
        if ("master".equals(userType)) {
            new MainFrame_Master().setVisible(true);
        } else if ("staff".equals(userType)) {
            new MainFrame_Staff().setVisible(true);
        }
        this.dispose();  // 현재 프레임 닫기
    }

// 주문 리스트의 데이터를 예약 테이블에 복사하는 메소드
    private void copyOrderListToReservationTable() {
        DefaultTableModel orderModel = (DefaultTableModel) orderListTable.getModel();  // 주문 리스트 테이블의 모델을 가져옴
        DefaultTableModel reservationModel = (DefaultTableModel) reservationTable.getModel();  // 예약 테이블의 모델을 가져옴
        reservationModel.setRowCount(0);  // 예약 테이블 초기화하여 기존 데이터 삭제

        // 주문 리스트 테이블의 모든 행을 반복
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            // 주문 리스트의 각 행의 데이터를 예약 테이블에 새로운 행으로 추가
            reservationModel.addRow(new Object[]{
                orderModel.getValueAt(i, 0), // 첫 번째 열 값 추가
                orderModel.getValueAt(i, 1), // 두 번째 열 값 추가
                orderModel.getValueAt(i, 2) // 세 번째 열 값 추가
            });
        }
    }

// 선택된 객실 번호와 주문 내역을 바탕으로 서비스 데이터를 생성하는 메소드
    private StringBuilder createServiceData(String selectedRoomNumber) {
        // StringBuilder를 사용해 서비스 데이터를 생성 (식당, 선택된 객실 번호 추가)
        StringBuilder serviceData = new StringBuilder("식당, ").append(selectedRoomNumber).append(", ");

        // 주문 리스트의 모든 행을 반복
        for (int i = 0; i < orderListTable.getRowCount(); i++) {
            if (i > 0) {
                serviceData.append("/");  // 첫 번째 항목 이후에는 구분자로 슬래시("/") 추가
            }
            // 각 주문의 항목 이름과 수량을 "/item/quantity" 형식으로 추가
            serviceData.append(orderListTable.getValueAt(i, 0)) // 주문 항목 이름 추가
                    .append("/").append(orderListTable.getValueAt(i, 2));  // 주문 항목 수량 추가
        }

        // 총 가격과 결제 방법 추가
        serviceData.append(", ").append(calculateTotalPrice()) // 총 가격 추가
                .append(", ").append(howPayComboBox.getSelectedItem());  // 선택된 결제 방법 추가

        return serviceData;  // 생성된 서비스 데이터 반환
    }

    @SuppressWarnings("unchecked")
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
        jScrollPane1 = new javax.swing.JScrollPane();
        reservationCheckTable = new javax.swing.JTable();
        backDialogBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        priceText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        reservationBtn = new javax.swing.JButton();
        payBtn = new javax.swing.JButton();
        howPayComboBox = new javax.swing.JComboBox<>();
        reservationCheck = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        orderListTable = new javax.swing.JTable();
        minusBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        backBtn1 = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        roomListCombo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        menuListTable = new javax.swing.JTable();

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
        if (reservationTable.getColumnModel().getColumnCount() > 0) {
            reservationTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            reservationTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            reservationTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

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
        jLabel25.setText("식당 서비스 예약");

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
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(howpayDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(reservationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel17))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(howpayDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reservationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout reservationDialogLayout = new javax.swing.GroupLayout(reservationDialog.getContentPane());
        reservationDialog.getContentPane().setLayout(reservationDialogLayout);
        reservationDialogLayout.setHorizontalGroup(
            reservationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationDialogLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        reservationDialogLayout.setVerticalGroup(
            reservationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationDialogLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

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

        backDialogBtn.setText("취소");
        backDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backDialogBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel3.setText("예약 확인");

        javax.swing.GroupLayout reservationCheckDialogLayout = new javax.swing.GroupLayout(reservationCheckDialog.getContentPane());
        reservationCheckDialog.getContentPane().setLayout(reservationCheckDialogLayout);
        reservationCheckDialogLayout.setHorizontalGroup(
            reservationCheckDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationCheckDialogLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(reservationCheckDialogLayout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(backDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reservationCheckDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(319, 319, 319))
        );
        reservationCheckDialogLayout.setVerticalGroup(
            reservationCheckDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationCheckDialogLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(backDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel1.setText("식당 서비스 이용");

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

        minusBtn.setText("빼기");
        minusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusBtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel8.setText("주문 확인");

        backBtn1.setText("이전");
        backBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtn1ActionPerformed(evt);
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

        jLabel7.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel7.setText("메뉴판");

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel2.setText("호수");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jLabel1)
                .addContainerGap(279, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roomListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backBtn1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(resetBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(73, 73, 73)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(addBtn)
                                            .addComponent(minusBtn)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(50, 50, 50)))))
                        .addGap(94, 94, 94))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(roomListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addBtn)
                                .addGap(44, 44, 44)
                                .addComponent(minusBtn)
                                .addGap(96, 96, 96))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void priceTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTextActionPerformed

    private void reservationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationBtnActionPerformed
        // '예약하기' 버튼 클릭 시 예약 다이얼로그를 표시하는 핸들러 메소드
        handleReservationButton();
    }//GEN-LAST:event_reservationBtnActionPerformed

    // '예약하기' 버튼을 처리하는 메소드
    private void handleReservationButton() {
        if (orderListTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "메뉴를 담아주세요.", "오류", JOptionPane.WARNING_MESSAGE);
            return;
        }
        copyOrderListToReservationTable();  // 주문 리스트 데이터를 예약 테이블로 복사
        showDialog(reservationDialog, "식당 서비스 예약", 700, 500);   // 예약 다이얼로그 표시
    }

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBtnActionPerformed
        // '결제' 버튼 클릭 시 호출되는 메소드
        handlePayButton();
    }//GEN-LAST:event_payBtnActionPerformed

// '결제' 버튼을 처리하는 메소드
    private void handlePayButton() {
        // 주문 내역이 비어 있는지 확인
        if (orderListTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "메뉴를 담아야합니다.", "경고", JOptionPane.WARNING_MESSAGE); // 경고 메시지 표시
            return; // 메소드 종료
        }

        // 선택된 객실 번호가 있는지 확인
        String selectedRoomNumber = (String) roomListCombo.getSelectedItem();
        if (selectedRoomNumber == null) {
            JOptionPane.showMessageDialog(this, "객실 번호를 선택해 주세요.", "오류", JOptionPane.WARNING_MESSAGE); // 오류 메시지 표시
            return; // 메소드 종료
        }

        // 서비스 데이터를 생성
        StringBuilder serviceData = createServiceData(selectedRoomNumber);

        // 서비스 데이터를 파일에 저장
        saveToFile("use_service.txt", serviceData.toString());

        // 결제 완료 메시지 표시
        JOptionPane.showMessageDialog(this, calculateTotalPrice() + "원 결제되었습니다.");

        // 주문 리스트 테이블 초기화
        ((DefaultTableModel) orderListTable.getModel()).setRowCount(0);

        // 가격 정보를 업데이트
        updatePriceText();
    }


    private void howPayComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howPayComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_howPayComboBoxActionPerformed

    private void reservationCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationCheckActionPerformed
        // '결제' 버튼 클릭 시 호출되는 메소드
        handleReservationCheck();
    }//GEN-LAST:event_reservationCheckActionPerformed

    private void handleReservationCheck() {
        // 예약 확인 다이얼로그를 표시하는 메소드 호출
        showDialog(reservationCheckDialog, "예약 확인", 800, 500);

        // 예약 확인 테이블의 모델을 가져와 초기화 (기존 데이터 삭제)
        DefaultTableModel reservationModel = (DefaultTableModel) reservationCheckTable.getModel();
        reservationModel.setRowCount(0);

        // 예약 데이터가 저장된 파일의 경로 지정
        String filePath = "service_reservation_list.txt";

        // 파일을 읽기 위한 BufferedReader 생성
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // 파일의 각 줄을 읽어 데이터를 처리
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // 쉼표를 기준으로 데이터 분리

                // 데이터 배열의 길이가 충분하고, 서비스 타입이 "식당"인 경우에만 처리
                if (data.length >= 10 && "식당".equals(data[0])) {
                    // 날짜 정보를 형식에 맞춰 조합 (년, 월, 일)
                    String date = data[2] + "년 " + data[3] + "월 " + data[4] + "일";

                    // 시간 정보를 형식에 맞춰 조합 (시, 분)
                    String time = data[5] + "시 " + data[6] + "분";

                    // 서비스 타입과 객실 번호 추출
                    String serviceType = data[0];
                    String roomNumber = data[1];

                    // 메뉴 정보(raw) 추출 후 각 메뉴와 수량을 포맷팅
                    String menuRaw = data[7];
                    String[] menuItems = menuRaw.split("/");  // "/"를 기준으로 메뉴 항목과 수량을 분리
                    StringBuilder menuFormatted = new StringBuilder();

                    // 메뉴 항목과 수량을 반복하여 문자열로 포맷팅
                    for (int i = 0; i < menuItems.length; i += 2) {
                        String itemName = menuItems[i]; // 메뉴 이름
                        String itemQuantity = menuItems[i + 1]; // 메뉴 수량
                        menuFormatted.append(itemName).append(" ").append(itemQuantity).append("개");

                        // 마지막 항목이 아닌 경우 " / " 추가
                        if (i + 2 < menuItems.length) {
                            menuFormatted.append(" / ");
                        }
                    }

                    // 총 금액과 결제 방법 추출
                    String totalAmount = data[8];
                    String paymentMethod = data[9];

                    // 테이블에 추가할 행 데이터 생성
                    Object[] rowData = {
                        serviceType, roomNumber, date, time, menuFormatted.toString(), totalAmount, paymentMethod};
                    // 행 데이터를 테이블 모델에 추가
                    reservationModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            // 파일 읽기 중 예외 발생 시 오류 메시지 출력
        }
    }


    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // '추가' 버튼 클릭 시 주문 목록에 메뉴를 추가하는 메소드
        addMenuToOrderList();
    }//GEN-LAST:event_addBtnActionPerformed

// 메뉴 리스트에서 선택한 메뉴를 주문 리스트에 추가하는 메소드
    private void addMenuToOrderList() {
        // 메뉴 리스트에서 선택된 행의 인덱스를 가져옴
        int selectedRow = menuListTable.getSelectedRow();

        // 선택된 행이 없을 경우 경고 메시지 표시 후 메소드 종료
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "추가할 메뉴를 선택하세요!", "알림", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 선택된 메뉴의 이름과 가격을 가져옴
        String menu = (String) menuListTable.getValueAt(selectedRow, 0);
        String price = (String) menuListTable.getValueAt(selectedRow, 1);

        // 주문 리스트 테이블의 모델을 가져옴
        DefaultTableModel orderModel = (DefaultTableModel) orderListTable.getModel();

        // 이미 주문 리스트에 동일한 메뉴가 있는지 확인
        boolean menuExists = false;
        for (int i = 0; i < orderModel.getRowCount(); i++) {
            if (menu.equals(orderModel.getValueAt(i, 0))) {
                // 이미 존재하는 메뉴의 경우 수량을 증가시킴
                int quantity = (int) orderModel.getValueAt(i, 2);
                orderModel.setValueAt(quantity + 1, i, 2);
                menuExists = true; // 메뉴가 이미 존재함을 표시
                break;
            }
        }

        // 동일한 메뉴가 없을 경우 새로운 행으로 추가
        if (!menuExists) {
            orderModel.addRow(new Object[]{menu, price, 1});
        }

        // 총 가격 정보를 업데이트
        updatePriceText();
    }

    // '빼기' 버튼 클릭 시 주문 목록에서 메뉴를 제거하는 메소드
    private void minusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusBtnActionPerformed
        removeMenuFromOrderList();
    }//GEN-LAST:event_minusBtnActionPerformed
    // 주문 리스트에서 선택한 메뉴의 수량을 감소시키거나 제거하는 메소드

// 주문 리스트에서 선택한 메뉴를 삭제하는 메소드
    private void removeMenuFromOrderList() {
        // 주문 리스트에서 선택된 행의 인덱스를 가져옴
        int selectedRow = orderListTable.getSelectedRow();

        // 선택된 행이 없을 경우 오류 메시지 표시 후 메소드 종료
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "삭제할 행을 선택하세요!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 주문 리스트 테이블의 모델을 가져옴
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();

        // 선택된 행의 수량을 가져옴
        int quantity = (int) model.getValueAt(selectedRow, 2);

        // 수량이 1이면 행을 삭제, 아니면 수량을 감소
        if (quantity == 1) {
            model.removeRow(selectedRow);  // 수량이 1일 경우 행을 삭제
        } else {
            model.setValueAt(quantity - 1, selectedRow, 2);  // 수량이 1보다 크면 수량을 1 감소시킴
        }

        // 총 가격 정보를 업데이트
        updatePriceText();
    }


    private void backBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtn1ActionPerformed
        // '이전' 버튼 클릭 시 메인 화면으로 돌아가는 메소드
        JOptionPane.showMessageDialog(this, "이전 페이지로 이동합니다.");
        navigateToMainFrame();
    }//GEN-LAST:event_backBtn1ActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // '초기화' 버튼 클릭 시 주문 목록을 초기화하는 메소드
        resetOrderList();
    }//GEN-LAST:event_resetBtnActionPerformed

    // 주문 리스트를 초기화하는 메소드
    private void resetOrderList() {
        ((DefaultTableModel) orderListTable.getModel()).setRowCount(0);  // 주문 목록을 초기화
        updatePriceText();   // 총 가격 업데이트
    }

    private void roomListComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomListComboActionPerformed
        // 선택된 객실 번호를 가져옴
        String selectedRoomNumber = getSelectedRoomNumber();
        if (selectedRoomNumber == null) {
            return;  // 객실 번호가 선택되지 않은 경우 메소드를 종료
        }

// 선택된 객실 번호에 해당하는 예약 날짜를 찾음
        String reservationDate = findReservationDate(selectedRoomNumber);
        if (reservationDate != null) {
            // 예약 날짜가 존재하는 경우, 연도 정보를 추출해 yearText 텍스트 필드에 설정
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

        // 파일을 읽기 위한 BufferedReader 생성
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // 파일의 각 줄을 읽어 데이터를 처리
            while ((line = br.readLine()) != null) {
                // 쉼표를 기준으로 데이터를 분리하여 배열에 저장
                String[] data = line.split(",");

                // 데이터 배열의 길이가 충분하고, 선택된 객실 번호와 일치하는 경우 처리
                if (data.length >= 7 && selectedRoomNumber.equals(data[5].trim())) {
                    // 일치하는 객실 번호에 해당하는 예약 날짜를 반환
                    return data[6].trim();
                }
            }
        } catch (IOException e) {
            // 파일을 읽는 도중 발생할 수 있는 예외를 처리하고 오류 메시지를 출력
            e.printStackTrace();
        }

        // 예약 날짜를 찾지 못한 경우 null 반환
        return null;
    }


    private void yearTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearTextActionPerformed

    private void monthTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthTextActionPerformed

    private void hourComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourComboActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        reservationDialog.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void reservationDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationDialogBtnActionPerformed
        String reservationData = generateReservationData();
        saveReservationToFile(reservationData);
        resetReservationDialog();
    }//GEN-LAST:event_reservationDialogBtnActionPerformed
    
    private String generateReservationData() {
        StringBuilder reservationData = new StringBuilder("식당");

        // yearText, monthText, dayText, hourCombo, minuteCombo 값 가져오기
        reservationData.append(", ").append((String) roomListCombo.getSelectedItem());
        reservationData.append(", ").append(yearText.getText());
        reservationData.append(", ").append((String) monthText.getSelectedItem());
        reservationData.append(", ").append((String) dayText.getSelectedItem());
        reservationData.append(", ").append((String) hourCombo.getSelectedItem());
        reservationData.append(", ").append((String) minuteCombo.getSelectedItem());

        // reservationTable의 메뉴와 수량을 문자열로 생성
        reservationData.append(", ").append(getMenuList());

        // priceText에서 총 금액을 가져와 추가
        reservationData.append(", ").append(priceText.getText());

        // howPayDialog에서 선택한 결제 방식 추가
        String howPay = (String) howpayDialog.getSelectedItem();
        reservationData.append(", ").append(howPay);

        return reservationData.toString();
    }

    private String getMenuList() {
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
        return menuList.toString();
    }

    private void saveReservationToFile(String reservationData) {
        String filePath = "service_reservation_list.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(reservationData);
            bw.newLine(); // 줄 바꿈 추가 -> 기존 값 아래에 새 값을 저장
            JOptionPane.showMessageDialog(this, "예약 정보가 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace(); // 파일 쓰기 오류 처리
        }
    }

    private void resetReservationDialog() {
        DefaultTableModel model = (DefaultTableModel) orderListTable.getModel();
        model.setRowCount(0);
        priceText.setText("0");
        reservationDialog.dispose();
    }
    private void howpayDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_howpayDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_howpayDialogActionPerformed

    private void backDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backDialogBtnActionPerformed
        reservationCheckDialog.dispose();
    }//GEN-LAST:event_backDialogBtnActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Service_RestaurantFrame("master").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn1;
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
