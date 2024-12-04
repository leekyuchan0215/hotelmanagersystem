package deu.hms.management.room;

import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RoomService {

    // 파일 이름을 저장할 변수
    private final String filename;

    // 생성자: 파일 이름을 초기화
    public RoomService(String filename) {
        this.filename = filename;
    }

    // 파일에서 데이터를 읽어와 테이블 모델에 채우는 메서드
    public void readFileAndPopulateTable(DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) { // 파일 읽기 스트림 생성
            String line;
            model.setRowCount(0); // 테이블 모델 초기화

            // 파일의 각 줄을 읽어서 처리
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(","); // 데이터를 콤마로 분리
                if (rowData.length == 4) { // 데이터가 4개 컬럼으로 구성되어 있는지 확인
                    model.addRow(rowData); // 테이블 모델에 행 추가
                }
            }
        } catch (IOException e) { // 파일 읽기 중 예외 처리
            // 오류 메시지 표시
            JOptionPane.showMessageDialog(null, "파일 읽기 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 테이블 모델 데이터를 파일에 저장하는 메서드
    public void saveTableDataToFile(DefaultTableModel model) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) { // 파일 쓰기 스트림 생성
            // 테이블 모델의 각 행 데이터를 파일에 작성
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder(); // 한 줄 데이터를 저장할 StringBuilder
                for (int j = 0; j < model.getColumnCount(); j++) {
                    sb.append(model.getValueAt(i, j).toString()); // 셀 데이터를 추가
                    if (j < model.getColumnCount() - 1) {
                        sb.append(","); // 마지막 컬럼이 아니면 콤마 추가
                    }
                }
                bw.write(sb.toString()); // 파일에 데이터 쓰기
                bw.newLine(); // 줄 바꿈
            }
            // 저장 성공 메시지 표시
            JOptionPane.showMessageDialog(null, "파일 저장 완료", "성공", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) { // 파일 쓰기 중 예외 처리
            // 오류 메시지 표시
            JOptionPane.showMessageDialog(null, "파일 저장 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
