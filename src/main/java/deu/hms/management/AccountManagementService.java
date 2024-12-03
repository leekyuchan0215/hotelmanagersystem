package deu.hms.management;

// AccountManagementFrame 클래스를 사용하기 위해 import합니다.
import deu.hms.management.account.AccountManagementFrame;
import javax.swing.JOptionPane; // JOptionPane은 메시지 창을 띄우기 위해 사용됩니다.

public class AccountManagementService {
    // 계정 관리 페이지를 열기 위한 메소드입니다.
    public void openAccountManagementPage() {
        // AccountManagementFrame 인스턴스를 생성합니다.
        AccountManagementFrame aframe = new AccountManagementFrame();
        
        // 생성한 창을 보이게 설정합니다.
        aframe.setVisible(true);
    }
}
