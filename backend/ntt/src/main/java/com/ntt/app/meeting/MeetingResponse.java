package com.ntt.app.meeting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

/**
 * packageName    : com.ntt.app.meeting
 * fileName       : MeetingResponse
 * author         : Kim
 * date           : 2022-11-29
 */
@Getter
@AllArgsConstructor
@Builder
public class MeetingResponse {

    private String title;
    private String content;
    private Set<Participant> participants;

    public static MeetingResponse from(Meeting meeting) {

        return MeetingResponse.builder()
                .title(meeting.getTitle())
                .content(meeting.getContent())
                .participants(meeting.getParticipants())
                .build();
    }
}
