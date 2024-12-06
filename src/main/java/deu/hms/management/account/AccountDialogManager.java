package deu.hms.management.account;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * AccountDialogManager 클래스는 계정 관리 관련 다이얼로그의 표시 및 처리를 담당하는 클래스입니다.
 * 이 클래스는 계정을 수정하거나 등록하는 다이얼로그를 표시하고, 선택된 계정을 삭제하는 기능을 제공합니다.
 */
public class AccountDialogManager {
    private final JDialog editDialog; // 계정 수정을 위한 다이얼로그 객체
    private final JDialog registrationDialog; // 계정 등록을 위한 다이얼로그 객체
    private final JTable accountTable; // 계정 정보를 표시하는 메인 테이블 객체
    private final JTable editTable; // 수정 시 계정 정보를 표시하는 테이블 객체

    /**
     * AccountDialogManager 클래스의 생성자입니다.
     * 
     * @param editDialog 계정 수정을 위한 다이얼로그 객체
     * @param registrationDialog 계정 등록을 위한 다이얼로그 객체
     * @param accountTable 계정 정보를 표시하는 메인 테이블 객체
     * @param editTable 수정 시 계정 정보를 표시하는 테이블 객체
     */
    public AccountDialogManager(JDialog editDialog, JDialog registrationDialog, JTable accountTable, JTable editTable) {
        this.editDialog = editDialog;
        this.registrationDialog = registrationDialog;
        this.accountTable = accountTable;
        this.editTable = editTable;
    }   

    /**
     * 선택된 계정을 수정하기 위한 다이얼로그를 표시하는 메서드입니다.
     * 
     * @param selectedRow 수정할 계정이 있는 테이블의 선택된 행 인덱스
     */
    public void showEditDialog(int selectedRow) {
        // editTable의 모델을 가져와 초기화합니다.
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel();
        editModel.setRowCount(0); // 기존 데이터를 초기화하여 새 데이터를 추가할 준비를 합니다.
        
        // 선택된 행의 데이터를 editTable에 복사합니다.
        String[] rowData = new String[4]; // 계정 정보는 4개의 열로 구성됩니다.
        for (int i = 0; i < 4; i++) {
            rowData[i] = accountTable.getValueAt(selectedRow, i).toString(); // 선택된 행의 각 열 데이터를 가져옵니다.
        }
        editModel.addRow(rowData); // editTable에 선택된 행의 데이터를 추가합니다.
        
        // 다이얼로그의 크기와 위치를 설정하고 표시합니다.
        editDialog.setSize(700, 300);
        editDialog.setLocationRelativeTo(null); // 화면의 중앙에 위치시킵니다.
        editDialog.setVisible(true); // 다이얼로그를 표시합니다.
    }

    /**
     * 계정을 등록하기 위한 다이얼로그를 표시하는 메서드입니다.
     */
    public void showRegistrationDialog() {
        // 등록 다이얼로그의 크기와 위치를 설정하고 표시합니다.
        registrationDialog.setSize(700, 300);
        registrationDialog.setLocationRelativeTo(null); // 화면의 중앙에 위치시킵니다.
        registrationDialog.setVisible(true); // 다이얼로그를 표시합니다.
    }

    /**
     * 선택된 계정을 삭제하는 메서드입니다.
     * 
     * @param selectedRow 삭제할 계정이 있는 테이블의 선택된 행 인덱스
     */
    public void deleteAccount(int selectedRow) {
        // accountTable의 모델을 가져와 선택된 행을 삭제합니다.
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        model.removeRow(selectedRow); // 선택된 행을 삭제합니다.
        
        // 삭제 성공 메시지를 사용자에게 표시합니다.
        JOptionPane.showMessageDialog(null, "선택된 계정이 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
    }
}
