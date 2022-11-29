package com.ntt.app.meeting;

import com.ntt.app.exception.BusinessException;
import com.ntt.app.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.ntt.app.meeting
 * fileName       : MeetingService
 * author         : Kim
 * date           : 2022-11-28
 */
@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

    @Transactional
    public Meeting findByIdWithParticipants(Long id) {
        return meetingRepository.findByIdFetchJoinParticipants(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEETING_NOT_FOUND));
    }

    @Transactional
    public MeetingResponse acceptApplicant(Long meetingId, String applicantId) {

        Meeting meeting = findByIdWithParticipants(meetingId);

        if(!meeting.acceptApplicant(applicantId)) {

            throw new BusinessException(ErrorCode.MEETING_APPLICANT_CHANGE_STATUS_FAIL);
        }
        return null;
    }
}
