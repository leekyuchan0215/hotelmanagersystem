/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deu.hms.checkout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.FileWriter;
import java.io.IOException;

public class CheckoutFrame extends javax.swing.JFrame {
    
    private LocalDateTime checkOutDateTime;
    private int extraFee = 0; // 추가 요금
    private int totalAmount = 100000; // 기본 요금
 
    public CheckoutFrame() {
        initComponents();
    }
    private void resetFields() {
    idField.setText(""); // ID 필드 초기화
    RoomArea.setText(""); // 객실 정보 초기화
    ChooseComboBox.setSelectedIndex(0); // 콤보 박스 초기화
    FeedbackArea.setText(""); // 피드백 초기화
    checkOutDateTime = null; // 체크아웃 시간 초기화
    extraFee = 0; // 추가 요금 초기화
}
private void saveFeedbackToFile(String feedback) {
    String filePath = "feedback.txt"; // 저장할 파일 경로
    try (FileWriter writer = new FileWriter(filePath, true)) { // true로 파일에 내용 추가
        String contentToSave = String.format("피드백 시간: %s\n%s\n\n", 
                                             LocalDateTime.now().toString(), feedback);
        writer.write(contentToSave); // 파일에 피드백 내용 저장
        JOptionPane.showMessageDialog(this, "피드백이 파일에 저장되었습니다.", "저장 완료", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "파일 저장 중 오류 발생: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idLabel = new javax.swing.JLabel();
        InitializationButton = new javax.swing.JButton();
        idLabel1 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        RoomButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        RoomArea = new javax.swing.JTextArea();
        PaymentLabel = new javax.swing.JLabel();
        ChooseComboBox = new javax.swing.JComboBox<>();
        FeedbackLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        FeedbackArea = new javax.swing.JTextArea();
        CheckOutButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        idLabel.setText("이름 또는 고유 번호 입력 :");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        InitializationButton.setText("초기화");
        InitializationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InitializationButtonActionPerformed(evt);
            }
        });

        idLabel1.setText("이름 또는 고유 번호 입력 :");

        idField.setName(""); // NOI18N
        idField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFieldActionPerformed(evt);
            }
        });

        RoomButton.setText("객실 정보 불러오기");
        RoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomButtonActionPerformed(evt);
            }
        });

        RoomArea.setColumns(20);
        RoomArea.setRows(5);
        jScrollPane1.setViewportView(RoomArea);

        PaymentLabel.setText("결제 유형 선택 :");

        ChooseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "카드", "현금" }));

        FeedbackLabel.setText("고객 피드백 :");

        FeedbackArea.setColumns(20);
        FeedbackArea.setRows(5);
        jScrollPane2.setViewportView(FeedbackArea);

        CheckOutButton.setText("체크아웃");
        CheckOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckOutButtonActionPerformed(evt);
            }
        });

        closeButton.setText("닫기");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(RoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(InitializationButton)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idField, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                        .addGap(102, 102, 102))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(CheckOutButton)
                        .addGap(28, 28, 28)
                        .addComponent(closeButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(FeedbackLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(PaymentLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ChooseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InitializationButton)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel1)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(RoomButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PaymentLabel)
                    .addComponent(ChooseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(FeedbackLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckOutButton)
                    .addComponent(closeButton))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InitializationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InitializationButtonActionPerformed
        // TODO add your handling code here:
        resetFields();
        JOptionPane.showMessageDialog(this, "모든 입력이 초기화되었습니다.", "초기화", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_InitializationButtonActionPerformed

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFieldActionPerformed

    private void RoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomButtonActionPerformed
        // TODO add your handling code here:
        checkOutDateTime = LocalDateTime.now(); // 현재 시간 저장
        String nameOrID = idField.getText().trim();

        if (nameOrID.equals("12345") || nameOrID.equalsIgnoreCase("홍길동")) {
            LocalTime checkOutLimit = LocalTime.of(11, 0); // 기준 체크아웃 시간 (오전 11시)
            LocalTime actualCheckOutTime = checkOutDateTime.toLocalTime();

            extraFee = actualCheckOutTime.isAfter(checkOutLimit) ? 20000 : 0;
            String roomInfo = String.format(
                "객실: 101호\n금액: 100,000원\n추가 요금: %d원\n총 금액: %d원",
                extraFee, (totalAmount + extraFee)
            );
            RoomArea.setText(roomInfo);
        } else {
            JOptionPane.showMessageDialog(this, "잘못된 이름 또는 고유 번호입니다.", "오류", JOptionPane.ERROR_MESSAGE);
            RoomArea.setText(""); // 입력 초기화
        }
    }//GEN-LAST:event_RoomButtonActionPerformed

    private void CheckOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckOutButtonActionPerformed
        // TODO add your handling code here:
        // 체크아웃 처리 코드
        if (checkOutDateTime == null) {
            JOptionPane.showMessageDialog(this, "먼저 객실 정보를 불러오세요.", "오류", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 결제 방식, 피드백, 객실 번호 및 고객 정보 가져오기
        String paymentType = (String) ChooseComboBox.getSelectedItem();
        String feedback = FeedbackArea.getText().trim();
        String roomNumber = "101호"; // 예시, 실제 로직에 따라 설정 가능
        String customerNameOrID = idField.getText().trim(); // 고객 이름 또는 ID

        // 체크아웃 완료 메시지 생성
        String message = String.format(
            "체크아웃 완료!\n결제 방식: %s\n추가 요금: %d원\n총 결제 금액: %d원",
            paymentType, extraFee, (totalAmount + extraFee), feedback.isEmpty() ? "없음" : feedback
        );
        JOptionPane.showMessageDialog(this, message, "체크아웃", JOptionPane.INFORMATION_MESSAGE);

        // 피드백 내용을 파일로 저장
        String filePath = "C:\\Users\\rlarh\\OneDrive\\바탕 화면\\feedback.txt";
        saveFeedbackToFile(filePath, feedback, roomNumber, customerNameOrID);

        resetFields();
        }

        // 파일에 피드백 저장하는 메서드
        private void saveFeedbackToFile(String filePath, String feedback, String roomNumber, String customerNameOrID) {
            // 피드백이 비어 있으면 저장하지 않음
            if (feedback.isEmpty()) {
                return; // 메서드 종료
            }
            try {
                FileWriter writer = new FileWriter(filePath, true); // append 모드로 파일 열기
                writer.write("=== 고객 피드백 ===\n");
                writer.write("시간: " + LocalDateTime.now() + "\n");
                writer.write("객실 번호: " + roomNumber + "\n");
                writer.write("고객 이름/ID: " + customerNameOrID + "\n");
                writer.write("피드백: " + (feedback.isEmpty() ? "없음" : feedback) + "\n");
                writer.write("\n--------------------\n\n");
                writer.close();
                JOptionPane.showMessageDialog(this, "피드백이 성공적으로 저장되었습니다!", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "피드백 저장 중 오류가 발생했습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
    }//GEN-LAST:event_CheckOutButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0); // 프로그램 종료
    }//GEN-LAST:event_closeButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CheckoutFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckoutFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckoutFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckoutFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckoutFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CheckOutButton;
    private javax.swing.JComboBox<String> ChooseComboBox;
    private javax.swing.JTextArea FeedbackArea;
    private javax.swing.JLabel FeedbackLabel;
    private javax.swing.JButton InitializationButton;
    private javax.swing.JLabel PaymentLabel;
    private javax.swing.JTextArea RoomArea;
    private javax.swing.JButton RoomButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
//테스트4