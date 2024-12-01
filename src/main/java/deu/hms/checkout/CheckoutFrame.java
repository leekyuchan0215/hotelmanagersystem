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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import deu.hms.checkIn.Customer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.awt.Font;


public class CheckoutFrame extends javax.swing.JFrame {
    
    private LocalDateTime checkOutDateTime;
    private int extraFee = 0; // 추가 요금
    private int totalAmount = 0; // 총 금액, 기본 값 제거  
    private Customer currentCustomer; // 현재 고객 정보를 저장하는 전역 변수
 
    public CheckoutFrame() {
        initComponents();
        loadCheckInList();
        checkInListArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }
    private void resetFields() {
    idField.setText(""); // ID 필드 초기화
    RoomArea.setText(""); // 객실 정보 초기화
    ChooseComboBox.setSelectedIndex(0); // 콤보 박스 초기화
    FeedbackArea.setText(""); // 피드백 초기화
    checkOutDateTime = null; // 체크아웃 시간 초기화
    extraFee = 0; // 추가 요금 초기화
}
    private void removeCustomerFromCheckInList(Customer customer) {
    String inputFile = "C:\\Users\\rlarh\\OneDrive\\바탕 화면\\호텔관리시스템\\hotelmanagersystem\\checked_in_customers.txt";
    String tempFile = "C:\\Users\\rlarh\\OneDrive\\바탕 화면\\호텔관리시스템\\hotelmanagersystem\\temp_checked_in_customers.txt";

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = reader.readLine()) != null) {
            // 현재 고객의 정보가 포함된 줄인지 확인
            if (!line.contains("고객 이름: " + customer.getName()) &&
                !line.contains("예약 번호: " + customer.getReservationId())) {
                writer.write(line);
                writer.newLine();
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "체크인 명단 업데이트 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return;
    }

    // 기존 파일을 임시 파일로 교체
    File originalFile = new File(inputFile);
    File updatedFile = new File(tempFile);

    if (originalFile.delete()) {
        updatedFile.renameTo(originalFile);
    } else {
        JOptionPane.showMessageDialog(this, "체크인 명단 파일 업데이트 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
}
    
    
    private void loadCheckInList() {
    String filePath = "C:\\Users\\rlarh\\OneDrive\\바탕 화면\\호텔관리시스템\\hotelmanagersystem\\checked_in_customers.txt";

    File file = new File(filePath);

    if (!file.exists()) {
        checkInListArea.setText("체크인된 고객이 없습니다."); // 명단이 없을 때 초기 메시지
        return;
    }

     try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        checkInListArea.setText(""); // 기존 내용을 초기화
        String line;
        while ((line = reader.readLine()) != null) {
            // 파일의 각 줄을 읽어 포맷에 맞게 추가
            String[] parts = line.split(", "); // 파일 데이터 분리
            if (parts.length >= 5) { // 데이터가 제대로 구성된 경우
                String formattedLine = String.format(
                    "고유 번호: %-10s | 이름: %-10s | 객실 번호: %-5s | 객실 요금: %-10s | 결제 방식: %-5s",
                    parts[1].split(": ")[1].trim(), // 고유 번호
                    parts[0].split(": ")[1].trim(), // 이름
                    parts[2].split(": ")[1].trim(), // 객실 번호
                    parts[3].split(": ")[1].trim(), // 객실 요금
                    parts[4].split(": ")[1].trim()  // 결제 방식
                );
                checkInListArea.append(formattedLine + "\n");
            } else {
                checkInListArea.append("데이터 형식 오류: " + line + "\n"); // 오류 데이터 처리
            }
        }
    } catch (IOException e) {
        checkInListArea.setText("체크인 명단을 불러오는 중 오류 발생!"); // 오류 메시지
        e.printStackTrace();
    }
}
    
    
    private void saveFeedbackToFile(String feedback) {
    // 저장할 파일 경로 업데이트
    String filePath = "C:\\Users\\rlarh\\OneDrive\\바탕 화면\\호텔관리시스템\\hotelmanagersystem\\feedback_list.txt";
    
    try (FileWriter writer = new FileWriter(filePath, true)) { // append 모드로 파일 열기
        String contentToSave = String.format("피드백 시간: %s\n%s\n\n", 
                                             LocalDateTime.now().toString(), feedback);
        writer.write(contentToSave); // 파일에 피드백 내용 저장
        JOptionPane.showMessageDialog(this, "피드백이 파일에 저장되었습니다.", "저장 완료", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "파일 저장 중 오류 발생: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
    }
}
    private boolean isCheckedOut(String nameOrID) {
    try (BufferedReader reader = new BufferedReader(new FileReader("checked_out_customers.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("예약 번호: " + nameOrID) || line.contains("고객 이름: " + nameOrID)) {
                return true; // 체크아웃된 고객 발견
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "체크아웃 정보를 읽는 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
    }
    return false; // 체크아웃되지 않은 고객
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
        refreshButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        checkInListArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

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
        ChooseComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseComboBoxActionPerformed(evt);
            }
        });

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

        refreshButton.setText("체크인 명단 새로고침");

        checkInListArea.setColumns(20);
        checkInListArea.setRows(5);
        jScrollPane3.setViewportView(checkInListArea);

        jLabel1.setText("체크인 명단 :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(PaymentLabel)
                .addGap(18, 18, 18)
                .addComponent(ChooseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 319, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(316, 316, 316)
                                .addComponent(CheckOutButton)
                                .addGap(18, 18, 18)
                                .addComponent(closeButton))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(FeedbackLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addComponent(idLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(311, 311, 311)
                                .addComponent(RoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(293, 293, 293)
                .addComponent(InitializationButton)
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InitializationButton)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel1)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RoomButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PaymentLabel)
                    .addComponent(ChooseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(FeedbackLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckOutButton)
                    .addComponent(closeButton))
                .addGap(25, 25, 25))
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
      String nameOrID = idField.getText().trim();

    if (nameOrID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "이름 또는 고유 번호를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 체크아웃된 고객인지 확인
    if (isCheckedOut(nameOrID)) {
        JOptionPane.showMessageDialog(this, "해당 고객은 이미 체크아웃되었습니다.", "오류", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 체크인된 고객 정보 검색
    currentCustomer = findCheckInCustomer(nameOrID);

    if (currentCustomer != null) {
        checkOutDateTime = LocalDateTime.now(); // 현재 시간 저장
        LocalTime checkOutLimit = LocalTime.of(11, 0); // 기준 체크아웃 시간
        LocalTime actualCheckOutTime = checkOutDateTime.toLocalTime();

        // 추가 요금 계산
        extraFee = actualCheckOutTime.isAfter(checkOutLimit) ? 20000 : 0;
        totalAmount = currentCustomer.getPaymentAmount() + extraFee; // 체크인 요금 + 추가 요금

        String roomInfo = String.format(
                "이름: %s\n객실: %s\n기본 요금: %d원\n추가 요금: %d원\n총 금액: %d원",
                currentCustomer.getName(), currentCustomer.getRoomNumber(),
                currentCustomer.getPaymentAmount(), extraFee, totalAmount
        );
        RoomArea.setText(roomInfo);
    } else {
        JOptionPane.showMessageDialog(this, "해당 고객의 체크인 정보를 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private Customer findCheckInCustomer(String nameOrID) {
    try (BufferedReader reader = new BufferedReader(new FileReader("checked_in_customers.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // 데이터 파싱: 고객 이름, 고유 번호, 객실 번호, 요금을 분리
            if (line.contains("고객 이름:") && line.contains("예약 번호:") && line.contains("객실 요금:")) {
                String[] parts = line.split(", ");
                String name = parts[0].split(": ")[1].trim(); // "고객 이름: 홍길동" -> "홍길동"
                String reservationId = parts[1].split(": ")[1].trim(); // "예약 번호: 12345" -> "12345"
                String roomNumber = parts[2].split(": ")[1].trim(); // "객실 번호: 101호" -> "101호"
                int paymentAmount = Integer.parseInt(parts[3].split(": ")[1].replace("원", "").trim()); // "객실 요금: 100000원" -> 100000

                // 입력값(고유번호 또는 이름)과 비교
                if (reservationId.equalsIgnoreCase(nameOrID) || name.equalsIgnoreCase(nameOrID)) {
                    return new Customer(name, reservationId, roomNumber, paymentAmount); // 객실 요금 포함
                }
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "체크인 정보를 읽는 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
    }
    return null; // 일치하는 고객 정보가 없을 경우
    }//GEN-LAST:event_RoomButtonActionPerformed

    private void CheckOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckOutButtonActionPerformed
    if (checkOutDateTime == null || currentCustomer == null) {
        JOptionPane.showMessageDialog(this, "먼저 객실 정보를 불러오세요.", "오류", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String paymentType = (String) ChooseComboBox.getSelectedItem();
    String feedback = FeedbackArea.getText().trim();
    String roomNumber = currentCustomer.getRoomNumber();
    String customerNameOrID = idField.getText().trim();

    // 체크아웃 완료 메시지
    String message = String.format(
    "체크아웃 완료!\n결제 방식: %s\n추가 요금: %d원\n총 결제 금액: %d원",
    paymentType, extraFee, totalAmount
    );
    JOptionPane.showMessageDialog(this, message, "체크아웃", JOptionPane.INFORMATION_MESSAGE);

    // 체크아웃 고객 기록
    saveCheckedOutCustomer(currentCustomer);
    
    // 체크아웃 완료 후 체크인 명단에서 삭제
    removeCustomerFromCheckInList(currentCustomer);

    // 체크인 명단 새로고침
    loadCheckInList();

    // 피드백 저장
    saveFeedbackToFile("C:\\Users\\rlarh\\OneDrive\\바탕 화면\\호텔관리시스템\\hotelmanagersystem\\feedback_list.txt",
            feedback, roomNumber, customerNameOrID);

    resetFields();
}
    private void saveCheckedOutCustomer(Customer customer) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\rlarh\\OneDrive\\바탕 화면\\호텔관리시스템\\hotelmanagersystem\\checked_out_customers.txt", true))) {
        writer.write("고객 이름: " + customer.getName()
                + ", 예약 번호: " + customer.getReservationId()
                + ", 객실 번호: " + customer.getRoomNumber());
        writer.newLine();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "체크아웃 정보를 저장하는 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
    }
}
        private void saveFeedbackToFile(String filePath, String feedback, String roomNumber, String customerNameOrID) {
    if (feedback.isEmpty()) {
        return; // 피드백이 없으면 저장하지 않음
    }
    try {
        FileWriter writer = new FileWriter(filePath, true); // append 모드로 파일 열기
        writer.write("=== 고객 피드백 ===\n");
        writer.write("시간: " + LocalDateTime.now() + "\n");
        writer.write("객실 번호: " + roomNumber + "\n");
        writer.write("고객 이름/ID: " + customerNameOrID + "\n");
        writer.write("피드백: " + feedback + "\n");
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

    private void ChooseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChooseComboBoxActionPerformed

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
    private javax.swing.JTextArea checkInListArea;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idLabel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables
}
//테스트4