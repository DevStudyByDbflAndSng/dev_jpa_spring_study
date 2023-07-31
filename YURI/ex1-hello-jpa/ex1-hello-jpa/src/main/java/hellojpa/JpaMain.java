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
           //  team.getMembers().add(member); // member를 집어 넣어 연관관계 맺음.
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); // ***** 이 코드를 넣어줘야 한다. 연관관계 주인인 곳에만 값을 넣어 줘야 한다.
            em.persist(member);

            // ***** 이 코드를 넣어 줘야 한다. 문제 발생 안 넣을시. (2가지 문제)
            // team.getMembers().add(member); // member를 집어 넣어 연관관계 맺음. // mappedBy로  되어있어서 읽기 전용이라 jpa에서 업데이트할 때 이 값을 안쓴다.

            em.flush(); // 영속성 컴텍스트에 있는거 db에 다 날리기
            em.clear();  // 영속성 컨텍스트를 초기화 시키기


            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
            List<Member> members = findTeam.getMembers();

            System.out.println("==============");
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("==============");


            tx.commit(); // commit 하는 시점에 db에 쿼리가 날라간다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
