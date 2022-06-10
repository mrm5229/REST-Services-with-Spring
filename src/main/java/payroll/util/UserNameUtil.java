package payroll.util;

public class UserNameUtil {
    public static NameHolder firstLastNameSplitter(String name){
        String[] parts = name.split(" ");
        return new NameHolder(parts[0], parts[1]);
    }

    public static class NameHolder {
        private final String firstName;
        private final String lastName;

        public NameHolder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
}
