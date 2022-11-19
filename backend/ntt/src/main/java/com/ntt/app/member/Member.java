package com.ntt.app.member;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.ntt.app.user
 * fileName       : User
 * author         : Kim
 * date           : 2022-10-26
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Setter
    @Column(name = "NAME")
    private String name;

    @Setter
    @Column(name = "AVATAR")
    private String avatar;

    @Setter
    @Column(name = "GITHUB")
    private String github;

    @OneToMany(mappedBy = "member")
    @Column(name = "MEMBER_TAGS")
    @Builder.Default
    private List<MemberTag> memberTags = new ArrayList<>();

    public void addMemberTag(MemberTag memberTag) {
        memberTags.add(memberTag);
    }

    public void removeMemberTag(MemberTag memberTag) {
        memberTags.remove(memberTag);
    }
}
