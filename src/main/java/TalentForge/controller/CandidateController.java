package TalentForge.controller;

import TalentForge.dto.CandidateRequest;
import TalentForge.dto.StageUpdateRequest;
import TalentForge.entity.Candidate;
import TalentForge.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public Candidate createCandidate(@Valid @RequestBody CandidateRequest request) {
        return candidateService.createCandidate(request);
    }

    @GetMapping
    public List<Candidate> getAllCandidates(
            Authentication authentication
    ){

        return candidateService.getAllCandidates(
                authentication.getName()
        );
    }

    @GetMapping("/my-applications")
    public List<Candidate> getMyApplications(
            Authentication authentication
    ) {

        return candidateService.getMyApplications(
                authentication.getName()
        );

    }

    @GetMapping("/{id}")
    public Candidate getCandidateById(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }

    @PutMapping("/{id}")
    public Candidate updateCandidate(
            @PathVariable Long id,
            @Valid @RequestBody CandidateRequest request) {

        return candidateService.updateCandidate(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCandidate(@PathVariable Long id) {

        candidateService.deleteCandidate(id);

        return "Candidate deleted successfully";
    }

    @PostMapping("/{id}/resume")
    public Candidate uploadResume(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ){

        return candidateService.uploadResume(
                id,
                file
        );
    }

    @PatchMapping("/{id}/stage")
    public Candidate updateStage(
            @PathVariable Long id,
            @RequestBody StageUpdateRequest request){

        return candidateService.updateStage(id, request);
    }

    @GetMapping("/jobs/{jobId}/applicants")
    public List<Candidate> getApplicantsByJob(
            @PathVariable Long jobId,
            Authentication authentication
    ){

        return candidateService.getApplicantsByJob(
                jobId,
                authentication.getName()
        );

    }
}