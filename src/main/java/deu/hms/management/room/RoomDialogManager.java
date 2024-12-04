package deu.hms.management.room;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RoomDialogManager {
    
   // 수정 다이얼로그를 관리하는 객체
    private final JDialog editDialog;
    // 등록 다이얼로그를 관리하는 객체
    private final JDialog registrationDialog;
    // 객실 정보를 표시하는 테이블
    private final JTable roomTable;
    // 수정된 정보를 표시하는 테이블
    private final JTable editTable;
    
    // 생성자: 다이얼로그와 테이블 객체를 초기화
    public RoomDialogManager(JDialog editDialog, JDialog registrationDialog, JTable roomTable, JTable editTable) {
        this.editDialog = editDialog; // 수정 다이얼로그
        this.registrationDialog = registrationDialog; // 등록 다이얼로그
        this.roomTable = roomTable; // 원본 테이블
        this.editTable = editTable; // 수정 테이블
    }

    // 수정 다이얼로그를 표시하는 메서드
    public void showEditDialog(int selectedRow) {
        // 수정 테이블 모델을 가져옴
        DefaultTableModel editModel = (DefaultTableModel) editTable.getModel();
        editModel.setRowCount(0);  // 기존 데이터 초기화
        
        // 선택된 행의 데이터를 배열로 저장
        String[] rowData = new String[4];
        for (int i = 0; i < 4; i++) {
            rowData[i] = roomTable.getValueAt(selectedRow, i).toString();  // 원본 테이블 데이터 가져오기
        }
        
        // 수정 테이블에 행 추가
        editModel.addRow(rowData);
        // 수정 다이얼로그 크기 및 위치 설정
        editDialog.setSize(700, 300);
        editDialog.setLocationRelativeTo(null);  // 화면 중앙에 배치
        editDialog.setVisible(true);   // 다이얼로그 표시
    }

    // 등록 다이얼로그를 표시하는 메서드
    public void showRegistrationDialog() {
        // 등록 다이얼로그 크기 및 위치 설정
        registrationDialog.setSize(700, 400);
        registrationDialog.setLocationRelativeTo(null);  // 화면 중앙에 배치
        registrationDialog.setVisible(true);  // 다이얼로그 표시
    }
    
    // 객실 삭제 기능을 처리하는 메서드
    public void deleteRoom(int selectedRow) {
        // 테이블 모델 가져오기
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
        model.removeRow(selectedRow);  // 선택된 행 삭제
        JOptionPane.showMessageDialog(null, "선택된 객실이 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
    }
}
