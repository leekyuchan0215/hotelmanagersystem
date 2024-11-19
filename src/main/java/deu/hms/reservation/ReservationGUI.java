package deu.hms.reservation;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ReservationGUI extends javax.swing.JFrame {
    private static Map<String, Reservation> reservations = new HashMap<>();
    
    public ReservationGUI() {
        initComponents();
        loadReservationsFromFile();
        initializeFloorOptions();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
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
        checkInTimeField = new javax.swing.JTextField();
        checkOutTimeField = new javax.swing.JTextField();

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
        floorCom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1층 - 10만원", "2층 - 15만원", "3층 - 20만원" }));
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

        checkInTime.setText("체크인 시간");

        checkOutTime.setText("체크아웃 시간 ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(reserveRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkReservationInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(updateReservation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numPeople)
                            .addComponent(Name))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numPeopleField, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(NameField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkInTime)
                            .addComponent(checkOutTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkOutTimeField, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(checkInTimeField)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(uniqueID)
                        .addGap(18, 18, 18)
                        .addComponent(uniqueIDField))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneNum)
                            .addComponent(floor)
                            .addComponent(room))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneNumField)
                            .addComponent(roomCom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(floorCom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkInTime)
                    .addComponent(checkInTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numPeople)
                    .addComponent(numPeopleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkOutTime)
                    .addComponent(checkOutTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNum)
                    .addComponent(phoneNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(floor)
                    .addComponent(floorCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(room)
                    .addComponent(roomCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reserveRegister)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uniqueID)
                    .addComponent(uniqueIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateReservation)
                    .addComponent(deleteReservation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkReservationInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Name;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel checkInTime;
    private javax.swing.JTextField checkInTimeField;
    private javax.swing.JLabel checkOutTime;
    private javax.swing.JTextField checkOutTimeField;
    private javax.swing.JButton checkReservationInfo;
    private javax.swing.JButton deleteReservation;
    private javax.swing.JTextArea displayInfo;
    private javax.swing.JLabel floor;
    private javax.swing.JComboBox<String> floorCom;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numPeople;
    private javax.swing.JTextField numPeopleField;
    private javax.swing.JLabel phoneNum;
    private javax.swing.JTextField phoneNumField;
    private javax.swing.JButton reserveRegister;
    private javax.swing.JLabel room;
    private javax.swing.JComboBox<String> roomCom;
    private javax.swing.JLabel uniqueID;
    private javax.swing.JTextField uniqueIDField;
    private javax.swing.JButton updateReservation;
    // End of variables declaration//GEN-END:variables
 
    // 층 선택 시 동작하는 메소드
    // 층별 가격을 ComboBox에 추가하는 초기화 메소드
    private void initializeFloorOptions() {
        floorCom.removeAllItems(); // 기존 층 옵션 제거

        for (int i = 1; i <= 20; i++) { // 1층부터 20층까지
            int roomPrice = 50000 + (i - 1) * 10000; // 1층: 50,000원, 한 층 올라갈수록 10,000원 추가
            String floorOption = i + "층 - " + roomPrice / 10000 + "만원";
            floorCom.addItem(floorOption);
        }
    }

// 층 선택 시 동작하는 메소드
    private void floorComActionPerformed(java.awt.event.ActionEvent evt) {
        updateRoomOptions();
    }

// 객실 옵션을 업데이트하는 메소드
    private void updateRoomOptions() {
        roomCom.removeAllItems(); // 기존 객실 옵션 제거

        int floor = floorCom.getSelectedIndex() + 1; // 선택된 층 번호 (1층부터 시작)

    // 선택된 층에 해당하는 5개의 객실 추가 (호수 형식: 층번호 + 01 ~ 05)
        for (int i = 1; i <= 5; i++) {
            String roomNumber = floor + "0" + i + "호"; // 예: 101호, 102호, ...
            roomCom.addItem(roomNumber);
        }
    }


    private void reserveRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        String name = NameField.getText();
        int numPeople = Integer.parseInt(numPeopleField.getText());
        String phoneNum = phoneNumField.getText();
        int floor = Integer.parseInt(floorCom.getSelectedItem().toString().substring(0, 1));
        int room = Integer.parseInt(roomCom.getSelectedItem().toString().replaceAll("[^0-9]", ""));
        String checkInTime = checkInTimeField.getText(); // 체크인 시간 입력값
        String checkOutTime = checkOutTimeField.getText();
        
        
        if (name.isEmpty() || phoneNum.isEmpty()) {
            JOptionPane.showMessageDialog(this, "모든 정보를 입력해 주세요.");
            return;
        }
        
        // 고유 번호 생성 (예시로 UUID 사용)
         String uniqueID = String.format("%03d", new Random().nextInt(1000));

        // 예약 객체 생성 및 예약 정보 추가
        Reservation reservation = new Reservation(name, numPeople, phoneNum, floor, room, checkInTime, checkOutTime);
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
            "체크아웃 시간: " + checkOutTime + "\n\n");  // 체크아웃 시간

        
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
        reservation.setcheckInTime(checkInTimeField.getText());
        reservation.setcheckOutTime(checkOutTimeField.getText());
    
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
                "체크아웃 시간: " + reservation.getcheckOutTime() + "\n\n");

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
        .append("체크아웃 시간: ").append(reservation.getcheckOutTime()).append("\n");

        displayInfo.setText(sb.toString());
    }



    // 예약 정보를 파일에 저장
    private void saveReservationsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt"))) {
            for (Map.Entry<String, Reservation> entry : reservations.entrySet()) {
                Reservation res = entry.getValue();
                writer.write(entry.getKey() + "," + res.getName() + "," + res.getNumPeople() + "," 
                    + res.getPhone() + "," + res.getFloor() + "," + res.getRoom() + "," + res.getcheckInTime() + "," + res.getcheckOutTime());
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
                if (data.length == 8) { // 데이터 형식 확인
                    String uniqueID = data[0];
                    String name = data[1];
                    int numPeople = Integer.parseInt(data[2]);
                    String phone = data[3];
                    int floor = Integer.parseInt(data[4]);
                    int room = Integer.parseInt(data[5]);
                    String checkInTime = data[6];
                    String checkOutTime = data[7];

                    reservations.put(uniqueID, new Reservation(name, numPeople, phone, floor, room, checkInTime, checkOutTime));
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

        public Reservation(String name, int numPeople, String phone, int floor, int room, String checkInTime, String checkOutTime) {
            this.name = name;
            this.numPeople = numPeople;
            this.phone = phone;
            this.floor = floor;
            this.room = room;
            this.checkInTime = checkInTime;
            this.checkOutTime = checkOutTime;
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