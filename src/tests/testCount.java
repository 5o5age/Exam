package tests;

import java.util.ArrayList;
import java.util.Scanner;
import work.Count;
import work.Film;
import work.Run;

public class testCount {
    public static void main(String[] args) {

        Run.films = new ArrayList<>();
        Run.films.add(new Film("Movie1", 2001, "Director A", true, "", ""));
        Run.films.add(new Film("Movie2", 2005, "Director B", false, "", ""));
        Run.films.add(new Film("Movie3", 2010, "Director C", true, "", ""));
        Run.films.add(new Film("Movie4", 2010, "Director D", false, "", ""));
        Run.films.add(new Film("Movie5", 2001, "Director E", true, "", ""));
        Run.films.add(new Film("Movie6", 2005, "Director F", false, "", ""));
        Run.films.add(new Film("Movie7", 2015, "Director G", true, "", ""));
        Run.films.add(new Film("Movie8", 2015, "Director H", false, "", ""));

        
        String input = "countall\ncountavailable\ncountrented\nreturn\n";
        Run.scanner = new Scanner(input);

        Count.count();

        // Expected output:
        // Currently listed films: 4
        // Currently available films: 2
        // Currently rented films: 2
    }
}
