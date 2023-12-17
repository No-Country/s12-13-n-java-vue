package com.latam.unamano.controller.userController;

import com.latam.unamano.service.userService.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Endpoint para obtener la información de los usuarios sin distinción de su rol. " +
            "Mediante este endpoint pueden obtenerse los worker_id o client_id que son diferentes del id" +
            "del usuario. Para los demás endpoints no se usan los id de usuario, sino el id del cliente " +
            "o de worker. ")
    public Object userData(HttpServletRequest request){
        return userService.getUserDataByJWT(request);
    }



}
