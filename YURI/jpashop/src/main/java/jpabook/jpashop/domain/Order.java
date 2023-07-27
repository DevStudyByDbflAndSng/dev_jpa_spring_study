package jpabook.jpashop.domain;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS") // 테이블명이 order 안되는 곳이 있기에 orders로 함
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")  // 이 방식의 설계는 사실, 객체지향스럽지 않다.  이런 설계는 관계형 db에 맞춘 설계이다.
    private Long memberId;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) // enumType은 Ordinal 쓰지 x, String O
    private OrderStatus status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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
