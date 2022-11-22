package com.ntt.app.member;

import com.ntt.app.auth.CustomUserDetails;
import com.ntt.app.member.dto.MemberResponse;
import com.ntt.app.member.dto.TagUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * packageName    : com.ntt.app.member
 * fileName       : MemberController
 * author         : Kim
 * date           : 2022-11-08
 */
@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PatchMapping("/tag")
    public MemberResponse updateTag(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody TagUpdateRequest request
    ) {
        return memberService.updateTag(request, customUserDetails.getId());
    }
}
