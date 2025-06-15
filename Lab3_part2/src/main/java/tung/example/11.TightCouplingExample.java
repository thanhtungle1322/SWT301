package tung.example;

class Printer {
    void print(String message) {
        System.out.println(message);
    }
}

class Report {
    private final Printer printer;

    public Report(Printer printer) {
        this.printer = printer;
    }

    void generate() {
        printer.print("Generating report...");
    }
}
