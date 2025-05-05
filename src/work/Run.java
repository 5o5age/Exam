package work;
import java.util.*;

public class Run {
    public static List<Film> films = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void run() {
        Deals.loadFilms();
        System.out.println();
        System.out.println("Welcome to the Film Rental Console        MO");
        System.out.println("                                          VO");
        System.out.println("[1] help   - Show available commands      RA");

        while (true) {
            System.err.println();
            System.out.print("> "); 
            String command = scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "exit":
                case "0":
                    Deals.saveFilms();
                    System.out.println("Exiting...");
                    return;
                case "help":
                case "1":
                    Deals.showHelp();
                    break;
                case "show":
                case "2":
                    Deals.showFilms();
                    break;
                case "add":
                case "3":
                    Deals.addFilm();
                    break;
                case "delete":
                case "4":
                    Deals.deleteFilm();
                    break;
                case "rent":
                case "5":
                    Deals.rentFilm();
                    break;
                case "return":
                case "6":
                    Deals.returnFilm();
                    break;
                case "sort":
                case "7":
                    Sort.sort();
                    break;
                case "filter":
                case "8":
                    Filter.filter();
                    break;
                case "count":
                case "9":
                    Count.count();
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }
    }

}
