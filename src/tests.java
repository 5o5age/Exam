import java.io.*;
import java.util.concurrent.ExecutorCompletionService;

public class tests {
    public static void main(String[] args) {
        

        String file = "src\\films.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {

                String[] row = line.split(",");

                for(Strings index : row)//Continue here
            }
        }
        catch(Exception e) {

        }
        finally {

        }
    }
}
