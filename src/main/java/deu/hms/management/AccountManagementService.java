package deu.hms.management;

import deu.hms.management.account.AccountManagementFrame;
import javax.swing.JOptionPane;

public class AccountManagementService {
    public void openAccountManagementPage() {
        AccountManagementFrame aframe = new AccountManagementFrame();
        
        aframe.setVisible(true);
    }
}
