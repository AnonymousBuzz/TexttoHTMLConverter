// declaration of package name for the Java class.
package com.example.demo;
//import necessary classes and packages
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class TextToHTMLConverter implements CommandLineRunner {
//This is the entry point of the application. It calls SpringApplication.run() to start the Spring Boot application.
    public static void main(String[] args) {
        SpringApplication.run(TextToHTMLConverter.class, args);
    }
    
//This method is part of the CommandLineRunner interface. It contains the code that will be executed when the application starts. It sets the input and output file paths.
    @Override
    public void run(String... args) {
        String inputFilePath = "src/main/resources/templates/input.txt"; // Replace with your input file path
        String outputFilePath = "src/main/resources/templates/output.html"; // Replace with your desired output file path

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            
//It writes the starting HTML tags to the output file.
            writer.write("<html><body>\n");
            
//This starts a loop to read each line from the input file.
            String line;
            while ((line = reader.readLine()) != null) {
//It splits each line into two parts using the "=" delimiter and trims any leading or trailing whitespaces.
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String keyword = parts[0].trim();
                    String value = parts[1].trim();

                    // Generate HTML based on the keyword
                   
                    
                    
                    switch (keyword) { 
                    
                       case "Header":
                	String[] title = value.split(",",2);
                    writer.write("<div class=\"row\" >\n"
                    		+ "        <div >\n"
                    		+ "            <h4>"+ title[0] +"</h4>"
                    		+ "            <h4>"+ title[1] +"</h4>"
                    		+ "        </div>\n"
                    		+ "       \n"
                    		+ "    </div>");
                    break;
                      
                            
                    case "Parties":
                       
                        writer.write("<div class=\"formborder\">\n"
                                + "    <legend>\n"
                                + "        <h3> " + value + "</h3>\n"
                                + "    </legend>\n"
                                + "    <div class=\"form-group\" id=\"guest-details\">\n"
                                + "        <div class=\"row\">\n"
                                + "            <div class=\"col-md-6\">Landlord/Property Owner Name<span class=\"star\" style=\"color: red;\">*</span> :\n"
                                + "                <input id=\"principalOwner\" th:text=\"${leaseObj.principalOwner}\" type=\"text\" class=\"form-control\" placeholder=\" \">\n"
                                + "                <span id=\"principal_owner\"></span>\n"
                                + "            </div>\n"
                                + "        </div>\n"
                                + "    </div>\n"
                                + "</div>");
                   
                        writer.write("<div class=\"formborder\">\n"
                                + "    <legend>\n"
                                + "        <h3> " + value + "</h3>\n"
                                + "    </legend>\n"
                                + "    <div class=\"form-group\" id=\"tenant-details\">\n"
                                + "        <div class=\"row\">\n"
                                + "            <div class=\"col-md-6\">Tenant Name<span class=\"star\" style=\"color: red;\">*</span> :\n"
                                + "                <input id=\"tenantName\" th:text=\"${leaseObj.tenantName}\" type=\"text\" class=\"form-control\" placeholder=\" \">\n"
                                + "                <span id=\"tenant_name\"></span>\n"
                                + "            </div>\n"
                                + "        </div>\n"
                                + "    </div>\n"
                                + "</div>");
                        break;
                   case "additionalHeader":
                        writer.write("<div class=\"form-group\">\n"
                                          + "          <div class=\"row\">\n"
                                          + "            <h4>" + value + "</h4>"
                                          + "          </div>"
                                          + "        </div>");
                              
                              break;
                                   case "additionalParagraph":
                               	writer.write("<div class=\"form-group\">\n"
                                   		+ "          <div class=\"row\">\n"
                                   		+ "            <p> " + value + " </p>\n"
                                   		+ "          </div>\n"
                                   		+ "        </div>");
                                   break;
                                   
                                   case "Title":
                                	   writer.write("<legend>\n"
                                	   		+ "      <h3>" + value + "</h3>\n"
                                	   		+ "    </legend>\n");
                                                      break;
                                   case "Box":
                                	   writer.write("<div class=\"formborder\" id=\"" + value + "\">");
                                                      break;
                                   case "Close":
                                	   if( value.equals("Form") ) {
                                		   writer.write("</div>");
                                       }
                                	   writer.write("</div>");
                                                      break;
                                   case "Form":
                                	   writer.write("<div class=\"form-group\"> <div class=\"row\">");
                                                      break;
                                   case "Input":
                                	   
                                	   String[] inputlist = value.split(",",2);
                                	   String id = inputlist[0].toLowerCase();
                                	   writer.write("<div class=\"col-md-6\">" + inputlist[0] + "\n"
                                	   		+ "          <input id=\"" + id + "\" th:text=\"${leaseObj. " + id + "}\" type=\"text\" class=\"form-control\" placeholder=\" \" disabled=\"disabled\" \n"
                                	   		+ "          value = \"" + inputlist[1] + "\">\n"
                                	   	+ "        </div>");
                                                      break;
   // Add more cases for other keywords as needed
                    }
                }
            }

            writer.write("</body></html>");
            reader.close();
            writer.close();

            System.out.println("HTML file generated successfully.");
//This prints a message to the console indicating that the HTML file has been successfully generated.
            } catch (IOException e) {
            e.printStackTrace();
        }
        
}
}