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
    private Map<String, Customer> customerMap; // 고객 정보를 저장할 Map
    private Map<Integer, Integer> roomPriceMap; // 객실 번호별 가격 저장
    private Customer currentCustomer; // 현재 선택된 고객 정보
    private boolean isCardValid = false; // 신용카드 유효성 상태를 나타냄

// reservations.txt 파일의 예약 데이터를 GUI에 표시
private JTextArea reservationListArea;

public Checkin() {
    setTitle("호텔 체크인 시스템");
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    customerMap = new HashMap<>(); // 고객 정보 초기화
    loadRoomPrices(); // 객실 가격 정보 로드 (중요)
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

// 객실 가격 정보를 초기화
private void loadRoomPrices() {
    roomPriceMap = new HashMap<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("room_list.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 4) {
                int roomNumber = Integer.parseInt(data[1]); // 객실 번호
                int price = Integer.parseInt(data[3]);      // 가격
                roomPriceMap.put(roomNumber, price);        // 객실 번호와 가격 저장
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "room_list.txt 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
    }
}

// 객실 번호로 가격 가져오기
private int calculatePaymentAmount(int roomNumber) {
    return roomPriceMap.getOrDefault(roomNumber, 0); // 기본값 0
}

private void loadReservationsFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 8) {
                String uniqueID = data[0];
                String name = data[1];
                int numPeople = Integer.parseInt(data[2]);
                String phone = data[3];
                int floor = Integer.parseInt(data[4]);
                int room = Integer.parseInt(data[5]);
                String checkInTime = data[6];
                String checkOutTime = data[7];

                int price = calculatePaymentAmount(room); // 객실 번호를 기반으로 가격 계산
                Customer customer = new Customer(name, uniqueID, String.valueOf(room), price);
                customerMap.put(uniqueID, customer);
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "reservations.txt 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
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
            isCardValid = false;
        } else if (currentCustomer != null && currentCustomer.isCheckedIn()) {
            customerInfoArea.setText("이미 체크인한 고객입니다.");
        } else {
            customerInfoArea.setText("해당 고객을 찾을 수 없습니다.");
        }
    });

    // 체크인 버튼 이벤트
    checkInButton.addActionListener(e -> {
        if (currentCustomer != null && isCardValid) {
            currentCustomer.checkIn();
            JOptionPane.showMessageDialog(this, "체크인이 완료되었습니다.");
            customerInfoArea.setText("체크인이 완료된 고객: " + currentCustomer.getName());
            saveCheckedInCustomerToFile(currentCustomer);
        } else {
            JOptionPane.showMessageDialog(this, "먼저 신용카드 정보를 확인하세요.");
        }
    });

    // 신용카드 정보 패널
    JPanel creditCardPanel = new JPanel(new FlowLayout());
    creditCardPanel.add(new JLabel("신용카드 정보:"));
    JTextField creditCardField = new JTextField(15);
    creditCardPanel.add(creditCardField);
    JButton validateCardButton = new JButton("신용카드 확인");
    creditCardPanel.add(validateCardButton);
    panel.add(creditCardPanel);

    // 신용카드 확인 버튼 이벤트
    validateCardButton.addActionListener(e -> {
        String cardNumber = creditCardField.getText();
        if (isValidCardNumber(cardNumber)) {
            isCardValid = true;
            JOptionPane.showMessageDialog(this, "유효한 신용카드입니다.");
        } else {
            isCardValid = false;
            JOptionPane.showMessageDialog(this, "유효하지 않은 신용카드 번호입니다.");
        }
    });

    // 예약 명단 초기 표시
    displayReservations();

    return panel;
}

// reservations.txt 데이터를 reservationListArea에 표시
private void displayReservations() {
    reservationListArea.setText(""); // 기존 내용 지우기
    try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            reservationListArea.append(line + "\n");
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "예약자 명단을 불러오는 중 오류가 발생했습니다: " + e.getMessage());
    }
}


    private Customer findCustomer(String input) {
        return customerMap.values().stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(input) || customer.getReservationId().equalsIgnoreCase(input))
                .findFirst()
                .orElse(null);
    }

  private void saveCheckedInCustomerToFile(Customer customer) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("checked_in_customers.txt", true))) {
        writer.write("고객 이름: " + customer.getName()
                + ", 예약 번호: " + customer.getReservationId()
                + ", 객실 번호: " + customer.getRoomNumber()
                + ", 객실 요금: " + customer.getPaymentAmount() + "원");
        writer.newLine();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "체크인 정보를 파일에 저장하는 중 오류가 발생했습니다: " + e.getMessage());
    }
}


    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{12,19}");
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

