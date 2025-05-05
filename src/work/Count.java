package work;

public class Count {
    public static void count() {
        while (true) {
            System.out.println();
            System.out.print("> ");
            System.out.println("Choose count type:");
            System.out.println("[1] countall        -  Count every film");
            System.out.println("[2] countavailable  -  Count available films");
            System.out.println("[3] countrented     -  Counts rented film");
            System.out.println("[0] return          -  Return to main\n");

            System.out.print("> ");

            String command = Run.scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "return":
                case "0":
                    System.out.println("Returning...\n");

                    System.out.println("Welcome to the Film Rental Console    MO");
                    System.out.println("                                      VO");
                    System.out.println("help   - Show available commands      RA");
                    return;
                case "countall":
                case "1":
                    countAll();
                    break;
                case "countavailable":
                case "2":
                    countAvailable();
                    break;
                case "countrented":
                case "3":
                    countRented();
                    break;
                default:
                    System.out.println(
                            "Unknown command. Available commands: |  return  |  count all  |  countavailable  |  countrented  |");
            }
        }

    }

    public static void countRented() {
        long rented = Run.films.stream()
                .filter(f -> !f.available)
                .count();
        System.out.println("Currently rented films: " + rented + "\n");
    }

    public static void countAll() {
        long all = Run.films.stream()
                .count();
        System.out.println("Currently listed films: " + all + "\n");
    }

    public static void countAvailable() {
        long available = Run.films.stream()
                .filter(f -> f.available)
                .count();
        System.out.println("Currently available films: " + available + "\n");
    }
}