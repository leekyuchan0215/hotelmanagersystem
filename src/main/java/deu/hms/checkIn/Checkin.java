/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deu.hms.checkIn;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Checkin extends JFrame {
    private Map<String, Customer> customerMap; // 예약 고객 정보를 저장할 Map
    private Map<String, Customer> onSiteCustomerMap; // 현장 체크인 고객 정보를 저장할 Map
    private Map<Integer, Integer> roomPriceMap; // 객실 번호와 가격 매핑
    private Customer currentCustomer; // 현재 선택된 고객 정보

// reservations.txt 파일의 예약 데이터를 GUI에 표시
private JTextArea reservationListArea;

public Checkin() {
    setTitle("호텔 체크인 시스템");
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    customerMap = new HashMap<>(); // 예약 고객 정보 초기화
    onSiteCustomerMap = new HashMap<>(); // 현장 체크인 고객 정보 초기화
    
    loadRoomPrices(); // 객실 가격 정보 로드
    loadReservationsFromFile(); // reservations.txt 파일에서 데이터 로드
    System.out.println("로드된 고객 수: " + customerMap.size()); // 로드된 고객 수 출력
    customerMap.forEach((key, customer) -> {
        System.out.println("고유번호: " + key + ", 이름: " + customer.getName());
    });

    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel checkInPanel = createCheckInPanel(); // 체크인 패널 생성
    tabbedPane.addTab("체크인", checkInPanel); // 탭에 추가

    add(tabbedPane); // 메인 프레임에 탭 추가
}

// room_list.txt에서 객실 번호와 가격을 로드
private void loadRoomPrices() {
    roomPriceMap = new HashMap<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("room_list.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 4) {
                int roomNumber = Integer.parseInt(data[1]); // 객실 번호
                int price = Integer.parseInt(data[3]);      // 가격
                roomPriceMap.put(roomNumber, price);        // 매핑 저장
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "room_list.txt 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
    }
}

// 객실 번호로 가격 가져오기
private int getRoomPrice(int roomNumber) {
    return roomPriceMap.getOrDefault(roomNumber, 0); // 기본값 0
}

private void loadReservationsFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("읽은 데이터: " + line);
            String[] data = line.split(",");
            if (data.length >= 8) { // 체크아웃 날짜는 무시, 최소 8개의 데이터만 처리
                String uniqueID = data[0];
                String name = data[1];
                int numPeople = Integer.parseInt(data[2]);
                String phoneNumber = data[3];
                int floor = Integer.parseInt(data[4]);
                int room = Integer.parseInt(data[5]);
                String checkInDate = data[6];
                boolean isCheckedIn = Boolean.parseBoolean(data[7]); // 체크인 여부

                int price = getRoomPrice(room);
                Customer customer = new Customer(name, uniqueID, String.valueOf(room), price, 
                                                 numPeople, phoneNumber, checkInDate);
                customer.setCheckedIn(isCheckedIn);
                customerMap.put(uniqueID, customer);
            } else {
                System.out.println("데이터 형식 오류: " + line);
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "reservations.txt 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
    }
}



private void updateReservationsFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt"))) {
        for (Customer customer : customerMap.values()) {
            writer.write(customer.getReservationId() + "," +
                         customer.getName() + "," +
                         customer.getNumPeople() + "," +
                         customer.getPhoneNumber() + "," +
                         customer.getRoomNumber().charAt(0) + "," + // 층수 추출
                         customer.getRoomNumber() + "," +
                         customer.getCheckInDate() + "," +
                         customer.isCheckedIn());
            writer.newLine();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "reservations.txt 파일을 업데이트하는 중 오류가 발생했습니다: " + e.getMessage());
    }
}



private JPanel createCheckInPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    // 예약 명단 표시 영역
    reservationListArea = new JTextArea(10, 50);
    reservationListArea.setEditable(false); // 사용자가 수정 불가
    JScrollPane scrollPane = new JScrollPane(reservationListArea); // 스크롤 가능
    panel.add(new JLabel("예약자 명단:"));
    panel.add(scrollPane);

    // 예약 명단 로드 버튼
    JButton loadReservationsButton = new JButton("예약자 명단 새로고침");
    panel.add(loadReservationsButton);
    loadReservationsButton.addActionListener(e -> displayReservations());

    // 예약 확인 입력 패널
    JPanel reservationPanel = new JPanel(new FlowLayout());
    reservationPanel.add(new JLabel("이름 또는 고유 번호:"));
    JTextField reservationField = new JTextField(15);
    reservationPanel.add(reservationField);

    // 예약 확인 버튼
    JButton confirmButton = new JButton("예약 확인");
    reservationPanel.add(confirmButton);
    panel.add(reservationPanel);

    // 고객 정보 표시 영역
    JTextArea customerInfoArea = new JTextArea(5, 50);
    customerInfoArea.setEditable(false);
    panel.add(new JScrollPane(customerInfoArea));
    
    // 결제 방식 선택
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
    

    // 체크인 버튼
    JButton checkInButton = new JButton("체크인");
    checkInButton.setEnabled(false);
    panel.add(checkInButton);

    // 예약 확인 버튼 이벤트
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

   // 체크인 버튼 이벤트
    checkInButton.addActionListener(e -> {
        if (currentCustomer != null) {
            String paymentMethod = cardButton.isSelected() ? "카드" : cashButton.isSelected() ? "현금" : "선택 안됨";
            if (paymentMethod.equals("선택 안됨")) {
                JOptionPane.showMessageDialog(this, "결제 방식을 선택해주세요.");
                return;
            }
            
            //체크인 처리
            currentCustomer.checkIn();
            JOptionPane.showMessageDialog(this, "체크인이 완료되었습니다.\n결제 방식: " + paymentMethod);
            
            //파일에 체크인 정보 저장
            saveCheckedInCustomerToFile(currentCustomer, paymentMethod);
            
             // 예약 파일 업데이트
            updateReservationsFile();
            
            // 예약 명단 갱신 (체크인된 고객은 표시되지 않음)
            displayReservations();
        } else {
            JOptionPane.showMessageDialog(this, "먼저 고객을 선택하세요.");
        }
    });
    
    // 현장 체크인 버튼
        JButton onSiteCheckInButton = new JButton("현장 체크인");
        panel.add(onSiteCheckInButton);
        onSiteCheckInButton.addActionListener(e -> showOnSiteCheckInDialog());
    
    // 예약 명단 초기 표시
    displayReservations();

    return panel;
}

// reservations.txt 데이터를 reservationListArea에 표시
private void displayReservations() {
    reservationListArea.setText(""); // 기존 내용 초기화
    customerMap.values().stream()
        .filter(customer -> !customer.isCheckedIn()) // 체크인되지 않은 고객만 필터링
        .forEach(customer -> {
            reservationListArea.append(
                "고유번호: " + customer.getReservationId()
                + ", 이름: " + customer.getName()
                + ", 객실 번호: " + customer.getRoomNumber() + "\n"
            );
        });
}

    private Customer findCustomer(String input) {
        return customerMap.values().stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(input) || customer.getReservationId().equalsIgnoreCase(input))
                .findFirst()
                .orElse(null);
    }

    private void saveCheckedInCustomerToFile(Customer customer,String paymentMethod) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("checked_in_customers.txt", true))) {
            writer.write("고객 이름: " + customer.getName()
                    + ", 예약 번호: " + customer.getReservationId()
                    + ", 객실 번호: " + customer.getRoomNumber()
                    + ", 객실 요금: " + customer.getPaymentAmount() + "원"
                    + ", 결제 방식: " + paymentMethod);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "체크인 정보를 파일에 저장하는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    //현장 체크인 코드 시작

  private void showOnSiteCheckInDialog() {
    JDialog onSiteDialog = new JDialog(this, "현장 체크인", true);
    onSiteDialog.setSize(700, 500);
    onSiteDialog.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // 이름과 전화번호
    JLabel nameLabel = new JLabel("이름:");
    gbc.gridx = 0;
    gbc.gridy = 0;
    onSiteDialog.add(nameLabel, gbc);

    JTextField nameField = new JTextField(20);
    gbc.gridx = 1;
    onSiteDialog.add(nameField, gbc);

    JLabel phoneLabel = new JLabel("전화번호:");
    gbc.gridx = 0;
    gbc.gridy = 1;
    onSiteDialog.add(phoneLabel, gbc);

    JTextField phoneField = new JTextField(20);
    gbc.gridx = 1;
    onSiteDialog.add(phoneField, gbc);

    // 층수, 객실 번호, 객실 유형 및 가격
    JLabel floorLabel = new JLabel("층수:");
    gbc.gridx = 0;
    gbc.gridy = 2;
    onSiteDialog.add(floorLabel, gbc);

    JComboBox<Integer> floorComboBox = new JComboBox<>();
    for (int i = 1; i <= 20; i++) floorComboBox.addItem(i);
    gbc.gridx = 1;
    onSiteDialog.add(floorComboBox, gbc);

    JLabel roomLabel = new JLabel("객실 번호:");
    gbc.gridx = 0;
    gbc.gridy = 3;
    onSiteDialog.add(roomLabel, gbc);

    JComboBox<String> roomComboBox = new JComboBox<>();
    gbc.gridx = 1;
    onSiteDialog.add(roomComboBox, gbc);

    JLabel roomTypeLabel = new JLabel("객실 유형:");
    gbc.gridx = 0;
    gbc.gridy = 4;
    onSiteDialog.add(roomTypeLabel, gbc);

    JTextField roomTypeField = new JTextField();
    roomTypeField.setEditable(false);
    gbc.gridx = 1;
    onSiteDialog.add(roomTypeField, gbc);

    JLabel roomPriceLabel = new JLabel("객실 가격:");
    gbc.gridx = 0;
    gbc.gridy = 5;
    onSiteDialog.add(roomPriceLabel, gbc);

    JTextField roomPriceField = new JTextField();
    roomPriceField.setEditable(false);
    gbc.gridx = 1;
    onSiteDialog.add(roomPriceField, gbc);

    // 결제 방식
    JLabel paymentLabel = new JLabel("결제 방식:");
    gbc.gridx = 0;
    gbc.gridy = 6;
    onSiteDialog.add(paymentLabel, gbc);

    JRadioButton cardButton = new JRadioButton("카드");
    JRadioButton cashButton = new JRadioButton("현금");
    ButtonGroup paymentGroup = new ButtonGroup();
    paymentGroup.add(cardButton);
    paymentGroup.add(cashButton);

    JPanel paymentPanel = new JPanel(new FlowLayout());
    paymentPanel.add(cardButton);
    paymentPanel.add(cashButton);
    gbc.gridx = 1;
    onSiteDialog.add(paymentPanel, gbc);

    // 버튼
    JButton confirmButton = new JButton("확인");
    JButton cancelButton = new JButton("취소");
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(confirmButton);
    buttonPanel.add(cancelButton);
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.gridwidth = 2;
    onSiteDialog.add(buttonPanel, gbc);

    // 이벤트 처리
    floorComboBox.addActionListener(e -> updateRoomComboBoxAndDetails(roomComboBox, roomTypeField, roomPriceField, (int) floorComboBox.getSelectedItem()));
    roomComboBox.addActionListener(e -> {
        String selectedRoom = (String) roomComboBox.getSelectedItem();
        if (selectedRoom != null) {
            int roomNumber = Integer.parseInt(selectedRoom);
            roomTypeField.setText(getRoomType(roomNumber));
            roomPriceField.setText(String.valueOf(getRoomPrice(roomNumber)));
        }
    });

    confirmButton.addActionListener(e -> {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String roomNumber = (String) roomComboBox.getSelectedItem();
        String roomType = roomTypeField.getText();
        int roomPrice = Integer.parseInt(roomPriceField.getText());
        String paymentMethod = cardButton.isSelected() ? "카드" : cashButton.isSelected() ? "현금" : "선택 안됨";

        if (name.isEmpty() || phone.isEmpty() || roomNumber == null || roomType.isEmpty() || paymentMethod.equals("선택 안됨")) {
            JOptionPane.showMessageDialog(onSiteDialog, "모든 정보를 입력해주세요.");
            return;
        }

        processOnSiteCheckIn(name, phone, roomNumber, roomType, roomPrice, paymentMethod);

        onSiteDialog.dispose();
    });

    cancelButton.addActionListener(e -> onSiteDialog.dispose());

    onSiteDialog.setVisible(true);
}


private void processOnSiteCheckIn(String name, String phone, String roomNumber, String roomType, int roomPrice, String paymentMethod) {
    String uniqueID = "OS-" + System.currentTimeMillis();

    Customer customer = new Customer(name, uniqueID, roomNumber, roomPrice, 1, phone, "현장 체크인");
    customer.checkIn();
    onSiteCustomerMap.put(uniqueID, customer);

    saveOnSiteCheckedInCustomerToFile(name, roomNumber, roomType, roomPrice, paymentMethod);

    JOptionPane.showMessageDialog(this, "현장 체크인이 완료되었습니다.\n결제 방식: " + paymentMethod);
}


private void saveOnSiteCheckedInCustomerToFile(String name, String roomNumber, String roomType, int roomPrice, String paymentMethod) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("checked_in_customers.txt", true))) {
        writer.write("고객 이름: " + name
                + ", 객실 번호: " + roomNumber
                + ", 객실 유형: " + roomType
                + ", 객실 요금: " + roomPrice + "원"
                + ", 결제 방식: " + paymentMethod);
        writer.newLine();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "체크인 정보를 파일에 저장하는 중 오류가 발생했습니다: " + e.getMessage());
    }
}

private void updateRoomComboBoxAndDetails(JComboBox<String> roomComboBox, JTextField roomTypeField, JTextField roomPriceField, int floor) {
    roomComboBox.removeAllItems();
    roomTypeField.setText("");
    roomPriceField.setText("");

    roomPriceMap.keySet().stream()
            .filter(roomNumber -> roomNumber / 100 == floor) // 층수로 필터링
            .forEach(roomNumber -> roomComboBox.addItem(String.valueOf(roomNumber)));
}

private String getRoomType(int roomNumber) {
    try (BufferedReader reader = new BufferedReader(new FileReader("room_list.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (Integer.parseInt(data[1]) == roomNumber) {
                return data[2]; // 객실 유형 반환
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "room_list.txt에서 데이터를 읽는 중 오류가 발생했습니다: " + e.getMessage());
    }
    return "알 수 없음";
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
public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Checkin().setVisible(true));
    }
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

