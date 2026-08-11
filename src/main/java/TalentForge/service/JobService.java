package TalentForge.service;

import TalentForge.dto.JobRequest;
import TalentForge.entity.Job;
import TalentForge.entity.User;
import TalentForge.enums.JobStatus;
import TalentForge.enums.UserRole;
import TalentForge.exception.ResourceNotFoundException;
import TalentForge.repository.JobRepository;
import TalentForge.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;


    public JobService(
            JobRepository jobRepository,
            UserRepository userRepository
    ) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }



    public Job createJob(JobRequest request, String email) {


        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));



        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setDepartment(request.getDepartment());

        job.setStatus(JobStatus.OPEN);

        job.setCreatedAt(LocalDateTime.now());
        job.setCreatedBy(recruiter);
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));


        if(user.getRole() == UserRole.ADMIN){

            return jobRepository.findAll();

        }


        return jobRepository.findByCreatedBy(user);
    }



    public Job getJobById(Long id) {

        return jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id " + id
                        ));
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

    public List<Job> searchJobs(
            String title,
            String department,
            JobStatus status
    ) {
        if(title != null && !title.isEmpty()) {
            return jobRepository
                    .findByTitleContainingIgnoreCase(title);
        }

        if(department != null && !department.isEmpty()) {
            return jobRepository
                    .findByDepartmentIgnoreCase(department);
        }


        if(status != null) {
            return jobRepository
                    .findByStatus(status);
        }
        return jobRepository.findAll();
    }

    public List<Job> getOpenJobs() {

        return jobRepository.findByStatus(JobStatus.OPEN);

    }
}