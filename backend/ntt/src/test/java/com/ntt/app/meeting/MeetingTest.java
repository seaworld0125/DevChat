package com.ntt.app.meeting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.ntt.app.meeting
 * fileName       : MeetingTest
 * author         : Kim
 * date           : 2022-11-29
 */
class MeetingTest {

    @Test
    void acceptApplicant_1() {

        // given
        String applicantId = "12345";
        Meeting meeting = Meeting.builder().build();
        meeting.addApplicant(applicantId);

        // when
        meeting.acceptApplicant(applicantId);

        // then
        for(Participant participant : meeting.getParticipants()) {

            if(participant.getMemberId().equals(applicantId)) {

                assertThat(participant.getStatus()).isEqualTo(Participant.Status.ACCEPT);
            }
        }
    }

    @Test
    void addApplicant_1() {

        // given
        Meeting meeting = Meeting.builder().build();

        // when
        meeting.addApplicant("12345");

        // then
        assertThat(meeting.getParticipants().size()).isEqualTo(1);
    }

    @Test
    void addApplicant_2() {

        // given
        Meeting meeting = Meeting.builder().build();

        // when
        meeting.addApplicant("12345");
        meeting.addApplicant("12345");

        // then
        assertThat(meeting.getParticipants().size()).isEqualTo(1);
    }
}