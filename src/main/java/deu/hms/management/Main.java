package deu.hms.management;

import javax.swing.UIManager; // UIManager는 GUI의 테마(모양)를 설정하기 위해 사용됩니다.
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        try {
            // UI 모양을 Nimbus 스타일로 설정합니다.
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        // 계정 관리 서비스를 위한 인스턴스를 생성합니다.
        AccountManagementService accountService = new AccountManagementService();
        
        // 객실 관리 서비스를 위한 인스턴스를 생성합니다.
        RoomManagementService roomService = new RoomManagementService();
        
        // 기타 서비스 관리 서비스를 위한 인스턴스를 생성합니다.
        ServiceManagementService serviceService = new ServiceManagementService();

        // 위에서 만든 서비스들을 프레임에 전달하여 ManagementFrame 인스턴스를 생성합니다.
        ManagementFrame frame = new ManagementFrame(accountService, roomService, serviceService);
        
        // 생성한 프레임을 보이게 설정합니다.
        frame.setVisible(true);
    }
}
