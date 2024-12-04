package deu.hms.management.service;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ServiceDialogManager {
    private JDialog editDialog;
    private JDialog registrationDialog;
    private JTable serviceTable;
    private JTable editTable;

    public ServiceDialogManager(JDialog editDialog, JDialog registrationDialog, JTable serviceTable, JTable editTable) {
        this.editDialog = editDialog;
        this.registrationDialog = registrationDialog;
        this.serviceTable = serviceTable;
        this.editTable = editTable;
    }

    public void showRegistrationDialog() {
        registrationDialog.setSize(600, 400);
        registrationDialog.setLocationRelativeTo(null);
        registrationDialog.setVisible(true);
    }

    public void showEditDialog(int selectedRow) {
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel();
        editModel.setRowCount(0);
        String[] rowData = new String[3];
        for (int i = 0; i < 3; i++) {
            rowData[i] = serviceTable.getValueAt(selectedRow, i).toString();
        }
        editModel.addRow(rowData);
        editDialog.setSize(700, 300);
        editDialog.setLocationRelativeTo(null);
        editDialog.setVisible(true);
    }

    public void registerService() {
        String service = JOptionPane.showInputDialog("서비스 종류를 입력하세요:");
        String food = JOptionPane.showInputDialog("음식을 입력하세요:");
        String price = JOptionPane.showInputDialog("가격을 입력하세요:");

        if (service == null || food == null || price == null || service.isEmpty() || food.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(null, "모든 필드를 채워주세요!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
        model.addRow(new Object[]{service, food, price});
        registrationDialog.dispose();
    }

    public void updateServiceData() {
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel();
        DefaultTableModel serviceModel = (DefaultTableModel) serviceTable.getModel();

        if (editModel.getRowCount() > 0) {
            String[] updatedRowData = new String[editModel.getColumnCount()];
            for (int i = 0; i < updatedRowData.length; i++) {
                updatedRowData[i] = editModel.getValueAt(0, i).toString();
            }

            int selectedRow = serviceTable.getSelectedRow();
            for (int i = 0; i < updatedRowData.length; i++) {
                serviceModel.setValueAt(updatedRowData[i], selectedRow, i);
            }
            JOptionPane.showMessageDialog(null, "데이터가 성공적으로 수정되었습니다.");
        }
        editDialog.dispose();
    }

    public void deleteService(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
        model.removeRow(selectedRow);
        JOptionPane.showMessageDialog(null, "선택된 서비스가 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
    }
}
