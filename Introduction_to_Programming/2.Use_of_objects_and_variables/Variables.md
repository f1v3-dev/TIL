
# 변수 (Variables)
변수는 객체에 대한 참조를 저장하는데 사용할 수 있는 메모리 위치를 나타낸다.
 

## 변수: 주요 속성(Main Properties)
1. 이름: 변수를 식별하는 기준
   - 문자 또는 '_'로 시작하는 일련의 문자, 숫자 또는 '_'문자
   - 소문자와 대문자는 다른 것으로 간주
   - 키워드 (class, public if, while ...)는 사용 불가
2. 타입
   - 변수가 저장할 수 있는 데이터 유형
   - String: 문자열에 대한 참조를 저장
   - int: 정수형에 대한 참조를 저장
3. 메모리 위치의 주소
   - 각 변수에는 연관된 메모리 위치가 존재
   - 메모리 위치의 크기는 변수 유형에 따라 다름.
   - Java에서 이 위치를 알 수있는 방법은 없다. (보안)
4. 값
   - 프로그램 실행 중 특정 시점에 변수가 나타난다.

## Variables & Shoe-boxes
1. 이름 name - label
2. 타입 type - form of the box
3. 주소 address - position in the cupboard
4. 값 value - shoe inside the box

## 변수 선언
- `type variableName;`과 같이 변수를 선언
- String name;

## 할당 (Assignment)
- `variableName = expression;`과 같이 값을 할당
- `name = "seungjo";`
- name 변수에는 `=` 오른쪽에 있는 표현식의 값이 할당되는 것을 알 수 있음.
- 초기화되지 않은 변수에는 정의된 값이 없어 할당될 때 까지 사용할 수 없음.

