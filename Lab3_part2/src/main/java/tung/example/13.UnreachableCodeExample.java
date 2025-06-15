package tung.example;

class UnreachableCodeExample {
    public static int getNumber() {
        System.out.println("Returning value...");
        return 42;
    }

    public static void main(String[] args) {
        System.out.println(getNumber());
    }
}
