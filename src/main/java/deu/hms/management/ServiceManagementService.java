package deu.hms.management;

import deu.hms.management.service.Management_Service;

public class ServiceManagementService {
     // 서비스 관리 페이지를 열기 위한 메소드입니다.
    public void openServiceManagementPage() {
         // Management_Service 인스턴스를 생성합니다.
        Management_Service sframe = new Management_Service();
        
        // 생성한 창을 보이게 설정합니다.
        sframe.setVisible(true);
    }
}
