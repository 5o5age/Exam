package work;

public class Count {
    public static String RESET = "\u001B[0m";
    public static String BLUE = "\u001B[34m";
    public static String GREEN = "\u001B[32m";
    public static String RED = "\u001B[31m";

    public static void count() {
        while (true) {
            System.out.println();
            System.out.println(BLUE + "========================================");
            System.out.println("               COUNT MENU               ");
            System.out.println("========================================" + RESET);
            System.out.println(BLUE + "[1] countall        - Count every film");
            System.out.println("[2] countavailable  - Count available films");
            System.out.println("[3] countrented     - Count rented films");
            System.out.println("[0] return          - Return to main menu" + RESET);
            System.out.println();

            System.out.print(BLUE + "> " + RESET);
            String command = Run.scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "return":
                case "0":
                    System.out.println(GREEN + "Returning to main menu..." + RESET);
                    System.out.println();
                    System.out.println(BLUE + "========================================");
                    System.out.println("     Welcome to the Film Rental App     ");
                    System.out.println("               MO  VO  RA               ");
                    System.out.println("========================================" + RESET);
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
                    System.out.println(RED + "Unknown command. Try one of:");
                    System.out.println("countall | countavailable | countrented | return" + RESET);
            }
        }
    }

    public static void countRented() {
        long rented = Run.films.stream().filter(f -> !f.available).count();
        System.out.println("Currently rented films: " + GREEN + rented + RESET + "\n");
    }

    public static void countAll() {
        long all = Run.films.stream().count();
        System.out.println("Total films listed: " + GREEN + all + RESET + "\n");
    }

    public static void countAvailable() {
        long available = Run.films.stream().filter(f -> f.available).count();
        System.out.println("Currently available films: " + GREEN + available + RESET + "\n");
    }
}
