package deu.hms.management;

import deu.hms.management.service.Management_Service;

public class ServiceManagementService {
    public void openServiceManagementPage() {
        Management_Service sframe = new Management_Service();
        sframe.setVisible(true);
    }
}
