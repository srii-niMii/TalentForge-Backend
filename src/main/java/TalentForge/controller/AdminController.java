package TalentForge.controller;


import TalentForge.dto.RegisterRequest;
import TalentForge.dto.UserResponse;
import TalentForge.service.AdminService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private final AdminService adminService;


    public AdminController(AdminService adminService){

        this.adminService = adminService;
    }


    @PostMapping("/recruiters")
    public UserResponse createRecruiter(
            @RequestBody RegisterRequest request
    ){

        return adminService.createRecruiter(request);
    }

}