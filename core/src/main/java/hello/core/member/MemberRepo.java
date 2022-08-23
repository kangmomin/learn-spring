package hello.core.member;

public interface MemberRepo {
    void save(Member member);
    Member findById(Long memberId);
}
