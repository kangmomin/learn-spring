package helloJpa;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {
    @Id @Column(nullable = false)
    private Long id;

    @Column(name = "name")
    private String username;
    private Integer age;

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
