/** 
 * Author: Nicholas Browning (c3302779)
 * File Name: Seat.java
 */
public class Seat {
    /** 
     * The declared strings will be used in the method as a result of encapulation and inheritance.
     * The method will be linked via the hashset implemented in 'Theatre.java'.
     * The method will allow the seats to be added to a booking, edited and display.
     * 
     * However, as of a method called 'canBookMore' in 'Theatre.java'. Seats can only be booked if,
     * a certain existing username is implemented three times. The method would still be active and taking the arguments
     * from the file but is eventually halted.
    */
    private String username;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String bookingTime;

    /**
     * The following methods will allow either a string to complete a booking or display the booking information
     */

    /**
      * Displays the booking information (Username)
      */
    public String getUsername() {
        return username;
    }

    /**
     * Sets a string to attempt to complete the booking (Username)
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
      * Displays the booking information (Name)
      */
    public String getName() {
        return name;
    }

    /**
     * Sets a string to attempt to complete the booking (Name)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
      * Displays the booking information (Phone)
      */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets a string to attempt to complete the booking (Phone)
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
      * Displays the booking information (Address)
      */
    public String getAddress() {
        return address;
    }

    /**
     * Sets a string to attempt to complete the booking (Address)
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
      * Displays the booking information (Email)
      */
    public String getEmail() {
        return email;
    }

    /**
     * Sets a string to attempt to complete the booking (Email)
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
      * Displays the booking information (Booking Time)
      */
    public String getBookingTime() {
        return bookingTime;
    }

    /**
     * Sets a string to attempt to complete the booking (Booking Time)
     */
    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }
}