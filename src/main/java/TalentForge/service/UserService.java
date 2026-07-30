package TalentForge.service;

import TalentForge.dto.RegisterRequest;
import TalentForge.entity.User;
import TalentForge.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public User registerUser(RegisterRequest request){

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());


        return userRepository.save(user);
    }
}