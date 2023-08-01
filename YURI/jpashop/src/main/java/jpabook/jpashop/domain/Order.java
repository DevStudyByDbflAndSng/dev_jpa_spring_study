package jpabook.jpashop.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS") // 테이블명이 order 안되는 곳이 있기에 orders로 함
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

//    @Column(name = "MEMBER_ID")  // 이 방식의 설계는 사실, 객체지향스럽지 않다.  이런 설계는 관계형 db에 맞춘 설계이다.
//    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") // 외래 키를 매핑할 때 사용 . 단방향 매핑이 좋은 것임. // JoinColumn 외래키를 지정했기 때문에 연관관계 주인임.***
    private Member member;


    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();


    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) // enumType은 Ordinal 쓰지 x, String O
    private OrderStatus status;


    public void addOrderItem(OrderItem orderItem) { // 양방향 연관관계이기 때문에 연관관계 편의 메소드를 만든 것임
         orderItems.add(orderItem);
         orderItem.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }



    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


}
