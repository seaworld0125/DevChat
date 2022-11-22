package com.ntt.app.member;

import com.ntt.app.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("select m from Member m left join fetch m.tags where m.id = :id")
    Optional<Member> findByIdFetchJoinTag(@Param("id") String id);
}
