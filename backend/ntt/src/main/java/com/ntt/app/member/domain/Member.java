package com.ntt.app.member.domain;

import com.ntt.app.member.dto.TagUpdateRequest;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Column(name = "NAME", length = 20)
    private String name;

    @Setter
    @Column(name = "AVATAR")
    private String avatar;

    @Setter
    @Column(name = "GITHUB")
    private String github;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    public boolean updateTag(TagUpdateRequest request) {

        // "새로운 태그 셋"에 없는 기존 태그는 "기존 태그 셋"에서 제거해야 함
        // "새로운 태그 셋"에만 있는 태그는 "기존 태그 셋"에 추가해야 함

        Set<Tag> newTags = request.getTags()
                .stream()
                .map(
                        E -> Tag.builder()
                                .name(E)
                                .member(this)
                                .build()
                )
                .collect(Collectors.toSet());

        Iterator<Tag> tagIterator = this.tags.iterator();

        while(tagIterator.hasNext()) {

            if(newTags.contains(tagIterator.next())) continue;

            tagIterator.remove();
        }
        this.tags.addAll(newTags);
        return true;
    }
}
