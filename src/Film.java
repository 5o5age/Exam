
public class Film {
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

