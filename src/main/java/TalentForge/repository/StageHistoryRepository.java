package TalentForge.repository;

import TalentForge.entity.StageHistory;
import TalentForge.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StageHistoryRepository
        extends JpaRepository<StageHistory, Long> {

    List<StageHistory> findByCandidate(Candidate candidate);
}