package com.ntt.app.user;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

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
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;
}
