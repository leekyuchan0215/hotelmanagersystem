/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.hms.checkIn;

public class Customer {
    private String name;
    private String reservationId;
    private String roomNumber;
    private int paymentAmount;
    private boolean isCheckedIn;

    public Customer(String name, String reservationId, String roomNumber, int paymentAmount) {
        this.name = name;
        this.reservationId = reservationId;
        this.roomNumber = roomNumber;
        this.paymentAmount = paymentAmount;
        this.isCheckedIn = false;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getReservationId() { return reservationId; }
    public String getRoomNumber() { return roomNumber; }
    public int getPaymentAmount() { return paymentAmount; }
    public boolean isCheckedIn() { return isCheckedIn; }

    public void checkIn() { this.isCheckedIn = true; }
}