package work;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    public static String RESET = "\u001B[0m";
    public static String BLUE = "\u001B[34m";
    public static String GREEN = "\u001B[32m";
    public static String RED = "\u001B[31m";

    public static void filter() {
        while (true) {
            System.out.println();
            System.out.println(BLUE + " [1] filteryear          - Filters films by year and saves to temporary.csv");
            System.out.println(" [2] filteravail         - Filters films by availability and saves to temporary.csv");
            System.out.println(" [0] return              - Return to main" + RESET);
            System.out.println();
            System.out.print(BLUE + "> " + RESET);

            String command = Run.scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "return":
                case "0":
                    System.out.println(BLUE + "Returning..." + RESET);
                    System.out.println();
                    System.out.println(GREEN + "Welcome to the Film Rental Console        MO");
                    System.out.println("                                          VO");
                    System.out.println("[1] help   - Show available commands      RA" + RESET);
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

    public static void filterByYear() {
        System.out.print(BLUE + "Enter the year to filter by: " + RESET);
        int targetYear;
        try {
            targetYear = Integer.parseInt(Run.scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid year input." + RESET);
            return;
        }

        List<Film> filtered = Run.films.stream().filter(f -> f.year == targetYear).collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println(RED + "No films found for the year " + targetYear + "." + RESET);
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
            System.out.println(GREEN + "Filtered films saved to temporary.csv:" + RESET);
            filtered.forEach(film -> System.out.println(GREEN + film + RESET));
        } catch (IOException e) {
            System.out.println(RED + "Error saving temporary file: " + e.getMessage() + RESET);
        }
    }

    public static void filterByAvailability() {
        System.out.print(BLUE + "Filter available or rented films? ([1] available / [2] rented): " + RESET);
        String input = Run.scanner.nextLine().trim().toLowerCase();

        boolean filterAvailable;
        if (input.equals("available") || input.equals("1")) {
            filterAvailable = true;
        } else if (input.equals("rented") || input.equals("2")) {
            filterAvailable = false;
        } else {
            System.out.println(RED + "Invalid input. Type 'available' or 'rented'." + RESET);
            return;
        }

        List<Film> filtered = Run.films.stream().filter(f -> f.available == filterAvailable).collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println(RED + "No films found matching that availability." + RESET);
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
            System.out.println();
            filtered.forEach(film -> System.out.println(GREEN + film + RESET));
            System.out.println();
        } catch (IOException e) {
            System.out.println(RED + "Error saving temporary file: " + e.getMessage() + RESET);
        }
    }

    public static void showAvailableFilms() {
        filterAndDisplayFilms(true);
    }
    
    public static void showRentedFilms() {
        filterAndDisplayFilms(false);
    }
    
    public static void filterAndDisplayFilms(boolean filterAvailable) {
        List<Film> filtered = Run.films.stream()
            .filter(f -> f.available == filterAvailable)
            .collect(Collectors.toList());
    
        if (filtered.isEmpty()) {
            System.out.println(RED + "No films found matching that availability." + RESET);
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
            System.out.println();
            filtered.forEach(film -> System.out.println(GREEN + film + RESET));
            System.out.println();
        } catch (IOException e) {
            System.out.println(RED + "Error saving temporary file: " + e.getMessage() + RESET);
        }
    }
}