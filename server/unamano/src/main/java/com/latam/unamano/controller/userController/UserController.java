package com.latam.unamano.controller.userController;

import com.latam.unamano.service.userService.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/data")
    public Object userData(HttpServletRequest request){
        return userService.getUserDataByJWT(request);
    }



}
