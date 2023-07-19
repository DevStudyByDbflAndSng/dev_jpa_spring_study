package hellojpa;

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
           List<Member> result =  em.createQuery("select m from Member as m", Member.class).getResultList();
           // 쿼리 대상이 컬럼명이 아닌 객체를 대상으로 함.

          for (Member member : result ) {
             System.out.println("name " + member.getName());
          }

          tx.commit();
       } catch (Exception e) {
          tx.rollback();
       } finally {
         em.close();
       }
       emf.close();
    }

    private static void logic(EntityManager em) {
        Long id = 1L;
        Member member = new Member();
        member.setId(id);
        member.setName("지한");
        member.setAge(2);

        em.persist(member);
        member.setAge(23); // 어떤 엔티티가 변경되었는지 추적하는 기능을 갖춤.

        Member findMember = em.find(Member.class, id);
        System.out.println(findMember.getName() + " " + findMember.getAge());

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println(members.size());

        em.remove(member);
    }
}
