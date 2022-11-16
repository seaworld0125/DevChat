package com.ntt.app.member;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * packageName    : com.ntt.app.member
 * fileName       : Tags
 * author         : Kim
 * date           : 2022-11-16
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "tag")
    @Column(name = "MEMBER_TAGS")
    private List<MemberTag> memberTags;

    @Column(name = "POSITION", unique = true, nullable = false)
    private String position;
}
