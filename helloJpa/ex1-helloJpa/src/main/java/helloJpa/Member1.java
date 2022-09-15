package helloJpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member1 {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Embedded
    private Period workPeriod;

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    @Embedded
    private Address homeAddress;

    // 값 타입 컬랙션. 값 타입을 다수 저장할 때 사용
    // 값 타입 컬랙션은 엔티티를 따로 만들지 않아도 해당 코드들 만드로 돌아간다.
    // member1객체와 같은 라이프 사이클. 즉 cascade all에 고아객체 삭제 기능을 갖추고 있다.
    // 값타입 컬랙션은 기본적으로 테이블을 나누기에 지연 로딩 전략을 사용한다.

    // 값타입은 고유 식별자가 없어 수정만 일어나도 부모의 자식요소를 다 지우고 다시 채워버림
    // 그 대안으로 엔티티를 직접 만들고 일대다 단방향 매핑후에 cascade와 고아객체삭제 기능을 넣어 해결하기도 함. *이 방법을 많이 씀.
    // 그럼 값타입 컬랙션은 언제 쓰나? 추적 필요도 없고 값이 바뀌어도 큰 문제가 안될 때 그만큼 단술할 때나 쓴다.
    @ElementCollection
    @CollectionTable(name = "favorite_food", joinColumns =
        @JoinColumn(name = "memeber_id")
    )
    @Column(name = "food_name")
    private Set<String> favorite = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "address_history", joinColumns =
            @JoinColumn(name = "member_id")
    )
    private List<Address> addressHistory = new ArrayList<>();


    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

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

    public Set<String> getFavorite() {
        return favorite;
    }

    public void setFavorite(Set<String> favorite) {
        this.favorite = favorite;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
