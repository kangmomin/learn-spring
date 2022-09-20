package jpql;

import javax.persistence.*;

@Entity

// 쿼리를 미리 정해두고 쓸 수 있음. 로딩 시점에 파싱을 하기 때문에 쿼리 에러를 잡기 좋다.
@NamedQuery(
        name = "findByUserName",
        query = "select m from Member m where m.name = :username"
)

public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType type;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
