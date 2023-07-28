package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
       EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");

       EntityManager em = emf.createEntityManager();
        //code

        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {

            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush(); // 영속성 컴텍스트에 있는거 db에 다 날리기
            em.clear();  // 영속성 컨텍스트를 초기화 시키기

            Member findMember = em.find(Member.class, member.getId()); // find()로 1차 캐쉬에서 가져옴

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam  = " + findTeam.getName());

            tx.commit(); // commit 하는 시점에 db에 쿼리가 날라간다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
