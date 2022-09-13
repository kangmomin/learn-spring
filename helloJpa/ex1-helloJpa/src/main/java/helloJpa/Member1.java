package helloJpa;

import javax.persistence.*;

@Entity
public class Member1 extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

//    @Column(name = "team_id")
//    private Long teamId;

    // join으로 team 객체를 바로 갖다줌.
//    @ManyToOne(fetch = FetchType.LAZY) // LAZY == 프록시 객체 == 지연로딩 *강추* 테이블이 커질 수록 SQL 예측이 불가(JPQL사용시)
    @ManyToOne(fetch = FetchType.EAGER) // EAGER == 엔티티 객체 == 즉시로딩 *비추*
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
