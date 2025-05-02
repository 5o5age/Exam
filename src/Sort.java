import java.util.Comparator;
import java.util.Scanner;

public class Sort {
    public static final Scanner scanner = new Scanner(System.in);
    public static void sort() {
        while (true) {
            System.out.println(" [1]  sortfilmaz           - Sorts list by film's name A-Z order");
            System.out.println(" [2]  sortfilmza           - Sorts list by film's name Z-A order");
            System.out.println(" [3]  sortdirectoraz       - Sorts list by director's name A-Z order");
            System.out.println(" [4]  sortdirectorza       - Sorts list by director's name in Z-A order");
            System.out.println(" [5]  sortyearasc          - Sorts list by year from oldest to newest");
            System.out.println(" [6]  sortyeardesc         - Sorts list by year from newest to oldest");
            System.out.println(" [7]  sortavailable        - Sorts list by availability (available films first)");
            System.out.println(" [8]  sortrented           - Sorts list by availability (rented films first)");
            System.out.println(" [0]  return               -  Return to main\n");



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
                case "sortdirectoraz":
                case "1":
                    sortDirectorAZ();
                    break;
                case "sortdirectorza":
                case "2":
                    sortDirectorZA();
                    break;
                case "sortfilmaz":
                case "3":
                    sortFilmAZ();
                    break;
                case "sortfilmza":
                case "4":
                    sortFilmZA();
                    break;
                case "sortyearasc":
                case "5":
                    sortYearAsc();
                    break;
                case "sortyeardesc":
                case "6":
                    sortYearDesc();
                    break;
                case "sortrented":
                case "7":
                    sortRented();
                    break;
                case "sortavailable":
                case "8":
                    sortAvailable();
                    break;
            }
        }
    }

    public static void sortFilmAZ() {
        Functionality.films.sort(Comparator.comparing(film -> film.name.toLowerCase()));
        System.out.println("Films sorted by film name A-Z:");
        Functionality.showFilms();
        System.out.println();
     }
    public static void sortFilmZA() {
        Functionality.films.sort(Comparator.comparing((Film film) -> film.name.toLowerCase()).reversed());
        System.out.println("Films sorted by film name Z-A:");
        Functionality.showFilms();
        System.out.println();
        }
    public static void sortDirectorAZ() {
        Functionality.films.sort(Comparator.comparing(film -> film.director.toLowerCase()));
        System.out.println("Films sorted by director name A-Z:");
        Functionality.showFilms();
        System.out.println();
        }
    public static void sortDirectorZA() {
        Functionality.films.sort(Comparator.comparing((Film film) -> film.director.toLowerCase()).reversed());
        System.out.println("Films sorted by director name Z-A:");
        Functionality.showFilms();
        System.out.println();
        }
    public static void sortYearAsc() {
        Functionality.films.sort(Comparator.comparingInt(film -> film.year));
        System.out.println("Films sorted by year (oldest to newest):");
        Functionality.showFilms();
        System.out.println();
        }
    public static void sortYearDesc() {
        Functionality.films.sort(Comparator.comparingInt((Film film) -> film.year).reversed());
        System.out.println("Films sorted by year (newest to oldest):");
        Functionality.showFilms();
        System.out.println();
        }
    public static void sortAvailable() {
        Functionality.films.sort(Comparator.comparing(film -> !film.available)); 
        System.out.println("Films sorted by available first");
        Functionality.showFilms();
        System.out.println();
        }
    public static void sortRented() {
        Functionality.films.sort(Comparator.comparing(film -> film.available));
        System.out.println("Films sorted by rented first");
        Functionality.showFilms();
        System.out.println();
        }
}
