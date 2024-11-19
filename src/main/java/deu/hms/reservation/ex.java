/*
reservations      : Map<String, Reservation> // 예약 정보를 저장하는 맵. 고유번호를 키로, Reservation 객체를 값으로 사용.



Name              : JLabel        // "이름" 텍스트를 표시하는 레이블.
numPeople         : JLabel        // "인원" 텍스트를 표시하는 레이블.
phoneNum          : JLabel        // "전화번호" 텍스트를 표시하는 레이블.
floor             : JLabel        // "층수" 텍스트를 표시하는 레이블.
room              : JLabel        // "호수" 텍스트를 표시하는 레이블.
uniqueID          : JLabel        // "고유번호" 텍스트를 표시하는 레이블.

NameField         : JTextField    // 사용자가 이름을 입력하는 텍스트 필드.
numPeopleField    : JTextField    // 사용자가 인원수를 입력하는 텍스트 필드.
phoneNumField     : JTextField    // 사용자가 전화번호를 입력하는 텍스트 필드.
uniqueIDField     : JTextField    // 사용자가 고유번호를 입력하는 텍스트 필드.

floorCom          : JComboBox<String> // 층수 선택 콤보박스 (1층, 2층, 3층).
roomCom           : JComboBox<String> // 호수 선택 콤보박스 (각 층별 방 번호).

reserveRegister   : JButton       // 예약 등록 버튼.
updateReservation : JButton       // 예약 수정 버튼.
checkReservationInfo : JButton    // 예약 정보 확인 버튼.
jButton2          : JButton       // 예시로 추가된 버튼 (사용되지 않음).

jScrollPane1      : JScrollPane   // 예약 정보를 출력할 텍스트 영역을 포함하는 스크롤 패널.
displayInfo       : JTextArea     // 예약 정보나 상태 메시지를 출력하는 텍스트 영역.



line              : String        // 예약 데이터를 파일에서 읽을 때 한 줄씩 저장하는 변수.
data              : String[]      // 파일에서 읽어온 데이터를 쉼표로 구분하여 저장하는 배열.
name              : String        // 예약 등록 시 사용자가 입력한 이름.
numPeople         : int           // 예약 등록 시 사용자가 입력한 인원수.
phoneNum          : String        // 예약 등록 시 사용자가 입력한 전화번호.
floor             : int           // 예약 등록 시 사용자가 선택한 층수.
room              : int           // 예약 등록 시 사용자가 선택한 호수.
uniqueID          : String        // 예약 등록 시 생성된 고유번호.

*/