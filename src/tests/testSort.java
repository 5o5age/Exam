package tests;

import java.util.*;
import work.Film;
import work.Run;

public class testSort {

    public static void main(String[] args) {

        Run.films.add(new Film("Movie1A", 2001, "Director A", true, "", ""));
        Run.films.add(new Film("Movie2B", 2005, "Director B", false, "", ""));
        Run.films.add(new Film("Movie3C", 2010, "Director C", true, "", ""));
        Run.films.add(new Film("Movie4D", 2010, "Director D", false, "", ""));
        Run.films.add(new Film("Movie5E", 2001, "Director E", true, "", ""));
        Run.films.add(new Film("Movie6F", 2005, "Director F", false, "", ""));
        Run.films.add(new Film("Movie7G", 2015, "Director G", true, "", ""));
        Run.films.add(new Film("Movie8H", 2015, "Director H", false, "", ""));
        
        // Test sorting by film name A-Z
        System.out.println("Testing sortFilmAZ method:\n");
        simulateInput("1\n"); 
        work.Sort.sortFilmAZ();

        // Test sorting by film name Z-A
        System.out.println("\nTesting sortFilmZA method:\n");
        simulateInput("2\n"); 
        work.Sort.sortFilmZA();

        // Test sorting by director name A-Z
        System.out.println("\nTesting sortDirectorAZ method:\n");
        simulateInput("3\n");
        work.Sort.sortDirectorAZ();

        // Test sorting by director name Z-A
        System.out.println("\nTesting sortDirectorZA method:\n");
        simulateInput("4\n");
        work.Sort.sortDirectorZA();

        // Test sorting by year from oldest to newest
        System.out.println("\nTesting sortYearAsc method:\n");
        simulateInput("5\n");
        work.Sort.sortYearAsc();

        // Test sorting by year from newest to oldest
        System.out.println("\nTesting sortYearDesc method:\n");
        simulateInput("6\n");
        work.Sort.sortYearDesc();

        // Test sorting by availability (available films first)
        System.out.println("\nTesting sortAvailable method:\n");
        simulateInput("7\n");
        work.Sort.sortAvailable();

        // Test sorting by rented films first
        System.out.println("\nTesting sortRented method:\n");
        simulateInput("8\n");
        work.Sort.sortRented();
    }

    // Helper method to simulate Scanner input from a string
    private static void simulateInput(String input) {
        work.Run.scanner = new Scanner(input);
    }
}
