package helloJpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {
    @Id //기본키 직접 할당
    @Column(nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
    // identity 는 PK를 얻기위해 DB에 값을 넣어봐야 하기에 persist를 한 순간 insert를 날린다.
    // 하지만 성능 파이가 심하진 않다.

    // sequance 는 다음 PK값만 DB에 요청읗 하게 된다.
    // 한번에 다음 한개의 값만을 요청하는게 아닌 다수의 값을 요청 및 수정할 수도 있다.
    private Long id;

    @Column(name = "name")
    private String username;
    private Integer age;

    public Member(String username) {
        this.username = username;
    }

    @Enumerated(EnumType.STRING) // EnumType.ORDINAL == Enum순서 저장, String == 이름 그대로 저장 << String 추천
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 둘다 date지만 최근 아래의 타입은 하이버네이트에서도 지원해줌.
    private LocalDateTime testLocalData;

    @Lob
    private String description;

    @Transient // 매핑 하지 않음.
    private String onlyMemory;


    public Member() {
    }
}
