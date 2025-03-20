import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        
            File file = new File("src\\names.csv");
            Set<Male> males = new TreeSet<>();


            try {
                Scanner inputStream = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        
    }
}