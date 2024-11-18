/*
변수 : 설명
reservations : Map<String, Reservation> 타입, 예약 정보를 저장하는 맵. 예약 고유번호를 키로, Reservation 객체를 값으로 저장.
Name : JLabel, "이름"이라는 텍스트를 표시하는 레이블.
numPeople : JLabel, "인원"이라는 텍스트를 표시하는 레이블.
phoneNum : JLabel, "전화번호"라는 텍스트를 표시하는 레이블.
floor : JLabel, "층수"라는 텍스트를 표시하는 레이블.
NameField : JTextField, 사용자가 이름을 입력하는 텍스트 필드.
room : JLabel, "호수"라는 텍스트를 표시하는 레이블.
numPeopleField : JTextField, 사용자가 인원수를 입력하는 텍스트 필드.
phoneNumField : JTextField, 사용자가 전화번호를 입력하는 텍스트 필드.
floorCom : JComboBox<String>, 층수 선택을 위한 콤보박스 (1층, 2층, 3층).
roomCom : JComboBox<String>, 호수 선택을 위한 콤보박스 (각 층별 방 번호).
reserveRegister : JButton, 예약 등록 버튼.
uniqueID : JLabel, "고유번호"라는 텍스트를 표시하는 레이블.
uniqueIDField : JTextField, 고유번호를 입력하는 텍스트 필드.
updateReservation : JButton, 예약 수정 버튼.
checkReservationInfo : JButton, 예약 정보 확인 버튼.
jScrollPane1 : JScrollPane, 예약 정보를 출력할 텍스트 영역을 포함하는 스크롤 패널.
displayInfo : JTextArea, 예약 정보나 상태 메시지를 출력하는 텍스트 영역.
jButton2 : JButton, 예시로 추가된 버튼 (사용되지 않음).
메서드 관련 변수:
line : 예약 데이터를 읽어올 때 한 줄씩 읽어오는 변수.
data : 읽어온 한 줄을 쉼표로 구분하여 저장하는 배열.
name : 예약 등록 시 사용자가 입력한 이름.
numPeople : 예약 등록 시 사용자가 입력한 인원수.
phoneNum : 예약 등록 시 사용자가 입력한 전화번호.
floor : 예약 등록 시 사용자가 선택한 층수.
room : 예약 등록 시 사용자가 선택한 호수.
uniqueID : 예약 등록 시 생성된 고유번호.
*/