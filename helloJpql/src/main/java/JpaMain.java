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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName(null);
            member.setAge(20);
            member.changeTeam(team);

            em.persist(member);

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
            String query = "select m.name From Member m "; // custom function
            List<String> resultList = em.createQuery(query, String.class).getResultList();
            for (String s : resultList) {
                System.out.println("s = " + s);
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
