package com.ntt.app.translate;

import com.ntt.app.auth.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.ntt.app.translate
 * fileName       : TranslateController
 * author         : Kim
 * date           : 2022-10-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-24        Kim       최초 생성
 */
@RestController
public class TranslateController {

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userDetails.getId();
    }
}
