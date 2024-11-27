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
    private int numPeople; // 인원수
    private String phoneNumber; // 전화번호
    private String checkInDate; // 체크인 날짜
    private boolean isCheckedIn;

    public Customer(String name, String reservationId, String roomNumber, int paymentAmount, 
                    int numPeople, String phoneNumber, String checkInDate) {
        this.name = name;
        this.reservationId = reservationId;
        this.roomNumber = roomNumber;
        this.paymentAmount = paymentAmount;
        this.isCheckedIn = false;
        this.numPeople = numPeople;
        this.phoneNumber = phoneNumber;
        this.checkInDate = checkInDate;
    }
    
    // 새로운 생성자 추가: 필요한 매개변수만 입력
public Customer(String name, String reservationId, String roomNumber, int paymentAmount) {
    this(name, reservationId, roomNumber, paymentAmount, 1, "N/A", "N/A"); // 기본값 설정
}

    // Getters and Setters
    public String getName() { return name; }
    public String getReservationId() { return reservationId; }
    public String getRoomNumber() { return roomNumber; }
    public int getPaymentAmount() { return paymentAmount; }
    public boolean isCheckedIn() { return isCheckedIn; }
    public void setCheckedIn(boolean isCheckedIn) {this.isCheckedIn = isCheckedIn;  }
    public void checkIn() { this.isCheckedIn = true; }
    
    public int getNumPeople() { return numPeople; }
    public void setNumPeople(int numPeople) { this.numPeople = numPeople; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getCheckInDate() { return checkInDate; }
    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }

}