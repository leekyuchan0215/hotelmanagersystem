package deu.hms.login;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame extends javax.swing.JFrame {

    public LoginFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        idLabel = new javax.swing.JLabel();
        idText = new javax.swing.JTextField();
        pwLabel = new javax.swing.JLabel();
        pwText = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        loginLabel.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        loginLabel.setText("직원 전용 로그인");

        idLabel.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        idLabel.setText("ID  ");

        pwLabel.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        pwLabel.setText("PW  ");

        pwText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwTextActionPerformed(evt);
            }
        });

        loginBtn.setText("로그인");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pwText, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idText, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pwLabel)
                    .addComponent(pwText, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(loginLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:
        String id = idText.getText();     // 텍스트 필드에 id를 입력받아 변수 id에 저장
        String pw = new String(pwText.getPassword());  // 패스워드 필드에 pw를 입력받아 변수 pw에 저장

        try {
            if (validateLogin(id, pw)) {     // id,pw 비교하는 메서드 호출
                // 로그인 성공
            } else {
                //로그인 실패
                JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 다시 확인해주세요.");
            }
        } catch (IOException e) {
            // 파일 입출력 과정에서 오류 발생
            JOptionPane.showMessageDialog(this, "파일을 읽는 중 오류가 발생했습니다.");
        }
    }//GEN-LAST:event_loginBtnActionPerformed

    private void pwTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwTextActionPerformed

    }//GEN-LAST:event_pwTextActionPerformed

    private boolean validateLogin(String id, String pw) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("id_pw.txt")) // "id_pw.txt"를 읽어 reader 변수에 저장
        ) {
            String line;
            
            while ((line = reader.readLine()) != null) {    // line변수에 reader를 한줄씩 저장하고 null이 아니라면 while문 수행
                String[] parts = line.split(",");    // 한 행에 있는 데이터들은 "," 로 나눔
                if (parts.length == 4) {      // 배열의 길이 4 ( 고유번호, ID, PW, 권한)
                    String storedId = parts[1];  // 배열 두번째 인덱스에 있는 아이디를 storedId 변수에 저장
                    String storedPw = parts[2]; // 배열 세 번째 인덱스에 있는 비밀번호를 storedPw 변수에 저장
                    String role = parts[3];  // 배열 네 번째 인덱스에 있는 권한(M or S)을 role 변수에 저장
                    
                    if (storedId.equals(id) && storedPw.equals(pw)) {   // 입력한 id,pw와 텍스트 파일에 있던 storedId, storedPw가 일치한다면
                        
                        JOptionPane.showMessageDialog(this, "ID : " + id + " 확인되었습니다.");
                        if (role.equals("M")) {
                            // 권한이 "M" ,즉 관리자라면
                            JOptionPane.showMessageDialog(this, "관리자 페이지로 이동합니다.");
                            this.dispose(); // 로그인 창 닫기
                            MainFrame_Master masterFrame = new MainFrame_Master();
                            masterFrame.setVisible(true); //관리자 전용 페이지 띄우기
                            
                        } else if (role.equals("S")) {
                            // 권한이 "S" ,즉 일반 직원이라면
                            JOptionPane.showMessageDialog(this, "직원 페이지로 이동합니다.");
                            this.dispose(); // 로그인 창 닫기
                            MainFrame_Staff staffFrame = new MainFrame_Staff();
                            staffFrame.setVisible(true); // 일반 직원 전용 페이지 띄우기
                        }
                        reader.close();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idText;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel pwLabel;
    private javax.swing.JPasswordField pwText;
    // End of variables declaration//GEN-END:variables
}
