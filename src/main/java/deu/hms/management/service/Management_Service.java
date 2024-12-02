package deu.hms.management.service;

import deu.hms.login.MainFrame_Master;
import deu.hms.management.account.AccountManagementFrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Management_Service extends javax.swing.JFrame {

    public Management_Service() {
        initComponents();
        loadTableData();
    }

    private void loadTableData() {
        // 테이블의 값들을 채우는 메서드
        DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
        model.setRowCount(0); // 기존 데이터 초기화
        readFileAndPopulateTable("menu_list.txt", model); // 파일에서 데이터를 읽어와 테이블을 채웁니다.
    }

    // 파일을 읽어서 JTable 모델에 데이터를 추가하는 메서드입니다.
    private void readFileAndPopulateTable(String filename, DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(",");
                if (rowData.length == 3) {
                    model.addRow(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일을 읽는 중 문제가 발생했습니다.");
        }
    }

    // JTable 데이터를 파일에 저장하는 메서드입니다.
    private void saveTableDataToFile() {
        DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
        saveTableDataToFile("menu_list.txt", model); // 테이블 데이터를 파일에 저장합니다.
    }

    private void saveTableDataToFile(String filename, DefaultTableModel model) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, false))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder rowBuilder = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    rowBuilder.append(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) {
                        rowBuilder.append(",");
                    }
                }
                bufferedWriter.write(rowBuilder.toString());
                bufferedWriter.newLine();
            }
            JOptionPane.showMessageDialog(this, "변경 사항이 저장되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "파일 저장 중 오류가 발생했습니다: " + ex.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void showEditDialog(int selectedRow) {
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel();
        editModel.setRowCount(0);

        String[] rowData = new String[3];
        for (int i = 0; i < 3; i++) {
            rowData[i] = serviceTable.getValueAt(selectedRow, i).toString();
        }
        editModel.addRow(rowData);

        editDialog.setSize(700, 300);
        editDialog.setLocationRelativeTo(this);
        editDialog.setTitle("서비스 수정");
        editDialog.setModal(false);
        editDialog.setVisible(true);
        editDialog.toFront();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registrationDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        serviceList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        foodText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        priceText = new javax.swing.JTextField();
        cancelBtn = new javax.swing.JButton();
        registrationDialogBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        editDialog = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        editTable = new javax.swing.JTable();
        backDialogBtn = new javax.swing.JButton();
        editDialogBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        serviceTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        registrationBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        storageBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(200, 200, 200));
        jPanel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel2.setText("서비스 종류");

        serviceList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "룸 서비스", "식당" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(serviceList);

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel3.setText("음식");

        foodText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodTextActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel4.setText("가격");

        cancelBtn.setText("취소");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        registrationDialogBtn.setText("등록");
        registrationDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationDialogBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel5.setText("서비스 등록");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registrationDialogBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(79, 79, 79))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jScrollPane2)
                                            .addGap(39, 39, 39)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(foodText, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(foodText, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout registrationDialogLayout = new javax.swing.GroupLayout(registrationDialog.getContentPane());
        registrationDialog.getContentPane().setLayout(registrationDialogLayout);
        registrationDialogLayout.setHorizontalGroup(
            registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        registrationDialogLayout.setVerticalGroup(
            registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel6.setText("서비스 수정");

        editTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "종류", "음식", "가격"
            }
        ));
        jScrollPane3.setViewportView(editTable);

        backDialogBtn.setText("취소");
        backDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backDialogBtnActionPerformed(evt);
            }
        });

        editDialogBtn.setText("수정");
        editDialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDialogBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editDialogLayout = new javax.swing.GroupLayout(editDialog.getContentPane());
        editDialog.getContentPane().setLayout(editDialogLayout);
        editDialogLayout.setHorizontalGroup(
            editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDialogLayout.createSequentialGroup()
                .addGroup(editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editDialogLayout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel6))
                    .addGroup(editDialogLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(editDialogLayout.createSequentialGroup()
                                .addComponent(backDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        editDialogLayout.setVerticalGroup(
            editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDialogLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(234, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        serviceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, "", null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "종류", "음식", "가격"
            }
        ));
        jScrollPane1.setViewportView(serviceTable);

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel1.setText("서비스 관리");

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

        editBtn.setText("수정");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registrationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(storageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(93, 93, 93)
                            .addComponent(backBtn))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(122, 122, 122))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationBtnActionPerformed
        // TODO add your handling code here:
        registrationDialog.setSize(600, 400);  // 다이얼로그 크기 설정
        registrationDialog.setLocationRelativeTo(this);  // 부모 컴포넌트를 기준으로 중앙에 배치
        registrationDialog.setTitle("계정 등록");  // 다이얼로그 제목 설정
        registrationDialog.setModal(false);  // 비모달로 설정 (부모 창과 상호작용 가능)
        registrationDialog.setVisible(true);  // 다이얼로그 표시
        registrationDialog.toFront();  // 다이얼로그를 화면 최상위로 가져오기
    }//GEN-LAST:event_registrationBtnActionPerformed

    private void foodTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_foodTextActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        registrationDialog.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void registrationDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationDialogBtnActionPerformed
        // TODO add your handling code here:
        registerService();
    }//GEN-LAST:event_registrationDialogBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "이전 화면으로 돌아갑니다.");
        this.dispose();
        new MainFrame_Master().setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = serviceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "삭제할 행을 선택하세요!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        deleteService(selectedRow);
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void storageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storageBtnActionPerformed
        // TODO add your handling code here:
        saveTableDataToFile();
    }//GEN-LAST:event_storageBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = serviceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "수정할 행을 선택하세요.");
            return;
        }
        showEditDialog(selectedRow);
    }//GEN-LAST:event_editBtnActionPerformed

    private void editDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDialogBtnActionPerformed
        // TODO add your handling code here:
        updateServiceData();
    }//GEN-LAST:event_editDialogBtnActionPerformed

    private void backDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backDialogBtnActionPerformed
        // TODO add your handling code here:
        editDialog.dispose();
    }//GEN-LAST:event_backDialogBtnActionPerformed

    private void registerService() {
        String service = serviceList.getSelectedValue();
        String food = foodText.getText().trim();
        String price = priceText.getText().trim();

        if (food.isEmpty() || price.isEmpty() || service == null) {
            JOptionPane.showMessageDialog(this, "모든 필드를 채워주세요!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu_list.txt", true))) {
            writer.write(service + "," + food + "," + price);
            writer.newLine();
        } catch (IOException ex) {
            Logger.getLogger(AccountManagementFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(this, "등록이 완료되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
        registrationDialog.dispose();
        foodText.setText("");
        priceText.setText("");
        serviceList.clearSelection();
        loadTableData();
    }

    private void deleteService(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
        model.removeRow(selectedRow);
        JOptionPane.showMessageDialog(this, "선택된 서비스가 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateServiceData() {
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel();
        DefaultTableModel serviceModel = (DefaultTableModel) serviceTable.getModel();

        if (editModel.getRowCount() > 0) {
            String[] updatedRowData = new String[editModel.getColumnCount()];
            for (int i = 0; i < updatedRowData.length; i++) {
                Object cellValue = editModel.getValueAt(0, i);
                updatedRowData[i] = cellValue != null ? cellValue.toString() : "";
            }

            int selectedRow = serviceTable.getSelectedRow();
            if (selectedRow != -1) {
                for (int i = 0; i < updatedRowData.length; i++) {
                    serviceModel.setValueAt(updatedRowData[i], selectedRow, i);
                }
                JOptionPane.showMessageDialog(this, "데이터가 성공적으로 수정되었습니다.");
            } else {
                JOptionPane.showMessageDialog(this, "수정할 행을 선택하세요.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "수정할 데이터가 없습니다.");
        }

        editDialog.dispose();
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Management_Service().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton backDialogBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JDialog editDialog;
    private javax.swing.JButton editDialogBtn;
    private javax.swing.JTable editTable;
    private javax.swing.JTextField foodText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField priceText;
    private javax.swing.JButton registrationBtn;
    private javax.swing.JDialog registrationDialog;
    private javax.swing.JButton registrationDialogBtn;
    private javax.swing.JList<String> serviceList;
    private javax.swing.JTable serviceTable;
    private javax.swing.JButton storageBtn;
    // End of variables declaration//GEN-END:variables
}
