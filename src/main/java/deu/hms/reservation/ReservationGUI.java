package deu.hms.reservation;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComboBox;
import java.time.LocalDate;


public class ReservationGUI extends javax.swing.JFrame {
    private static Map<String, Reservation> reservations = new HashMap<>();
    
    public ReservationGUI() {
        initComponents();
        loadReservationsFromFile();
        initializeFloorOptions();
        initializeDatePickers();
    }
    private void initializeDatePickers() {
    // 현재 연도, 월, 일로 콤보박스 초기화
        LocalDate now = LocalDate.now();
    
    // 연도 설정 (현재 연도부터 5년 후까지)
        checkInY.addItem(String.valueOf(now.getYear()));
        checkOutY.addItem(String.valueOf(now.getYear()));
        for (int i = 1; i <= 3; i++) {
            checkInY.addItem(String.valueOf(now.getYear() + i));
            checkOutY.addItem(String.valueOf(now.getYear() + i));
        }
    
    // 월 설정 (1월부터 12월까지)
        for (int i = 1; i <= 12; i++) {
            String month = String.format("%02d", i); // 01, 02, ..., 12 형식으로
            checkInM.addItem(month);
            checkOutM.addItem(month);
        }

    // 일 설정 (각 월에 맞게 일수 계산)
        updateDayOptions(now.getYear(), now.getMonthValue());
    
    // 체크인 날짜와 체크아웃 날짜에 이벤트 리스너 추가
        checkInY.addActionListener(evt -> updateDayOptions(Integer.parseInt(checkInY.getSelectedItem().toString()), Integer.parseInt(checkInM.getSelectedItem().toString())));
        checkInM.addActionListener(evt -> updateDayOptions(Integer.parseInt(checkInY.getSelectedItem().toString()), Integer.parseInt(checkInM.getSelectedItem().toString())));
        checkOutY.addActionListener(evt -> updateDayOptions(Integer.parseInt(checkOutY.getSelectedItem().toString()), Integer.parseInt(checkOutM.getSelectedItem().toString())));
        checkOutM.addActionListener(evt -> updateDayOptions(Integer.parseInt(checkOutY.getSelectedItem().toString()), Integer.parseInt(checkOutM.getSelectedItem().toString())));
    }



    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        paymentGroup = new javax.swing.ButtonGroup();
        Name = new javax.swing.JLabel();
        numPeople = new javax.swing.JLabel();
        phoneNum = new javax.swing.JLabel();
        floor = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        room = new javax.swing.JLabel();
        numPeopleField = new javax.swing.JTextField();
        phoneNumField = new javax.swing.JTextField();
        floorCom = new javax.swing.JComboBox<>();
        roomCom = new javax.swing.JComboBox<>();
        reserveRegister = new javax.swing.JButton();
        uniqueID = new javax.swing.JLabel();
        uniqueIDField = new javax.swing.JTextField();
        updateReservation = new javax.swing.JButton();
        checkReservationInfo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayInfo = new javax.swing.JTextArea();
        deleteReservation = new javax.swing.JButton();
        checkInTime = new javax.swing.JLabel();
        checkOutTime = new javax.swing.JLabel();
        checkInY = new javax.swing.JComboBox<>();
        checkInM = new javax.swing.JComboBox<>();
        checkInD = new javax.swing.JComboBox<>();
        checkOutY = new javax.swing.JComboBox<>();
        checkOutM = new javax.swing.JComboBox<>();
        checkOutD = new javax.swing.JComboBox<>();
        cardRadioButton = new javax.swing.JRadioButton();
        payRadioButton = new javax.swing.JRadioButton();
        CreditCardButton = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Name.setText("이름");

        numPeople.setText("인원");

        phoneNum.setText("전화번호");

        floor.setText("층수");

        reserveRegister.addActionListener(evt -> reserveRegisterActionPerformed(evt));
        updateReservation.addActionListener(evt -> updateReservationActionPerformed(evt));
        checkReservationInfo.addActionListener(evt -> checkReservationInfoActionPerformed(evt));

        room.setText("호수");

        floorCom.addActionListener(evt -> floorComActionPerformed(evt));
        updateRoomOptions();

        roomCom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "101호" }));

        reserveRegister.setText("예약등록");

        uniqueID.setText("고유번호");

        updateReservation.setText("예약 수정");

        checkReservationInfo.setText("예약 정보 확인");

        displayInfo.setColumns(20);
        displayInfo.setRows(5);
        jScrollPane1.setViewportView(displayInfo);

        deleteReservation.addActionListener(evt -> deleteReservationActionPerformed(evt));
        deleteReservation.setText("예약취소");

        checkInTime.setText("체크인 날짜");

        checkOutTime.setText("체크아웃 날짜 ");

        paymentGroup.add(payRadioButton);
        paymentGroup.add(cardRadioButton);
        cardRadioButton.setText("카드 선결제");

        payRadioButton.setText("현장결제");

        // 이벤트 리스너 추가 코드
        CreditCardButton.addActionListener(e -> openCreditCardGUI());
        CreditCardButton.setText("카드등록");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(phoneNum)
                                .addGap(18, 18, 18)
                                .addComponent(phoneNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(numPeople)
                                    .addComponent(Name))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(numPeopleField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkOutTime)
                                    .addComponent(checkInTime))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkInY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkOutY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(checkInM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(checkInD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(checkOutM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(checkOutD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(floor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(floorCom, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(room)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roomCom, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(uniqueID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(uniqueIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(payRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CreditCardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reserveRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(deleteReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkReservationInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkInTime)
                    .addComponent(checkInY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkInM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkInD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numPeople)
                    .addComponent(numPeopleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkOutTime)
                    .addComponent(checkOutY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkOutM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkOutD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNum)
                    .addComponent(phoneNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(floor)
                    .addComponent(floorCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(room)
                    .addComponent(roomCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reserveRegister)
                    .addComponent(cardRadioButton)
                    .addComponent(payRadioButton)
                    .addComponent(CreditCardButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uniqueID)
                    .addComponent(uniqueIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateReservation)
                    .addComponent(deleteReservation)
                    .addComponent(checkReservationInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreditCardButton;
    private javax.swing.JLabel Name;
    private javax.swing.JTextField NameField;
    private javax.swing.JRadioButton cardRadioButton;
    private javax.swing.JComboBox<String> checkInD;
    private javax.swing.JComboBox<String> checkInM;
    private javax.swing.JLabel checkInTime;
    private javax.swing.JComboBox<String> checkInY;
    private javax.swing.JComboBox<String> checkOutD;
    private javax.swing.JComboBox<String> checkOutM;
    private javax.swing.JLabel checkOutTime;
    private javax.swing.JComboBox<String> checkOutY;
    private javax.swing.JButton checkReservationInfo;
    private javax.swing.JButton deleteReservation;
    private javax.swing.JTextArea displayInfo;
    private javax.swing.JLabel floor;
    private javax.swing.JComboBox<String> floorCom;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numPeople;
    private javax.swing.JTextField numPeopleField;
    private javax.swing.JRadioButton payRadioButton;
    private javax.swing.ButtonGroup paymentGroup;
    private javax.swing.JLabel phoneNum;
    private javax.swing.JTextField phoneNumField;
    private javax.swing.JButton reserveRegister;
    private javax.swing.JLabel room;
    private javax.swing.JComboBox<String> roomCom;
    private javax.swing.JLabel uniqueID;
    private javax.swing.JTextField uniqueIDField;
    private javax.swing.JButton updateReservation;
    // End of variables declaration//GEN-END:variables

    

    private void openCreditCardGUI() {
    // CreditCardGUI 창 열기
        java.awt.EventQueue.invokeLater(() -> {
            new CreditCardGUI().setVisible(true); // CreditCardGUI 인스턴스 생성 및 표시
        });
    }

    private void updateDayOptions(int year, int month) {
    // 월별 최대 일수 계산
        int maxDays = getDaysInMonth(year, month);
    
        checkInD.removeAllItems();
        checkOutD.removeAllItems();
    
    // 1일부터 최대 일수까지 날짜 추가
        for (int day = 1; day <= maxDays; day++) {
            String dayStr = String.format("%02d", day);
            checkInD.addItem(dayStr);
            checkOutD.addItem(dayStr);
        }
    }
    private int getDaysInMonth(int year, int month) {
    // 1월, 3월, 5월, 7월, 8월, 10월, 12월은 31일
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
    // 4월, 6월, 9월, 11월은 30일
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
    // 2월은 윤년 계산
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        return 30; // 기본값 (적용되지 않음)
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
    private void validateCheckInAndCheckOut() {
        LocalDate checkInDate = getDateFromComboBoxes(checkInY, checkInM, checkInD);
        LocalDate checkOutDate = getDateFromComboBoxes(checkOutY, checkOutM, checkOutD);

    }
    private LocalDate getDateFromComboBoxes(JComboBox<String> yearCombo, JComboBox<String> monthCombo, JComboBox<String> dayCombo) {
    int year = Integer.parseInt(yearCombo.getSelectedItem().toString());
    int month = Integer.parseInt(monthCombo.getSelectedItem().toString());
    int day = Integer.parseInt(dayCombo.getSelectedItem().toString());
    return LocalDate.of(year, month, day);
}

    


    private Map<Integer, String[]> loadRoomData() {
        Map<Integer, String[]> floorData = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("room_list.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
            // 각 줄은 "층,호수,등급,가격" 형식으로 되어 있다고 가정
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int floor = Integer.parseInt(parts[0]); // 층
                    String grade = parts[2];  // 등급
                    String price = parts[3];  // 가격

                // 층별로 등급과 가격을 저장 (호수는 별도로 처리하지 않음)
                    floorData.put(floor, new String[]{grade, price});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return floorData;
    }

    // 층 선택 시 동작하는 메소드
    // 층별 가격을 ComboBox에 추가하는 초기화 메소드
    private void initializeFloorOptions() {
        floorCom.removeAllItems(); // 기존 층 옵션 제거

    // 층별 정보 맵 (층 -> 등급, 가격)
        Map<Integer, String[]> floorData = loadRoomData();

        for (int i = 1; i <= 20; i++) { // 1층부터 20층까지
        // 해당 층에 대한 정보가 있으면 층 정보를 추가
            if (floorData.containsKey(i)) {
                String[] info = floorData.get(i);
                String floorOption = i + "층 - " + info[0] + " - " + info[1] + "원"; // 등급, 가격 포함
                floorCom.addItem(floorOption);
            }
        }
    }

// 층 선택 시 동작하는 메소드
    private void floorComActionPerformed(java.awt.event.ActionEvent evt) {
        updateRoomOptions();
    }

// 객실 옵션을 업데이트하는 메소드
    // 객실 옵션을 업데이트하는 메소드 (예약된 방 숨기기 포함)
    private void updateRoomOptions() {
    // 선택된 층 정보를 가져옴
        int floor = floorCom.getSelectedIndex() + 1; // 선택된 층 번호 (1층부터 시작)

    // 층별 객실 정보 읽기
        Map<Integer, String[]> floorData = loadRoomData();

    // 해당 층의 예약된 방 번호 확인
        Set<Integer> reservedRooms = new HashSet<>();
        for (Reservation reservation : reservations.values()) {
            if (reservation.getFloor() == floor) {
                reservedRooms.add(reservation.getRoom());
            }
        }

    // 기존 객실 옵션을 모두 제거
        roomCom.removeAllItems();

    // 층에 해당하는 호수 (1호부터 5호까지) 추가
        for (int i = 1; i <= 5; i++) {
            String roomNumber = floor + "0" + i; // 예: 101, 102 ...
            int room = Integer.parseInt(roomNumber);
            roomCom.addItem(roomNumber + "호");
        // 예약된 방이 아니면 콤보박스에 추가
            
        }
    }

    private boolean isRoomAvailable(String checkInTime, String checkOutTime, int floor, int room) {
        try (BufferedReader reader = new BufferedReader(new FileReader("reservation.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");  // 쉼표로 데이터 분리

                if (details.length < 9) continue;  // 데이터 형식이 맞지 않으면 무시

                String existingCheckIn = details[6].trim();  // 파일에 저장된 체크인 날짜
                String existingCheckOut = details[7].trim();  // 파일에 저장된 체크아웃 날짜
                int existingFloor = Integer.parseInt(details[4].trim());  // 파일의 층수
                int existingRoom = Integer.parseInt(details[5].trim());  // 파일의 방번호

            // 같은 층과 같은 방인지 확인
                if (existingFloor == floor && existingRoom == room) {
                    LocalDate existingIn = LocalDate.parse(existingCheckIn);  // 기존 예약 체크인
                    LocalDate existingOut = LocalDate.parse(existingCheckOut);  // 기존 예약 체크아웃
                    LocalDate newIn = LocalDate.parse(checkInTime);  // 새로운 예약 체크인
                    LocalDate newOut = LocalDate.parse(checkOutTime);  // 새로운 예약 체크아웃

                // 날짜가 겹치는지 확인 (겹치면 false 반환)
                    if (!(newOut.isBefore(existingIn) || newIn.isAfter(existingOut))) {
                        return true;  // 날짜가 겹치는 경우, 예약 불가능
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return false;  // 예약 가능
    }






    private void reserveRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        LocalDate checkInDate = getDateFromComboBoxes(checkInY, checkInM, checkInD);
        LocalDate checkOutDate = getDateFromComboBoxes(checkOutY, checkOutM, checkOutD);

        if (checkOutDate.isBefore(checkInDate)) {
            JOptionPane.showMessageDialog(this, "체크아웃 날짜는 체크인 날짜보다 빠를 수 없습니다.");
            return; // 예약 진행 중단
        }
        String name = NameField.getText();
        int numPeople = Integer.parseInt(numPeopleField.getText());
        String phoneNum = phoneNumField.getText();
        int floor = Integer.parseInt(floorCom.getSelectedItem().toString().substring(0, 1));
        int room = Integer.parseInt(roomCom.getSelectedItem().toString().replaceAll("[^0-9]", ""));
        Map<Integer, String[]> floorData = loadRoomData();
        String[] roomInfo = floorData.get(floor); 
        // 체크인 날짜 및 체크아웃 날짜
        String checkInYear = checkInY.getSelectedItem().toString();
        String checkInMonth = checkInM.getSelectedItem().toString();
        String checkInDay = checkInD.getSelectedItem().toString();
        String checkOutYear = checkOutY.getSelectedItem().toString();
        String checkOutMonth = checkOutM.getSelectedItem().toString();
        String checkOutDay = checkOutD.getSelectedItem().toString();
        // 라디오 버튼 초기화

    // 체크인 및 체크아웃 시간 결합 (년-월-일 형식으로)
        String checkInTime = checkInYear + "-" + checkInMonth + "-" + checkInDay;
        String checkOutTime = checkOutYear + "-" + checkOutMonth + "-" + checkOutDay;

        
        if (!isRoomAvailable(checkInTime, checkOutTime, floor, room)) {
            JOptionPane.showMessageDialog(this, "해당 날짜에 이미 예약된 방입니다.");
            return; // 중복된 예약이므로 진행 중단
        }
        
        
        if (name.isEmpty() || phoneNum.isEmpty()) {
            JOptionPane.showMessageDialog(this, "모든 정보를 입력해 주세요.");
            return;
        }
        String PaymentType = ""; // 결제 유형 초기화

    // payRadioButton 또는 cardRadioButton이 선택되었는지 확인


// 라디오 버튼 선택 여부에 따라 결제 유형 변경
        if (cardRadioButton.isSelected()) {
            PaymentType = "카드 선결제";

        // CreditCardGUI의 isCardInfoValid 값 확인
            if (CreditCardGUI.isCardInfoValid() == 0) {
                JOptionPane.showMessageDialog(this, "카드 등록을 해주십시오.");
                return; // 예약 진행 중단
            }
        // 결제 완료 후 isCardInfoValid 값을 0으로 초기화
            CreditCardGUI.resetCardInfoValid();
        }

        if (payRadioButton.isSelected()) {
            PaymentType = "현장 결제";
        } else if (!cardRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "결제 유형을 선택해 주세요.");
            return;
        }
        
        // 고유 번호 생성 (예시로 UUID 사용)
         String uniqueID = String.format("%03d", new Random().nextInt(1000));

        // 예약 객체 생성 및 예약 정보 추가
        Reservation reservation = new Reservation(name, numPeople, phoneNum, floor, room, checkInTime, checkOutTime, PaymentType);
        reservations.put(uniqueID, reservation);
        
        // 예약 정보를 파일에 저장
        saveReservationsToFile();
        
        displayInfo.append("예약 완료\n" + 
            "고유번호: " + uniqueID + "\n" +  // 고유번호
            "이름: " + name + "\n" +  // 이름
            "인원: " + numPeople + "\n" +  // 인원수
            "전화번호: " + phoneNum + "\n" +  // 전화번호
            "층수: " + floor + "\n" +  // 층수
            "호수: " + room + "\n"+  // 호수
            "체크인 시간: " + checkInTime + "\n" + // 체크인 시간
            "체크아웃 시간: " + checkOutTime + "\n" +  // 체크아웃 시간
            "결제 유형: " + PaymentType + "\n\n");

        
        JOptionPane.showMessageDialog(this, "예약이 완료되었습니다.");
    }

    // 예약 수정 기능
    private void updateReservationActionPerformed(java.awt.event.ActionEvent evt) {
        String uniqueID = uniqueIDField.getText();
    
        if (uniqueID.isEmpty() || !reservations.containsKey(uniqueID)) {
            JOptionPane.showMessageDialog(this, "올바른 고유번호를 입력해주세요.");
            return;
        }

        Reservation reservation = reservations.get(uniqueID);
        reservation.setName(NameField.getText());
        reservation.setNumPeople(Integer.parseInt(numPeopleField.getText()));
        reservation.setPhone(phoneNumField.getText());
        reservation.setFloor(floorCom.getSelectedIndex() + 1); // 1층, 2층, 3층
        reservation.setRoom(Integer.parseInt(roomCom.getSelectedItem().toString().replaceAll("[^0-9]", ""))); // 숫자 추출
        String checkInYear = checkInY.getSelectedItem().toString();
        String checkInMonth = checkInM.getSelectedItem().toString();
        String checkInDay = checkInD.getSelectedItem().toString();
        String checkInTime = checkInYear + "-" + checkInMonth + "-" + checkInDay;

    // 체크아웃 날짜 결합 (년-월-일)
        String checkOutYear = checkOutY.getSelectedItem().toString();
        String checkOutMonth = checkOutM.getSelectedItem().toString();
        String checkOutDay = checkOutD.getSelectedItem().toString();
        String checkOutTime = checkOutYear + "-" + checkOutMonth + "-" + checkOutDay;

    // 예약 날짜 업데이트
        reservation.setcheckInTime(checkInTime);
        reservation.setcheckOutTime(checkOutTime);

        String PaymentType = "현장 결제"; // 기본값

    // payRadioButton 또는 cardRadioButton이 선택되었는지 확인
        if (cardRadioButton.isSelected()) {
            PaymentType = "카드 선결제";

        // CreditCardGUI의 isCardInfoValid 값 확인
            if (CreditCardGUI.isCardInfoValid() == 0) {
                JOptionPane.showMessageDialog(this, "카드 등록을 해주십시오.");
                return; // 예약 진행 중단
            }

        // 결제 완료 후 isCardInfoValid 값을 0으로 초기화
            CreditCardGUI.resetCardInfoValid();
        } 
    
        if (payRadioButton.isSelected()) {
            PaymentType = "현장 결제";
        } else if (!cardRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "결제 유형을 선택해 주세요.");
            return; // 결제 유형 미선택 시 예약 진행 중단
        }

    // 결제 유형 업데이트
        reservation.setPaymentType(PaymentType);
    
        saveReservationsToFile(); // 수정된 내용 저장

        displayInfo.setText(""); // 기존 텍스트 지우기
        displayInfo.append("수정된 예약 정보:\n" +
                "고유번호: " + uniqueID + "\n" +
                "이름: " + reservation.getName() + "\n" +
                "인원: " + reservation.getNumPeople() + "\n" +
                "전화번호: " + reservation.getPhone() + "\n" +
                "층수: " + reservation.getFloor() + "\n" +
                "호수: " + reservation.getRoom() + "\n" +
                "체크인 시간: " + reservation.getcheckInTime() + "\n" +
                "체크아웃 시간: " + reservation.getcheckOutTime() + "\n" +
                "결제 유형: " + reservation.getPaymentType() + "\n");

        JOptionPane.showMessageDialog(this, "예약이 수정되었습니다.");
    }






    // 예약 정보 확인 기능
    private void checkReservationInfoActionPerformed(java.awt.event.ActionEvent evt) {
    // 고유번호 입력 확인
        String uniqueID = uniqueIDField.getText();
        if (uniqueID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "고유번호를 입력해 주세요.");
            return;
        }

    // 해당 고유번호 예약 정보 확인
        Reservation reservation = reservations.get(uniqueID);
        if (reservation == null) {
            JOptionPane.showMessageDialog(this, "입력한 고유번호에 해당하는 예약이 없습니다.");
            return;
        }

    // 예약 정보 출력
        StringBuilder sb = new StringBuilder();
        sb.append("고유번호: ").append(uniqueID).append("\n")
        .append("이름: ").append(reservation.getName()).append("\n")
        .append("인원: ").append(reservation.getNumPeople()).append("\n")
        .append("전화번호: ").append(reservation.getPhone()).append("\n")
        .append("층수: ").append(reservation.getFloor()).append("\n")
        .append("호수: ").append(reservation.getRoom()).append("\n")
        .append("체크인 시간: ").append(reservation.getcheckInTime()).append("\n")
        .append("체크아웃 시간: ").append(reservation.getcheckOutTime()).append("\n")
        .append("결제유형: ").append(reservation.getPaymentType()).append("\n");

        displayInfo.setText(sb.toString());
    }



    // 예약 정보를 파일에 저장
    private void saveReservationsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt"))) {
            for (Map.Entry<String, Reservation> entry : reservations.entrySet()) {
                Reservation res = entry.getValue();
                writer.write(entry.getKey() + "," + res.getName() + "," + res.getNumPeople() + "," 
                    + res.getPhone() + "," + res.getFloor() + "," + res.getRoom() + "," + res.getcheckInTime() + "," + res.getcheckOutTime()+ "," + res.getPaymentType());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 파일에서 예약 정보를 읽어옴
    private static void loadReservationsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 9) { // 데이터 형식 확인
                    String uniqueID = data[0];
                    String name = data[1];
                    int numPeople = Integer.parseInt(data[2]);
                    String phone = data[3];
                    int floor = Integer.parseInt(data[4]);
                    int room = Integer.parseInt(data[5]);
                    String checkInTime = data[6];
                    String checkOutTime = data[7];
                    String PaymentType = data[8];

                    reservations.put(uniqueID, new Reservation(name, numPeople, phone, floor, room, checkInTime, checkOutTime, PaymentType));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 예약 취소 기능 추가
    private void deleteReservationActionPerformed(java.awt.event.ActionEvent evt) {
    // 고유번호 입력 확인
        String uniqueID = uniqueIDField.getText();
        if (uniqueID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "고유번호를 입력해 주세요.");
            return;
        }

    // 해당 고유번호 예약 정보 삭제
        if (!reservations.containsKey(uniqueID)) {
            JOptionPane.showMessageDialog(this, "입력한 고유번호에 해당하는 예약이 없습니다.");
            return;
        }

    // 예약 삭제
        reservations.remove(uniqueID);

    // 파일에 변경된 예약 정보 저장
        saveReservationsToFile();

    // 화면에 삭제된 예약 정보를 출력
        displayInfo.setText(""); // 기존 텍스트 지우기
        displayInfo.append("예약이 취소되었습니다.\n" +
                "고유번호: " + uniqueID + "\n");

        JOptionPane.showMessageDialog(this, "예약이 취소되었습니다.");
    }
    
    

    // Reservation 클래스가 필요합니다.
    public static class Reservation {
        private String name;
        private int numPeople;
        private String phone;
        private int floor;
        private int room;
        private String checkInTime;    // 체크인 시간
        private String checkOutTime;
        private String PaymentType;

        

        public Reservation(String name, int numPeople, String phone, int floor, int room, String checkInTime, String checkOutTime, String PaymentType) {
            this.name = name;
            this.numPeople = numPeople;
            this.phone = phone;
            this.floor = floor;
            this.room = room;
            this.checkInTime = checkInTime;
            this.checkOutTime = checkOutTime;
            this.PaymentType = PaymentType;
        }
        public String getPaymentType() {
            return PaymentType;
        }

    // 결제 유형 Setter (필요 시 추가)
        public void setPaymentType(String PaymentType) {
            this.PaymentType = PaymentType;
        }
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumPeople() {
            return numPeople;
        }

        public void setNumPeople(int numPeople) {
            this.numPeople = numPeople;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public int getRoom() {
            return room;
        }

        public void setRoom(int room) {
            this.room = room;
        }
        public void setcheckInTime(String checkInTime) {
            this.checkInTime = checkInTime;
        }
        public String getcheckInTime() {
            return checkInTime;
        }
        public void setcheckOutTime(String checkOutTime) {
            this.checkOutTime = checkOutTime;
        }
        public String getcheckOutTime() {
            return checkOutTime;
        }
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new ReservationGUI().setVisible(true));

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservationGUI().setVisible(true);
            }
        });
    }
}