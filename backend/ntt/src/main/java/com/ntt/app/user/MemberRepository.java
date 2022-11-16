package com.ntt.app.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.ntt.app.user
 * fileName       : UserRepository
 * author         : Kim
 * date           : 2022-11-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-06        Kim       최초 생성
 */
public interface MemberRepository extends JpaRepository<Member, String> {
}
