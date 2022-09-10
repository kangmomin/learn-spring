package helloJpa;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) //main table을 extends하는 subtable을 생성하는 JOIN 전략
// 테이블의 정규화, 팔요한 데이터만 요구할 수도 있음, 저장공간이 효율적임. 조인의 사용으로 성능 저하, 조회 쿼리가 복잡
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //main table에 다 넣고 Dtype으로 구분하는 단일 테이블 전략 => 성능적 이점
// 조회가 단순하고 성능이 빠름. 자식 엔티티의 컬림은 NULLABLE컬럼이어야 하고 테이블이 커질 수 있어 상황에 따라 성능 저하가 있을 수 있음.
// 단순하고 확장 가능성이 적을떄 쓰면 좋음.
@DiscriminatorColumn //Dtype담당 어노테이션(자식 테이블의 이름[타입]을 적어준다)
//public class Item { // abstract를 주면 Item테이블이 따로 생성되지 않음
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
