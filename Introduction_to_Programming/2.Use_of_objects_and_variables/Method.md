# Method call

`object.methodName(parameters)`
- `object`는 호출 객체를 나타낸다.
- `methodName(...)`은 호출된 메서드이다.
- `parameters`는 메서드에 전달되는 매개변수이다.

> ex. `System.out.println("Hello!");`
> - System.out은 호출 객체
> - println(...)은 호출된 메서드
> - "Hello!"는 메서드에 전달된 매개변수
> - 결과를 반환하지는 않지만 System.out 객체에 대한 작업을 수행한다. -> 모니터에 출력


## 메서드의 반환 타입
- `"xxx".concat("yyy");`을 호출했다고 가정해보자.
- 반환 값은 무엇인가?: String `"xxxyyy"`를 반환한다.
- `concat()`의 반환 타입이 String이기 때문
  
> `System.out.println("xxx".concat("yyy").concat("zzz"));`를 생각해보기 !  
> 호출 개체를 나타내는 식은 왼쪽에서 오른쪽으로 수행

> `System.out.println("xxx".concat("yyy".concat("zzz")));`  
> 하위 표현식 -> 상위 표현식으로, 내부에서 외부로


## Static Method
- 메서드를 호출할 때 호출하는 객체가 필요하지 않은 메서드를 의미한다.
- 즉 호출 객체를 지정할 필요가 없고 매개변수만 지정하면 된다.
- 
- 