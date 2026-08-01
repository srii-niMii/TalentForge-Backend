package TalentForge.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ResumeUploadRequest {

    private MultipartFile file;

}