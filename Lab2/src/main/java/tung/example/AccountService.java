package tung.example;

public class AccountService {

    public boolean registerAccount(String username, String password, String email) {
        return username != null && !username.isEmpty()
                && isValidPassword(password)
                && isValidEmail(email);
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() > 6;
    }
    public boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty()
                && username.matches("^[a-zA-Z0-9_]{3,}$");
    }
}

