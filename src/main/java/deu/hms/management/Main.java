package deu.hms.management;

import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        AccountManagementService accountService = new AccountManagementService();
        RoomManagementService roomService = new RoomManagementService();
        ServiceManagementService serviceService = new ServiceManagementService();

        ManagementFrame frame = new ManagementFrame(accountService, roomService, serviceService);
        frame.setVisible(true);
    }
    
}
