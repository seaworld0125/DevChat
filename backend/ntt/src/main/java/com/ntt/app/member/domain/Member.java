package com.ntt.app.member.domain;

import com.ntt.app.member.dto.TagUpdateRequest;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "TAGS")
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    public boolean updateTag(TagUpdateRequest request) {

        if(request.getTags() == null || request.getTags().isEmpty() || request.getTags().size() > 3) return false;

        // 새로운 태그 셋에 없는 기존 태그는 기존 태그 셋에서 제거해야 함
        // 새로운 태그 셋에만 있는 태그는 기존 태그 셋에 추가해야 함
        // 둘 다 가지고 있는 태그는 아무런 동작을 하지 않음

        for(Tag tag : this.tags) {

            if(!request.getTags().contains(tag.getName())) {
                this.tags.remove(tag);
            }
        }
        for(String tagName : request.getTags()) {

            if(!this.tags.contains(tagName)) {
                this.tags.add(
                        Tag.builder()
                                .name(tagName)
                                .member(this)
                                .build()
                );
            }
        }
        return true;
    }
}
