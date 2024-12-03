package deu.hms.management.account;

public class Account {

        private String uniqueNumber;
        private String id;
        private String password;
        private String role;

        public Account(String uniqueNumber, String id, String password, String role) {
            this.uniqueNumber = uniqueNumber;
            this.id = id;
            this.password = password;
            this.role = role;
        }

        // Getter와 Setter 메서드들
        public String getUniqueNumber() {
            return uniqueNumber;
        }

        public void setUniqueNumber(String uniqueNumber) {
            this.uniqueNumber = uniqueNumber;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    
}
