package deu.hms.management.account;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AccountDialogManager {
    private final JDialog editDialog;
    private final JDialog registrationDialog;
    private final JTable accountTable;
    private final JTable editTable;

    public AccountDialogManager(JDialog editDialog, JDialog registrationDialog, JTable accountTable, JTable editTable) {
        this.editDialog = editDialog;
        this.registrationDialog = registrationDialog;
        this.accountTable = accountTable;
        this.editTable = editTable;
    }

    // 계정 수정을 위한 다이얼로그를 표시하는 메서드
    public void showEditDialog(int selectedRow) {
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel();
        editModel.setRowCount(0);
        String[] rowData = new String[4];
        for (int i = 0; i < 4; i++) {
            rowData[i] = accountTable.getValueAt(selectedRow, i).toString();
        }
        editModel.addRow(rowData);
        editDialog.setSize(700, 300);
        editDialog.setLocationRelativeTo(null);
        editDialog.setVisible(true);
    }

    // 계정을 등록하기 위한 다이얼로그를 표시하는 메서드
    public void showRegistrationDialog() {
        registrationDialog.setSize(700, 300);
        registrationDialog.setLocationRelativeTo(null);
        registrationDialog.setVisible(true);
    }

    // 계정을 삭제하는 메서드
    public void deleteAccount(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        model.removeRow(selectedRow);
        JOptionPane.showMessageDialog(null, "선택된 계정이 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
    }
}