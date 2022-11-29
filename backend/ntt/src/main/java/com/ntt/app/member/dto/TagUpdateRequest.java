package com.ntt.app.member.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * packageName    : com.ntt.app.member
 * fileName       : MemberUpdateRequest
 * author         : Kim
 * date           : 2022-11-21
 */
@NoArgsConstructor
@AllArgsConstructor
public class TagUpdateRequest {

    @Size(min = 1, max = 3)
    private Set<String> tags;

    private void checkValid() {
        if(tags == null) tags = new HashSet<>();
    }

    public Set<String> getTags() {
        checkValid();
        return tags;
    }
}
