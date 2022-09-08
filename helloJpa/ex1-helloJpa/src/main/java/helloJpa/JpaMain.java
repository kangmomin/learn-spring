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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member1 member1 = new Member1();
            member1.setName("member1");
            // jpa는 주인 값만 보기에 team의 members는 수정을 해도 상관치 않는다.
            // 하지만 db에서 데이터를 새로 뽑지 않는 이상 team의 members에는 새로운 변경하상이 존재하지 않기에
            // 둘 다 추가해 주는 쪽을 추천한다.
            member1.setTeam(team);
            em.persist(member1);

//            team.getMembers().add(member1);

            Member1 findMember = em.find(Member1.class, member1.getId());
            List<Member1> members = findMember.getTeam().getMembers();
            for (Member1 m : members) {
                System.out.println("m.getName() = " + m.getName());
            }

            tx.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        // 서버상의 캐시 저장소.
//        // DB에서 값을 가져와 수정하거나 추가할 때 처음엔 Db에서 값을 들고와
//        // 등록하고 사용하고 추후엔 재활용한 후 마지막에 쿼리를 날린다.
//        // like) spring container
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try {
//            // jpa와 관련 없는 비영속 상태
//            Member member1 = new Member("A");
//            Member member2 = new Member("B");
//            // jpa와 관련 없는 비영속 상태
//
//            Member findMember = em.find(Member.class, 1L);
//            List<Member> result = em.createQuery("SELECT m FROM Member as m", Member.class)
//                    .setFirstResult(5) // 5번째부터
//                    .setMaxResults(8) // 8번째까지
//                    .getResultList(); // 결과값 도출
//            // jpa는 query중심 개발에서 벗어나
//            // 객체중심 개발을 하기 수월하게 만들어졌으며 메소드의 조합만으로 쿼리가 가능해진다
//
//            // 영속성
//            // em에 저장해서 한번에 주기에 트랜젝션만 같다면 ==값을 던져줌.
//            // commit전까진 쿼리를 보내지 않음.
//            em.persist(member1);
//            em.persist(member2);
//
//            // em에 저장된 값들의 쿼리를 날림.
//            // em에 모았다 한번에 보낸다.
//
//            // 아래 flush함수가 쿼리를 날리는 코드임.(
//            // 영속성 컨택스트를 비우지 않고 DB와 동기화하는 작업
////            em.flush();
//            tx.commit();
//        } catch (Exception e) {
//            System.out.println("e = " + e);
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//        emf.close();
    }
}