package deu.hms.management.account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * AccountService 클래스는 계정 데이터를 파일로부터 읽고, 파일에 저장하는 기능을 제공합니다.
 * 계정 등록, 데이터 로드 및 저장을 통해 계정 정보를 관리합니다.
 */
public class AccountService {
    private final String filename; // 계정 데이터를 저장하는 파일의 경로

    /**
     * AccountService 클래스의 생성자입니다.
     * 
     * @param filename 계정 데이터를 저장하는 파일의 경로
     */
    public AccountService(String filename) {
        this.filename = filename;
    }

    /**
     * 파일에서 데이터를 읽어와 테이블을 채우는 메서드입니다.
     * 
     * @param model JTable의 DefaultTableModel 객체로, 파일 데이터를 추가합니다.
     */
    public void readFileAndPopulateTable(DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            model.setRowCount(0); // 기존 데이터를 초기화합니다.
            while ((line = br.readLine()) != null) { // 파일의 각 줄을 읽습니다.
                String[] rowData = line.split(","); // 쉼표를 기준으로 데이터를 분리합니다.
                if (rowData.length == 4) { // 각 행은 4개의 요소로 구성됩니다.
                    model.addRow(rowData); // 데이터를 테이블 모델에 추가합니다.
                }
            }
        } catch (IOException e) {
            System.out.println("파일을 읽는 중 문제가 발생했습니다.");
        }
    }

    /**
     * JTable 데이터를 파일에 저장하는 메서드입니다.
     * 
     * @param model JTable의 DefaultTableModel 객체로, 파일에 저장할 데이터를 제공합니다.
     */
    public void saveTableDataToFile(DefaultTableModel model) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, false))) {
            // 테이블의 각 행 데이터를 파일에 작성합니다.
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder rowBuilder = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    rowBuilder.append(model.getValueAt(i, j).toString()); // 각 셀의 값을 가져옵니다.
                    if (j < model.getColumnCount() - 1) {
                        rowBuilder.append(","); // 각 값은 쉼표로 구분합니다.
                    }
                }
                bufferedWriter.write(rowBuilder.toString()); // 행 데이터를 파일에 작성합니다.
                bufferedWriter.newLine(); // 다음 행을 작성하기 위해 줄 바꿈을 추가합니다.
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 새로운 계정을 파일에 등록하는 메서드입니다.
     * 
     * @param number 고유 번호
     * @param id 계정 ID
     * @param pw 계정 비밀번호
     * @param role 계정의 권한 (관리자, 직원 등)
     */
    public void registerAccount(String number, String id, String pw, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(number + "," + id + "," + pw + "," + role); // 새로운 계정 정보를 파일에 추가합니다.
            writer.newLine(); // 다음 계정을 추가할 수 있도록 줄 바꿈을 추가합니다.
        } catch (IOException ex) {
            System.out.println("계정을 등록하는 중 오류가 발생했습니다.");
        }
    }
}
