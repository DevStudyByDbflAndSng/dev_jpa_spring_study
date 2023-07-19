- @Entity : JPA가 관리할 객체
- @Id : 데이터베이스 PK와 매핑

### 특징
JPA의 모든 변경은 트랜잭션 안에서 실행
엔티티 매니저는 쓰레드간에 공유X  
엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유함

### 엔티티 매니저 생성 과정
```
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
  
}
```
1. 엔티티 매니저 설정
	1. 엔티티 매니저 팩토리는 앱 전체에서 딱 한 번만 생성하고 공유해서 사용함.
2. 트랜잭션 관리
	1. 항상 트랜잭션 안에서 데이터를 변경해야 한다. 트랜잭션 없이 데이터를 변경하면 예외가 발생함.
3. 비즈니스 로직

#### JPQL
JPA를 사용하면 엔티티 객체를 중심으로 개발, 데이터베이스에 관한 처리는 JPA에게 맡겨야 함
애플리케이션이 필요한 데이터만 데이터베이스에서 불러오려면 결국 검색 조건이 포함된 SQL을 사용해야 한다.
**JPA 는 JPQL이라는 쿼리언어로 해결**
- JPQL은 엔티티 객체를 대상으로 쿼리함.
- SQL은 데이터베이스 테이블을 대상으로 쿼리함.
