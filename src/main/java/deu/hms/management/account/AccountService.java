package deu.hms.management.account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AccountService {
    private final String filename;

    public AccountService(String filename) {
        this.filename = filename;
    }

    // 파일에서 데이터를 읽어와 테이블을 채우는 메서드
    public void readFileAndPopulateTable(DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            model.setRowCount(0); // 기존 데이터를 초기화합니다.
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(",");
                if (rowData.length == 4) {
                    model.addRow(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일을 읽는 중 문제가 발생했습니다.");
        }
    }

    // 테이블 데이터를 파일에 저장하는 메서드
    public void saveTableDataToFile(DefaultTableModel model) {
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
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 계정을 등록하는 메서드
    public void registerAccount(String number, String id, String pw, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(number + "," + id + "," + pw + "," + role);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("계정을 등록하는 중 오류가 발생했습니다.");
        }
    }
}
