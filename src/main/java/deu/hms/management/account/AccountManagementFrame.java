
package deu.hms.management.account;

import deu.hms.login.MainFrame_Master;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AccountManagementFrame extends javax.swing.JFrame {

    int num;
    String id, pw;

    public AccountManagementFrame() {
        initComponents();
        loadTableData(); // JTable 초기화 시 데이터 로드
    }

    // 테이블의 값들을 채우는 메서드
    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        model.setRowCount(0); // 기존 데이터 초기화

        try (BufferedReader br = new BufferedReader(new FileReader("id_pw.txt"))) {    // "id_pw.txt"파일 가져와 읽기
            String line;
            while ((line = br.readLine()) != null) {  //읽어들인 행이 비어있지 않다면 반복 
                // 데이터 형식: 고유번호, ID, PW, 관리자 권한
                String[] rowData = line.split(",");    // ','로 구분된 데이터
                if (rowData.length == 4) {
                    model.addRow(rowData);     // JTable에 행 추가
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일을 읽는 중 문제가 발생했습니다.");
        }
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registrationDialog = new javax.swing.JDialog();
        registrationFrame2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        numberText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        rightList = new javax.swing.JList<>();
        registrationCancelDialog = new javax.swing.JButton();
        registrationDialogBtn = new javax.swing.JButton();
        pwText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        idText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();
        registrationBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        storageBtn = new javax.swing.JButton();

        registrationFrame2.setBackground(new java.awt.Color(200, 200, 200));

        jLabel6.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jLabel6.setText("고유 번호");

        numberText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberTextActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jLabel7.setText("ID");

        rightList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "M", "S" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(rightList);

        registrationCancelDialog.setText("취소");
        registrationCancelDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationCancelDialogActionPerformed(evt);
            }
        });

        registrationDialogBtn.setText(" 등록");
        registrationDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationDialogBtnActionPerformed(evt);
            }
        });

        pwText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwTextActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jLabel10.setText("PW");

        idText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTextActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jLabel11.setText("권한");

        javax.swing.GroupLayout registrationFrame2Layout = new javax.swing.GroupLayout(registrationFrame2);
        registrationFrame2.setLayout(registrationFrame2Layout);
        registrationFrame2Layout.setHorizontalGroup(
            registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationFrame2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(registrationFrame2Layout.createSequentialGroup()
                        .addComponent(registrationCancelDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registrationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registrationFrame2Layout.createSequentialGroup()
                        .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberText, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(30, 30, 30)
                        .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registrationFrame2Layout.createSequentialGroup()
                                .addComponent(pwText, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registrationFrame2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(154, 154, 154)))
                        .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        registrationFrame2Layout.setVerticalGroup(
            registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationFrame2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(numberText)
                    .addComponent(idText)
                    .addComponent(pwText))
                .addGap(64, 64, 64)
                .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrationCancelDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout registrationDialogLayout = new javax.swing.GroupLayout(registrationDialog.getContentPane());
        registrationDialog.getContentPane().setLayout(registrationDialogLayout);
        registrationDialogLayout.setHorizontalGroup(
            registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registrationFrame2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        registrationDialogLayout.setVerticalGroup(
            registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registrationFrame2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel1.setText("계정 관리 ");

        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "고유번호", "ID", "PW", "관리자 권한"
            }
        ));
        jScrollPane1.setViewportView(accountTable);

        backBtn.setText("뒤로");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        registrationBtn.setText("등록");
        registrationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("삭제");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        storageBtn.setText("저장");
        storageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storageBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(registrationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(storageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(backBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void storageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storageBtnActionPerformed
        // TODO add your handling code here:
        storageBtn.addActionListener((e) -> {
            try {
                //JTable의 데이터를 읽어올 TableModel
                DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
                int rowCount = model.getRowCount();
                int columnCount = model.getColumnCount();

                //파일에 내용 저장
                FileWriter writer = new FileWriter("id_pw.txt", false); //파일 덮어쓰기
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                //JTable 데이터를 파일에 저장
                for (int i = 0; i < rowCount; i++) {
                    StringBuilder rowBuilder = new StringBuilder();
                    for (int j = 0; j < columnCount; j++) {
                        rowBuilder.append(model.getValueAt(i, j).toString());
                        if (j < columnCount - 1) {
                            rowBuilder.append(","); //컬럼 구분자 (,)
                        }
                    }
                    bufferedWriter.write(rowBuilder.toString());
                    bufferedWriter.newLine(); //줄 바꿈
                }
                bufferedWriter.close();
                writer.close();

                JOptionPane.showMessageDialog(this, "변경 사항이 저장되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "파일 저장 중 오류가 발생했습니다: " + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }//GEN-LAST:event_storageBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // 뒤로 버튼 눌렀을 때 동작
        JOptionPane.showMessageDialog(this, "이전 화면으로 돌아갑니다.");
        this.dispose();
        MainFrame_Master mainframe = new MainFrame_Master();
        mainframe.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    private void registrationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationBtnActionPerformed
        //  등록 버튼 눌렀을 때 동작 : 새로운 계정을 등록할 수 있는 창 띄우기
        registrationDialog.setSize(700, 300);  // 다이얼로그 크기 설정
        registrationDialog.setLocationRelativeTo(this);  // 부모 컴포넌트를 기준으로 중앙에 배치
        registrationDialog.setTitle("계정 등록");  // 다이얼로그 제목 설정
        registrationDialog.setModal(false);  // 비모달로 설정 (부모 창과 상호작용 가능)
        registrationDialog.setVisible(true);  // 다이얼로그 표시
        registrationDialog.toFront();  // 다이얼로그를 화면 최상위로 가져오기
    }//GEN-LAST:event_registrationBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        deleteBtn.addActionListener(e -> {
            int selectedRow = accountTable.getSelectedRow();  //선택된 행의 인덱스 가져오기

            /**
             * if(selectedRow == -1) { // 선택된 행이 없으면 -1 반환
             * JOptionPane.showMessageDialog(this,"삭제할 행을선택하세요!","오류",JOptionPane.ERROR_MESSAGE); return; }
             */
            //JTable 의 모델 가져오기
            DefaultTableModel model = (DefaultTableModel) accountTable.getModel();

            //행 삭제
            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "선택된 계정이 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
        });
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void numberTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberTextActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_numberTextActionPerformed

    private void pwTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwTextActionPerformed

    private void idTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_idTextActionPerformed

    private void registrationDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationDialogBtnActionPerformed
        // 새로운 계정 등록할 수 있는 창
        registrationDialogBtn.addActionListener(e -> {
            // 입력값 가져오기
            String number = numberText.getText().trim();
            String id = idText.getText().trim();
            String pw = pwText.getText().trim();
            String role = rightList.getSelectedValue(); // 선택된 값 가져오기
            // 유효성 검사
            if (number.isEmpty() || id.isEmpty() || pw.isEmpty() || role == null) {
                JOptionPane.showMessageDialog(this, "모든 필드를 채워주세요!", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // "id_pw.txt" 파일에 데이터 추가
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("id_pw.txt", true))) {
                writer.write(number + "," + id + "," + pw + "," + role); // 데이터를 "number,id,pw,role" 형식으로 저장
                writer.newLine(); // 줄바꿈 추가
            } catch (IOException ex) {
                Logger.getLogger(AccountManagementFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            // 성공 메시지
            JOptionPane.showMessageDialog(this, "등록이 완료되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
            registrationDialog.dispose();
            // 입력 필드 초기화
            numberText.setText("");
            idText.setText("");
            pwText.setText("");
            rightList.clearSelection();
            // 테이블 데이터 갱신
            loadTableData();
        });

    }//GEN-LAST:event_registrationDialogBtnActionPerformed

    private void registrationCancelDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationCancelDialogActionPerformed
        
        registrationDialog.dispose();
    }//GEN-LAST:event_registrationCancelDialogActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AccountManagementFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accountTable;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField idText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField numberText;
    private javax.swing.JTextField pwText;
    private javax.swing.JButton registrationBtn;
    private javax.swing.JButton registrationCancelDialog;
    private javax.swing.JDialog registrationDialog;
    private javax.swing.JButton registrationDialogBtn;
    private javax.swing.JPanel registrationFrame2;
    private javax.swing.JList<String> rightList;
    private javax.swing.JButton storageBtn;
    // End of variables declaration//GEN-END:variables

    private void initializeOtherFrames() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /*private void loadTableData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}
