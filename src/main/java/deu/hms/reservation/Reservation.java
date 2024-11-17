package deu.hms.reservation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 예약 정보를 관리하는 클래스
public class Reservation {
    private String name; // 이름
    private int numberOfPeople; // 인원 수
    private String phoneNumber; // 전화번호
    private int floor; // 층수
    private int roomNumber; // 호수
    private String uniqueID; // 고유번호

    // 모든 예약을 저장할 Map
    private static Map<String, Reservation> reservations = new HashMap<>();

    // 생성자
    public Reservation(String name, int numberOfPeople, String phoneNumber, int floor, int roomNumber) {
        this.name = name;
        this.numberOfPeople = numberOfPeople;
        this.phoneNumber = phoneNumber;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.uniqueID = generateUniqueID(); // 고유번호 생성
    }

    // 고유번호 생성 메소드
    private String generateUniqueID() {
        Random rand = new Random();
        String id;
        do {
            int num = rand.nextInt(900) + 100; // 100 ~ 999
            id = String.valueOf(num);
        } while(reservations.containsKey(id));
        return id;
    }

    // 예약 저장 메소드
    public static String saveReservation(String name, int numberOfPeople, String phoneNumber, int floor, int roomNumber) {
        Reservation reservation = new Reservation(name, numberOfPeople, phoneNumber, floor, roomNumber);
        reservations.put(reservation.getUniqueID(), reservation);
        return reservation.getUniqueID();
    }

    // 예약 수정 메소드
    public static boolean modifyReservation(String uniqueID, String name, int numberOfPeople, String phoneNumber, int floor, int roomNumber) {
        if(reservations.containsKey(uniqueID)) {
            Reservation res = new Reservation(name, numberOfPeople, phoneNumber, floor, roomNumber);
            res.setUniqueID(uniqueID); // 기존 고유번호 유지
            reservations.put(uniqueID, res);
            return true;
        } else {
            return false;
        }
    }

    // 예약 정보 조회 메소드
    public static Reservation getReservationInfo(String uniqueID) {
        return reservations.get(uniqueID);
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getFloor() {
        return floor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }
}
