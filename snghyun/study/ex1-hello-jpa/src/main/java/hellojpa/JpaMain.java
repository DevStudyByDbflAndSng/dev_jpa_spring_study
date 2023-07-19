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
//          Member member = new Member();
//          member.setId(1L);
//          member.setName("HelloA");
//          em.persist(member);
//          em.remove(1L);
//          Member findMember = em.find(Member.class, 1L);
//          findMember.setName("BYE1");
          // JPA의 모든 변경은 트랜잭션 안에서 실행
          // 엔티티 매니저는 쓰레드간에 공유X
          // 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유함
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
}
