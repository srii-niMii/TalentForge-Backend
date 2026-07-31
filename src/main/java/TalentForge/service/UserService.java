package TalentForge.service;

import TalentForge.dto.RegisterRequest;
import TalentForge.entity.User;
import TalentForge.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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


        user.setRole(request.getRole());


        return userRepository.save(user);
    }
}