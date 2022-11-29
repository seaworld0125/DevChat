package com.ntt.app.member.domain;

import com.ntt.app.member.dto.TagUpdateRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName    : com.ntt.app.member.domain
 * fileName       : MemberTest
 * author         : Kim
 * date           : 2022-11-21
 */
class MemberTest {

    @Test
    void updateTag_1() {

        // given
        Member member = Member.builder().build();
        Set<String> tagSet = new HashSet<>(Set.of("123", "1234", "12345"));
        TagUpdateRequest updateRequest = new TagUpdateRequest(tagSet);

        // when
        member.updateTag(updateRequest);

        // then
        Assertions.assertThat(member.getTags().size()).isEqualTo(3);
    }

    @Test
    void updateTag_2() {

        // given
        Member member = Member.builder().build();
        Set<String> tagSet = new HashSet<>(Set.of("123", "1234", "12345"));
        TagUpdateRequest updateRequest = new TagUpdateRequest(tagSet);

        // when
        member.updateTag(updateRequest);
        member.updateTag(updateRequest);

        // then
        Assertions.assertThat(member.getTags().size()).isEqualTo(3);
    }
}