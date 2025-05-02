import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Film {
    String name;
    int year;
    String director;
    boolean available;
    String borrowedBy;
    String returnDate;

    public Film(String name, int year, String director, boolean available, String borrowedBy, String returnDate) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.available = available;
        this.borrowedBy = borrowedBy;
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return String.format("%-30s | %-4d | %-25s | %-30s", name, year, director, (available ? "Available" : "Taken by " + borrowedBy + " until " + returnDate));
    }
}

public class FilmRentalConsole {
    private static final List<Film> films = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadFilms();
        System.out.println("Welcome to the Film Rental Console    MO");
        System.out.println("                                      VO");
        System.out.println("help   - Show available commands      RA");


       
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "exit":
                case "0":
                    saveFilms();
                    System.out.println("Exiting...");
                    return;
                case "show":
                case "1":
                    showFilms();
                    break;
                case "help":
                case "2":
                    showHelp();
                    break;
                case "add":
                case "3":
                    addFilm();
                    break;
                case "rent":
                case "4":
                    rentFilm();
                    break;
                case "return":
                case "5":
                    returnFilm();
                    break;
                case "sort":
                case "6":
                    sort();
                    break;
                case "filter":
                case "7":
                    filter();
                    break;
                case "count":
                case "8":
                    count();
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }
    }

    private static void showFilms() {
        if (films.isEmpty()) {
            System.out.println("No films available.");
        } else {
            for (Film film : films) {
                System.out.println(film);
            }
        }
    }

    private static String overlay() { // TODO: add overlay to places it matters, make it red.
        return("Available commands: |  show  |  help  |  add  |  rent  |  return  |  exit  |");
    }

    private static void showHelp() {
        System.out.println("Available commands:");
        System.out.println(" [1]  show                - Show all films");
        System.out.println(" [2]  help                - Show this help message");
        System.out.println(" [3]  add                 - Add film to the catalogue");
        System.out.println(" [4]  rent                - Rent out existing film");
        System.out.println(" [5]  return              - Return rented out film");
        System.out.println(" [6]  sort                - Open file sorting systems");
        System.out.println(" [7]  filter              - Open file filtering systems");
        System.out.println(" [8]  count               - Open file counting systems");
        System.out.println(" [0]  exit                - Exit the program and save data");
    }

    private static void loadFilms() {
        File file = new File("src\\data\\test.csv");
        if (!file.exists()) return;
       
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    int year = Integer.parseInt(parts[1].trim());
                    String director = parts[2].trim();
                    boolean available = Boolean.parseBoolean(parts[3].trim());
                    String borrowedBy = available ? "" : parts[4].trim();
                    String returnDate = available ? "" : parts[5].trim();
                    films.add(new Film(name, year, director, available, borrowedBy, returnDate));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading films: " + e.getMessage());
        }
    }

    private static void saveFilms() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\data\\test.csv"))) {
            for (Film film : films) {
                writer.write(String.join(",", film.name, String.valueOf(film.year), film.director, String.valueOf(film.available), film.borrowedBy, film.returnDate));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving films: " + e.getMessage());
        }
    }



    private static void addFilm() { //Adds film

        System.out.print("Enter film name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter release year: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter director: ");
        String director = scanner.nextLine().trim();

        System.out.println(name + ", " + year + ", " + director + "  -  is this correct? [y/n]"); //Asks to check if film data is correct
        String answer = scanner.nextLine().trim();

        if (answer.equals("y")) {
            films.add(new Film(name, year, director, true, "", ""));
            System.out.println("Film added successfully!");
        } else {
            System.out.println("Film not added.");
        }
    }
   
    private static void rentFilm() {
        System.out.println("Show all films? [y/n]");
        String answer = scanner.nextLine().trim();
        if (answer.equals("y")) {
            showFilms();
        }

        System.out.print("Enter film name to rent: ");
        String name = scanner.nextLine().trim();
       
        for (Film film : films) {
            if (film.name.equalsIgnoreCase(name) && film.available) {
                System.out.print("Enter client's name: ");
                film.borrowedBy = scanner.nextLine().trim();
                System.out.print("Enter return date (YYYY-MM-DD): ");
                film.returnDate = scanner.nextLine().trim();
                film.available = false;
                System.out.println(film.name + " has been successfully rented");
                return;
            }
        }
        System.out.println("Film not available or does not exist.");
    }
   
    private static void returnFilm() {
        System.out.println("Show all films? [y/n]");
        String answer = scanner.nextLine().trim();
        if (answer.equals("y")) {
            showFilms();
        }


        System.out.print("Enter film name to return: ");
        String name = scanner.nextLine().trim();
       
        for (Film film : films) {
            if (film.name.equalsIgnoreCase(name) && !film.available) {
                film.available = true;
                film.borrowedBy = "";
                film.returnDate = "";
                System.out.println("Film returned successfully!");
                return;
            }
        }
        System.out.println("Film not found or already available.");
    }

    





    private static void count() {
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
        long rented = films.stream()
            .filter(f -> !f.available)
            .count();
        System.out.println("Currently rented films: " + rented + "\n");
    }
    private static void countAll() {
        long all = films.stream()
            .count();
        System.out.println("Currently listed films: " + all + "\n");
    }
    private static void countAvailable() {
        long available = films.stream()
            .filter(f -> f.available)
            .count();
        System.out.println("Currently available films: " + available + "\n");
    } 


    private static void sort() {
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

    private static void sortFilmAZ() {
        films.sort(Comparator.comparing(film -> film.name.toLowerCase())); // case-insensitive
        System.out.println("Films sorted by film name A–Z:");
        showFilms();
        System.out.println();
     }
    private static void sortFilmZA() {
        films.sort(Comparator.comparing((Film film) -> film.name.toLowerCase()).reversed());
        System.out.println("Films sorted by film name Z–A:");
        showFilms();
        System.out.println();
        }
    private static void sortDirectorAZ() {
        films.sort(Comparator.comparing(film -> film.director.toLowerCase())); // full name, case-insensitive
        System.out.println("Films sorted by director name A–Z:");
        showFilms();
        System.out.println();
        }
    private static void sortDirectorZA() {
        films.sort(Comparator.comparing((Film film) -> film.director.toLowerCase()).reversed());
        System.out.println("Films sorted by director name Z–A:");
        showFilms();
        System.out.println();
        }
    private static void sortYearAsc() {
        films.sort(Comparator.comparingInt(film -> film.year));
        System.out.println("Films sorted by year (oldest to newest):");
        showFilms();
        System.out.println();
        }
    private static void sortYearDesc() {
        films.sort(Comparator.comparingInt((Film film) -> film.year).reversed());
        System.out.println("Films sorted by year (newest to oldest):");
        showFilms();
        System.out.println();
        }
    private static void sortAvailable() {
        films.sort(Comparator.comparing(film -> !film.available)); 
        System.out.println("Films sorted by available first");
        showFilms();
        System.out.println();
        }
    private static void sortRented() {
        films.sort(Comparator.comparing(film -> film.available));
        System.out.println("Films sorted by rented first");
        showFilms();
        System.out.println();
        }

    public static void filter() {
        while (true) {
            System.out.println(" [1] filteryear          - Filters films by year and saves to temporary.csv");
            System.out.println(" [2] filteravail         - Filters films by availability and saves to temporary.csv");
            System.out.println(" [0] return              -  Return to main\n");

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
                case "filteryear":
                case "1":
                    filterByYear();
                    break;
                case "filteravail":
                case "2":
                    filterByAvailability();
                    break;
            }
        }
    }


    private static void filterByYear() {
        System.out.print("Enter the year to filter by: ");
        int targetYear;
        try {
            targetYear = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid year input.");
            return;
        }

        List<Film> filtered = films.stream().filter(f -> f.year == targetYear).collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No films found for the year " + targetYear + ".");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\data\\temporary.csv"))) {
            for (Film film : filtered) {
                writer.write(String.join(",", 
                film.name,
                String.valueOf(film.year),
                film.director,
                String.valueOf(film.available),
                film.borrowedBy,
                film.returnDate));
                writer.newLine();
            }
            System.out.println("Filtered films saved to temporary.csv:");
            filtered.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error saving temporary file: " + e.getMessage());
        }
    }


    private static void filterByAvailability() {
        System.out.print("Filter available or rented films? (available/rented): ");
        String input = scanner.nextLine().trim().toLowerCase();
    
        boolean filterAvailable;
        if (input.equals("available")) {
            filterAvailable = true;
        } else if (input.equals("rented")) {
            filterAvailable = false;
        } else {
            System.out.println("Invalid input. Type 'available' or 'rented'.");
            return;
        }
    
        List<Film> filtered = films.stream().filter(f -> f.available == filterAvailable).collect(Collectors.toList());
    
        if (filtered.isEmpty()) {
            System.out.println("No films found matching that availability.");
            return;
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\data\\temporary.csv"))) {
            for (Film film : filtered) {
                writer.write(String.join(",", 
                film.name,
                String.valueOf(film.year),
                film.director,
                String.valueOf(film.available),
                film.borrowedBy,
                film.returnDate));

                writer.newLine();
            }
            System.out.println("Filtered films saved to temporary.csv:\n");
    
            filtered.forEach(System.out::println);
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error saving temporary file: " + e.getMessage());
        }
    }
}