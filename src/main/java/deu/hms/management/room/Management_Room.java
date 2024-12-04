package deu.hms.management.room;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class Management_Room extends javax.swing.JFrame {
    // 객실 데이터를 처리하는 RoomService 객체
    private final RoomService roomService;
    // 다이얼로그 관리를 담당하는 RoomDialogManager 객체
    private final RoomDialogManager dialogManager;
  
     // 생성자
    public Management_Room() {
        initComponents();  // UI 초기화
         // room_list.txt 파일로부터 데이터를 관리하기 위한 RoomService 생성
        roomService = new RoomService("room_list.txt");
         // 다이얼로그 및 테이블을 관리하기 위한 RoomDialogManager 생성
        dialogManager = new RoomDialogManager(editDialog, registrationDialog, roomTable, editTable);
        loadTableData();  // 테이블에 데이터 로드
    }
    
    // 테이블 데이터를 로드하는 메서드
    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();  // 테이블 모델 가져오기
        roomService.readFileAndPopulateTable(model);  // 파일에서 읽어와 테이블에 데이터 채우기
    }
    
    // 테이블 데이터를 저장하는 메서드
    private void saveTableData() {
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();  // 테이블 모델 가져오기
        roomService.saveTableDataToFile(model);  // 테이블 데이터를 파일에 저장
        // 저장 완료 메시지 표시
        JOptionPane.showMessageDialog(this, "변경 사항이 저장되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
    }

     // 이전 관리 화면으로 돌아가는 메서드
    private void backToManagementFrame() {
        JOptionPane.showMessageDialog(this, "이전 화면으로 돌아갑니다.");
       // 다른 관리 서비스 객체 생성
        deu.hms.management.AccountManagementService accountService = new deu.hms.management.AccountManagementService();
        deu.hms.management.RoomManagementService roomService = new deu.hms.management.RoomManagementService();
        deu.hms.management.ServiceManagementService serviceService = new deu.hms.management.ServiceManagementService();
        
        // ManagementFrame 화면을 새로 생성하고 표시
        deu.hms.management.ManagementFrame managementFrame = new deu.hms.management.ManagementFrame(accountService, roomService, serviceService);
        managementFrame.setVisible(true);  // 새로운 프레임 표시
        this.dispose();   // 현재 프레임 닫기
    }
    
    // 객실 데이터를 수정하는 메서드
    private void updateRoomData() {
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel(); // 수정 테이블 모델 가져오기
        DefaultTableModel roomModel = (DefaultTableModel) roomTable.getModel(); // 원본 테이블 모델 가져오기

        if (editModel.getRowCount() > 0) { // 수정 테이블에 데이터가 있는 경우
            String[] updatedRowData = new String[editModel.getColumnCount()]; // 수정 데이터를 저장할 배열 생성
            for (int i = 0; i < updatedRowData.length; i++) {
                Object cellValue = editModel.getValueAt(0, i); // 수정 테이블의 데이터 가져오기
                updatedRowData[i] = cellValue != null ? cellValue.toString() : ""; // null 값 처리
            }

            int selectedRow = roomTable.getSelectedRow(); // 원본 테이블에서 선택된 행 가져오기
            if (selectedRow != -1) { // 선택된 행이 있는 경우
                for (int i = 0; i < updatedRowData.length; i++) {
                    roomModel.setValueAt(updatedRowData[i], selectedRow, i); // 수정된 데이터로 업데이트
                }
                JOptionPane.showMessageDialog(this, "데이터가 성공적으로 수정되었습니다."); // 성공 메시지
            } else {
                JOptionPane.showMessageDialog(this, "수정할 행을 선택하세요."); // 선택 오류 메시지
            }
        } else {
            JOptionPane.showMessageDialog(this, "수정할 데이터가 없습니다."); // 수정 데이터 없음 오류 메시지
        }

        editDialog.dispose(); // 수정 다이얼로그 닫기
    }
    
     // 객실을 등록하는 메서드
    private void registerRoom() {
        String floor = floorList.getSelectedValue(); // 선택된 층 가져오기
        String room = roomText.getText().trim(); // 입력된 호수 가져오기
        String rating = ratingList.getSelectedValue(); // 선택된 등급 가져오기
        String price = priceText.getText().trim(); // 입력된 가격 가져오기

        if (floor == null || room.isEmpty() || rating == null || price.isEmpty()) {  // 필수 입력값 확인
            JOptionPane.showMessageDialog(this, "모든 필드를 채워주세요!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> roomData = new ArrayList<>();   // 파일 데이터를 저장할 리스트
        try (BufferedReader reader = new BufferedReader(new FileReader("room_list.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");    // 데이터 분리
                if (parts.length > 1 && parts[1].trim().equals(room)) {  // 중복된 호수 확인
                    JOptionPane.showMessageDialog(this, "이미 있는 객실입니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                roomData.add(line);     // 데이터 추가
            }
        } catch (IOException ex) {
            Logger.getLogger(Management_Room.class.getName()).log(Level.SEVERE, null, ex);  // 예외 처리
        }
        
        // 새 데이터를 추가하고 정렬
        roomData.add(floor + "," + room + "," + rating + "," + price);
        roomData.sort((a, b) -> {
            String[] partsA = a.split(",");
            String[] partsB = b.split(",");
            int floorA = Integer.parseInt(partsA[0].trim());
            int floorB = Integer.parseInt(partsB[0].trim());
            if (floorA != floorB) {
                return Integer.compare(floorA, floorB);  // 층 기준 정렬
            }
            int roomA = Integer.parseInt(partsA[1].trim());
            int roomB = Integer.parseInt(partsB[1].trim());
            return Integer.compare(roomA, roomB);  // 호수 기준 정렬
        });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("room_list.txt"))) {
            for (String data : roomData) {
                writer.write(data);  // 데이터 쓰기
                writer.newLine();  // 줄바꿈
            }
        } catch (IOException ex) {
            Logger.getLogger(Management_Room.class.getName()).log(Level.SEVERE, null, ex);  // 예외 처리
        }

        JOptionPane.showMessageDialog(this, "등록이 완료되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);  // 성공 메시지


        registrationDialog.dispose(); // 등록 다이얼로그 닫기
        floorList.clearSelection(); // 선택 초기화
        roomText.setText(""); // 입력 초기화
        ratingList.clearSelection(); // 선택 초기화
        priceText.setText(""); // 입력 초기화
        loadTableData(); // 테이블 데이터 다시 로드
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editDialog = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        editTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        deleteDialogBtn = new javax.swing.JButton();
        changeDialogBtn = new javax.swing.JButton();
        registrationDialog = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        floorList = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        roomText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ratingList = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        priceText = new javax.swing.JTextField();
        registrationDialogBtn = new javax.swing.JButton();
        backRegistrationBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        registrationBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        storageBtn = new javax.swing.JButton();
        changeBtn = new javax.swing.JButton();

        editTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "층", "호수", "등급", "가격"
            }
        ));
        jScrollPane2.setViewportView(editTable);

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel2.setText("객실 수정");

        deleteDialogBtn.setText("취소");
        deleteDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDialogBtnActionPerformed(evt);
            }
        });

        changeDialogBtn.setText("수정");
        changeDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDialogBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editDialogLayout = new javax.swing.GroupLayout(editDialog.getContentPane());
        editDialog.getContentPane().setLayout(editDialogLayout);
        editDialogLayout.setHorizontalGroup(
            editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editDialogLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editDialogLayout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel2))
                    .addGroup(editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(editDialogLayout.createSequentialGroup()
                            .addComponent(deleteDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(changeDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );
        editDialogLayout.setVerticalGroup(
            editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editDialogLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel3.setText("객실 등록");

        jLabel5.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jLabel5.setText("층");

        floorList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(floorList);

        jLabel6.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jLabel6.setText("호수");

        jLabel7.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jLabel7.setText("등급");

        ratingList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Standard", "Deluxe", "Premium" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(ratingList);

        jLabel8.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jLabel8.setText("가격");

        priceText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTextActionPerformed(evt);
            }
        });

        registrationDialogBtn.setText("등록");
        registrationDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationDialogBtnActionPerformed(evt);
            }
        });

        backRegistrationBtn.setText("취소");
        backRegistrationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backRegistrationBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout registrationDialogLayout = new javax.swing.GroupLayout(registrationDialog.getContentPane());
        registrationDialog.getContentPane().setLayout(registrationDialogLayout);
        registrationDialogLayout.setHorizontalGroup(
            registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registrationDialogLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registrationDialogLayout.createSequentialGroup()
                        .addGroup(registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registrationDialogLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registrationDialogLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(87, 87, 87)
                        .addGroup(registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(roomText, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112)
                        .addGroup(registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addGroup(registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(79, 79, 79))
                    .addGroup(registrationDialogLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(backRegistrationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registrationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))))
            .addGroup(registrationDialogLayout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        registrationDialogLayout.setVerticalGroup(
            registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationDialogLayout.createSequentialGroup()
                .addGroup(registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registrationDialogLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel4))
                    .addGroup(registrationDialogLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registrationDialogLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(27, 27, 27)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(registrationDialogLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(27, 27, 27)
                                    .addComponent(roomText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(41, 41, 41))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registrationDialogLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(27, 27, 27)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registrationDialogLayout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(27, 27, 27)
                                    .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backRegistrationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        roomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "층", "호수", "등급", "가격"
            }
        ));
        jScrollPane1.setViewportView(roomTable);

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel1.setText("객실 관리");

        backBtn.setText("뒤로");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        registrationBtn.setText("등록");
        registrationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("삭제");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        storageBtn.setText("저장");
        storageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storageBtnActionPerformed(evt);
            }
        });

        changeBtn.setText("수정");
        changeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registrationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(changeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(storageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(122, 122, 122)
                            .addComponent(backBtn))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(backBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDialogBtnActionPerformed
        // 수정 다이얼로그 닫기
        editDialog.dispose();
    }//GEN-LAST:event_deleteDialogBtnActionPerformed

    private void changeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeBtnActionPerformed
        // 테이블에서 선택된 행을 기반으로 수정 다이얼로그 열기
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "수정할 행을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        dialogManager.showEditDialog(selectedRow);
    }//GEN-LAST:event_changeBtnActionPerformed

    private void changeDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDialogBtnActionPerformed
        // 수정 다이얼로그의 데이터를 적용
        updateRoomData();
    }//GEN-LAST:event_changeDialogBtnActionPerformed

    private void storageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storageBtnActionPerformed
        // 데이터 저장 버튼 클릭 시 파일에 저장
        saveTableData();
    }//GEN-LAST:event_storageBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // 뒤로 가기 버튼 클릭 시 관리 화면으로 돌아가기
        backToManagementFrame();
    }//GEN-LAST:event_backBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // 삭제 버튼 클릭 시 선택된 행 삭제
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "삭제할 행을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        dialogManager.deleteRoom(selectedRow);  
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void registrationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationBtnActionPerformed
        // 등록 다이얼로그 열기 
        dialogManager.showRegistrationDialog();
    }//GEN-LAST:event_registrationBtnActionPerformed

    private void priceTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTextActionPerformed

    private void registrationDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationDialogBtnActionPerformed
        registerRoom();
    }//GEN-LAST:event_registrationDialogBtnActionPerformed

    private void backRegistrationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backRegistrationBtnActionPerformed
        // TODO add your handling code here:
        registrationDialog.dispose();
    }//GEN-LAST:event_backRegistrationBtnActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Management_Room().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton backRegistrationBtn;
    private javax.swing.JButton changeBtn;
    private javax.swing.JButton changeDialogBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton deleteDialogBtn;
    private javax.swing.JDialog editDialog;
    private javax.swing.JTable editTable;
    private javax.swing.JList<String> floorList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField priceText;
    private javax.swing.JList<String> ratingList;
    private javax.swing.JButton registrationBtn;
    private javax.swing.JDialog registrationDialog;
    private javax.swing.JButton registrationDialogBtn;
    private javax.swing.JTable roomTable;
    private javax.swing.JTextField roomText;
    private javax.swing.JButton storageBtn;
    // End of variables declaration//GEN-END:variables
}
