package deu.hms.management;

import deu.hms.management.room.Management_Room;

public class RoomManagementService {
     // 객실 관리 페이지를 열기 위한 메소드입니다.
    public void openRoomManagementPage() {
        // Management_Room 인스턴스를 생성합니다.
        Management_Room rframe = new Management_Room();
        
         // 생성한 창을 보이게 설정합니다.
        rframe.setVisible(true);
    }
}
