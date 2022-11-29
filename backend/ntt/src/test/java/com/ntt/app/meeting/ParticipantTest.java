package com.ntt.app.meeting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.ntt.app.meeting
 * fileName       : ParticipantTest
 * author         : Kim
 * date           : 2022-11-29
 */
class ParticipantTest {

    @Test
    void accept() {

        // given
        Participant participant = Participant.builder().build();

        // when
        participant.accept();

        // then
        assertThat(participant.getStatus()).isEqualTo(Participant.Status.ACCEPT);
    }
}