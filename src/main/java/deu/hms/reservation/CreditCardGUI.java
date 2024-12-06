
package deu.hms.reservation;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreditCardGUI extends javax.swing.JFrame {

    private static int cardInfoValid = 0; // 카드 정보 유효 여부를 나타내는 변수

    public static int isCardInfoValid() {
        return cardInfoValid;
    }

    public static void setCardInfoValid(int value) {
        cardInfoValid = value;
    }

    public static void resetCardInfoValid() {
        cardInfoValid = 0;
    }
    public CreditCardGUI() {
        setLocationRelativeTo(null);  // 화면 가운데 띄우기
        initComponents();
        setUpInputValidation();
    }

    private void setUpInputValidation() {
        // 카드번호 필드 4자리 제한
        JTextField[] cardFields = {cardField1, cardField2, cardField3, cardField4};
        for (JTextField field : cardFields) {
            field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()) || field.getText().length() >= 4) {
                        e.consume(); // 숫자가 아니거나 4자리를 초과하면 입력 방지
                    }
                }
            });
        }

        // 보안번호(CVV) 필드 3자리 제한
        cvvField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || cvvField.getText().length() >= 3) {
                    e.consume();
                }
            }
        });

        // 비밀번호 필드 2자리 제한 및 가림 처리
        passwordField.setEchoChar('*'); // 입력 시 가려짐
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || passwordField.getText().length() >= 2) {
                    e.consume();
                }
            }
        });

        // 유효기간 월(MM) 필드 2자리 제한
        mmField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || mmField.getText().length() >= 2) {
                    e.consume();
                }
            }
        });

        // 유효기간 연도(YY) 필드 2자리 제한
        yyField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || yyField.getText().length() >= 2) {
                    e.consume();
                }
            }
        });
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card = new javax.swing.JLabel();
        cardField1 = new javax.swing.JTextField();
        expiaryDate = new javax.swing.JLabel();
        cvv = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        goBack = new javax.swing.JButton();
        payCom = new javax.swing.JButton();
        cardField2 = new javax.swing.JTextField();
        cardField3 = new javax.swing.JTextField();
        cardField4 = new javax.swing.JTextField();
        mmField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        yyField = new javax.swing.JTextField();
        cvvField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        card.setText("카드번호");

        expiaryDate.setText("유효기간");

        cvv.setText("보안번호");

        password.setText("비밀번호");

        goBack.setText("이전으로");
        goBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackActionPerformed(evt);
            }
        });

        payCom.setText("결제완료");
        goBack.addActionListener(evt -> dispose());
        payCom.addActionListener(evt -> payComActionPerformed(evt));

        jLabel5.setText("MM");

        jLabel6.setText("YY");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(card)
                                .addGap(18, 18, 18)
                                .addComponent(cardField1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardField2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardField3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardField4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(expiaryDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mmField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yyField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(password)
                                .addGap(24, 24, 24)
                                .addComponent(passwordField)
                                .addGap(256, 256, 256)))
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cvv)
                        .addGap(18, 18, 18)
                        .addComponent(cvvField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(goBack)
                .addGap(33, 33, 33)
                .addComponent(payCom)
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(card)
                    .addComponent(cardField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expiaryDate)
                    .addComponent(mmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(yyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cvv)
                    .addComponent(cvvField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goBack)
                    .addComponent(payCom))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_goBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel card;
    private javax.swing.JTextField cardField1;
    private javax.swing.JTextField cardField2;
    private javax.swing.JTextField cardField3;
    private javax.swing.JTextField cardField4;
    private javax.swing.JLabel cvv;
    private javax.swing.JTextField cvvField;
    private javax.swing.JLabel expiaryDate;
    private javax.swing.JButton goBack;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField mmField;
    private javax.swing.JLabel password;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton payCom;
    private javax.swing.JTextField yyField;
    // End of variables declaration//GEN-END:variables

    private void payComActionPerformed(java.awt.event.ActionEvent evt) {
        if (isInputValid()) {
            setCardInfoValid(1);
            JOptionPane.showMessageDialog(this, "결제가 완료되었습니다.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "모든 값을 정확히 입력하세요.");
        }
    }

    private boolean isInputValid() {
        return cardField1.getText().length() == 4 &&
               cardField2.getText().length() == 4 &&
               cardField3.getText().length() == 4 &&
               cardField4.getText().length() == 4 &&
               cvvField.getText().length() == 3 &&
               passwordField.getText().length() == 2 &&
               mmField.getText().length() == 2 &&
               yyField.getText().length() == 2;
    }


}