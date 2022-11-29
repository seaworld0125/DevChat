package com.ntt.app.meeting;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * packageName    : com.ntt.app.meeting
 * fileName       : Meeting
 * author         : Kim
 * date           : 2022-11-28
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Participant> participants = new HashSet<>();

    public boolean acceptApplicant(String id) {

        for(Participant participant : participants) {

            if(!participant.getMemberId().equals(id)) continue;

            participant.accept();
            return true;
        }
        return false;
    }

    public boolean addApplicant(String id) {

        if(this.participants.contains(id)) return false;

        this.participants.add(
                Participant.builder()
                        .memberId(id)
                        .meeting(this)
                        .build()
        );
        return true;
    }
}
