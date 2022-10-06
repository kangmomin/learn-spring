# Todo

**Transaction**
* transactional = 데이터의 저장이 일어나는 곳(주로 service)에 사용

**Autowired**

**영속성 컨텍스트와 em, emf의 차이**

**thread**

**다형성**

**영속성 전의**

**OneToMany ManyToOne == 사용처 기준To-**

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
