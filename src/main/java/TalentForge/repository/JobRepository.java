package TalentForge.repository;

import TalentForge.entity.Job;
import TalentForge.entity.User;
import TalentForge.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByCreatedBy(User user);
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByDepartmentIgnoreCase(String department);
    List<Job> findByStatus(JobStatus status);
}