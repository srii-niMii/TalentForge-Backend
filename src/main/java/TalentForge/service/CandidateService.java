package TalentForge.service;

import TalentForge.dto.CandidateRequest;
import TalentForge.dto.StageUpdateRequest;
import TalentForge.entity.Candidate;
import TalentForge.entity.Job;
import TalentForge.entity.StageHistory;
import TalentForge.entity.User;
import TalentForge.enums.CandidateStage;
import TalentForge.enums.UserRole;
import TalentForge.exception.ResourceNotFoundException;
import TalentForge.repository.CandidateRepository;
import TalentForge.repository.JobRepository;
import TalentForge.repository.StageHistoryRepository;
import TalentForge.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final StageHistoryRepository stageHistoryRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;


    public CandidateService(
            CandidateRepository candidateRepository,
            JobRepository jobRepository,
            StageHistoryRepository stageHistoryRepository,
            UserRepository userRepository, FileStorageService fileStorageService
    ) {

        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
        this.stageHistoryRepository = stageHistoryRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    public Candidate createCandidate(CandidateRequest request) {

        Job job = jobRepository.findById(
                request.getJobId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Job not found with id "
                                + request.getJobId()
                )
        );
        if (candidateRepository.existsByEmailAndJob(
                request.getEmail(),
                job
        )) {
            throw new IllegalArgumentException(
                    "You have already applied for this job."
            );
        }


        Candidate candidate = new Candidate();

        candidate.setName(request.getName());
        candidate.setEmail(request.getEmail());
        candidate.setPhone(request.getPhone());
        candidate.setSource(request.getSource());

        candidate.setJob(job);

        candidate.setCurrentStage(CandidateStage.APPLIED);

        candidate.setCreatedAt(LocalDateTime.now());

        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates(String email) {


        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));


        if(user.getRole() == UserRole.ADMIN) {

            return candidateRepository.findAll();

        }


        return candidateRepository.findByJobCreatedBy(user);
    }


    public List<Candidate> getMyApplications(String email) {
        return candidateRepository.findByEmail(email);
    }


    public Candidate getCandidateById(Long id) {


        return candidateRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found with id " + id
                        ));
    }





    public Candidate updateCandidate(
            Long id,
            CandidateRequest request
    ) {


        Candidate candidate = getCandidateById(id);


        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id " + request.getJobId()
                        ));



        candidate.setName(request.getName());
        candidate.setEmail(request.getEmail());
        candidate.setPhone(request.getPhone());
        candidate.setSource(request.getSource());
        candidate.setJob(job);

        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id) {

        Candidate candidate = getCandidateById(id);

        candidateRepository.delete(candidate);
    }

    public Candidate updateStage(
            Long id,
            StageUpdateRequest request
    ) {


        Candidate candidate = getCandidateById(id);


        CandidateStage oldStage =
                candidate.getCurrentStage();



        candidate.setCurrentStage(
                request.getStage()
        );


        Candidate updatedCandidate =
                candidateRepository.save(candidate);




        StageHistory history = new StageHistory();
        history.setCandidate(candidate);
        history.setFromStage(oldStage);
        history.setToStage(request.getStage());
        history.setChangedBy(request.getChangedBy());
        history.setNote(request.getNote());
        history.setChangedAt(LocalDateTime.now());
        stageHistoryRepository.save(history);
        return updatedCandidate;
    }

    public Candidate uploadResume(
            Long id,
            MultipartFile file
    ){
        Candidate candidate =
                getCandidateById(id);

        String filePath =
                fileStorageService.saveFile(file);

        candidate.setResumeUrl(filePath);

        return candidateRepository.save(candidate);
    }

    public List<Candidate> getApplicantsByJob(
            Long jobId,
            String email
    ) {


        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id " + jobId
                        )
                );



        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );


        if (!job.getCreatedBy()
                .getId()
                .equals(recruiter.getId())) {


            throw new RuntimeException(
                    "You cannot access applicants of this job"
            );

        }



        return candidateRepository.findByJob(job);

    }
}