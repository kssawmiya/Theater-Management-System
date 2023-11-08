import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Theatre {
    static int[][] seats = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // row 1 with 12 seats
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // row 2 with 16 seats
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} // row 3 with 20 seats
    };

    // Declare an ArrayList to store the sold tickets
    static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public static void main(String[] args) {
        // Display the welcome message
        String text = "Welcome to the New Theatre";
        int textLength = text.length();
        String border = " ".repeat(15)+"=".repeat(textLength + 4); // Add 4 to account for the two spaces and two "=" characters on either side of the text
        System.out.println(border);
        System.out.println(" ".repeat(15)+"= " + text + " =");
        System.out.println(border);

        // Create a Scanner object to get input from the user
        Scanner scanner = new Scanner(System.in);
        int choice = -1; // if it is not initiated to -1 do while loop will fail

        do {
            // Display the menu options
            System.out.println(" ".repeat(15)+"1) Buy a ticket");
            System.out.println(" ".repeat(15)+"2) Print seating area");
            System.out.println(" ".repeat(15)+"3) Cancel ticket");
            System.out.println(" ".repeat(15)+"4) List available seats");
            System.out.println(" ".repeat(15)+"5) Save to file");
            System.out.println(" ".repeat(15)+"6) Load from file");
            System.out.println(" ".repeat(15)+"7) Print ticket information and total price");
            System.out.println(" ".repeat(15)+"8) Sort tickets by price");
            System.out.println(" ".repeat(15)+"0) Quit");

            System.out.println("=".repeat(80));
            System.out.print("Please enter your choice: ");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Handle the case where the user inputs letters instead of a number
                System.out.println("Invalid input. Please try again.");
                scanner.next(); // Clear the scanner's input buffer
                continue;
            }

            switch (choice) {
                case 1:
                    // Call the buy_ticket() method
                    buy_ticket();
                    System.out.println("=".repeat(80));
                    break;
                case 2:
                    // Call the print_seating_area() method
                    print_seating_area();
                    System.out.println("=".repeat(80));
                    break;
                case 3:
                    // Call the cancel_ticket() method
                    cancel_ticket();
                    System.out.println("=".repeat(80));
                    break;
                case 4:
                    // Call the show_available() method
                    show_available();
                    System.out.println("=".repeat(80));
                    break;
                case 5:
                    // Call the save() method
                    save();
                    System.out.println("=".repeat(80));
                    break;
                case 6:
                    // call the load() method
                    load();
                    System.out.println("=".repeat(80));
                    break;
                case 7:
                    // call the show_tickets_info() method
                    show_tickets_info();
                    System.out.println("=".repeat(80));
                    break;
                case 8:
                    // call the sort_tickets() method
                    sort_tickets();
                    System.out.println("=".repeat(80));
                    break;
                case 0:
                    // When user choose to exit by pressing 0
                    System.out.println("Thank you for using the Theatre system. Goodbye!");
                    System.out.println("=".repeat(80));
                    break;
                default:
                    // user enters an invalid option
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("=".repeat(80));
                    break;
            }

        } while (choice != 0); // continue the loop until choice==0

        scanner.close();//close the scanner object
    }

    public static boolean isValidEmail(String email) {

        // Define a regular expression pattern to match against email addresses
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Compile the regular expression pattern into a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object to match the email parameter against the pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, false otherwise
        return matcher.matches();

    }

    public static void buy_ticket() {
        // Creating a new scanner object to read input from user
        Scanner scanner = new Scanner(System.in);

        // Print ticket prices before asking for row number
        System.out.println("Ticket Prices:");
        System.out.println("1st row: $60");
        System.out.println("2nd row: $40");
        System.out.println("3rd row: $20");

        // Prompting the user to enter the row number
        System.out.print("Enter the row number (1-3): ");
        while (!scanner.hasNextInt()) {  // keep prompting until a valid integer is entered
            System.out.println("Please enter a valid integer.");
            scanner.next();  // discard the invalid input
        }
        int row = scanner.nextInt();

        // Checking if the entered row number is valid or not. If it is not valid then returning from the method.
        if (row < 1 || row > 3) {
            System.out.println("Invalid row number. Please try again.");
            return;
        }
        // Initializing maxSeats and price variables based on the row number entered by user.
        int maxSeats = 0;
        double price = 0.0;
        if (row == 1) {
            maxSeats = 12;
            price = 60.0;
        } else if (row == 2) {
            maxSeats = 16;
            price = 40.0;
        } else {
            maxSeats = 20;
            price = 20.0;
        }

        // Prompting the user to enter the seat number
        System.out.print("Enter the seat number (1-" + maxSeats + "): ");
        while (!scanner.hasNextInt()) {  // keep prompting until a valid integer is entered
            System.out.println("Please enter a valid integer.");
            scanner.next();  // discard the invalid input
        }
        int seat = scanner.nextInt();

        // Checking if the entered seat number is valid or not. If it is not valid then returning from the method.
        if (seat < 1 || seat > maxSeats) {
            System.out.println("Invalid seat number. Please try again.");
            return;
        }

        // Checking if the selected seat is already booked or not.
        // If it is already booked then returning from the method.
        if (seats[row-1][seat-1] == 1) {
            System.out.println("Sorry, that seat is already taken.");
            return;
        }


        // Prompting the user to enter the name, surname and email
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter surname: ");
        String surname = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();

        // Validating the email address entered by the user. If it is invalid then returning from the method.
        if (!isValidEmail(email)){
            System.out.println("Invalid email address");
            return;
        }
        // Creating a new person object using the name, surname and email entered by the user.
        Person person = new Person(name, surname, email);

        // Creating a new ticket object using the row, seat, price and person object created above.
        Ticket ticket = new Ticket(row, seat, price, person);

        // Adding the ticket to the list of tickets
        tickets.add(ticket);

        // Marking the selected seat as booked.
        seats[row-1][seat-1] = 1;
        System.out.println("Seat " + seat + " in row " + row + " has been successfully booked.");
    }
    public static void print_seating_area() {
        System.out.println("                   ***********");
        System.out.println("                   *  STAGE  *");
        System.out.println("                   ***********");

        for (int i = 0; i < seats.length; i++) {
//            System.out.print(" ");
            for (int j = 0; j < (seats.length - i)*2; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j < seats[i].length; j++) {
                if (j==seats[i].length/2){
                    System.out.print("  ");
                }
                if (seats[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
    // This method allows the user to cancel a ticket by specifying the row and seat number
    public static void cancel_ticket() {
        // Create a new Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user to enter the row number and seat number of the ticket to be canceled
        System.out.println("Enter row number:");
        while (!scanner.hasNextInt()) {  // keep prompting until a valid integer is entered
            System.out.println("Please enter a valid integer.");
            scanner.next();  // discard the invalid input
        }
        int row = scanner.nextInt();

        System.out.println("Enter seat number:");
        while (!scanner.hasNextInt()) {  // keep prompting until a valid integer is entered
            System.out.println("Please enter a valid integer.");
            scanner.next();  // discard the invalid input
        }

        int seat = scanner.nextInt();

        // Check if the row and seat numbers are within range
        if (row < 1 || row > seats.length || seat < 1 || seat > seats[row-1].length) {
            System.out.println("Invalid row or seat number.");
            return;
        }

        // Check if the seat is already available (not sold)
        if (seats[row-1][seat-1] == 0) {
            System.out.println("This seat is already available.");
            return;
        }

        // Cancel the ticket (make the seat available again)
        seats[row-1][seat-1] = 0;
        System.out.println("Ticket canceled for row " + row + ", seat " + seat);

        // Loop through all the tickets in the "tickets" ArrayList
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            // If the current ticket matches the row and seat number entered by the user, remove it from the ArrayList
            if (ticket.getRow() == row && ticket.getSeat() == seat) {
                tickets.remove(i);
                System.out.println("The ticket has been removed from the system.");
                break;
            }
        }
    }

    // This method displays the available seats in the seating arrangement stored in the "seats" 2D array
    public static void show_available() {
        // Loop through all the rows in the "seats" array
        for (int i = 0; i < seats.length; i++) {
            // Print the row number and a message indicating the available seats in that row
            System.out.print("Seats available in row " + (i+1) + ": ");

            // Loop through all the seats in the current row
            for (int j = 0; j < seats[i].length; j++) {
                // If the current seat is available (marked as 0), print its seat number
                if (seats[i][j] == 0) {
                    System.out.print((j+1) + ", ");
                }
            }

            // Print a newline character to move to the next row
            System.out.println();
        }
    }

    // This method saves the current state of the "seats" array to a file named "seats.txt"
    public static void save() {
        try {
            // Create a new FileWriter object with the file name "seats.txt"
            FileWriter writer = new FileWriter("seats.txt");

            // Loop through each element in the "seats" array and write it to the file as a string
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    writer.write(Integer.toString(seats[i][j]));

                    // If this is not the last element in the row, add a comma to separate it from the next element
                    if (j != seats[i].length - 1) {
                        writer.write(",");
                    }
                }

                // After writing all the elements in the row, add a newline character to start a new row
                writer.write("\n");
            }

            // Close the FileWriter object to free up system resources
            writer.close();

            // Print a message indicating that the seats were successfully saved to the file
            System.out.println("Seats saved to file.");
        } catch (IOException e) {
            // If an error occurs while saving the seats, print an error message and a stack trace
            System.out.println("An error occurred while saving seats.");
            e.printStackTrace();
        }
    }

    // This method loads the state of the "seats" array from a file named "seats.txt"
    public static void load() {
        try {
            // Create a new File object with the file name "seats.txt"
            File file = new File("seats.txt");

            // Create a new Scanner object to read data from the file
            Scanner scanner = new Scanner(file);

            // Initialize a counter for the current row in the "seats" array
            int row = 0;

            // Loop through each line of the file and parse the data as integers to fill in the "seats" array
            while (scanner.hasNextLine() && row < seats.length) {
                // Read the line and split it by commas to get an array of strings representing each seat in the row
                String[] data = scanner.nextLine().split(",");

                // Loop through the array of strings and parse each element as an integer to fill in the "seats" array
                for (int i = 0; i < seats[row].length; i++) {
                    seats[row][i] = Integer.parseInt(data[i]);
                }

                // Increment the row counter to move to the next row in the "seats" array
                row++;
            }

            // Close the Scanner object to free up system resources
            scanner.close();

            // Print a message indicating that the seats data was loaded successfully
            System.out.println("Seats data loaded successfully.");
        } catch (FileNotFoundException e) {
            // If the file is not found, print an error message indicating that it was not found
            System.out.println("File not found.");
        } catch (Exception e) {
            // If an error occurs while loading the seats data, print an error message and the stack trace
            System.out.println("Error loading seats data: " + e.getMessage());
        }
    }

    // This method displays the details of all tickets stored in the "tickets" ArrayList
    public static void show_tickets_info() {
        // Initialize the total price of all tickets to 0.0
        double total_price = 0.0;

        // Print the heading for the tickets information
        System.out.println("Tickets information:");

        // Loop through all the tickets in the "tickets" ArrayList
        for (Ticket ticket : tickets) {
            // Print the details of the current ticket
            ticket.print();

            // Add the price of the current ticket to the total price of all tickets
            total_price += ticket.getPrice();

            // Print a separator line between tickets
            System.out.println("===================================");
        }

        // Print the total price of all tickets to 2 decimal places
        System.out.printf("Total price of all tickets: £%.2f\n", total_price);
    }

    /**
     * Sorts a list of tickets by price in ascending order using selection sort algorithm
     */
    public static void sort_tickets() {
        // create a new list with the same elements as the original list of tickets
        List<Ticket> sortedTickets = new ArrayList<>(tickets);

        // iterate through the list and compare each ticket's price with the prices of the following tickets
        for (int i = 0; i < sortedTickets.size() - 1; i++) {
            for (int j = i + 1; j < sortedTickets.size(); j++) {
                // if a ticket's price is greater than the price of the following ticket, swap their positions
                if (sortedTickets.get(i).getPrice() > sortedTickets.get(j).getPrice()) {
                    Ticket temp = sortedTickets.get(i);
                    sortedTickets.set(i, sortedTickets.get(j));
                    sortedTickets.set(j, temp);
                }
            }
        }

        // print the sorted list of tickets
        System.out.println("Sorted tickets by price:");
        for (Ticket t : sortedTickets) {
            t.print();
            System.out.println("-".repeat(40));
        }
    }






}
