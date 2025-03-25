import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class testsReader {

    public static void main(String[] args) throws Exception {
        readCSV();
    }
    
    public static void readCSV() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("test.csv"));
            System.out.println(reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
