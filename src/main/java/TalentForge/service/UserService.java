package TalentForge.service;

import TalentForge.dto.RegisterRequest;
import TalentForge.entity.User;
import TalentForge.enums.UserRole;
import TalentForge.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import TalentForge.dto.LoginRequest;
import TalentForge.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public User registerUser(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){

            throw new IllegalArgumentException(
                    "Email already registered"
            );
        }
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );


        user.setRole(UserRole.RECRUITER);


        return userRepository.save(user);
    }

    public String loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid email or password"
                        ));

        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )){

            throw new IllegalArgumentException(
                    "Invalid email or password"
            );
        }


        return jwtService.generateToken(user.getEmail());
    }
}