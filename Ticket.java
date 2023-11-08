// Define a class named "Ticket"
public class Ticket {
    // Declare private instance variables for row, seat, price and person
    private int row;
    private int seat;
    private double price;
    private Person person;

    // Define a constructor to initialize the row, seat, price and person values
    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Define getter and setter methods for row
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    // Define getter and setter methods for seat
    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    // Define getter and setter methods for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Define getter and setter methods for person
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // Define a method to print the ticket information
    public void print() {
        System.out.println("Ticket information:");
        System.out.println("Person name: " + this.person.getName());
        System.out.println("Person surname: " + this.person.getSurname());
        System.out.println("Person email: " + this.person.getEmail());
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + price);
    }
}
