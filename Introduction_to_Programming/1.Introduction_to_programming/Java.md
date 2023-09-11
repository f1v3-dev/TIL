# Java

자바는 `imperative programming paradigm`, `Functional programming paradigm`을 지원하는 현대적인 고급 `object-oriented programming Language`이다.

## 자바의 특징
- 단순하다.
- 동일한 프로그램이 OS에 상관없이 동작한다.
- 매우 풍부한 라이브러리를 사용할 수 있다.
- 웹 개발에 사용할 수 있다.
- Java Virtual Machine 기반
- Safety (JVM은 인터넷을 통해 실행되는 애플리케이션에 대한 원치 않는 접근을 금지함.)

## The first Java program

```java
import java.lang.*;

public class First {
    public static void main(String[] args) {
        System.out.println("This is my first Java program");
    }
}
```
- `import java.lang.*;`: 미리 정의된 클래스/프로그램의 라이브러리 호출 (java.lang 라이브러리는 자동으로 import 된다.) 
- `public class First {...}`: First라는 클래스 정의
- `public static void main(String[] args) {...}`: 기본 메서드 정의 (메서드는 Java에서 작업을 구현한 것을 의미한다.)
- `System.out.println("This is my first java program")`: 모니터에 메시지를 출력하는 명령문
- `println`: System.out 객체에 적용된 PrintStream 클래스의 메서드 
- `"This is my first java program"`: 표시할 문장을 나타내는 String class의 객체

## Second program

```java
public class Second {
    public static void main(String[] args) {
        System.out.println("This is my second java program...");
        System.out.println("... and it will not be my last one.");
    }
}

// 두 명령문의 순서는 프로그램에 나타나는 순서대로 실행된다.
```