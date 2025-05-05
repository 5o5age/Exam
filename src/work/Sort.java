package work;

import java.util.Comparator;

public class Sort {

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

            String command = Run.scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "return":
                case "0":
                    System.out.println("Returning...\n");

                    System.out.println("Welcome to the Film Rental Console        MO");
                    System.out.println("                                          VO");
                    System.out.println("[1] help   - Show available commands      RA");
                    return;
                case "sortfilmaz":
                case "1":
                    sortFilmAZ();
                    break;
                case "sortfilmza":
                case "2":
                    sortFilmZA();
                    break;
                case "sortdirectoraz":
                case "3":
                    sortDirectorAZ();
                    break;
                case "sortdirectorza":
                case "4":
                    sortDirectorZA();
                    break;
                case "sortyearasc":
                case "5":
                    sortYearAsc();
                    break;
                case "sortyeardesc":
                case "6":
                    sortYearDesc();
                    break;
                case "sortavailable":
                case "7":
                    sortAvailable();
                    break;
                case "sortrented":
                case "8":
                    sortRented();
                    break;
            }
        }
    }

    public static void sortFilmAZ() {
        System.out.println();
        Run.films.sort(Comparator.comparing(film -> film.name.toLowerCase()));
        System.out.println("Films sorted by film name A-Z:\n");
        Deals.showFilms();
        System.out.println();
    }

    public static void sortFilmZA() {
        System.out.println();
        Run.films.sort(Comparator.comparing((Film film) -> film.name.toLowerCase()).reversed());
        System.out.println("Films sorted by film name Z-A:\n");
        Deals.showFilms();
        System.out.println();
    }

    public static void sortDirectorAZ() {
        System.out.println();
        Run.films.sort(Comparator.comparing(film -> film.director.toLowerCase()));
        System.out.println("Films sorted by director name A-Z:");
        Deals.showFilms();
        System.out.println();
    }

    public static void sortDirectorZA() {
        System.out.println();
        Run.films.sort(Comparator.comparing((Film film) -> film.director.toLowerCase()).reversed());
        System.out.println("Films sorted by director name Z-A:\n");
        Deals.showFilms();
        System.out.println();
    }

    public static void sortYearAsc() {
        System.out.println();
        Run.films.sort(Comparator.comparingInt(film -> film.year));
        System.out.println("Films sorted by year (oldest to newest):\n");
        Deals.showFilms();
        System.out.println();
    }

    public static void sortYearDesc() {
        System.out.println();
        Run.films.sort(Comparator.comparingInt((Film film) -> film.year).reversed());
        System.out.println("Films sorted by year (newest to oldest):\n");
        Deals.showFilms();
        System.out.println();
    }

    public static void sortAvailable() {
        System.out.println();
        Run.films.sort(Comparator.comparing(film -> !film.available));
        System.out.println("Films sorted by available first:\n");
        Deals.showFilms();
        System.out.println();
    }

    public static void sortRented() {
        System.out.println();
        Run.films.sort(Comparator.comparing(film -> film.available));
        System.out.println("Films sorted by rented first:\n");
        Deals.showFilms();
        System.out.println();
    }
}
