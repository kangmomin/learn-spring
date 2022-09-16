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
            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            TypedQuery<Member> query = em.createQuery("select m from Member as m where m.name = :username", Member.class); // 반황 타입이 명확할 때
            query.setParameter("username", "member1");

            Query query1 = em.createQuery("select m.name, m.age from Member as m"); // 반환 타입이 명확하지 않을 때

            List<Member> resultList = query.getResultList();
            System.out.println("resultList = " + resultList);

            tx.commit();
        } catch (NonUniqueResultException e) {

            System.out.println("single result에 list가 발견됨");
            System.out.println("e = " + e);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }

        emf.close();
    }
}
