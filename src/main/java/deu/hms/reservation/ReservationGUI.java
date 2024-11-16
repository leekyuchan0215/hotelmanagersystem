package deu.hms.reservation;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 예약 시스템 GUI 클래스
public class ReservationGUI extends JFrame {
    private JTextField nameField;
    private JTextField peopleField;
    private JTextField phoneField;
    private JComboBox<String> floorCombo;
    private JComboBox<String> roomCombo;
    private JButton reserveButton;
    private JTextField uniqueIDField;
    private JButton modifyButton;
    private JButton checkButton;
    private JTextArea outputArea;

    public  ReservationGUI() {
        setTitle("호텔 예약 시스템");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 1));

        // 이름 입력
        add(new JLabel("이름 : "));
        nameField = new JTextField();
        add(nameField);

        // 인원 입력
        add(new JLabel("인원 : "));
        peopleField = new JTextField();
        add(peopleField);

        // 전화 번호 입력
        add(new JLabel("전화 번호 : "));
        phoneField = new JTextField();
        add(phoneField);

        // 층수 선택
        add(new JLabel("층수 : "));
        String[] floors = {"1층 (10만원)", "2층 (15만원)", "3층 (20만원)"};
        floorCombo = new JComboBox<>(floors);
        add(floorCombo);

        // 호수 선택
        add(new JLabel("호수 : "));
        roomCombo = new JComboBox<>();
        updateRoomCombo(1);
        add(roomCombo);

        // 층수에 따른 호수 변경
        floorCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int floor = floorCombo.getSelectedIndex() + 1;
                updateRoomCombo(floor);
            }
        });

        // 예약 확인 버튼
        reserveButton = new JButton("예약 확인");
        add(reserveButton);

        // 고유번호 입력
        add(new JLabel("고유번호 입력칸 : "));
        uniqueIDField = new JTextField();
        add(uniqueIDField);

        // 예약 수정 버튼
        modifyButton = new JButton("예약 수정");
        add(modifyButton);

        // 예약 정보 확인 버튼
        checkButton = new JButton("예약정보 확인");
        add(checkButton);

        // 정보 출력 영역
        outputArea = new JTextArea(6, 20);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        // 버튼 이벤트 처리
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveAction();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyAction();
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAction();
            }
        });

        setVisible(true);
    }

    // 호수 업데이트 메소드
    private void updateRoomCombo(int floor) {
        roomCombo.removeAllItems();
        switch(floor) {
            case 1:
                roomCombo.addItem("101호");
                roomCombo.addItem("102호");
                roomCombo.addItem("103호");
                break;
            case 2:
                roomCombo.addItem("201호");
                roomCombo.addItem("202호");
                roomCombo.addItem("203호");
                break;
            case 3:
                roomCombo.addItem("301호");
                roomCombo.addItem("302호");
                roomCombo.addItem("303호");
                break;
        }
    }

    // 예약 확인 액션
    private void reserveAction() {
        String name = nameField.getText();
        String peopleStr = peopleField.getText();
        String phone = phoneField.getText();
        int floor = floorCombo.getSelectedIndex() + 1;
        String roomStr = (String) roomCombo.getSelectedItem();
        int roomNumber = Integer.parseInt(roomStr.substring(0,3));

        // 입력 검증
        if(name.isEmpty() || peopleStr.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "모든 필드를 입력해주세요.");
            return;
        }

        int numberOfPeople;
        try {
            numberOfPeople = Integer.parseInt(peopleStr);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "인원은 숫자여야 합니다.");
            return;
        }

        // 예약 저장
        String uniqueID = Reservation.saveReservation(name, numberOfPeople, phone, floor, roomNumber);
        outputArea.setText("예약 완료!\n고유번호: " + uniqueID);
    }

    // 예약 수정 액션
    private void modifyAction() {
        String uniqueID = uniqueIDField.getText();
        if(uniqueID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "고유번호를 입력해주세요.");
            return;
        }

        String name = nameField.getText();
        String peopleStr = peopleField.getText();
        String phone = phoneField.getText();
        int floor = floorCombo.getSelectedIndex() + 1;
        String roomStr = (String) roomCombo.getSelectedItem();
        int roomNumber = Integer.parseInt(roomStr.substring(0,3));

        // 입력 검증
        if(name.isEmpty() || peopleStr.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "모든 필드를 입력해주세요.");
            return;
        }

        int numberOfPeople;
        try {
            numberOfPeople = Integer.parseInt(peopleStr);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "인원은 숫자여야 합니다.");
            return;
        }

        boolean success = Reservation.modifyReservation(uniqueID, name, numberOfPeople, phone, floor, roomNumber);
        if(success) {
            outputArea.setText("예약 수정 완료!");
        } else {
            JOptionPane.showMessageDialog(this, "해당 고유번호의 예약이 없습니다.");
        }
    }

    // 예약 정보 확인 액션
    private void checkAction() {
        String uniqueID = uniqueIDField.getText();
        if(uniqueID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "고유번호를 입력해주세요.");
            return;
        }

        Reservation res = Reservation.getReservationInfo(uniqueID);
        if(res != null) {
            String info = "이름: " + res.getName() + "\n" +
                          "인원: " + res.getNumberOfPeople() + "\n" +
                          "전화번호: " + res.getPhoneNumber() + "\n" +
                          "층수: " + res.getFloor() + "층\n" +
                          "호수: " + res.getRoomNumber() + "호\n" +
                          "고유번호: " + res.getUniqueID();
            outputArea.setText(info);
        } else {
            JOptionPane.showMessageDialog(this, "해당 고유번호의 예약이 없습니다.");
        }
    }

    @SuppressWarnings("unchecked")
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
            java.util.logging.Logger.getLogger(ReservationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservationGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
