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
    private Customer currentCustomer; // 현재 선택된 고객 정보
    private boolean isCardValid = false; // 신용카드 유효성 상태를 나타냄. 카드가 유효하면 true

    public Checkin() {
        setTitle("호텔 체크인 시스템");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        customerMap = new HashMap<>(); // 고객 정보 초기화
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

    // reservations.txt 파일에서 데이터 로드
    private void loadReservationsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("읽은 데이터: " + line); // 디버깅 로그 추가
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

                    Customer customer = new Customer(name, uniqueID, floor + "0" + room, calculatePaymentAmount(floor));
                    customerMap.put(uniqueID, customer); // 고유번호를 키로 사용
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "reservations.txt 파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 체크인 패널 생성 및 이벤트 리스너 설정
    private JPanel createCheckInPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // 예약 확인 입력 패널(reservationPanel)
        JPanel reservationPanel = new JPanel(new FlowLayout());
        reservationPanel.add(new JLabel("이름 또는 고유 번호:"));
        JTextField reservationField = new JTextField(15);
        reservationPanel.add(reservationField);

        // 예약 확인 버튼(confirmButton)
        JButton confirmButton = new JButton("예약 확인");
        reservationPanel.add(confirmButton);
        panel.add(reservationPanel);

        // 고객 정보 표시 영역
        JTextArea customerInfoArea = new JTextArea(5, 30);
        customerInfoArea.setEditable(false);
        panel.add(new JScrollPane(customerInfoArea));

        // 체크인 버튼 (초기에는 비활성화 상태)
        JButton checkInButton = new JButton("체크인");
        checkInButton.setEnabled(false);
        panel.add(checkInButton);

        // 예약 확인 버튼 클릭 이벤트
        confirmButton.addActionListener(e -> {
            String reservationInput = reservationField.getText();
            currentCustomer = findCustomer(reservationInput);

            if (currentCustomer != null && !currentCustomer.isCheckedIn()) {
                customerInfoArea.setText("고객 정보:\n"
                        + "이름: " + currentCustomer.getName() + "\n"
                        + "고유 번호: " + currentCustomer.getReservationId() + "\n"
                        + "객실 번호: " + currentCustomer.getRoomNumber());
                checkInButton.setEnabled(true); // 체크인 버튼 활성화
                isCardValid = false; // 카드 상태 초기화
            } else if (currentCustomer != null && currentCustomer.isCheckedIn()) {
                customerInfoArea.setText("이미 체크인한 고객입니다.");
            } else {
                customerInfoArea.setText("해당 고객을 찾을 수 없습니다.");
            }
        });

        // 체크인 버튼 클릭 이벤트
        checkInButton.addActionListener(e -> {
            if (currentCustomer != null && isCardValid) {
                currentCustomer.checkIn(); // 체크인 완료
                JOptionPane.showMessageDialog(this, "체크인이 완료되었습니다.");
                customerInfoArea.setText("체크인이 완료된 고객: " + currentCustomer.getName());
                saveCheckedInCustomerToFile(currentCustomer); // 체크인 정보 저장
            } else {
                JOptionPane.showMessageDialog(this, "먼저 신용카드 정보를 확인하세요.");
            }
        });

        // 신용카드 정보 입력 및 확인 패널
        JPanel creditCardPanel = new JPanel(new FlowLayout());
        creditCardPanel.add(new JLabel("신용카드 정보:"));
        JTextField creditCardField = new JTextField(15);
        creditCardPanel.add(creditCardField);
        JButton validateCardButton = new JButton("신용카드 확인");
        creditCardPanel.add(validateCardButton);
        panel.add(creditCardPanel);

        // 신용카드 확인 버튼 클릭 이벤트
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

        return panel;
    }

    // 고객 이름 또는 고유번호로 고객 찾기
    private Customer findCustomer(String input) {
        input = input.trim(); // 입력값 공백 제거
        for (Customer customer : customerMap.values()) {
            if (customer.getName().equalsIgnoreCase(input) || customer.getReservationId().equalsIgnoreCase(input)) {
                return customer;
            }
        }
        return null;
    }

    // 체크인 완료된 고객 정보를 파일에 저장
    private void saveCheckedInCustomerToFile(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("checked_in_customers.txt", true))) {
            writer.write("고객 이름: " + customer.getName() + ", 예약 번호: " + customer.getReservationId() + ", 객실 번호: " + customer.getRoomNumber());
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "체크인 정보를 파일에 저장하는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 신용카드 번호 유효성 검사
    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{12,19}");
    }

    // 결제 금액 계산 (예: 층수 기반)
    private int calculatePaymentAmount(int floor) {
        return 50000 + (floor - 1) * 10000;
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

