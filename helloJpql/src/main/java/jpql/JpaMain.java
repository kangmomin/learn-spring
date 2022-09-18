package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            for (int i = 0; i < 10; i++) {
                Team team = new Team();
                team.setName("team" + i);
                em.persist(team);

                Member member = new Member();
                member.setName("member" + i);
                member.setAge(i);
                member.changeTeam(team);

                em.persist(member);
            }

            em.flush();
            em.clear();

            String query = "select m.name, 'HELLO', TRUE from Member m" +
                    " where m.type = :userType";
            List result = em.createQuery(query)
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList();
            for (Object o : result) {
                System.out.println("o = " + o);
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
