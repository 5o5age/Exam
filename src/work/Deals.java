package work;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deals {
    public static void showFilms() {
        if (Run.films.isEmpty()) {
            System.out.println("No films available.");
        } else {
            for (Film film : Run.films) {
                System.out.println(film);
            }
        }
    }

    public static void showHelp() {
        System.out.println("Available commands:");
        System.out.println(" [1]  help                - Show this help message");
        System.out.println(" [2]  show                - Show all films");
        System.out.println(" [3]  add                 - Add film to the catalogue");
        System.out.println(" [4]  delete              - Deletes film from the catalogue");
        System.out.println(" [5]  rent                - Rent out existing film");
        System.out.println(" [6]  return              - Return rented out film");
        System.out.println(" [7]  sort                - Open file sorting systems");
        System.out.println(" [8]  filter              - Open file filtering systems");
        System.out.println(" [9]  count               - Open file counting systems");
        System.out.println(" [0]  exit                - Exit the program and save data");
    }

    public static void loadFilms() {
        File file = new File("data\\test.csv");
        if (!file.exists())
            return;

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
                    Run.films.add(new Film(name, year, director, available, borrowedBy, returnDate));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading films: " + e.getMessage());
        }
    }
    public static void saveFilms() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data\\test.csv"))) {
            for (Film film : Run.films) {
                writer.write(String.join(",", film.name, String.valueOf(film.year), film.director,
                        String.valueOf(film.available), film.borrowedBy, film.returnDate));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving films: " + e.getMessage());
        }
    }

    public static void addFilm() {
        System.out.print("Enter film name: ");
        String name = Run.scanner.nextLine().trim();
    
        int year = -1;
        while (true) {
            System.out.print("Enter release year: ");
            String yearInput = Run.scanner.nextLine().trim();
            try {
                year = Integer.parseInt(yearInput);
                if (year >= 1500) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a valid number for the year.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the year.");
            }
        }
    
        System.out.print("Enter director: ");
        String director = Run.scanner.nextLine().trim();
    
        System.out.println(name + ", " + year + ", " + director + "  -  is this correct? [y/n]");
        String answer = Run.scanner.nextLine().trim().toLowerCase();
    
        if (answer.equals("y")) {
            Run.films.add(new Film(name, year, director, true, "", ""));
            System.out.println("Film added successfully!");
        } else {
            System.out.println("Film not added.");
        }
    }

    public static void rentFilm() {
        System.out.println("\nShow all available films? [y/n]");
        System.out.print("> ");
        String answer = Run.scanner.nextLine().trim();
        if (answer.equals("y")) {
            Filter.showAvailableFilms();
        }

        System.out.print("Enter film name to rent: ");
        String name = Run.scanner.nextLine().trim();

        for (Film film : Run.films) {
            if (film.name.equalsIgnoreCase(name) && film.available) {
                System.out.print("Enter client's name: ");
                film.borrowedBy = Run.scanner.nextLine().trim();
                System.out.println();

                System.out.println("             vvvvvvvvvv");
                System.out.println("Return date: " + day());
                System.out.println("This is suggested, basic rent of 14 days, is it correct? [y/n]");
                
                System.out.print("> ");
                String rentAnswer = Run.scanner.nextLine().trim();
                if (rentAnswer.equals("y")) {
                    film.returnDate = day();
                    film.available = false;
                } else if (rentAnswer.equals("n")) {
                    System.out.println();
                    System.out.println("Please input return date manually (YYYY-MM-DD):");
                    System.out.print("> ");
                    film.returnDate = Run.scanner.nextLine().trim();
                    film.available = false;
                } else {
                    System.out.println("Unavailable command.");
                    return;
                }
                System.out.println(film.name + " has been successfully rented until " + film.returnDate);
                return;
            }
        }
        System.out.println("Film not available or does not exist.");
    }

    public static void returnFilm() {
        System.out.println("Show all rented films? [y/n]");
        System.out.print("> ");
        String answer = Run.scanner.nextLine().trim();
        if (answer.equals("y")) {
            Filter.showRentedFilms();
        }

        System.out.print("Enter film name to return: ");
        String name = Run.scanner.nextLine().trim();

        for (Film film : Run.films) {
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


    public static void deleteFilm() {
        System.out.println();
        System.out.println("Show all films? [y/n]");
        System.out.print("> ");
        String answer = Run.scanner.nextLine().trim();
        if (answer.equals("y")) {
            for (Film film : Run.films) {
                System.out.println(film);
            }
        System.out.println();
        } else if (answer.equals("n")) {
            System.out.println();
        } else {
            System.out.println("Unavailable command.");
        }

    
        System.out.print("Enter film name to delete: ");
        String name = Run.scanner.nextLine().trim();
    
        for (int i = 0; i < Run.films.size(); i++) {
            Film film = Run.films.get(i);
            if (film.name.equalsIgnoreCase(name)) {
                System.out.println("Are you sure you want to delete \"" + film.name + "\"? [y/n]");
                System.out.print("> ");
                String confirm = Run.scanner.nextLine().trim();
                if (confirm.equalsIgnoreCase("y")) {
                    Run.films.remove(i);
                    System.out.println("Film deleted.");
                } else {
                    System.out.println("Deletion cancelled.");
                }
                return;
            }
        }
    
        System.out.println("Film not found.");
    }

    public static String day() {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(14);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return futureDate.format(formatter);
    }
}

