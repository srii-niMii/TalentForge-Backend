package TalentForge.controller;

import TalentForge.dto.JobRequest;
import TalentForge.entity.Job;
import TalentForge.enums.JobStatus;
import TalentForge.service.JobService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public Job createJob(
            @RequestBody JobRequest request,
            Authentication authentication
    ) {

        return jobService.createJob(
                request,
                authentication.getName()
        );
    }

    @GetMapping
    public List<Job> getAllJobs(
            Authentication authentication
    ) {

        return jobService.getAllJobs(
                authentication.getName()
        );
    }

    @GetMapping("/{id}")
    public Job getJobById(
            @PathVariable Long id
    ) {

        return jobService.getJobById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable Long id
    ){

        jobService.deleteJob(id);

        return "Job deleted successfully";
    }

    @PutMapping("/{id}")
    public Job updateJob(
            @PathVariable Long id,
            @RequestBody JobRequest request
    ){

        return jobService.updateJob(id, request);
    }

    @PatchMapping("/{id}/close")
    public Job closeJob(
            @PathVariable Long id
    ){

        return jobService.closeJob(id);
    }

    @GetMapping("/search")
    public List<Job> searchJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) JobStatus status
    ) {
        return jobService.searchJobs(
                title,
                department,
                status
        );

    }
}