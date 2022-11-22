package com.ntt.app.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * packageName    : com.ntt.app.member
 * fileName       : MemberUpdateRequest
 * author         : Kim
 * date           : 2022-11-21
 */
@Getter
@NoArgsConstructor
public class TagUpdateRequest {

    private Set<String> tags;
}
