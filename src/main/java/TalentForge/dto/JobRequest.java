package TalentForge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String department;

}