package TalentForge.controller;


import TalentForge.dto.RegisterRequest;
import TalentForge.entity.User;
import TalentForge.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserService userService;


    public AuthController(UserService userService){
        this.userService = userService;
    }



    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request){

        return userService.registerUser(request);

    }

}