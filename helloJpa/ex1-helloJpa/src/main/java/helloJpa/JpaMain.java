package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 서버상의 캐시 저장소.
        // DB에서 값을 가져와 수정하거나 추가할 때 처음엔 Db에서 값을 들고와
        // 등록하고 사용하고 추후엔 재활용한 후 마지막에 쿼리를 날린다.
        // like) spring container
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // jpa와 관련 없는 비영속 상태
            Member member = new Member();
            member.setId(1L);
            member.setName("hello");
            // jpa와 관련 없는 비영속 상태

            Member findMember = em.find(Member.class, 1L);
            List<Member> result = em.createQuery("SELECT m FROM Member as m", Member.class)
                    .setFirstResult(5) // 5번째부터
                    .setMaxResults(8) // 8번째까지
                    .getResultList(); // 결과값 도출
            // jpa는 query중심 개발에서 벗어나
            // 객체중심 개발을 하기 수월하게 만들어졌으며 메소드의 조합만으로 쿼리가 가능해진다

            // 영속성
            // em에 저장해서 한번에 주기에 트랜젝션만 같다면 ==값을 던져줌.
            // commit전까진 쿼리를 보내지 않음.
            em.persist(member);

            // em에 저장된 값들의 쿼리를 날림.
            // em에 모았다 한번에 보낸다.
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
