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
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.name, m.age) from Member m", MemberDTO.class)
                    .getResultList(); // DTO를 활용한 스칼라 타입 프로젝션 처리

            MemberDTO memberDTO = resultList.get(0);
            System.out.println("memberDTO.getUsername = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge = " + memberDTO.getAge());

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
