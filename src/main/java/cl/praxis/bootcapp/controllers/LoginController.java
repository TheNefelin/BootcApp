package cl.praxis.bootcapp.controllers;

import cl.praxis.bootcapp.entities.LoginDTO;
import cl.praxis.bootcapp.entities.Role;
import cl.praxis.bootcapp.entities.UserEntity;
import cl.praxis.bootcapp.services.imp.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class LoginController {
    @Autowired
    CustomUserDetailsService userDetailsService;

    @GetMapping("/login")
    public String routeLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String routeRegister() {
           return "register";
    }


    @PostMapping("/register")
    public String userRegister(@ModelAttribute UserEntity user, @RequestParam String passwordConfirmation) {
        if (!user.getPassword().equals(passwordConfirmation)) {
            return "redirect:/register?error=passwordMismatch";
        }

          UserEntity userNew = userDetailsService.register(user);
           if(userNew != null && user.getId() != 0) {
               return "login";
           } else {
               return "redirect:/register";
           }
    }


    @PostMapping("/login")
    public String userLogin(@RequestBody LoginDTO loginDTO, String email) {
        LoginDTO user = userDetailsService.authenticate(loginDTO, email);
            if(user != null) {
                return "/user/users";
            }
        return "redirect:/login";
    }
}
