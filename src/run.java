import java.util.*;


public class run {
    public static final List<Film> films = new ArrayList<>();
    public static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        Deals.loadFilms();
        System.out.println("Welcome to the Film Rental Console    MO");
        System.out.println("                                      VO");
        System.out.println("help   - Show available commands      RA");


       
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "exit":
                case "0":
                    Deals.saveFilms();
                    System.out.println("Exiting...");
                    return;
                case "show":
                case "1":
                    Deals.showFilms();
                    break;
                case "help":
                case "2":
                    Deals.showHelp();
                    break;
                case "add":
                case "3":
                    Deals.addFilm();
                    break;
                case "rent":
                case "4":
                    Deals.rentFilm();
                    break;
                case "return":
                case "5":
                    Deals.returnFilm();
                    break;
                case "sort":
                case "6":
                    Sort.sort();
                    break;
                case "filter":
                case "7":
                    Filter.filter();
                    break;
                case "count":
                case "8":
                    Count.count();
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }
    }

}

