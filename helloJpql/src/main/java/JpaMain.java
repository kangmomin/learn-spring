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
            String query = "select function('group_concat', m.name) From Member m "; // custom function
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
