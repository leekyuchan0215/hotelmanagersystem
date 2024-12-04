/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deu.hms.checkout;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import deu.hms.checkIn.Customer;
import deu.hms.login.MainFrame_Master;
import deu.hms.login.MainFrame_Staff;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.util.Map;
import java.util.HashMap;

public class CheckoutFrame extends javax.swing.JFrame {

    private String userType;            // 사용자 유형
    private LocalDateTime checkOutDateTime; // 체크아웃 시각
    private int extraFee = 0;           // 추가 요금
    private int totalAmount = 0;        // 총 금액  
    private Customer currentCustomer;  // 현재 체크아웃 중인 고객 정보

    public CheckoutFrame(String userType) {
        initComponents();       // GUI 컴포넌트 초기화
        loadCheckInList();      // 체크인된 고객 리스트 로드
        checkInListArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.userType = userType;
    }
    
    //입력 필드 초기화
    private void resetFields() {
        idField.setText(""); // ID 필드 초기화
        RoomArea.setText(""); // 객실 정보 초기화
        ChooseComboBox.setSelectedIndex(0); // 결제 유형 초기화
        FeedbackArea.setText(""); // 피드백 텍스트 초기화
        checkOutDateTime = null; // 체크아웃 시간 초기화
        extraFee = 0; // 추가 요금 초기화
    }
    
    //특정 객실의 서비스 사용 금액을 계산(룸 서비스, 식당)
    private int calculateServiceCharges(String roomNumber, String serviceType) {
        String filePath = "use_service.txt";
        return parseServiceFile(filePath, roomNumber, serviceType);
    }

    private int calculateReservedServiceCharges(String roomNumber, String serviceType) {
        String filePath = "service_reservation_list.txt";   // 예약된 서비스 파일 경로
        int total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // 쉼표를 기준으로 데이터 분리
                if (parts.length >= 10) {         // 필드 수 검증
                    String type = parts[0].trim();       // 서비스 유형 (예: 식당, 룸서비스)
                    String room = parts[1].trim();       // 객실 번호
                    String details = parts[7].trim();    // 주문 세부사항
                    String priceStr = parts[8].trim();   // 금액
                    String action = parts[9].trim();     // 상태 (예: 객실 등록)

                    // 조건: 서비스 유형과 객실 번호가 일치하고 "객실 등록" 상태여야 함
                    if (type.equals(serviceType) && room.equals(roomNumber) && action.equals("객실 등록")) {
                        try {
                            // 금액 추출 (숫자만 파싱)
                            int price = Integer.parseInt(priceStr.replaceAll("[^0-9]", ""));
                            total += price; // 서비스 금액 누적

                        } catch (NumberFormatException e) {
                            System.out.println("금액 파싱 오류: " + priceStr);
                        }
                    }    
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "파일 읽기 오류: " + filePath + "\n" + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
        return total;       // 총 금액 반환
    }

    private int parseServiceFile(String filePath, String roomNumber, String serviceType) {
        int total = 0; // 서비스별 합산 금액 초기화
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("읽은 데이터: " + line); // 디버깅용 출력

                // 데이터 파싱
                String[] parts = line.split(","); // 쉼표 기준으로 분리
                if (parts.length >= 5) {                // 필드 수 검증
                    String type = parts[0].trim();      // 서비스 유형 (예: 식당, 룸서비스)
                    String room = parts[1].trim();      // 객실 번호
                    String details = parts[2].trim();   // 세부 주문 내역
                    String priceStr = parts[3].trim();  // 금액
                    String action = parts[4].trim();    // 상태 (예: 객실 등록)

                    // 디버깅 메시지
                    System.out.println("type: " + type + ", room: " + room + ", price: " + priceStr + ", action: " + action);

                    // 조건: 특정 서비스 유형, 해당 객실 번호, "객실 등록"
                    if (type.equals(serviceType) && room.equals(roomNumber) && action.equals("객실 등록")) {
                        try {
                            // 금액 추출 (숫자만 파싱)
                            int price = Integer.parseInt(priceStr.replaceAll("[^0-9]", ""));
                            total += price;

                            // 디버깅 출력
                            System.out.println("적용된 금액: " + price + ", 누적 금액: " + total);
                        } catch (NumberFormatException e) {
                            System.out.println("금액 파싱 오류: " + priceStr);
                        }
                    } else {
                        System.out.println("조건 불일치: type=" + type + ", serviceType=" + serviceType
                                + ", room=" + room + ", roomNumber=" + roomNumber + ", action=" + action);
                    }
                } else {
                    System.out.println("데이터 형식 오류: " + line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "파일 읽기 오류: " + filePath + "\n" + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
        return total;
    }
    // 체크인된 고객 명단에서 특정 고객을 삭제하는 메서드
    private void removeCustomerFromCheckInList(Customer customer) {
        String inputFile = "checked_in_customers.txt";  // 체크인 명단 파일 경로
        String tempFile = "C:\\Users\\rlarh\\OneDrive\\바탕 화면\\호텔관리시스템\\hotelmanagersystem\\temp_checked_in_customers.txt";

        boolean customerFound = false; // 삭제 대상 고객이 발견되었는지 여부

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            // 체크인 명단 파일의 각 줄을 읽으며 처리
            while ((line = reader.readLine()) != null) {
                // 현재 줄에 삭제 대상 고객의 고유 번호 또는 이름이 포함되어 있는지 확인
                if (line.contains("고유번호: " + customer.getReservationId()) || line.contains("이름: " + customer.getName())) {
                    customerFound = true; // 삭제 대상 발견
                    continue; // 해당 줄은 임시 파일에 쓰지 않고 건너뜀
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            // 파일 읽기/쓰기 중 오류가 발생한 경우 사용자에게 알림
            JOptionPane.showMessageDialog(this, "체크인 명단 업데이트 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
        // 삭제 대상 고객을 찾지 못한 경우 알림 메시지 출력
        if (!customerFound) {
            JOptionPane.showMessageDialog(this, "삭제 대상 고객 정보를 찾을 수 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            return; // 고객을 찾지 못한 경우 더 이상 진행하지 않음
        }

        // 기존 파일을 임시 파일로 교체
        File originalFile = new File(inputFile);
        File updatedFile = new File(tempFile);

        // 기존 파일 삭제 후 임시 파일을 원래 파일 이름으로 변경
        if (originalFile.delete()) {
            if (!updatedFile.renameTo(originalFile)) {
                JOptionPane.showMessageDialog(this, "체크인 명단 파일 교체 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "체크인 명단 파일 삭제 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // 체크인된 고객 명단을 파일에서 읽어와 화면에 표시하는 메서드
    private void loadCheckInList() {
        String filePath = "checked_in_customers.txt";   // 체크인 명단 파일 경로
        
        // 파일 객체 생성
        File file = new File(filePath);

        // 파일 존재 여부 확인
        if (!file.exists()) {
            // 파일이 존재하지 않을 경우, 텍스트 영역에 메시지 출력
            checkInListArea.setText("체크인된 고객이 없습니다."); 
            return;
        }
        
         // 파일 읽기
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            checkInListArea.setText(""); // 기존 내용을 초기화
            String line;
            
            // 파일의 각 줄을 읽어서 처리
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", "); 
                
                if (parts.length >= 5) { // 데이터 구성 검증 (최소 5개의 필드)
                    // 고유 번호, 이름, 객실 번호, 체크인 날짜, 체크아웃 날짜, 결제 유형 추출
                    String id = parts[0].split(": ")[1].trim(); // 고유 번호
                    String name = parts[1].split(": ")[1].trim(); // 이름
                    String roomNumber = parts[2].split(": ")[1].trim(); // 객실 번호
                    String checkInDate = parts[3].split(": ")[1].trim(); // 체크인 날짜
                    String checkOutDate = parts[4].split(": ")[1].trim(); // 체크아웃 날짜
                    String paymentType = parts[5].split(": ")[1].trim(); // 결제 유형

                    // 읽은 데이터를 포맷팅하여 텍스트 영역에 추가
                    String formattedLine = String.format(
                            "이름: %s, 고유번호: %s, 객실 번호: %s, 체크인 날짜: %s, 체크아웃 날짜: %s, 결제 유형: %s",
                            name, id, roomNumber, checkInDate, checkOutDate, paymentType
                    );
                    checkInListArea.append(formattedLine + "\n");
                } else {
                    checkInListArea.append("데이터 형식 오류: " + line + "\n"); // 오류 데이터 처리
                }
            }
        } catch (IOException e) {
            // 파일 읽기 중 오류가 발생한 경우 텍스트 영역에 메시지 출력 및 로그 기록
            checkInListArea.setText("체크인 명단을 불러오는 중 오류 발생!"); // 오류 메시지
            e.printStackTrace();
        }
    }
    
    // 고객 피드백을 파일에 저장하는 메서드, feedback 저장할 피드백 내용
    private void saveFeedbackToFile(String feedback) {
        String filePath = "feedback_list.txt";  // 피드백 저장 파일 경로

        try (FileWriter writer = new FileWriter(filePath, true)) { 
            // 저장할 피드백 내용 생성 (시간 포함)
            String contentToSave = String.format("피드백 시간: %s\n%s\n\n",
                    LocalDateTime.now().toString(), feedback);
            writer.write(contentToSave); // 파일에 피드백 내용 저장
            
             // 저장 완료 메시지 표시
            JOptionPane.showMessageDialog(this, "피드백이 파일에 저장되었습니다.", "저장 완료", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            // 파일 쓰기 중 오류 발생 시 사용자에게 알림
            JOptionPane.showMessageDialog(this, "파일 저장 중 오류 발생: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    // 특정 고객이 체크아웃 상태인지 확인하는 메서드
    // nameOrID 고객의 이름 또는 예약 번호
    // 체크아웃된 고객이면 true, 그렇지 않으면 false
    private boolean isCheckedOut(String nameOrID) {
         // 체크아웃 명단 파일을 읽기
        try (BufferedReader reader = new BufferedReader(new FileReader("checked_out_customers.txt"))) {
            String line;
             // 파일의 각 줄을 읽어 확인
            while ((line = reader.readLine()) != null) {
                // 현재 줄에 고객의 이름 또는 예약 번호가 포함되어 있는지 확인
                if (line.contains("예약 번호: " + nameOrID) || line.contains("고객 이름: " + nameOrID)) {
                    return true; // 체크아웃된 고객 발견
                }
            }
        } catch (IOException e) {
            // 파일 읽기 중 오류 발생 시 사용자에게 알림
            JOptionPane.showMessageDialog(this, "체크아웃 정보를 읽는 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
        return false; // 파일에서 해당 고객 정보를 찾지 못한 경우
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
        backBtn = new javax.swing.JButton();
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

        backBtn.setText("이전");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(322, 322, 322)
                                .addComponent(CheckOutButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(backBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addComponent(idLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(311, 311, 311)
                                .addComponent(RoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 201, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(293, 293, 293)
                .addComponent(InitializationButton)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(FeedbackLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(PaymentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChooseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InitializationButton)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel1)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RoomButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PaymentLabel)
                    .addComponent(ChooseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FeedbackLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckOutButton)
                    .addComponent(backBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // 초기화 버튼 클릭 동작
    private void InitializationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InitializationButtonActionPerformed
        // 입력 필드와 UI 요소를 초기 상태로 리셋
        resetFields();
        
         // 사용자에게 초기화 완료 메시지 표시
        JOptionPane.showMessageDialog(this, "모든 입력이 초기화되었습니다.", "초기화", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_InitializationButtonActionPerformed

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFieldActionPerformed
    // 객실 정보 조회 버튼 동작
    private void RoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomButtonActionPerformed
        // ID 필드에서 입력된 고객 이름 또는 고유 번호를 가져옴
        String nameOrID = idField.getText().trim();
        
        // 입력값이 비어 있는 경우 경고 메시지를 표시하고 종료
        if (nameOrID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "이름 또는 고유 번호를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 고객이 이미 체크아웃된 상태인지 확인
        if (isCheckedOut(nameOrID)) {
            JOptionPane.showMessageDialog(this, "해당 고객은 이미 체크아웃되었습니다.", "오류", JOptionPane.WARNING_MESSAGE);
            return;
        }

         // 체크인된 고객 정보를 검색
        currentCustomer = findCheckInCustomer(nameOrID);

        // 체크인된 고객이 있는 경우
        if (currentCustomer != null) {
             // 현재 체크아웃 시간을 설정
            checkOutDateTime = LocalDateTime.now(); 
            
            // 체크아웃 시간 제한 설정 (11:00 AM)
            LocalTime checkOutLimitTime = LocalTime.of(11, 0); 

            // 체크아웃 날짜를 검색
            String checkOutDateString = findCheckOutDate(nameOrID);
            if (checkOutDateString == null) {
                // 체크아웃 날짜를 찾지 못한 경우 오류 메시지 표시
                JOptionPane.showMessageDialog(this, "체크아웃 날짜를 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 체크아웃 날짜와 현재 날짜 비교
            LocalDate checkOutDate = LocalDate.parse(checkOutDateString);
            LocalDate currentDate = checkOutDateTime.toLocalDate();

            int overdueDays = 0;        // 초과 날짜
            int lateCheckOutFee = 0;    // 늦은 체크아웃 추가 요금

            // 체크아웃 날짜를 초과한 경우 초과 날짜 계산 (하루당 50,000원 추가 요금)
            if (currentDate.isAfter(checkOutDate)) {
                overdueDays = (int) java.time.temporal.ChronoUnit.DAYS.between(checkOutDate, currentDate);
            }

            // 체크아웃 시간이 제한 시간을 초과한 경우 (20,000원 추가 요금)
            LocalTime actualCheckOutTime = checkOutDateTime.toLocalTime();
            if (currentDate.equals(checkOutDate) && actualCheckOutTime.isAfter(checkOutLimitTime)) {
                lateCheckOutFee = 20000;
            }

            // 초과 요금 합산
            extraFee = overdueDays * 50000 + lateCheckOutFee;

            // 예약되지 않은 룸 서비스 및 식당 사용 금액 계산
            int roomServiceCharge = calculateServiceCharges(currentCustomer.getRoomNumber(), "룸서비스");
            int diningCharge = calculateServiceCharges(currentCustomer.getRoomNumber(), "식당");

            // 예약된 룸 서비스 및 식당 금액 계산
            int reservedRoomServiceCharge = calculateReservedServiceCharges(currentCustomer.getRoomNumber(), "룸서비스");
            int reservedDiningCharge = calculateReservedServiceCharges(currentCustomer.getRoomNumber(), "식당");

            // 총 금액 계산
            totalAmount = currentCustomer.getPaymentAmount() + extraFee
                    + roomServiceCharge + diningCharge
                    + reservedRoomServiceCharge + reservedDiningCharge;

            // 객실 정보를 포맷팅하여 텍스트 영역에 표시
            String roomInfo = String.format(
                    "객실: %s\n기본 요금: %d원\n체크아웃 시간 초과 요금: %d원\n체크아웃 날짜 초과 요금: %d원\n"
                    + "룸 서비스 금액: %d원\n식당 금액: %d원\n예약한 룸 서비스 금액: %d원\n예약한 식당 금액: %d원\n총 금액: %d원",
                    currentCustomer.getRoomNumber(),    // 고객이 예약한 객실 번호
                    currentCustomer.getPaymentAmount(), // 고객의 기본 결제 금액 (객실 요금)
                    lateCheckOutFee,        // 체크아웃 시간이 11:00 AM 이후일 경우 부과되는 초과 요금 (20,000원)
                    overdueDays * 50000,    // 체크아웃 날짜를 초과한 일수에 따른 초과 요금 (하루당 50,000원)
                    roomServiceCharge,      // 예약 없이 사용한 룸 서비스 총 금액
                    diningCharge,           // 예약 없이 사용한 식당 서비스 총 금액
                    reservedRoomServiceCharge,  // 예약된 룸 서비스 총 금액
                    reservedDiningCharge,       // 예약된 식당 서비스 총 금액
                    totalAmount         // 모든 요금을 합산한 총 결제 금액
            );
            RoomArea.setText(roomInfo); // 텍스트 영역에 객실 정보 표시

        } else {
            // 체크인 정보를 찾지 못한 경우 오류 메시지 표시
            JOptionPane.showMessageDialog(this, "해당 고객의 체크인 정보를 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // 체크인된 고객 명단에서 특정 고객을 검색하는 메서드
    private Customer findCheckInCustomer(String nameOrID) {
        String filePath = "checked_in_customers.txt";   // 체크인된 고객 정보가 저장된 파일 경로

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 파일의 각 줄을 읽으며 고객 정보를 확인
            while ((line = reader.readLine()) != null) {
                System.out.println("읽은 데이터: " + line);

                Map<String, String> customerData = parseCustomerData(line);

                // 필요한 데이터 필드 추출
                String id = customerData.get("고유번호");   // 고객의 고유번호
                String name = customerData.get("이름");    // 고객 이름
                String roomNumber = customerData.get("객실 번호");  // 객실 번호
                String paymentStr = customerData.get("결제 금액");  // 결제 금액

                // 필수 데이터가 누락된 경우 알림 메시지 표시 후 다음 줄로 진행
                if (id == null || name == null || roomNumber == null || paymentStr == null) {
                    JOptionPane.showMessageDialog(this, "필수 데이터가 누락되었습니다: " + line, "오류", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // 결제 금액을 정수로 변환 (숫자만 추출)
                int paymentAmount = Integer.parseInt(paymentStr.replaceAll("[^0-9]", ""));

                // 고객의 고유번호 또는 이름이 입력값과 일치하는 경우
                if (id.equals(nameOrID) || name.equals(nameOrID)) {
                    // 해당 고객 정보를 담은 Customer 객체를 반환
                    return new Customer(name, id, roomNumber, paymentAmount); // 고객 객체 반환
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "파일 읽기 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "결제 금액 변환 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
        // 고객 정보를 찾지 못한 경우 null 반환
        return null;
    }
    
    // 고객의 체크아웃 날짜를 검색하는 메서드
    private String findCheckOutDate(String nameOrID) {
        String filePath = "checked_in_customers.txt";   // 체크인된 고객 정보가 저장된 파일 경로

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
             // 파일의 각 줄을 읽으며 고객 정보를 확인
            while ((line = reader.readLine()) != null) {
                System.out.println("읽은 데이터: " + line); // 디버깅용 출력

                // 현재 줄에 고객의 고유번호 또는 이름이 포함되어 있는지 확인
                if (line.contains("고유번호: " + nameOrID) || line.contains("이름: " + nameOrID)) {
                    System.out.println("일치하는 줄 발견: " + line);

                    // 고객 정보가 포함된 줄을 찾음
                    String[] parts = line.split(", "); // 쉼표와 공백 기준으로 나누기

                    // "체크아웃 날짜:" 필드를 검색
                    for (String part : parts) {
                        if (part.trim().startsWith("체크아웃 날짜:")) {
                            String checkOutDate = part.split(": ")[1].trim();
                            return checkOutDate; // 체크아웃 날짜 반환
                        }
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "파일 읽기 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
        return null; // 체크아웃 날짜를 찾지 못한 경우
    }

    // 고객 데이터를 파싱하여 키-값 형식의 Map으로 변환하는 메서드(예: "이름: 홍길동" -> {"이름": "홍길동"})
    private Map<String, String> parseCustomerData(String line) {
        Map<String, String> dataMap = new HashMap<>();  // 결과를 저장할 Map 생성
        String[] parts = line.split(", ");              // 쉼표와 공백 기준으로 문자열 분리
        
        // 각 부분을 "키: 값" 형태로 파싱
        for (String part : parts) {
            String[] keyValue = part.split(": ");
            if (keyValue.length == 2) {
                dataMap.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }
        return dataMap;
    }//GEN-LAST:event_RoomButtonActionPerformed
    // 체크아웃 버튼 클릭 동작
    private void CheckOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckOutButtonActionPerformed
        // 체크아웃 시간 또는 현재 고객 정보가 없는 경우 경고 메시지를 표시하고 종료
        if (checkOutDateTime == null || currentCustomer == null) {
            JOptionPane.showMessageDialog(this, "먼저 객실 정보를 불러오세요.", "오류", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 결제 유형 및 피드백 입력값 가져오기
        String paymentType = (String) ChooseComboBox.getSelectedItem(); // 선택된 결제 유형
        String feedback = FeedbackArea.getText().trim();                // 피드백 텍스트
        String roomNumber = currentCustomer.getRoomNumber();             // 객실 번호
        String customerNameOrID = idField.getText().trim();             // 입력된 고객 이름 또는 고유 번호

        // 객실 정보 및 요금 세부사항 표시
        String message = String.format(
                "객실: %s\n"
                + "기본 요금: %d원\n"
                + "체크아웃 시간 초과 요금: %d원\n"
                + "체크아웃 날짜 초과 요금: %d원\n"
                + "룸 서비스 금액: %d원\n"
                + "식당 금액: %d원\n"
                + "예약한 룸 서비스 금액: %d원\n"
                + "예약한 식당 금액: %d원\n"
                + "총 금액: %d원\n\n"
                + "결제 방식: %s\n",
                roomNumber,                                       // 고객이 이용한 객실 번호
                currentCustomer.getPaymentAmount(),               // 고객의 기본 객실 요금
                (extraFee > 20000 ? 20000 : 0),                   // 체크아웃 시간이 제한 시간(11:00 AM)을 초과했을 경우 부과되는 시간 초과 요금 (최대 20,000원)
                (extraFee > 20000 ? extraFee - 20000 : extraFee), // 체크아웃 날짜를 초과한 경우 부과되는 날짜 초과 요금
                calculateServiceCharges(roomNumber, "룸서비스"),    // 예약 없이 사용된 룸 서비스의 총 금액
                calculateServiceCharges(roomNumber, "식당"),       // 예약 없이 이용된 식당 서비스의 총 금액
                calculateReservedServiceCharges(roomNumber, "룸서비스"),    // 예약된 룸 서비스의 총 금액
                calculateReservedServiceCharges(roomNumber, "식당"),       // 예약된 식당 서비스의 총 금액
                totalAmount,    // 기본 요금, 추가 요금, 모든 서비스 요금을 합한 총 결제 금액
                paymentType     // 고객이 선택한 결제 방식 (카드 또는 현금)
        );

        // 체크아웃 완료 메시지 표시
        JOptionPane.showMessageDialog(this, message, "체크아웃 완료!", JOptionPane.INFORMATION_MESSAGE);

        // 체크아웃 고객을 파일에 기록
        saveCheckedOutCustomer(currentCustomer);

        // 체크아웃 완료 후 고객 정보를 체크인 명단에서 삭제
        removeCustomerFromCheckInList(currentCustomer);

        // 체크인 명단 새로고침
        loadCheckInList();

        // 고객 피드백 파일에 저장
        saveFeedbackToFile("feedback_list.txt",
                feedback, roomNumber, customerNameOrID);

        // 입력 필드 및 상태 초기화
        resetFields();
    }
    
    // 체크아웃된 고객 정보를 파일에 저장하는 메서드
    private void saveCheckedOutCustomer(Customer customer) {
        // 파일 쓰기: checked_out_customers.txt에 데이터를 추가
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("checked_out_customers.txt", true))) {
            // 고객 정보를 파일에 작성
            writer.write("고객 이름: " + customer.getName()          // 고객 이름
                    + ", 예약 번호: " + customer.getReservationId()  // 고객 예약 번호
                    + ", 객실 번호: " + customer.getRoomNumber());   // 고객 객실 번호
            writer.newLine();   // 줄바꿈 추가
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "체크아웃 정보를 저장하는 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    //고객 피드백을 파일에 저장하는 메서드
    private void saveFeedbackToFile(String filePath, String feedback, String roomNumber, String customerNameOrID) {
        if (feedback.isEmpty()) {
            return; // 피드백이 없으면 저장하지 않음
        }
        try {
            FileWriter writer = new FileWriter(filePath, true); // append 모드로 파일 열기
            writer.write("=== 고객 피드백 ===\n");                  // 피드백 구분 표시
            writer.write("시간: " + LocalDateTime.now() + "\n");   // 피드백 저장 시간
            writer.write("객실 번호: " + roomNumber + "\n");        // 고객의 객실 번호
            writer.write("고객 이름/ID: " + customerNameOrID + "\n");   // 고객의 이름 또는 ID
            writer.write("피드백: " + feedback + "\n");                 // 고객 피드백 내용
            writer.write("\n--------------------\n\n");                // 구분선 추가
            writer.close();     // 파일 닫기
            // 저장 성공 메시지 표시
            JOptionPane.showMessageDialog(this, "피드백이 성공적으로 저장되었습니다!", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "피드백 저장 중 오류가 발생했습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_CheckOutButtonActionPerformed
    // 뒤로가기 버튼 클릭 동작
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // 사용자에게 이전 페이지로 이동 알림
        JOptionPane.showMessageDialog(this, "이전 페이지로 이동합니다.");
        // 메인 프레임으로 이동
        navigateToMainFrame();
    }//GEN-LAST:event_backBtnActionPerformed
    //현재 창을 닫고 사용자 유형에 따라 적절한 메인 프레임으로 이동
    private void navigateToMainFrame() {
        if ("master".equals(userType)) {
            // 사용자 유형이 "master"인 경우 관리자 메인 화면으로 이동
            new MainFrame_Master().setVisible(true);
        } else if ("staff".equals(userType)) {
            // 사용자 유형이 "staff"인 경우 직원 메인 화면으로 이동
            new MainFrame_Staff().setVisible(true);
        }
        // 현재 창 닫기
        this.dispose();
    }
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
                new CheckoutFrame("master").setVisible(true);
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
    private javax.swing.JButton backBtn;
    private javax.swing.JTextArea checkInListArea;
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
