package com.example.demo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class TextToHTMLConverter implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TextToHTMLConverter.class, args);
    }

    @Override
    public void run(String... args) {
        String inputFilePath = "src/main/resources/templates/input.txt"; // Replace with your input file path
        String outputFilePath = "src/main/resources/templates/output.html"; // Replace with your desired output file path

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

            writer.write("<html><body>\n");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String keyword = parts[0].trim();
                    String value = parts[1].trim();

                    // Generate HTML based on the keyword
                    switch (keyword) {
                        case "Title":
                            writer.write("<h1>" + value + "</h1>\n");
                            break;
                        case "Subtitle":
                            writer.write("<h2>" + value + "</h2>\n");
                            break;
                        case "Section":
                            writer.write("<h3>" + value + "</h3>\n");
                            break;
                        case "Agreement Header":
                            writer.write("<p><strong>" + value + "</strong></p>\n");
                            break;
                        case "Agreement Paragraph":
                            writer.write("<p>" + value + "</p>\n");
                            break;
                        // Add more cases for other keywords as needed
                    }
                }
            }

            writer.write("</body></html>");
            reader.close();
            writer.close();

            System.out.println("HTML file generated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}