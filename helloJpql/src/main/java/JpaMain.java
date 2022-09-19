import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);
            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setName("회원1");
            member1.setAge(20);
            member1.changeTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("회원2");
            member2.setAge(20);
            member2.changeTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setName("회원3");
            member3.setAge(20);
            member3.changeTeam(teamB);
            em.persist(member3);


            em.flush();
            em.clear();

//            String query = "select " +
//                    "case when m.age <= 10 then '학생요금'" +
//                    "   when m.age >= 60 then '경로요금'" +
//                    "   else '일반요금' END " +
//                    "from Member m";
//            String query = "select coalesce(m.name, '이름 없는 회원') from Member m"; // 값이 없다면
//            String queryNullif = "select nullif(m.name, '관리자') from Member m"; // 관리자라면 null로
            // m.name과 같은 경로 탐색이 더 이상 불가능한 상태를 상태필드라 함.

            // 묵시적 조인이 발생하는 갑들. 걍 쓰지 마삼.
            // m.team과 같이 묵시적 조인이 발생하고 그 하위로 더 많은 값을 읽을 수 있는 것을 단일 값 연관 경로라고 함.
            // 컬랙션 값의 경우 size를 제외한 값을 탐색할 수 없음
            // 페이징 API 사용 불가. 일대다 패치시 데이터 뻥튀기 때문
            // fetch join은 별칭을 줘선 안됨. 애초에 값을 모두 가져오는 것을 전제로 하기에 어떤 변수가 일어날지 모름.

            // 일반적으로 엔티티와 같은 결과를 도출하면 패치 아니면 DTO가 좋음.
            String query = "select t From Team t"; // custom function
            List<Member> resultList1 = em.createQuery(query, Member.class)
                    .getResultList();
            for (Member member : resultList1) {
                System.out.println("member = " + member.getName());
            }

            tx.commit();
        } catch (NonUniqueResultException e) {

            System.out.println("single result에 list가 발견됨");
            System.out.println("e = " + e);
        } catch (Exception e) {
            System.out.println("[error] with:"+e);
        } finally {
            em.close();
        }

        emf.close();
    }
}
