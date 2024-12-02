package deu.hms.checkIn;

public class Customer {
    private String name;
    private String reservationId;
    private String roomNumber;
    private int paymentAmount;
    private int numPeople; // 인원수
    private String phoneNumber; // 전화번호
    private String checkInDate; // 체크인 날짜
    private String checkOutDate; // 체크아웃 날짜
    private boolean isCheckedIn; // 체크인 여부
    private String paymentType; // 현장결제 or 선결제
    private String paymentMethod; // 카드 or 현금

    public Customer(String name, String reservationId, String roomNumber, int paymentAmount,
                    int numPeople, String phoneNumber, String checkInDate) {
        this.name = name;
        this.reservationId = reservationId;
        this.roomNumber = roomNumber;
        this.paymentAmount = paymentAmount;
        this.numPeople = numPeople;
        this.phoneNumber = phoneNumber;
        this.checkInDate = checkInDate;
        this.isCheckedIn = false;
    }
    
    //체크아웃 생성자(수정하고 싶으면 수정해도 됩니다) 위에 생성자만 안바뀌면 되용
    public Customer(String name, String reservationId, String roomNumber, int paymentAmount) {
    this(name, reservationId, roomNumber, paymentAmount, 0, "unknown", "unknown");
}

   // Getters and Setters
    public String getName() { return name; }
    public String getReservationId() { return reservationId; }
    public String getRoomNumber() { return roomNumber; }
    public int getPaymentAmount() { return paymentAmount; }
    public int getNumPeople() { return numPeople; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public boolean isCheckedIn() { return isCheckedIn; }
    public String getPaymentType() { return paymentType; }
    public String getPaymentMethod() { return paymentMethod; }

    public void setName(String name) { this.name = name; }
    public void setReservationId(String reservationId) { this.reservationId = reservationId; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setPaymentAmount(int paymentAmount) { this.paymentAmount = paymentAmount; }
    public void setNumPeople(int numPeople) { this.numPeople = numPeople; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }
    public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }
    public void setCheckedIn(boolean checkedIn) { isCheckedIn = checkedIn; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
