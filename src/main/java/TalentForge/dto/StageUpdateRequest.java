package TalentForge.dto;

import TalentForge.enums.CandidateStage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StageUpdateRequest {

    private CandidateStage stage;

    private String changedBy;

    private String note;
}