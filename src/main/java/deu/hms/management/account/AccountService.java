
package deu.hms.management.account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private String filename;

    public AccountService(String filename) {
        this.filename = filename;
    }

    public List<Account> loadAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    accounts.add(new Account(data[0], data[1], data[2], data[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void saveAccounts(List<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Account account : accounts) {
                writer.write(account.getUniqueNumber() + "," + account.getId() + "," +
                             account.getPassword() + "," + account.getRole());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
