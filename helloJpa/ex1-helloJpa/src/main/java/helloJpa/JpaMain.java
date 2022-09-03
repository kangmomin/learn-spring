package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("hello");

            Member findMember = em.find(Member.class, 1L);
            List<Member> result = em.createQuery("SELECT m FROM Member as m", Member.class)
                    .setFirstResult(5) // 5번째부터
                    .setMaxResults(8) // 8번째까지
                    .getResultList(); // 결과값 도출
            // jpa는 query중심 개발에서 벗어나
            // 객체중심 개발을 하기 수월하게 만들어졌으며 메소드의 조합만으로 쿼리가 가능해진다

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
