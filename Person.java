// Define a class named "Person"
public class Person {
    // Declare private instance variables for name, surname and email
    private String name;
    private String surname;
    private String email;

    // Define a getter method to retrieve the name
    public String getName() {
        return name;
    }

    // Define a setter method to set the name
    public void setName(String name) {
        this.name = name;
    }

    // Define a getter method to retrieve the surname
    public String getSurname() {
        return surname;
    }

    // Define a setter method to set the surname
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Define a getter method to retrieve the email
    public String getEmail() {
        return email;
    }

    // Define a setter method to set the email
    public void setEmail(String email) {
        this.email = email;
    }

    // Define a constructor to initialize the name, surname and email values
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
