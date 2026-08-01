package TalentForge.service;

import TalentForge.dto.ApplicationRequest;
import TalentForge.entity.Candidate;
import TalentForge.entity.Job;
import TalentForge.enums.CandidateStage;
import TalentForge.exception.ResourceNotFoundException;
import TalentForge.repository.CandidateRepository;
import TalentForge.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PublicJobService {


    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;


    public PublicJobService(
            JobRepository jobRepository,
            CandidateRepository candidateRepository
    ){

        this.jobRepository = jobRepository;
        this.candidateRepository = candidateRepository;

    }


    public Candidate apply(
            Long jobId,
            ApplicationRequest request
    ){

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id " + jobId
                        ));


        if(candidateRepository.existsByEmail(request.getEmail())){

            throw new IllegalArgumentException(
                    "Candidate already applied"
            );

        }


        Candidate candidate = new Candidate();


        candidate.setName(
                request.getName()
        );


        candidate.setEmail(
                request.getEmail()
        );


        candidate.setPhone(
                request.getPhone()
        );


        candidate.setSource(
                "WEBSITE"
        );


        candidate.setJob(job);


        candidate.setCurrentStage(
                CandidateStage.APPLIED
        );


        candidate.setCreatedAt(
                LocalDateTime.now()
        );

        return candidateRepository.save(candidate);

    }

}