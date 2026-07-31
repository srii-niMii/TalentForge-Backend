package TalentForge.controller;

import TalentForge.dto.CandidateRequest;
import TalentForge.entity.Candidate;
import TalentForge.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
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
}