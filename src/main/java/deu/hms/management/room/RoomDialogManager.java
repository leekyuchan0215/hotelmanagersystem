package deu.hms.management.room;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RoomDialogManager {
    private JDialog editDialog;
    private JDialog registrationDialog;
    private JTable roomTable;
    private JTable editTable;

    public RoomDialogManager(JDialog editDialog, JDialog registrationDialog, JTable roomTable, JTable editTable) {
        this.editDialog = editDialog;
        this.registrationDialog = registrationDialog;
        this.roomTable = roomTable;
        this.editTable = editTable;
    }

    public void showEditDialog(int selectedRow) {
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel();
        editModel.setRowCount(0);
        String[] rowData = new String[4];
        for (int i = 0; i < 4; i++) {
            rowData[i] = roomTable.getValueAt(selectedRow, i).toString();
        }
        editModel.addRow(rowData);
        editDialog.setSize(700, 300);
        editDialog.setLocationRelativeTo(null);
        editDialog.setVisible(true);
    }

    public void showRegistrationDialog() {
        registrationDialog.setSize(700, 400);
        registrationDialog.setLocationRelativeTo(null);
        registrationDialog.setVisible(true);
    }

    public void deleteRoom(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
        model.removeRow(selectedRow);
        JOptionPane.showMessageDialog(null, "선택된 객실이 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
    }
}
