package helloJpa;

import javax.persistence.*;

@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "locker_id")
    private Long id;

    @Column(name = "locker_name")
    private String name;

    // 1:1 일때 주인만이 자신의 table을 수정할 수 있기에
    // 수정할 곳을 주인으로 설정한다.
    @OneToOne(mappedBy = "locker")
    private Member1 member;
}
