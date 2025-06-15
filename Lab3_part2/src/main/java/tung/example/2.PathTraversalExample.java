package tung.example;

import java.io.*;
import java.nio.file.*;

class SafePathTraversalExample {
    private static final String BASE_DIRECTORY = "./files"; // thư mục cho phép

    public static void main(String[] args) {
        String userInput = "../secret.txt"; // Giả lập đầu vào người dùng

        try {
            Path basePath = Paths.get(BASE_DIRECTORY).toRealPath();
            Path inputPath = Paths.get(BASE_DIRECTORY, userInput).normalize().toRealPath();

            // Kiểm tra xem file nằm trong thư mục cho phép không
            if (!inputPath.startsWith(basePath)) {
                System.out.println("Access denied: Illegal file path.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(inputPath.toFile()))) {
                System.out.println("Reading file: " + inputPath);
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
