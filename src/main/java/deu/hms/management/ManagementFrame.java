package deu.hms.management;

import deu.hms.login.MainFrame_Master;
import deu.hms.management.account.AccountManagementFrame;
import deu.hms.management.room.Management_Room;
import deu.hms.management.service.Management_Service;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class ManagementFrame extends javax.swing.JFrame {

    /**
     * Creates new form ManagementFrame
     */
    public ManagementFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        roomManagementBtn = new javax.swing.JButton();
        serviceManagementBtn = new javax.swing.JButton();
        accountManagementBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("관리 페이지");

        roomManagementBtn.setText("객실 관리");
        roomManagementBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomManagementBtnActionPerformed(evt);
            }
        });

        serviceManagementBtn.setText("서비스 관리");
        serviceManagementBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceManagementBtnActionPerformed(evt);
            }
        });

        accountManagementBtn.setText("계정 관리");
        accountManagementBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountManagementBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(serviceManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(accountManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(roomManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(serviceManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        backBtn.setText("이전");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(backBtn)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(backBtn))
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void accountManagementBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountManagementBtnActionPerformed
        // 계정 관리 버튼을 눌렀을 때 동작
        JOptionPane.showMessageDialog(this, "계정 관리 페이지로 이동합니다.");
        this.dispose();  // 현재 창 닫기
        AccountManagementFrame aframe = new AccountManagementFrame();
        aframe.setVisible(true);
    }//GEN-LAST:event_accountManagementBtnActionPerformed

    private void serviceManagementBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceManagementBtnActionPerformed
        // 서비스 관리 버튼을 눌렀을 때 동작
        JOptionPane.showMessageDialog(this, "서비스 관리 페이지로 이동합니다.");
        this.dispose();  // 현재 창 닫기
        Management_Service sframe = new Management_Service();
        sframe.setVisible(true);
    }//GEN-LAST:event_serviceManagementBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // 이전 버튼을 눌렀을 때 동작:
        this.dispose(); // 로그인 창 닫기
        MainFrame_Master mainframe = new MainFrame_Master();
        mainframe.setVisible(true);  // 메인 화면 띄우기
    }//GEN-LAST:event_backBtnActionPerformed

    private void roomManagementBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomManagementBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "객실 관리 페이지로 이동합니다.");
        this.dispose();  // 현재 창 닫기
        Management_Room rframe = new Management_Room();
        rframe.setVisible(true);
    }//GEN-LAST:event_roomManagementBtnActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagementFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accountManagementBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton roomManagementBtn;
    private javax.swing.JButton serviceManagementBtn;
    // End of variables declaration//GEN-END:variables
}
