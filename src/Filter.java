import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Filter {
    public static final Scanner scanner = new Scanner(System.in);
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

        List<Film> filtered = Functionality.films.stream().filter(f -> f.year == targetYear).collect(Collectors.toList());

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
    
        List<Film> filtered = Functionality.films.stream().filter(f -> f.available == filterAvailable).collect(Collectors.toList());
    
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
