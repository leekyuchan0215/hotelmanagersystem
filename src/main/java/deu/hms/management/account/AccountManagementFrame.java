package deu.hms.management.account;

import deu.hms.management.AccountManagementService;
import deu.hms.management.ManagementFrame;
import deu.hms.management.RoomManagementService;
import deu.hms.management.ServiceManagementService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AccountManagementFrame extends javax.swing.JFrame {

    private final AccountService accountService; // 계정 관련 파일 입출력을 처리하는 AccountService 객체
    private final AccountDialogManager dialogManager; // 계정 수정 및 등록 다이얼로그를 관리하는 AccountDialogManager 객체

    /**
     * AccountManagementFrame 클래스의 생성자입니다. GUI 컴포넌트를 초기화하고, AccountService와
     * AccountDialogManager를 초기화합니다.
     */
    public AccountManagementFrame() {
        
        initComponents();
        accountService = new AccountService("id_pw.txt"); // 파일 경로를 이용해 AccountService를 초기화합니다.
        dialogManager = new AccountDialogManager(editDialog, registrationDialog, accountTable, editTable); // 다이얼로그 관리 객체를 초기화합니다.
        loadTableData(); // JTable 초기화 시 데이터 로드
        setLocationRelativeTo(null);  // 화면 가운데 띄우기
    }

    /**
     * 테이블 데이터를 파일에서 읽어와 JTable에 로드하는 메서드입니다.
     */ 
    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        accountService.readFileAndPopulateTable(model); // 파일 데이터를 읽어 테이블에 추가합니다.
    }

    /**
     * JTable의 데이터를 파일에 저장하는 메서드입니다.
     */
    private void saveTableData() {
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        accountService.saveTableDataToFile(model); // 테이블 데이터를 파일에 저장합니다.
        JOptionPane.showMessageDialog(this, "데이터가 성공적으로 저장되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 계정 수정을 처리하는 메서드입니다. editTable의 데이터를 가져와 accountTable의 선택된 행에 업데이트합니다.
     */
    
    private void updateAccountData() {
        // DefaultTableModel 객체를 editTable과 accountTable에서 가져옵니다.
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel(); // 수정 데이터가 있는 테이블의 모델
        DefaultTableModel accountModel = (DefaultTableModel) accountTable.getModel(); // 원본 계정 데이터 테이블의 모델

        // editModel에 데이터가 있는지 확인합니다.
        if (editModel.getRowCount() > 0) { // 수정 테이블에 최소 하나의 행이 있는 경우
            // 수정된 데이터를 저장할 배열 생성 (컬럼 수만큼 크기를 설정)
            String[] updatedRowData = new String[editModel.getColumnCount()];

            // editModel의 첫 번째 행 데이터를 배열로 복사
            for (int i = 0; i < updatedRowData.length; i++) {
                Object cellValue = editModel.getValueAt(0, i); // 셀 데이터를 가져옴
                // 셀 데이터가 null인지 확인 후 문자열로 변환
                updatedRowData[i] = cellValue != null ? cellValue.toString() : "";
            }

            // accountTable에서 선택된 행의 인덱스를 가져옵니다.
            int selectedRow = accountTable.getSelectedRow();

            // 선택된 행이 있는지 확인
            if (selectedRow != -1) { // 선택된 행이 존재하는 경우
                // 선택된 행의 데이터를 수정된 데이터로 업데이트
                for (int i = 0; i < updatedRowData.length; i++) {
                    accountModel.setValueAt(updatedRowData[i], selectedRow, i); // 데이터 설정
                }            
          
                // 성공 메시지 표시
                JOptionPane.showMessageDialog(this, "데이터가 성공적으로 수정되었습니다.");
            } else {
                // 선택된 행이 없는 경우 경고 메시지 표시
                JOptionPane.showMessageDialog(this, "수정할 행을 선택하세요.");
            }
        } else {
            // editModel에 데이터가 없는 경우 경고 메시지 표시
            JOptionPane.showMessageDialog(this, "수정할 데이터가 없습니다.");
        }

        // 수정 작업이 완료되었으므로 다이얼로그를 닫습니다.
        editDialog.dispose();
    }

    /**
     * 새로운 계정을 등록하는 메서드입니다. 사용자 입력을 통해 계정을 생성하고, 테이블에 추가합니다.
     */
    private void registerAccount() {
        String number = numberText.getText().trim(); // 고유 번호 입력 필드에서 값을 가져옵니다.
        String id = idText.getText().trim(); // ID 입력 필드에서 값을 가져옵니다.
        String pw = pwText.getText().trim(); // PW 입력 필드에서 값을 가져옵니다.
        String role = rightList.getSelectedValue(); // 권한 리스트에서 선택된 값을 가져옵니다.

        if (number.isEmpty() || id.isEmpty() || pw.isEmpty() || role == null) {
            JOptionPane.showMessageDialog(this, "모든 필드를 채워주세요!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        accountService.registerAccount(number, id, pw, role); // 새로운 계정을 파일에 등록합니다.
        JOptionPane.showMessageDialog(this, "등록이 완료되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
        registrationDialog.dispose(); // 등록이 완료되면 다이얼로그를 닫습니다.
        loadTableData(); // 등록 후 테이블을 새로고침합니다.
    }

    /**
     * 관리 화면으로 돌아가는 메서드입니다. 이전 화면으로 돌아가기 위해 ManagementFrame을 표시합니다.
     */
    private void backToManagementFrame() {
        JOptionPane.showMessageDialog(this, "이전 화면으로 돌아갑니다.");
        AccountManagementService accountService = new AccountManagementService();
        RoomManagementService roomService = new RoomManagementService();
        ServiceManagementService serviceService = new ServiceManagementService();

        ManagementFrame managementFrame = new ManagementFrame(accountService, roomService, serviceService);
        managementFrame.setVisible(true); // 관리 화면을 표시합니다.
        this.dispose(); // 현재 화면을 닫습니다.
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
        jLabel2 = new javax.swing.JLabel();
        editDialog = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        editTable = new javax.swing.JTable();
        backDialogBtn = new javax.swing.JButton();
        editDialogBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();
        registrationBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        storageBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();

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

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel2.setText("계정 등록");

        javax.swing.GroupLayout registrationFrame2Layout = new javax.swing.GroupLayout(registrationFrame2);
        registrationFrame2.setLayout(registrationFrame2Layout);
        registrationFrame2Layout.setHorizontalGroup(
            registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationFrame2Layout.createSequentialGroup()
                .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addGap(11, 11, 11))))
                    .addGroup(registrationFrame2Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        registrationFrame2Layout.setVerticalGroup(
            registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationFrame2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registrationFrame2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(registrationFrame2Layout.createSequentialGroup()
                        .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numberText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idText)
                            .addComponent(pwText))))
                .addGap(46, 46, 46)
                .addGroup(registrationFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrationCancelDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrationDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout registrationDialogLayout = new javax.swing.GroupLayout(registrationDialog.getContentPane());
        registrationDialog.getContentPane().setLayout(registrationDialogLayout);
        registrationDialogLayout.setHorizontalGroup(
            registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registrationFrame2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        registrationDialogLayout.setVerticalGroup(
            registrationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registrationFrame2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel3.setText("계정 수정");

        editTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "고유번호", "ID", "PW", "권한"
            }
        ));
        jScrollPane2.setViewportView(editTable);

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
                .addGap(14, 14, 14)
                .addGroup(editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editDialogLayout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel3))
                    .addGroup(editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(editDialogLayout.createSequentialGroup()
                            .addComponent(backDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        editDialogLayout.setVerticalGroup(
            editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editDialogBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
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
                                .addGap(18, 18, 18)
                                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
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
                    .addComponent(storageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void storageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storageBtnActionPerformed
        // 저장 버튼 눌렀을 때 동작:
        saveTableData();
    }//GEN-LAST:event_storageBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // 뒤로 버튼 눌렀을 때 동작
        backToManagementFrame();
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void registrationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationBtnActionPerformed
        //  등록 버튼 눌렀을 때 동작 : 새로운 계정을 등록할 수 있는 창 띄우기
        dialogManager.showRegistrationDialog();
    }//GEN-LAST:event_registrationBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // 삭제 버튼을 눌렀을 때 동작
        // 삭제 버튼을 눌렀을 때 동작
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "삭제할 행을 선택하세요!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        dialogManager.deleteAccount(selectedRow);
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
        registerAccount();
    }//GEN-LAST:event_registrationDialogBtnActionPerformed

    private void registrationCancelDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationCancelDialogActionPerformed
        registrationDialog.dispose();
    }//GEN-LAST:event_registrationCancelDialogActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "수정할 행을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        dialogManager.showEditDialog(selectedRow);
    }//GEN-LAST:event_editBtnActionPerformed

    private void backDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backDialogBtnActionPerformed
        // TODO add your handling code here:
        editDialog.dispose();
    }//GEN-LAST:event_backDialogBtnActionPerformed

    private void editDialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDialogBtnActionPerformed
        // editTable 및 accountTable의 모델 가져오기
        updateAccountData();
    }//GEN-LAST:event_editDialogBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accountTable;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton backDialogBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JDialog editDialog;
    private javax.swing.JButton editDialogBtn;
    private javax.swing.JTable editTable;
    private javax.swing.JTextField idText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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

}
