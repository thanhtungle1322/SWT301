package tung.example;

import java.util.Scanner;

class SecureCredentialsExample {
    private static final String ENV_USERNAME = System.getenv("APP_USERNAME");
    private static final String ENV_PASSWORD = System.getenv("APP_PASSWORD");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String inputUser = scanner.nextLine();

        System.out.print("Enter password: ");
        String inputPass = scanner.nextLine();

        if (authenticate(inputUser, inputPass)) {
            System.out.println("Access granted");
        } else {
            System.out.println("Access denied");
        }

        scanner.close();
    }

    private static boolean authenticate(String user, String pass) {
        return user.equals(ENV_USERNAME) && pass.equals(ENV_PASSWORD);
    }
}
