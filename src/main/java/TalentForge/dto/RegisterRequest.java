package TalentForge.dto;

import TalentForge.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private UserRole role;
}