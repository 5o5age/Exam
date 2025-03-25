import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class testsWriter {
    public static void main(String[] args) {
        writeCSV();
    }

    public static void writeCSV() { //Adds text to CSV file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("test.csv", true));
            
            System.out.println("Please write films data that you want to add in the following order:");
            System.out.println("Film, Year,  Director");
            
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Film's name: ");
            String name = scanner.nextLine();

            System.out.print("Year: ");
            String year = scanner.nextLine();

            System.out.print("Director: ");
            String director = scanner.nextLine();
            
            System.out.print("Is this correct? [y/n]: ");
            System.out.println(name + "," + year + "," + director);
            String answer = scanner.nextLine();

            if ("y".equals(answer)) {
                writer.write(name + ", " + year + ", " + director);
                writer.newLine();
            } else {
                writeCSV();
            }

            writer.close(); 
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
