package tests;

import java.util.*;
import work.Film;
import work.Run;

public class testDeals {

    public static void main(String[] args) {

        Run.films.add(new Film("Movie1", 2001, "Director A", true, "", ""));
        Run.films.add(new Film("Movie2", 2005, "Director B", false, "", ""));
        Run.films.add(new Film("Movie3", 2010, "Director C", true, "", ""));
        Run.films.add(new Film("Movie4", 2010, "Director D", false, "", ""));
        Run.films.add(new Film("Movie5", 2001, "Director E", true, "", ""));
        Run.films.add(new Film("Movie6", 2005, "Director F", false, "", ""));
        Run.films.add(new Film("Movie7", 2015, "Director G", true, "", ""));
        Run.films.add(new Film("Movie8", 2015, "Director H", false, "", ""));

        String simulatedInput = "Inception\n2010\nChristopher Nolan\ny\n";
        simulateInput(simulatedInput);
        
        // Test the addFilm method
        System.out.println("Testing addFilm method:\n");
        work.Deals.addFilm();
        
        // Check the film was added
        System.out.println("\nFilms in catalog after adding:");
        work.Deals.showFilms();
        
        // Simulate user input for renting a film
        simulatedInput = "y\nInception\nJohn Doe\ny\n";
        simulateInput(simulatedInput);
        
        // Test rentFilm method
        System.out.println("\nTesting rentFilm method:\n");
        work.Deals.rentFilm();
        
        // Check the film is rented
        System.out.println("\nFilms in catalog after renting:");
        work.Deals.showFilms();
        
        // Simulate user input for returning a film
        simulatedInput = "y\nInception\n";
        simulateInput(simulatedInput);
        
        // Test returnFilm method
        System.out.println("\nTesting returnFilm method:\n");
        work.Deals.returnFilm();
        
        // Check the film is returned
        System.out.println("\nFilms in catalog after returning:");
        work.Deals.showFilms();
    }

    private static void simulateInput(String input) {
        work.Run.scanner = new Scanner(input);
    }
}
