package deu.hms.management.service;

import deu.hms.login.MainFrame_Master;
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

public class Management_Service extends javax.swing.JFrame {

    public Management_Service() {
        initComponents();
        loadTableData();
    }

    private void loadTableData() {
        // 테이블의 값들을 채우는 메서드

        DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
        model.setRowCount(0); // 기존 데이터 초기화

        try (BufferedReader br = new BufferedReader(new FileReader("menu_list.txt"))) {     // "menu_list.txt"파일 가져와 읽기
            String line;
            while ((line = br.readLine()) != null) {   //읽어들인 행이 비어있지 않다면 반복 
                // 데이터 형식: 고유번호, ID, PW, 관리자 권한
                String[] rowData = line.split(",");    // ','로 구분된 데이터
                if (rowData.length == 3) {
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

        registrationDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        serviceList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        foodText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        priceText = new javax.swing.JTextField();
        cancelBtn = new javax.swing.JButton();
        registrationDialogBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        serviceTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        registrationBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        storageBtn = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(200, 200, 200));
        jPanel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel2.setText("서비스 종류");

        serviceList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "룸 서비스", "식당" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(serviceList);

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel3.setText("음식");

        foodText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodTextActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel4.setText("가격");

        cancelBtn.setText("취소");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        registrationDialogBtn.setText("등록");
        registrationDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationDialogBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registrationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(79, 79, 79))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addGap(39, 39, 39)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(foodText, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(foodText, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout registrationDialogLayout = new javax.swing.GroupLayout(registrationDialog.getContentPane());
        registrationDialog.getContentPane().setLayout(registrationDialogLayout);
        registrationDialogLayout.setHorizontalGroup(
            registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        registrationDialogLayout.setVerticalGroup(
            registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        serviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, "", null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "종류", "음식", "가격"
            }
        ));
        jScrollPane1.setViewportView(serviceTable);

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel1.setText("서비스 관리");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registrationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(storageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(93, 93, 93)
                            .addComponent(backBtn))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(122, 122, 122))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationBtnActionPerformed
        // TODO add your handling code here:
        registrationDialog.setSize(700, 300);  // 다이얼로그 크기 설정
        registrationDialog.setLocationRelativeTo(this);  // 부모 컴포넌트를 기준으로 중앙에 배치
        registrationDialog.setTitle("계정 등록");  // 다이얼로그 제목 설정
        registrationDialog.setModal(false);  // 비모달로 설정 (부모 창과 상호작용 가능)
        registrationDialog.setVisible(true);  // 다이얼로그 표시
        registrationDialog.toFront();  // 다이얼로그를 화면 최상위로 가져오기
    }//GEN-LAST:event_registrationBtnActionPerformed

    private void foodTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_foodTextActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        registrationDialog.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void registrationDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationDialogBtnActionPerformed
        // TODO add your handling code here:
        registrationDialogBtn.addActionListener(e -> {
            // 입력값 가져오기
            String service = serviceList.getSelectedValue();
            String food = foodText.getText().trim();
            String price = priceText.getText().trim();

            // 유효성 검사
            if (food.isEmpty() || price.isEmpty() || service == null) { // 3개 중 하나라도 입력하지 않았다면
                JOptionPane.showMessageDialog(this, "모든 필드를 채워주세요!", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // "menu_list.txt" 파일에 데이터 추가
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu_list.txt", true))) {
                writer.write(service + "," + food + "," + price); // 데이터를 "service,food,price" 형식으로 저장
                writer.newLine(); // 줄바꿈 추가 (중요!)
            } catch (IOException ex) {
                Logger.getLogger(AccountManagementFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            // 성공 메시지
            JOptionPane.showMessageDialog(this, "등록이 완료되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);

            // 다이얼로그 닫기 및 입력 필드 초기화
            registrationDialog.dispose();
            foodText.setText("");
            priceText.setText("");
            serviceList.clearSelection();

            // 테이블 데이터 갱신
            loadTableData();
        });
    }//GEN-LAST:event_registrationDialogBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "이전 화면으로 돌아갑니다.");
        this.dispose();
        MainFrame_Master mainframe = new MainFrame_Master();
        mainframe.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        deleteBtn.addActionListener(e -> {
            int selectedRow = serviceTable.getSelectedRow();  //선택된 행의 인덱스 가져오기

            /**
             * if(selectedRow == -1) { // 선택된 행이 없으면 -1 반환
             * JOptionPane.showMessageDialog(this,"삭제할
             * 행을선택하세요!","오류",JOptionPane.ERROR_MESSAGE); return; }
             */
            //JTable 의 모델 가져오기
            DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();

            //행 삭제
            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "선택된 서비스가 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
        });
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void storageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storageBtnActionPerformed
        // TODO add your handling code here:
        storageBtn.addActionListener((e) -> {
            try {
                //JTable의 데이터를 읽어올 TableModel
                DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
                int rowCount = model.getRowCount();   // 행의 개수를 rowCount 변수에 저장
                int columnCount = model.getColumnCount();  // 열의 개수를 columnCount 변수에 저장

                //파일에 내용 저장
                FileWriter writer = new FileWriter("menu_list.txt", false);   // 파일 덮어쓰기
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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Management_Service().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField foodText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField priceText;
    private javax.swing.JButton registrationBtn;
    private javax.swing.JDialog registrationDialog;
    private javax.swing.JButton registrationDialogBtn;
    private javax.swing.JList<String> serviceList;
    private javax.swing.JTable serviceTable;
    private javax.swing.JButton storageBtn;
    // End of variables declaration//GEN-END:variables
}
