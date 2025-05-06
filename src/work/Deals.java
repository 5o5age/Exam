package work;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deals {
    public static String RESET = "\u001B[0m";
    public static String BLUE = "\u001B[34m";
    public static String GREEN = "\u001B[32m";
    public static String RED = "\u001B[31m";
    public static String ORANGE = "\u001B[33m";


    public static void showFilms() {
        if (Run.films.isEmpty()) {
            System.out.println(RED + "No films available." + RESET);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate today = LocalDate.now();

            for (Film film : Run.films) {
                String output = film.toString();

                if (!film.available && film.returnDate != null) {
                    try {
                        LocalDate dueDate = LocalDate.parse(film.returnDate.trim(), formatter);
                        if (film.available == false) {
                            if (dueDate.isBefore(today)) {
                            System.out.println(RED + output + RESET);
                            continue;
                            }
                        System.out.println(ORANGE + output + RESET);
                        continue;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println(RED + "[INVALID DATE] " + output + RESET);
                        continue;
                    }
                }

                System.out.println(GREEN + output + RESET);
            }
        }
    }
    


    public static void showHelp() {
        System.out.println(BLUE + "Available commands:" + RESET);
        System.out.println(BLUE + " [1]  help                - Show this help message");
        System.out.println(" [2]  show                - Show all films");
        System.out.println(" [3]  add                 - Add film to the catalogue");
        System.out.println(" [4]  delete              - Deletes film from the catalogue");
        System.out.println(" [5]  rent                - Rent out existing film");
        System.out.println(" [6]  return              - Return rented out film");
        System.out.println(" [7]  sort                - Open file sorting systems");
        System.out.println(" [8]  filter              - Open file filtering systems");
        System.out.println(" [9]  count               - Open file counting systems");
        System.out.println(" [0]  exit                - Exit the program and save data" + RESET);
    }

    public static void loadFilms() {
        File file = new File("src\\data\\filmData.csv");
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
                    Run.films.add(new Film(name, year, director, available, borrowedBy, returnDate));
                }
            }
        } catch (IOException e) {
            System.out.println(RED + "Error loading films: " + e.getMessage() + RESET);
        }
    }

    public static void saveFilms() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\data\\filmData.csv"))) {
            for (Film film : Run.films) {
                writer.write(String.join(",", film.name, String.valueOf(film.year), film.director,
                        String.valueOf(film.available), film.borrowedBy, film.returnDate));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(RED + "Error saving films: " + e.getMessage() + RESET);
        }
    }

    public static void addFilm() {
        System.out.print(BLUE + "Enter film name: " + RESET);
        String name = Run.scanner.nextLine().trim();

        int year = -1;
        while (true) {
            System.out.print(BLUE + "Enter release year: " + RESET);
            String yearInput = Run.scanner.nextLine().trim();
            try {
                year = Integer.parseInt(yearInput);
                if (year >= 1500) break;
                else System.out.println(RED + "Invalid input. Please enter a valid number for the year." + RESET);
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a valid number for the year." + RESET);
            }
        }

        System.out.print(BLUE + "Enter director: " + RESET);
        String director = Run.scanner.nextLine().trim();

        System.out.println(BLUE + name + ", " + year + ", " + director + "  -  is this correct? [y/n]" + RESET);
        String answer = Run.scanner.nextLine().trim().toLowerCase();

        if (answer.equals("y")) {
            Run.films.add(new Film(name, year, director, true, "", ""));
            System.out.println(GREEN + "Film added successfully!" + RESET);
        } else {
            System.out.println(RED + "Film not added." + RESET);
        }
    }

    public static void rentFilm() {
        System.out.println(BLUE + "\nShow all available films? [y/n]" + RESET);
        System.out.print(BLUE + "> " + RESET);
        String answer = Run.scanner.nextLine().trim();
        if (answer.equals("y")) Filter.showAvailableFilms();

        System.out.print(BLUE + "Enter film name to rent: " + RESET);
        String name = Run.scanner.nextLine().trim();

        for (Film film : Run.films) {
            if (film.name.equalsIgnoreCase(name) && film.available) {
                System.out.print(BLUE + "Enter client's name: " + RESET);
                film.borrowedBy = Run.scanner.nextLine().trim();

                System.out.println();
                System.out.println(GREEN + "Return date: " + day() + RESET);
                System.out.println(BLUE + "This is suggested, basic rent of 14 days, is it correct? [y/n]" + RESET);
                System.out.print(BLUE + "> " + RESET);
                String rentAnswer = Run.scanner.nextLine().trim();

                if (rentAnswer.equals("y")) {
                    film.returnDate = day();
                    film.available = false;
                } else if (rentAnswer.equals("n")) {
                    System.out.println(BLUE + "\nPlease input return date manually (YYYY-MM-DD):" + RESET);
                    System.out.print(BLUE + "> " + RESET);
                    film.returnDate = Run.scanner.nextLine().trim();
                    film.available = false;
                } else {
                    System.out.println(RED + "Unavailable command." + RESET);
                    return;
                }

                System.out.println(GREEN + film.name + " has been successfully rented until " + film.returnDate + RESET);
                return;
            }
        }

        System.out.println(RED + "Film not available or does not exist." + RESET);
    }

    public static void returnFilm() {
        System.out.println(BLUE + "Show all rented films? [y/n]" + RESET);
        System.out.print(BLUE + "> " + RESET);
        String answer = Run.scanner.nextLine().trim();
        if (answer.equals("y")) Filter.showRentedFilms();

        System.out.print(BLUE + "Enter film name to return: " + RESET);
        String name = Run.scanner.nextLine().trim();

        for (Film film : Run.films) {
            if (film.name.equalsIgnoreCase(name) && !film.available) {
                film.available = true;
                film.borrowedBy = "";
                film.returnDate = "";
                System.out.println(GREEN + "Film returned successfully!" + RESET);
                return;
            }
        }

        System.out.println(RED + "Film not found or already available." + RESET);
    }

    public static void deleteFilm() {
        System.out.println(BLUE + "\nShow all films? [y/n]" + RESET);
        System.out.print(BLUE + "> " + RESET);
        String answer = Run.scanner.nextLine().trim();
        if (answer.equals("y")) {
            for (Film film : Run.films) {
                System.out.println(GREEN + film + RESET);
            }
            System.out.println();
        } else if (!answer.equals("n")) {
            System.out.println(RED + "Unavailable command." + RESET);
        }

        System.out.print(BLUE + "Enter film name to delete: " + RESET);
        String name = Run.scanner.nextLine().trim();

        for (int i = 0; i < Run.films.size(); i++) {
            Film film = Run.films.get(i);
            if (film.name.equalsIgnoreCase(name)) {
                System.out.println(BLUE + "Are you sure you want to delete \"" + film.name + "\"? [y/n]" + RESET);
                System.out.print(BLUE + "> " + RESET);
                String confirm = Run.scanner.nextLine().trim();
                if (confirm.equalsIgnoreCase("y")) {
                    Run.films.remove(i);
                    System.out.println(GREEN + "Film deleted." + RESET);
                } else {
                    System.out.println(BLUE + "Deletion cancelled." + RESET);
                }
                return;
            }
        }

        System.out.println(RED + "Film not found." + RESET);
    }

    public static String day() {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return futureDate.format(formatter);
    }
}