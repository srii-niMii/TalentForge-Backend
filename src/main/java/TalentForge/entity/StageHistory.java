package TalentForge.entity;

import TalentForge.enums.CandidateStage;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stage_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StageHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Enumerated(EnumType.STRING)
    private CandidateStage fromStage;

    @Enumerated(EnumType.STRING)
    private CandidateStage toStage;

    private String changedBy;

    private String note;

    private LocalDateTime changedAt;
}