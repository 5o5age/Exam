import java.util.Scanner;


public class Count {
    public static final Scanner scanner = new Scanner(System.in);
    public static void count() {
        while (true) {
            System.out.print("> ");
            System.out.println("Choose count type:");
            System.out.println("[1] countall        -  Count every film");
            System.out.println("[2] countavailable  -  Count available films");
            System.out.println("[3] countrented     -  Counts rented film");
            System.out.println("[0] return          -  Return to main\n");



            System.out.print("> ");

            String command = scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "return":
                case "0":
                    System.out.println("Returning...\n");
                    
                    System.out.println("Welcome to the Film Rental Console    MO");
                    System.out.println("                                      VO");
                    System.out.println("help   - Show available commands      RA");
                    return;
                case "countrented":
                case "1":
                    countRented();
                    break;
                case "countavailable":
                case "2":
                    countAvailable();
                    break;
                case "countall":
                case "3":
                    countAll();
                    break;
                default:
                    System.out.println("Unknown command. Available commands: |  return  |  count all  |  countavailable  |  countrented  |");
                }
        }

    }

    private static void countRented() {
        long rented = Functionality.films.stream()
            .filter(f -> !f.available)
            .count();
        System.out.println("Currently rented films: " + rented + "\n");
    }
    private static void countAll() {
        long all = Functionality.films.stream()
            .count();
        System.out.println("Currently listed films: " + all + "\n");
    }
    private static void countAvailable() {
        long available = Functionality.films.stream()
            .filter(f -> f.available)
            .count();
        System.out.println("Currently available films: " + available + "\n");
    }
}