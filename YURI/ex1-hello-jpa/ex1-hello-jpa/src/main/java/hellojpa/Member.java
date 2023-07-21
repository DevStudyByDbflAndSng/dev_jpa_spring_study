package hellojpa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.EnumType.ORDINAL;

@Entity
public class Member {

    @Id
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "varchar(100) default 'EMPTY'") // nullable = false하면 not null 제약 조건이 걸림, updatable = false 하면 이 컬럼은 절대 변경 안됨.
    private String username;
    private BigDecimal age;
    @Enumerated(EnumType.STRING) // enum 타입 쓰고 싶을 때 // ** 주의!! ENUM타입에 ORDINAL 쓰면 안됨!! 굉장히 위험 !!!!
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP) // 과거버전 , 날짜 타입
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP) // 과거버전
    private Date lastModifiedDate;

    private LocalDate testLocalDate; // 년,월   // 최신 버전
    private LocalDateTime testLocalDateTime; // 년,월,일  // 최신 버전
    @Lob  // varchar를 넘어선 큰 데이터를 넣고 싶을 때 씀
    private String description;

    @Transient  // db -x / 메모리에서만 쓰겠다는 의미
    private int tmep;

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTmep() {
        return tmep;
    }

    public void setTmep(int tmep) {
        this.tmep = tmep;
    }
}
