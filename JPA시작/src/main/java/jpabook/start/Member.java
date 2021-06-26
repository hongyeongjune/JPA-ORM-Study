package jpabook.start;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MEMBER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    @Getter
    private String username;

    @Getter
    private int age;

    public void update(int age) {
        this.age = age;
    }

}
