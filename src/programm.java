import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class programm {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        start(scanner);
        scanner.close();
    }

    private static void start(Scanner scanner) {

        System.out.println("Welcome to the Command Console!");
        run(scanner);
    }


    private static void run(Scanner scanner) {

        boolean running = true;

        System.out.println("Type 'help' for a list of available commands.");
        
        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine();

           switch (command) {
               case "help":
                   showHelp();
                   break;
               case "show":
                   showInfo();
                   break;
               case "exit":
                   running = false;
                   System.out.println("Exiting... Goodbye!");
                   break;
               case "add":
                   addFilm();
                   break;
               default:
                   System.out.println("Unknown command. Type 'help' for a list of commands.");
                   break;
           }
        }
        scanner.close();
    }



    private static void showHelp() {
        System.out.println("\nAvailable commands:");
        System.out.println("help      - Show this help message.");
        System.out.println("show      - Display some information.");
        System.out.println("add       - Add film to the catalogue");
        System.out.println("exit      - Exit the program.");
        System.out.println();
    }

    private static void showInfo() {
        System.out.println("\nThis is a simple console program written in Java.");
        System.out.println("You can type commands to interact with it.");
        System.out.println();
    }

    private static void addFilm() { // Adds text to CSV file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("test.csv", true));
            
            System.out.println("Please write films data that you want to add in the following order:");
            System.out.println("Film, Year, Director");
            
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Film's name: ");
            String name = scanner.nextLine();
    
            System.out.print("Year: ");
            String year = scanner.nextLine();
    
            System.out.print("Director: ");
            String director = scanner.nextLine();
            
            System.out.print("Is this correct? [y/n]: ");
            System.out.println(name + ", " + year + ", " + director);
            String answer = scanner.nextLine();
    
            if ("y".equals(answer)) {
                writer.write(name + "," + year + "," + director);
                writer.newLine();
                System.out.println("Film added to the catalogue.");
                run(scanner);
            } else {
                System.out.println("Please re-enter the film details.");
                addFilm(); 
            }
            
            writer.close(); 
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

        

