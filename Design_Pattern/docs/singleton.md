
# 싱글톤(Singleton) 패턴
> 인스턴스를 오직 한개만 제공하는 클래스.

- 인스턴스를 오직 1개만 만들어서 제공하는 클래스가 필요

### 언제 사용하는가?
- 만약 게임의 설정화면이 여러 개가 존재한다면?..

### 특징
- 싱글톤 패턴은 new를 사용하지 않는다!
- private 생성자를 통해 생성을 못하게 막아버린다.
- 생성을 하지 않으므로 글로벌하게 접근할 수 있는 방법을 제공해야 한다.

### 문제점
- 멀티스레드 환경에서 싱글톤이 안전한가? -> 안전하지 않다..
- 멀티스레드에 안전하게 싱글톤을 만들어야 한다.



## 왜 Thread-safe하지 않을까?
![thread.png](thread.png)
- 위의 if문에 각각 다른 스레드가 들어갈 수 있다
- 따라서 Thread-safe하게 만들어야 한다.

### 해결 방법
1. `synchronized`
- `synchronized`를 사용해서 한 번에 한 스레드만 들어오게 할 수도 있다.
- 하지만 위의 방법은 lock을 사용하기 때문에 성능의 문제가 발생

![synchronized.png](synchronized.png)

2. `이른 초기화(eager initialization)`
- 이 방법도 thread-safe 하다.
- 하지만 미리 생성하는데 비용이 적다거나, 나중에 사용하지 않을 경우엔 쓸모가 없다

![eager_init.png](eager_init.png)
   
3. `double checked locking`
- jdk 1.5부터 동작

- ![double_checked_locking.png](double_checked_locking.png)

4. `static inner 클래스`
- **권장하는 방법!**
- lazy loading이 가능해짐