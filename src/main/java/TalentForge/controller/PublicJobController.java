package TalentForge.controller;

import TalentForge.dto.ApplicationRequest;
import TalentForge.entity.Candidate;
import TalentForge.service.PublicJobService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/jobs")
public class PublicJobController {


    private final PublicJobService publicJobService;


    public PublicJobController(
            PublicJobService publicJobService
    ){
        this.publicJobService = publicJobService;
    }



    @PostMapping("/{jobId}/apply")
    public Candidate applyJob(
            @PathVariable Long jobId,
            @RequestBody ApplicationRequest request
    ){

        return publicJobService.apply(
                jobId,
                request
        );
    }

}