import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Male implements Comparable<Male> {
    private String name;
    private int age;

    public Male(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Male{" +"name='" + name + '\'' +", age=" + age +'}';
    }

    @Override
    public int compareTo(Male o) {
        return name.compareTo(o.getName());
    }

}



    /* public static void main(String[] args) {
        try {
            File myObj = new File("src//films.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        }   catch (FileNotFoundException e) {
            System.out.println("An error occourred.");
            e.printStackTrace();
        }
    } */
