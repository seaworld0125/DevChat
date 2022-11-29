package com.ntt.app.member.domain;

import lombok.*;

import javax.persistence.*;

/**
 * packageName    : com.ntt.app.member
 * fileName       : Tags
 * author         : Kim
 * date           : 2022-11-16
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne
    @ToString.Exclude
    private Member member;

    @Override
    public boolean equals(Object t) {
        if(t instanceof Tag) {
            return this.name.equals(((Tag)t).getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
