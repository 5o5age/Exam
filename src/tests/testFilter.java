package tests;

import java.util.*;
import work.Film;
import work.Run;

public class testFilter {

    public static void main(String[] args) {

        Run.films.add(new Film("Movie1", 2001, "Director A", true, "", ""));
        Run.films.add(new Film("Movie2", 2005, "Director B", false, "", ""));
        Run.films.add(new Film("Movie3", 2010, "Director C", true, "", ""));
        Run.films.add(new Film("Movie4", 2010, "Director D", false, "", ""));
        Run.films.add(new Film("Movie5", 2001, "Director E", true, "", ""));
        Run.films.add(new Film("Movie6", 2005, "Director F", false, "", ""));
        Run.films.add(new Film("Movie7", 2015, "Director G", true, "", ""));
        Run.films.add(new Film("Movie8", 2015, "Director H", false, "", ""));
        
        
        String simulatedInput = "2010\n";
        simulateInput(simulatedInput);
        
        System.out.println("Testing filterByYear method:\n");
        work.Filter.filterByYear();
        
        simulatedInput = "available\n"; 
        simulateInput(simulatedInput);
        
        System.out.println("\nTesting filterByAvailability method:\n");
        work.Filter.filterByAvailability();
        
        simulatedInput = "y\n"; 
        simulateInput(simulatedInput);
        
        System.out.println("\nTesting showAvailableFilms method:\n");
        work.Filter.showAvailableFilms();
        
        simulatedInput = "n\n"; 
        simulateInput(simulatedInput);
        
        System.out.println("\nTesting showRentedFilms method:\n");
        work.Filter.showRentedFilms();
    }

    private static void simulateInput(String input) {
        work.Run.scanner = new Scanner(input);
    }
}

