package helloJpa;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    public List<Member1> getMembers() {
        return members;
    }

    public void setMembers(List<Member1> members) {
        this.members = members;
    }

    // 객체에선 양방향 매핑이 불가능하기에 단방향 2개로 쓰는데
    // 이 때 값의 수정이 이뤄질 때 양쪽중 어느 곳에서 수정해야 할지 알 수 없다.
    // 해서 mappedBy가 없는곳이 주인이 돼서 mappedBy를 관리한다.
    // (주인은 FK를 가진 객체로 하는걸 추천함.)
    @OneToMany(mappedBy = "team")
    private List<Member1> members = new ArrayList<>();

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
