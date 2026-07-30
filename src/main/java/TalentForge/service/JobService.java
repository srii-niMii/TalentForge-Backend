package TalentForge.service;

import TalentForge.dto.JobRequest;
import TalentForge.entity.Job;
import TalentForge.enums.JobStatus;
import TalentForge.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJob(JobRequest request) {

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setDepartment(request.getDepartment());

        job.setStatus(JobStatus.OPEN);

        job.setCreatedAt(LocalDateTime.now());

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}