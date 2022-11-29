package com.ntt.app.member.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName    : com.ntt.app.member.domain
 * fileName       : TagTest
 * author         : Kim
 * date           : 2022-11-28
 */
class TagTest {

    @Test
    void equalsAndHashCodeTest() {

        Set<Tag> tags = new HashSet<>();

        tags.add(
                Tag.builder()
                        .name("backend")
                        .build()
        );

        Assertions.assertThat(
                tags.contains(
                        Tag.builder()
                                .name("backend")
                                .build()
                )
        ).isTrue();
    }
}