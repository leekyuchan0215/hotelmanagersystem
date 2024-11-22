package deu.hms.management.room;

import deu.hms.management.ManagementFrame;
import deu.hms.management.account.AccountManagementFrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Management_Room extends javax.swing.JFrame {

    public Management_Room() {
        initComponents();
        loadTableData(); // JTable 초기화 시 데이터 로드
    }

    private void loadTableData() {
        // 테이블의 값들을 채우는 메서드

        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
        model.setRowCount(0); // 기존 데이터 초기화

        try (BufferedReader br = new BufferedReader(new FileReader("room_list.txt"))) {     // "room_list.txt"파일 가져와 읽기
            String line;
            while ((line = br.readLine()) != null) {   //읽어들인 행이 비어있지 않다면 반복 
                // 데이터 형식 :  층, 호수, 등급, 가격
                String[] rowData = line.split(",");    // ','로 구분된 데이터
                if (rowData.length == 4) {
                    model.addRow(rowData);     // JTable에 행 추가
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일을 읽는 중 문제가 발생했습니다.");
        }
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
        jButton3 = new javax.swing.JButton();
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

        jButton3.setText("취소");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                        .addGroup(registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(registrationDialogLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registrationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79))
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
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        // TODO add your handling code here:
        editDialog.dispose();
    }//GEN-LAST:event_deleteDialogBtnActionPerformed

    private void changeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = roomTable.getSelectedRow(); // 선택된 행 인덱스
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "수정할 행을 선택하세요.");
            return;
        }

        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel();
        editModel.setRowCount(0); // 기존 데이터를 초기화
        String[] rowData = new String[4];
        for (int i = 0; i < 4; i++) {
            rowData[i] = roomTable.getValueAt(selectedRow, i).toString();
        }
        editModel.addRow(rowData); // 선택한 행 데이터를 editTable에 추가

        editDialog.setSize(700, 300);  // 다이얼로그 크기 설정
        editDialog.setLocationRelativeTo(this);  // 부모 컴포넌트를 기준으로 중앙에 배치
        editDialog.setTitle("객실 수정");  // 다이얼로그 제목 설정
        editDialog.setModal(false);  // 비모달로 설정 (부모 창과 상호작용 가능)
        editDialog.setVisible(true);  // 다이얼로그 표시
        editDialog.toFront();  // 다이얼로그를 화면 최상위로 가져오기
    }//GEN-LAST:event_changeBtnActionPerformed

    private void changeDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDialogBtnActionPerformed
        // TODO add your handling code here:
        // editTable의 첫 번째 행 데이터 가져오기
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel();
        DefaultTableModel roomModel = (DefaultTableModel) roomTable.getModel();

        // editTable이 비어있지 않은지 확인
        if (editModel.getRowCount() > 0) {
            // 수정된 데이터를 editTable에서 가져옴
            String[] updatedRowData = new String[editModel.getColumnCount()];
            for (int i = 0; i < updatedRowData.length; i++) {
                Object cellValue = editModel.getValueAt(0, i); // 첫 번째 행의 데이터 가져오기
                updatedRowData[i] = cellValue != null ? cellValue.toString() : ""; // null 방지
            }

            // roomTable에서 선택된 행 가져오기
            int selectedRow = roomTable.getSelectedRow();
            if (selectedRow != -1) {
                // roomTable의 해당 행 데이터 업데이트
                for (int i = 0; i < updatedRowData.length; i++) {
                    roomModel.setValueAt(updatedRowData[i], selectedRow, i);
                }
                JOptionPane.showMessageDialog(this, "데이터가 성공적으로 수정되었습니다.");
            } else {
                JOptionPane.showMessageDialog(this, "수정할 행을 선택하세요.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "수정할 데이터가 없습니다.");
        }

        // 다이얼로그 닫기
        editDialog.dispose();
    }//GEN-LAST:event_changeDialogBtnActionPerformed

    private void storageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storageBtnActionPerformed
        // TODO add your handling code here:
        storageBtn.addActionListener((e) -> {
            try {
                //JTable의 데이터를 읽어올 TableModel
                DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
                int rowCount = model.getRowCount();   // 행의 개수를 rowCount 변수에 저장
                int columnCount = model.getColumnCount();  // 열의 개수를 columnCount 변수에 저장

                //파일에 내용 저장
                FileWriter writer = new FileWriter("room_list.txt", false);   // 파일 덮어쓰기
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                //JTable 데이터를 파일에 저장
                for (int i = 0; i < rowCount; i++) {
                    StringBuilder rowBuilder = new StringBuilder();
                    for (int j = 0; j < columnCount; j++) {
                        rowBuilder.append(model.getValueAt(i, j).toString());
                        if (j < columnCount - 1) {
                            rowBuilder.append(","); //컬럼 구분자 (,)
                        }
                    }
                    bufferedWriter.write(rowBuilder.toString());
                    bufferedWriter.newLine(); //줄 바꿈
                }
                bufferedWriter.close();
                writer.close();

                JOptionPane.showMessageDialog(this, "변경 사항이 저장되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "파일 저장 중 오류가 발생했습니다: " + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }//GEN-LAST:event_storageBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "이전 화면으로 돌아갑니다.");
        this.dispose();
        ManagementFrame mframe = new ManagementFrame();
        mframe.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // 삭제 버튼을 눌렀을 때 동작
        deleteBtn.addActionListener(e -> {
            int selectedRow = roomTable.getSelectedRow();  //선택된 행의 인덱스 가져오기

            /**
             * if(selectedRow == -1) { // 선택된 행이 없으면 -1 반환
             * JOptionPane.showMessageDialog(this,"삭제할
             * 행을선택하세요!","오류",JOptionPane.ERROR_MESSAGE); return; }
             */
            //JTable 의 모델 가져오기
            DefaultTableModel model = (DefaultTableModel) roomTable.getModel();

            //행 삭제
            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "선택된 계정이 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
        });
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void registrationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationBtnActionPerformed
        // TODO add your handling code here:
         //  등록 버튼 눌렀을 때 동작 : 새로운 계정을 등록할 수 있는 창 띄우기
        registrationDialog.setSize(700, 400);  // 다이얼로그 크기 설정
        registrationDialog.setLocationRelativeTo(this);  // 부모 컴포넌트를 기준으로 중앙에 배치
        registrationDialog.setTitle("계정 등록");  // 다이얼로그 제목 설정
        registrationDialog.setModal(false);  // 비모달로 설정 (부모 창과 상호작용 가능)
        registrationDialog.setVisible(true);  // 다이얼로그 표시
        registrationDialog.toFront();  // 다이얼로그를 화면 최상위로 가져오기
    }//GEN-LAST:event_registrationBtnActionPerformed

    private void priceTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTextActionPerformed

    private void registrationDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationDialogBtnActionPerformed
        // TODO add your handling code here:
        // 새로운 계정 등록할 수 있는 창
        registrationDialogBtn.addActionListener(e -> {
            // 입력값 가져오기
            String floor = floorList.getSelectedValue();  // numberText에 입력한 값을 number 변수에 저장
            String room = roomText.getText().trim();  // idText 에 입력한 값을 id 변수에 저장
            String rating = ratingList.getSelectedValue();  // pwText에 입력한 값을 pw 변수에 저장
            String price = priceText.getText().trim(); // rightList에서 선택한 값을 role 변수에 저장
            // 유효성 검사
            if (floor == null || room.isEmpty() || rating == null || price.isEmpty()) { // 4개중 하나라도 입력하지 않았다면
                JOptionPane.showMessageDialog(this, "모든 필드를 채워주세요!", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // "room_list.txt" 파일에 데이터 추가
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("room_list.txt", true))) {
                writer.write(floor + "," + room + "," + rating + "," + price);  // 데이터를 "floor,room,rating,price" 형식으로 저장
                writer.newLine(); // 줄바꿈 추가
            } catch (IOException ex) {
                Logger.getLogger(AccountManagementFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            // 성공 메시지
            JOptionPane.showMessageDialog(this, "등록이 완료되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);

            registrationDialog.dispose();
            // 입력 필드 초기화
            floorList.clearSelection();
            roomText.setText("");
            ratingList.clearSelection();
            priceText.setText("");
            // 테이블 데이터 갱신
            loadTableData();
        });
    }//GEN-LAST:event_registrationDialogBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        registrationDialog.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Management_Room().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton changeBtn;
    private javax.swing.JButton changeDialogBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton deleteDialogBtn;
    private javax.swing.JDialog editDialog;
    private javax.swing.JTable editTable;
    private javax.swing.JList<String> floorList;
    private javax.swing.JButton jButton3;
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
