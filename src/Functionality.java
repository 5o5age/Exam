import java.io.*;
import java.util.*;


public class Functionality {
    public static final List<Film> films = new ArrayList<>();
    public static final Scanner scanner = new Scanner(System.in);

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

    public static void showFilms() {
        if (films.isEmpty()) {
            System.out.println("No films available.");
        } else {
            for (Film film : films) {
                System.out.println(film);
            }
        }
    }

    public static String overlay() { // TODO: add overlay to places it matters, make it red.
        return("Available commands: |  show  |  help  |  add  |  rent  |  return  |  exit  |");
    }

    public static void showHelp() {
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

    public static void loadFilms() {
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

    public static void saveFilms() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\data\\test.csv"))) {
            for (Film film : films) {
                writer.write(String.join(",", film.name, String.valueOf(film.year), film.director, String.valueOf(film.available), film.borrowedBy, film.returnDate));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving films: " + e.getMessage());
        }
    }



    public static void addFilm() { //Adds film

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
   
    public static void rentFilm() {
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
   
    public static void returnFilm() {
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
}
