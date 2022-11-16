package com.ntt.app.member;

import lombok.*;

import javax.persistence.*;

/**
 * packageName    : com.ntt.app.member
 * fileName       : MemberTag
 * author         : Kim
 * date           : 2022-11-16
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class MemberTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Tag tag;
}
