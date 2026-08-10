package TalentForge.controller;

import TalentForge.dto.LoginResponse;
import TalentForge.dto.RegisterRequest;
import TalentForge.dto.UserResponse;
import TalentForge.entity.User;
import TalentForge.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import TalentForge.dto.LoginRequest;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserService userService;


    public AuthController(UserService userService){
        this.userService = userService;
    }



    @PostMapping("/register/recruiter")
    public UserResponse register(@Valid @RequestBody RegisterRequest request){

        User user = userService.registerRecruiter(request);
        
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

    }

    @PostMapping("/register/candidate")
    public UserResponse registerCandidate(
            @Valid @RequestBody RegisterRequest request
    ) {

        User user =
                userService.registerCandidate(request);


        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

    }


    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        User user = userService.loginUser(request);
        String token = userService.generateToken(user);

        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                token );
    }

}