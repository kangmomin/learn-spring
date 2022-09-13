package helloJpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "parent_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL) // 영속성 전이.
    // 데이터는 영속성 컨텍스트에 저장되는데 이 때 데이터들을 따로 저장하기보단
    // 가장 상귀 객체에 전이 함으로써 한번에 저장될 수 있도록 한다
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }

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
}
