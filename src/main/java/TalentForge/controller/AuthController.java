package TalentForge.controller;


import TalentForge.dto.LoginResponse;
import TalentForge.dto.RegisterRequest;
import TalentForge.dto.UserResponse;
import TalentForge.entity.User;
import TalentForge.service.UserService;
import org.springframework.web.bind.annotation.*;
import TalentForge.dto.LoginRequest;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserService userService;


    public AuthController(UserService userService){
        this.userService = userService;
    }



    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request){

        User user = userService.registerUser(request);
        
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
        String token = userService.loginUser(request);
        return new LoginResponse(token);
    }

}