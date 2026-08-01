package TalentForge.repository;

import TalentForge.entity.Candidate;
import TalentForge.entity.Job;
import TalentForge.entity.User;
import TalentForge.enums.CandidateStage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByJob(Job job);

    List<Candidate> findByCurrentStage(CandidateStage stage);

    List<Candidate> findByJobAndCurrentStage(Job job, CandidateStage stage);

    List<Candidate> findByNameContainingIgnoreCase(String name);

    List<Candidate> findByJobCreatedBy(User user);

    boolean existsByEmail(String email);
}