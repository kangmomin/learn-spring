# Todo

**Transaction**
* transactional = 데이터의 저장이 일어나는 곳(주로 service)에 사용

**Autowired**

**영속성 컨텍스트와 em, emf의 차이**

**thread**

**다형성**

**영속성 전의**

**OneToMany ManyToOne == 사용처 기준To-**
- ToOne관계는 모두 lazy설정
- FK를 가진 쪽에서 Join

**준영속 상태**
- 영속 상태에서 벗어난 엔티티이나 고유 식별자(PK)가 존재함.

**stream, map, collect, etc**
> ### stream
  List와 비슷하지만 List완 달리 stream자체에서 루프를 돌리며 모든 값에 변화를 줄 수 있다.
  ex) List.foreach... for function -> stream.function
  paralleStream을 통해 다른 성정 없이도 병렬화가 가능함(성능 좋음)
  filter, sorted, map, collect, etc는 stream에서 제공하는 함수
  
> ### map
  map에 들어있는 메소드를 각 요소마다 실행한 후 저장한다.
 
> ### collect
  우리가 원하는 대로 자료형 변환이 가능함.

**api 최적화**
-
- 엔티티 조회 -> DTO변환, 컬랙션 = batch_fech_size로 해결
- DTO 직접 조회 -> 성능은 약간 나음, v5 ㅊㅊ

**OSIV**
-
- Open - Session - In - View - ON
- 간단히 DB커넥션을 transactional에서 가져와 res가 나갈떄까지 들고 있는다
- 이로인해 lazy로딩 등을 어디서든 사용은 가능하다
- 하지만 너무 오래 갖고 있기에 커넥션이 부족해지는 현상 = 커넥션이 마를 수도 있다
---------
- Open - Session - In - View - OFF
- transactional에서 시작해 해당 로직이 끝나면 커넥션을 돌려줌.
- 이 상태면 지연로딩을 트랙잭션 내에서 해결해야하기에 강제 호출을 해두어야 한다.

이럴땐 그냥 Service를 다시 만들든 해서 readOnly인 트랜잭션 안에서 돌아가게 한다.
그 상태에서 DTO로 변환하는 것. 커넥션을 많이 쓴다면 ON(ex: ADMIN) 많이 쓴다면 OFF(ex: Member)

**NoArgumentConstructure**
**groupBy, having**