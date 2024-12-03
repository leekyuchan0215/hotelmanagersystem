package deu.hms.management;

import deu.hms.management.room.Management_Room;

public class RoomManagementService {
    public void openRoomManagementPage() {
        Management_Room rframe = new Management_Room();
        rframe.setVisible(true);
    }
}
