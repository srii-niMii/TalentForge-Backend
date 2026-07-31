package TalentForge.service;

import TalentForge.dto.JobRequest;
import TalentForge.entity.Job;
import TalentForge.enums.JobStatus;
import TalentForge.exception.ResourceNotFoundException;
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

    public Job getJobById(Long id) {

        return jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id " + id));
    }

    public Job updateJob(Long id, JobRequest request) {

        Job job = getJobById(id);

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setDepartment(request.getDepartment());

        return jobRepository.save(job);
    }


    public void deleteJob(Long id) {

        Job job = getJobById(id);

        jobRepository.delete(job);
    }


    public Job closeJob(Long id) {

        Job job = getJobById(id);

        job.setStatus(JobStatus.CLOSED);

        return jobRepository.save(job);
    }
}