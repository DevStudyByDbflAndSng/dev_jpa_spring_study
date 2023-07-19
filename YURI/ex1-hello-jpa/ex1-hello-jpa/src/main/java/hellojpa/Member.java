package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    @Id
    private Long id;
    @Column(name = "name")
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING) // enum 타입
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP) // 날짜 타입
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob  // varchar를 넘어선 큰 데이터를 넣고 싶을 때 씀
    private String description;


    public Member() {

    }
}
