package TalentForge.service;

import TalentForge.dto.RegisterRequest;
import TalentForge.dto.UserResponse;
import TalentForge.entity.User;
import TalentForge.enums.UserRole;
import TalentForge.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdminService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AdminService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public UserResponse createRecruiter(
            RegisterRequest request
    ){

        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException(
                    "Email already exists"
            );
        }


        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(UserRole.RECRUITER);


        User savedUser = userRepository.save(user);


        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }
}