package deu.hms.reservation;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ReservationGUI extends javax.swing.JFrame {
    private static Map<String, Reservation> reservations = new HashMap<>();
    
    public ReservationGUI() {
        initComponents();
        loadReservationsFromFile();
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(uniqueID)
                            .addGap(18, 18, 18)
                            .addComponent(uniqueIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(numPeople)
                                .addComponent(Name))
                            .addGap(42, 42, 42)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(numPeopleField, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(NameField)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(phoneNum)
                                .addComponent(floor)
                                .addComponent(room))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(phoneNumField)
                                .addComponent(floorCom, 0, 270, Short.MAX_VALUE)
                                .addComponent(roomCom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(reserveRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateReservation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkReservationInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numPeople)
                    .addComponent(numPeopleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(updateReservation)
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
    private javax.swing.JButton checkReservationInfo;
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
 
    private void floorComActionPerformed(java.awt.event.ActionEvent evt) {
    updateRoomOptions();
    }
    private void updateRoomOptions() {
        roomCom.removeAllItems();
        int selectedFloor = floorCom.getSelectedIndex() + 1; // 1층, 2층, 3층
        for (int i = 1; i <= 3; i++) {
            roomCom.addItem(selectedFloor + "0" + i + "호");
        }
    }
    private void reserveRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        String name = NameField.getText();
        int numPeople = Integer.parseInt(numPeopleField.getText());
        String phoneNum = phoneNumField.getText();
        int floor = Integer.parseInt(floorCom.getSelectedItem().toString().substring(0, 1));
        int room = Integer.parseInt(roomCom.getSelectedItem().toString().replaceAll("[^0-9]", ""));
        
        if (name.isEmpty() || phoneNum.isEmpty()) {
            JOptionPane.showMessageDialog(this, "모든 정보를 입력해 주세요.");
            return;
        }
        
        // 고유 번호 생성 (예시로 UUID 사용)
         String uniqueID = String.format("%03d", new Random().nextInt(1000));

        // 예약 객체 생성 및 예약 정보 추가
        Reservation reservation = new Reservation(name, numPeople, phoneNum, floor, room);
        reservations.put(uniqueID, reservation);
        
        // 예약 정보를 파일에 저장
        saveReservationsToFile();
        
        displayInfo.append("예약 완료:\n" + 
            "고유번호: " + uniqueID + "\n" +  // 고유번호
            "이름: " + name + "\n" +  // 이름
            "인원: " + numPeople + "\n" +  // 인원수
            "전화번호: " + phoneNum + "\n" +  // 전화번호
            "층수: " + floor + "\n" +  // 층수
            "호수: " + room + "\n\n");  // 호수

        
        JOptionPane.showMessageDialog(this, "예약이 완료되었습니다.");
    }

    // 예약 수정 기능
    private void updateReservationActionPerformed(java.awt.event.ActionEvent evt) {
    String uniqueID = uniqueIDField.getText();
    
    // 고유번호가 비었거나, 해당 고유번호 예약이 없으면 경고 메시지
    if (uniqueID.isEmpty() || !reservations.containsKey(uniqueID)) {
        JOptionPane.showMessageDialog(this, "올바른 고유번호를 입력해주세요.");
        return;
    }

    // 고유번호에 해당하는 예약 정보 수정
    Reservation reservation = reservations.get(uniqueID);
    
    // 사용자 입력으로 예약 정보 수정
        reservation.setName(NameField.getText());
        reservation.setNumPeople(Integer.parseInt(numPeopleField.getText()));
        reservation.setPhone(phoneNumField.getText());
        reservation.setFloor(Integer.parseInt((String) floorCom.getSelectedItem()));
        reservation.setRoom(Integer.parseInt((String) roomCom.getSelectedItem()));

    // 수정된 예약 정보 화면에 표시
        StringBuilder sb = new StringBuilder();
        sb.append("고유번호: ").append(uniqueID).append("\n")
        .append("이름: ").append(reservation.getName()).append("\n")
        .append("인원: ").append(reservation.getNumPeople()).append("\n")
        .append("전화번호: ").append(reservation.getPhone()).append("\n")
        .append("층수: ").append(reservation.getFloor()).append("\n")
        .append("호수: ").append(reservation.getRoom()).append("\n\n");

    // 텍스트 영역에 수정된 예약 정보 출력
        displayInfo.setText("");  // 기존 내용 지우고
        displayInfo.append(sb.toString());  // 수정된 내용 추가

    // 수정된 예약 정보를 파일에 저장
        saveReservationsToFile();

    // "수정되었습니다" 메시지
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
        .append("호수: ").append(reservation.getRoom()).append("\n");

        displayInfo.setText(sb.toString());
    }



    // 예약 정보를 파일에 저장
    private void saveReservationsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt"))) {
            for (Map.Entry<String, Reservation> entry : reservations.entrySet()) {
                Reservation res = entry.getValue();
                writer.write(entry.getKey() + "," + res.getName() + "," + res.getNumPeople() + "," 
                    + res.getPhone() + "," + res.getFloor() + "," + res.getRoom());
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
                if (data.length == 6) { // 데이터 형식 확인
                    String uniqueID = data[0];
                    String name = data[1];
                    int numPeople = Integer.parseInt(data[2]);
                    String phone = data[3];
                    int floor = Integer.parseInt(data[4]);
                    int room = Integer.parseInt(data[5]);

                    reservations.put(uniqueID, new Reservation(name, numPeople, phone, floor, room));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Reservation 클래스가 필요합니다.
    public static class Reservation {
        private String name;
        private int numPeople;
        private String phone;
        private int floor;
        private int room;

        public Reservation(String name, int numPeople, String phone, int floor, int room) {
            this.name = name;
            this.numPeople = numPeople;
            this.phone = phone;
            this.floor = floor;
            this.room = room;
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