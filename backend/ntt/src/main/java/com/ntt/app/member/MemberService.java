package com.ntt.app.member;

import com.ntt.app.Exception.BusinessException;
import com.ntt.app.Exception.ErrorCode;
import com.ntt.app.member.domain.Member;
import com.ntt.app.member.dto.MemberResponse;
import com.ntt.app.member.dto.TagUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.ntt.app.member
 * fileName       : MemberService
 * author         : Kim
 * date           : 2022-11-18
 */
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member findById(String id) {

        return memberRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }

    @Transactional
    public Member findByIdWithMemberTags(String id) {

        return memberRepository.findByIdFetchJoinTag(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }

    @Transactional
    public MemberResponse updateTag(TagUpdateRequest request, String id) {

        Member member = findByIdWithMemberTags(id);

        if(!member.updateTag(request)) {

            throw new BusinessException(ErrorCode.MEMBER_TAG_UPDATE_FAIL);
        }
        return MemberResponse.from(member);
    }
}
