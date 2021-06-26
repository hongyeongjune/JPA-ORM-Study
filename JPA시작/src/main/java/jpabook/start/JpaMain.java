package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 - 생성
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpabook");

        // 엔티티 매니저 - 생성
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 트랜잭션 - 획득
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {

            // 트랜잭션 - 시작
            entityTransaction.begin();
            // 비즈니스 로직 - 실행
            logic(entityManager);
            // 트랜잭션 - 커밋
            entityTransaction.commit();
        } catch (Exception e) {
            // 트랜잭션 - 롤백
            entityTransaction.rollback();
        } finally {
            // 엔티티 매니저 - 종료
            entityManager.close();
        }

        // 엔티티 매니저 팩토리 - 종료
        entityManagerFactory.close();
    }

    private static void logic(EntityManager entityManager) {

        String id = "identity";
        Member member = new Member(id, "영준", 26);

        entityManager.persist(member);

        member.update(27);

        Member savedMember = entityManager.find(Member.class, id);
        System.out.println("savedMember : " + savedMember.getUsername() + ", age : " + savedMember.getAge());

        List<Member> memberList = entityManager.createQuery("select m from Member m", Member.class).getResultList();

        System.out.println("Member.size : " + memberList.size());

        entityManager.remove(member);
    }
}
