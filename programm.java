import java.util.Scanner;


public class programm {
    public static void main(String[] args) throws Exception {
       Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Command Console!");
        System.out.println("Type 'help' for a list of available commands.");
        
        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("help")) {
                showHelp();
            } else if (command.equals("show")) {
                showInfo();
            } else if (command.equals("exit")) {
                running = false;
                System.out.println("Exiting... Goodbye!");
            } else {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }

        scanner.close();
    }

    private static void showHelp() {
        System.out.println("\nAvailable commands:");
        System.out.println("help      - Show this help message.");
        System.out.println("show      - Display some information.");
        System.out.println("exit      - Exit the program.");
        System.out.println();
    }

    private static void showInfo() {
        System.out.println("\nThis is a simple console program written in Java.");
        System.out.println("You can type commands to interact with it.");
        System.out.println();
    }

    

}

        

