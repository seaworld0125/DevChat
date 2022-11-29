package com.ntt.app.meeting;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
@JsonIncludeProperties({"memberId", "status"})
public class Participant {

    public enum Status {
        PENDING,
        ACCEPT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MEMBER_ID", nullable = false)
    private String memberId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDING;

    @ManyToOne
    @ToString.Exclude
    private Meeting meeting;

    @Override
    public boolean equals(Object t) {
        if(t instanceof Participant) {
            return this.memberId.equals(((Participant) t).getMemberId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.memberId.hashCode();
    }

    public void accept() {
        this.status = Status.ACCEPT;
    }
}