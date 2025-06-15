package tung.example;

interface Constants {
    int MAX_USERS = 100;
}

class InterfaceFieldModificationExample {
    public static void main(String[] args) {
        System.out.println("Max users allowed: " + Constants.MAX_USERS);
    }
}
