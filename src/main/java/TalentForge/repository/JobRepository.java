package TalentForge.repository;

import TalentForge.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByStatus(TalentForge.enums.JobStatus status);

}