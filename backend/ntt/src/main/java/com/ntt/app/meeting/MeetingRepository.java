package com.ntt.app.meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * packageName    : com.ntt.app.meeting
 * fileName       : MeetingRepository
 * author         : Kim
 * date           : 2022-11-28
 */
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    @Query("select m from Meeting m left join fetch m.participants where m.id = :id")
    Optional<Meeting> findByIdFetchJoinParticipants(@Param("id") Long id);
}
