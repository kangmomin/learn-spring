package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);


    List<Member> findListByUsername(String username); //이름 타입 설정(컬랙션)
    Member findMemberByUsername(String username); //이름 타입 설정(단건)
    Optional<Member> findOptionalByUsername(String username);

    // join을 같이 해서 count쿼리도 느려지는걸 방지
    @Query(countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);
    Slice<Member> findSliceByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    // 큰 차이를 내지 못함으로 테스트 후 적용
    // 영속성 컨텍스트에 원본값의 스냅샷으로 값의 변동을 체크하는데
    // 스냅샷을 값을 가져올 때 찍음. readOnly를 설정하면 해당 스냅샷을 생성치 않으며 성능 최적화를 해줌
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    List<Member> findAll();

    // 자세한 설명은 jpa책,,,
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);
}
