package deu.hms.management.room;

import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RoomService {
    private final String filename;

    public RoomService(String filename) {
        this.filename = filename;
    }

    public void readFileAndPopulateTable(DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            model.setRowCount(0);
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(",");
                if (rowData.length == 4) {
                    model.addRow(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "파일 읽기 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveTableDataToFile(DefaultTableModel model) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    sb.append(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) sb.append(",");
                }
                bw.write(sb.toString());
                bw.newLine();
            }
            JOptionPane.showMessageDialog(null, "파일 저장 완료", "성공", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "파일 저장 오류: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}