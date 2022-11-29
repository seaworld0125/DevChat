package com.ntt.app.member.dto;

import com.ntt.app.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.ntt.app.member
 * fileName       : MemberDto
 * author         : Kim
 * date           : 2022-11-18
 */
@Getter
@AllArgsConstructor
@Builder
public class MemberResponse {

    private String name;
    private String avatar;
    private String github;
    private List<String> tags;

    public static MemberResponse from(Member member) {

        return MemberResponse.builder()
                .name(member.getName())
                .avatar(member.getAvatar())
                .github(member.getGithub())
                .tags(
                        member.getTags().stream()
                                .map(e -> e.getName())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
