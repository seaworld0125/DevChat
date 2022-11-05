package com.ntt.app.user;

import lombok.*;

import javax.persistence.*;

/**
 * packageName    : com.ntt.app.user
 * fileName       : User
 * author         : Kim
 * date           : 2022-10-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-26        Kim       최초 생성
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Setter
    @Column(name = "NAME")
    private String name;

    @Setter
    @Column(name = "AVATAR_URL")
    private String avatarUrl;
}
