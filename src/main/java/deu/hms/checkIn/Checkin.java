/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deu.hms.checkIn;

import deu.hms.login.MainFrame_Master;
import deu.hms.login.MainFrame_Staff;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//Checkin 클래스 : 호텔 체크인 시스템의 GUI와 로직으로 구성

public class Checkin extends JFrame {
    
     private String userType;  // "manager" 또는 "staff"를 저장하는 변수
    private Map<String, Customer> customerMap; // 예약 고객 정보를 저장할 Map
    private Map<Integer, RoomData> roomDataMap; // 객실 정보를 저장할 Map
    private Customer currentCustomer; // 현재 선택된 고객 정보
    private JTextArea reservationListArea; // 예약 명단 표시 영역

    public Checkin(String userType) {
        this.userType = userType;
        setTitle("호텔 체크인 시스템");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        customerMap = new HashMap<>();
        roomDataMap = new HashMap<>();
        loadRoomData(); // room_list.txt에서 객실 정보 로드
        loadReservationsFromFile(); // reservations.txt에서 예약 정보 로드

        JTabbedPane tabbedPane = new JTabbedPane(); // 탭 UI 생성
        JPanel checkInPanel = createCheckInPanel(); //  체크인 패널 생성
        tabbedPane.addTab("체크인", checkInPanel); // 탭에 체크인 패널 추가

        add(tabbedPane); // 메인 프레임에 탭 추가
    }

        //이전페이지(메인페이지)이동 로직
        //userType에 따라 다른 화면 호출함
    private void navigateToMainFrame() {
        if ("master".equals(userType)) {
            new MainFrame_Master().setVisible(true);
        } else if ("staff".equals(userType)) {
            new MainFrame_Staff().setVisible(true);
        }
        this.dispose();         //현재 창 닫기
    }
    
    // room_list.txt에서 객실정보를 로드하여 roomDataMap에 저장
    private void loadRoomData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("room_list.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) { //객실 데이터는 4개의 필드로 구성
                    int roomNumber = Integer.parseInt(data[1]);
                    String roomType = data[2];
                    int roomPrice = Integer.parseInt(data[3]);
                    roomDataMap.put(roomNumber, new RoomData(roomType, roomPrice));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "room_list.txt 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    //reservations.txt 파일에서 예약 정보를 로드하여 customerMap에 저장
    private void loadReservationsFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 9) { // 최소 9개의 데이터가 있어야 처리 할 수 있음
                String uniqueID = data[0];
                String name = data[1];
                int numPeople = Integer.parseInt(data[2]);
                String phoneNumber = data[3];
                int floor = Integer.parseInt(data[4]);
                int room = Integer.parseInt(data[5]);
                String checkInDate = data[6];
                String checkOutDate = data[7];
                String paymentType = data[8];
                String paymentMethod = data.length > 9 ? data[9] : "카드"; // 기본값 카드
                boolean isCheckedIn = data.length > 10 && Boolean.parseBoolean(data[10]); // 기본값 false(체크인 안한 상태)

                Customer customer = new Customer(name, uniqueID, String.valueOf(room), 
                        0, numPeople, phoneNumber, checkInDate);
                customer.setCheckOutDate(checkOutDate);
                customer.setPaymentType(paymentType);
                customer.setPaymentMethod(paymentMethod);
                customer.setCheckedIn(isCheckedIn);

                customerMap.put(uniqueID, customer);
            } else {
                System.out.println("잘못된 데이터 형식: " + line);
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "reservations.txt 파일 읽기 오류: " + e.getMessage());
    }
}


    // reservations.txt 파일에 예약 정보를 저장
    private void updateReservationsFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt"))) {
            for (Customer customer : customerMap.values()) {
                writer.write(customer.getReservationId() + ","
                        + customer.getName() + ","
                        + customer.getNumPeople() + ","
                        + customer.getPhoneNumber() + ","
                        + customer.getRoomNumber().charAt(0) + ","
                        + customer.getRoomNumber() + ","
                        + customer.getCheckInDate() + ","
                        + customer.getCheckOutDate() + ","
                        + customer.getPaymentType() + ","
                        + customer.getPaymentMethod() + ","
                        + customer.isCheckedIn());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "reservations.txt 파일 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

    //체크인된 고객 정보를 checked_in_customers.txt에 저장
  private void saveCheckedInCustomerToFile(Customer customer) {
    String filePath = "checked_in_customers.txt"; // 저장할 파일 경로
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // append 모드로 파일 열기
        int roomPrice = roomDataMap.get(Integer.parseInt(customer.getRoomNumber())).getRoomPrice(); // 방 가격 가져오기
        writer.write("고유번호: " + customer.getReservationId() + ", " +
                     "이름: " + customer.getName() + ", " +
                     "객실 번호: " + customer.getRoomNumber() + ", " +
                     "체크인 날짜: " + customer.getCheckInDate() + ", " +
                     "체크아웃 날짜: " + customer.getCheckOutDate() + ", " +
                     "결제 유형: " + customer.getPaymentType() + ", " +
                     "결제 방식: " + customer.getPaymentMethod() + ", " +
                     "결제 금액: " + roomPrice + "원");
        writer.newLine(); // 줄바꿈 추가
        System.out.println("체크인된 고객 정보가 파일에 저장되었습니다.");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "checked_in_customers.txt 파일 저장 중 오류 발생: " + e.getMessage());
    }
}
    //요청사항 입력
  private void showRequestInputDialog(Customer customer) {
    JDialog requestDialog = new JDialog(this, "요청사항 입력", true);
    requestDialog.setSize(400, 300);
    requestDialog.setLayout(new BorderLayout());

    JLabel label = new JLabel("고객 요청사항을 입력하세요:");
    JTextArea requestArea = new JTextArea(10, 30);
    JScrollPane scrollPane = new JScrollPane(requestArea);

    JButton saveButton = new JButton("저장");
    JButton cancelButton = new JButton("취소");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(saveButton);
    buttonPanel.add(cancelButton);

    requestDialog.add(label, BorderLayout.NORTH);
    requestDialog.add(scrollPane, BorderLayout.CENTER);
    requestDialog.add(buttonPanel, BorderLayout.SOUTH);

    saveButton.addActionListener(e -> {
        String request = requestArea.getText().trim();
        if (!request.isEmpty()) {
            saveRequestToFile(customer, request);
            JOptionPane.showMessageDialog(this, "요청사항이 저장되었습니다.");
            requestDialog.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "요청사항을 입력하세요.");
        }
    });

    cancelButton.addActionListener(e -> requestDialog.dispose());

    requestDialog.setVisible(true);
}

  
  private void saveRequestToFile(Customer customer, String request) {
    String filePath = "request_list.txt";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        writer.write("고유번호: " + customer.getReservationId() + ", " +
                     "이름: " + customer.getName() + ", " +
                     "객실 번호: " + customer.getRoomNumber() + ", " +
                     "요청사항: " + request);
        writer.newLine();
        System.out.println("요청사항이 파일에 저장되었습니다.");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "request_list.txt 파일 저장 중 오류 발생: " + e.getMessage());
    }
}

    

    // 예약자 명단과 체크인 패널 생성
    private JPanel createCheckInPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //예약명단 표시 영역
        reservationListArea = new JTextArea(10, 50);
        reservationListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reservationListArea);
        panel.add(new JLabel("예약자 명단:"));
        panel.add(scrollPane);

        //예약 명단 새로고침 버튼(명단 업데이트)
        JButton loadReservationsButton = new JButton("예약자 명단 새로고침");
        panel.add(loadReservationsButton);
        loadReservationsButton.addActionListener(e -> displayReservations());

        //예약자 확인 영역
        JPanel reservationPanel = new JPanel(new FlowLayout());
        reservationPanel.add(new JLabel("이름 또는 고유 번호:"));
        JTextField reservationField = new JTextField(15);
        reservationPanel.add(reservationField);

        //예약 확인 버튼
        JButton confirmButton = new JButton("예약 확인");
        reservationPanel.add(confirmButton);
        panel.add(reservationPanel);
        
        //고객 정보 표시 영역
        JTextArea customerInfoArea = new JTextArea(5, 50);
        customerInfoArea.setEditable(false);
        panel.add(new JScrollPane(customerInfoArea));

        //결제 방식 선택
        JPanel paymentPanel = new JPanel(new FlowLayout());
        paymentPanel.add(new JLabel("결제 방식:"));
        JRadioButton cardButton = new JRadioButton("카드");
        JRadioButton cashButton = new JRadioButton("현금");
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cardButton);
        paymentGroup.add(cashButton);
        paymentPanel.add(cardButton);
        paymentPanel.add(cashButton);
        panel.add(paymentPanel);
        
        //체크인 버튼
        JButton checkInButton = new JButton("체크인");
        checkInButton.setEnabled(false);
        panel.add(checkInButton);
        
        //현장 체크인 버튼
        JButton onSiteCheckInButton = new JButton("현장 체크인");
        panel.add(onSiteCheckInButton);
        onSiteCheckInButton.addActionListener(e -> showOnSiteCheckInDialog());
        
        // 뒤로가기 버튼 
        JButton backButton = new JButton("뒤로가기");
        panel.add(backButton);
        backButton.addActionListener(e->{
            JOptionPane.showMessageDialog(this, "이전 페이지로 이동합니다.");
            navigateToMainFrame();
        });
        
        //예약 확인 버튼 눌렀을때 동작
        confirmButton.addActionListener(e -> {
            String reservationInput = reservationField.getText().trim();
            currentCustomer = findCustomer(reservationInput);

            if (currentCustomer != null && !currentCustomer.isCheckedIn()) {
                customerInfoArea.setText("고객 정보:\n"
                        + "이름: " + currentCustomer.getName() + "\n"
                        + "고유 번호: " + currentCustomer.getReservationId() + "\n"
                        + "객실 번호: " + currentCustomer.getRoomNumber());
                checkInButton.setEnabled(true);
            } else if (currentCustomer != null && currentCustomer.isCheckedIn()) {
                customerInfoArea.setText("이미 체크인한 고객입니다.");
            } else {
                customerInfoArea.setText("해당 고객을 찾을 수 없습니다.");
            }
        });

      //체크인 버튼 동작
      checkInButton.addActionListener(e -> {
    if (currentCustomer != null) {
        String paymentType = currentCustomer.getPaymentType();
        
        
          // 카드 선결제 고객 처리
        if (paymentType.equals("카드 선결제")) {
            currentCustomer.setPaymentMethod("카드"); // 결제 방식 자동 설정
        } else if (paymentType.equals("현장 결제")) {
            String paymentMethod = cardButton.isSelected() ? "카드" : cashButton.isSelected() ? "현금" : "선택 안됨";
            if (paymentMethod.equals("선택 안됨")) {
                JOptionPane.showMessageDialog(this, "결제 방식을 선택해주세요.");
                return;
            }
            currentCustomer.setPaymentMethod(paymentMethod);
        }

        currentCustomer.setCheckedIn(true);
        updateReservationsFile(); // reservations.txt 파일 업데이트

        saveCheckedInCustomerToFile(currentCustomer); // checked_in_customers.txt 파일에 저장
        
        // 요청사항 입력 다이얼로그 표시
        showRequestInputDialog(currentCustomer);
        
        JOptionPane.showMessageDialog(this, "체크인이 완료되었습니다.");
        displayReservations(); // 예약 목록 갱신
    } else {
        JOptionPane.showMessageDialog(this, "먼저 고객을 선택하세요.");
    }
});


        displayReservations(); //초기 예약 명단 표시
        return panel;
    }

    // 현장 체크인 다이얼로그
   private void showOnSiteCheckInDialog() {
    setLocationRelativeTo(null);  // 화면 가운데 띄우기
    JDialog onSiteDialog = new JDialog(this, "현장 체크인", true);
    onSiteDialog.setSize(700, 600);
    onSiteDialog.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel nameLabel = new JLabel("이름:");
    JTextField nameField = new JTextField(20);
    JLabel phoneLabel = new JLabel("전화번호:");
    JTextField phoneField = new JTextField(20);
    JLabel floorLabel = new JLabel("층수:");
    JComboBox<Integer> floorComboBox = new JComboBox<>();
    JLabel roomLabel = new JLabel("객실 번호:");
    JComboBox<String> roomComboBox = new JComboBox<>();
    JLabel roomTypeLabel = new JLabel("객실 유형:");
    JTextField roomTypeField = new JTextField(20);
    JLabel roomPriceLabel = new JLabel("객실 가격:");
    JTextField roomPriceField = new JTextField(20);
    JLabel checkInDateLabel = new JLabel("체크인 날짜 (YYYY-MM-DD):");
    JTextField checkInDateField = new JTextField(15);
    JLabel checkOutDateLabel = new JLabel("체크아웃 날짜 (YYYY-MM-DD):");
    JTextField checkOutDateField = new JTextField(15);
    JLabel paymentLabel = new JLabel("결제 방식:");
    JRadioButton cardButton = new JRadioButton("카드");
    JRadioButton cashButton = new JRadioButton("현금");
    ButtonGroup paymentGroup = new ButtonGroup();
    JButton confirmButton = new JButton("확인");
    JButton cancelButton = new JButton("취소");

    roomTypeField.setEditable(false);
    roomPriceField.setEditable(false);
    for (int i = 1; i <= 20; i++) floorComboBox.addItem(i);

    gbc.gridx = 0; gbc.gridy = 0; onSiteDialog.add(nameLabel, gbc);
    gbc.gridx = 1; onSiteDialog.add(nameField, gbc);
    gbc.gridx = 0; gbc.gridy = 1; onSiteDialog.add(phoneLabel, gbc);
    gbc.gridx = 1; onSiteDialog.add(phoneField, gbc);
    gbc.gridx = 0; gbc.gridy = 2; onSiteDialog.add(floorLabel, gbc);
    gbc.gridx = 1; onSiteDialog.add(floorComboBox, gbc);
    gbc.gridx = 0; gbc.gridy = 3; onSiteDialog.add(roomLabel, gbc);
    gbc.gridx = 1; onSiteDialog.add(roomComboBox, gbc);
    gbc.gridx = 0; gbc.gridy = 4; onSiteDialog.add(roomTypeLabel, gbc);
    gbc.gridx = 1; onSiteDialog.add(roomTypeField, gbc);
    gbc.gridx = 0; gbc.gridy = 5; onSiteDialog.add(roomPriceLabel, gbc);
    gbc.gridx = 1; onSiteDialog.add(roomPriceField, gbc);
    gbc.gridx = 0; gbc.gridy = 6; onSiteDialog.add(checkInDateLabel, gbc);
    gbc.gridx = 1; onSiteDialog.add(checkInDateField, gbc);
    gbc.gridx = 0; gbc.gridy = 7; onSiteDialog.add(checkOutDateLabel, gbc);
    gbc.gridx = 1; onSiteDialog.add(checkOutDateField, gbc);
    gbc.gridx = 0; gbc.gridy = 8; onSiteDialog.add(paymentLabel, gbc);
    gbc.gridx = 1; 
    JPanel paymentPanel = new JPanel();
    paymentPanel.add(cardButton);
    paymentPanel.add(cashButton);
    paymentGroup.add(cardButton);
    paymentGroup.add(cashButton);
    onSiteDialog.add(paymentPanel, gbc);
    gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2;
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(confirmButton);
    buttonPanel.add(cancelButton);
    onSiteDialog.add(buttonPanel, gbc);

    floorComboBox.addActionListener(e -> updateRoomComboBox(roomComboBox, roomTypeField, roomPriceField, (int) floorComboBox.getSelectedItem()));

    confirmButton.addActionListener(e -> {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String roomNumber = (String) roomComboBox.getSelectedItem();
        String checkInDate = checkInDateField.getText().trim();
        String checkOutDate = checkOutDateField.getText().trim();
        String paymentMethod = cardButton.isSelected() ? "카드" : cashButton.isSelected() ? "현금" : null;

        if (name.isEmpty() || phone.isEmpty() || roomNumber == null || checkInDate.isEmpty() || checkOutDate.isEmpty() || paymentMethod == null) {
            JOptionPane.showMessageDialog(onSiteDialog, "모든 정보를 입력해주세요.");
            return;
        }

        int roomPrice = Integer.parseInt(roomPriceField.getText());
        int uniqueID = (int) (Math.random() * 900) + 100; // 100 ~ 999 사이의 난수 생성
        Customer customer = new Customer(name, String.valueOf(uniqueID), roomNumber, roomPrice, 1, phone, checkInDate);
        customer.setCheckOutDate(checkOutDate);
        customer.setPaymentType("현장 결제");
        customer.setPaymentMethod(paymentMethod);
        customer.setCheckedIn(true); // 체크인 상태로 설정

        // 고객 정보를 checked_in_customers.txt에 저장
        saveCheckedInCustomerToFile(customer);

        // 고객 정보를 customerMap에 추가 (필요 시 생략 가능)
        customerMap.put(String.valueOf(uniqueID), customer);

        JOptionPane.showMessageDialog(onSiteDialog, "현장 체크인이 완료되었습니다!");
        onSiteDialog.dispose();
    });

    cancelButton.addActionListener(e -> onSiteDialog.dispose());

    onSiteDialog.setVisible(true);
}


    private void updateRoomComboBox(JComboBox<String> roomComboBox, JTextField roomTypeField, JTextField roomPriceField, int floor) {
        roomComboBox.removeAllItems();
        roomTypeField.setText("");
        roomPriceField.setText("");

        roomDataMap.forEach((roomNumber, roomData) -> {
            if (roomNumber / 100 == floor) {
                roomComboBox.addItem(String.valueOf(roomNumber));
            }
        });

        roomComboBox.addActionListener(e -> {
            String selectedRoom = (String) roomComboBox.getSelectedItem();
            if (selectedRoom != null) {
                RoomData roomData = roomDataMap.get(Integer.parseInt(selectedRoom));
                roomTypeField.setText(roomData.getRoomType());
                roomPriceField.setText(String.valueOf(roomData.getRoomPrice()));
            }
        });
    }
    
    //예약정보 표시
    private void displayReservations() {
        reservationListArea.setText("");
        if (customerMap.isEmpty()) {
            reservationListArea.append("예약된 고객이 없습니다.\n");
            return;
        }

        customerMap.values().stream()
                .filter(customer -> !customer.isCheckedIn())
                .forEach(customer -> reservationListArea.append(
                        "고유번호: " + customer.getReservationId() +
                                ", 이름: " + customer.getName() +
                                ", 객실 번호: " + customer.getRoomNumber() +
                                ", 체크인 날짜: " + customer.getCheckInDate() +
                                ", 체크아웃 날짜: " + customer.getCheckOutDate() +
                                ", 결제 유형: " + customer.getPaymentType() + "\n"));
    }
    
    //고객 정보 검색
    private Customer findCustomer(String input) {
        return customerMap.values().stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(input) || customer.getReservationId().equalsIgnoreCase(input))
                .findFirst()
                .orElse(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
     //RoomData 클래스: 객실 데이터 구조체
    static class RoomData {
        private final String roomType; //객실유형
        private final int roomPrice;    //객실 가격

        public RoomData(String roomType, int roomPrice) {
            this.roomType = roomType;
            this.roomPrice = roomPrice;
        }

        public String getRoomType() {
            return roomType;
        }

        public int getRoomPrice() {
            return roomPrice;
        }
    }
}
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

