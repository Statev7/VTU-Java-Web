package com.example.MoviesSystem.controllers;

import com.example.MoviesSystem.features.users.models.UserFormModel;
import com.example.MoviesSystem.features.users.services.contracts.UserService;
import com.example.MoviesSystem.security.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth/login")
    public String Login(){
        if(SecurityUtil.getSessionUser() != null){
            return "redirect:/movies";
        }
        
        return "login";
    }


    @GetMapping("/auth/register")
    public String Register(Model model){
        if(SecurityUtil.getSessionUser() != null){
            return "redirect:/movies";
        }

        UserFormModel user = new UserFormModel();
        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("/auth/register")
    public String Register(@Valid @ModelAttribute("user") UserFormModel user, BindingResult bindingResult, Model model){

        if(this.userService.isExistByUsername(user.getUsername())){
            bindingResult.addError(new FieldError("user", "username", "This username is already taken."));
        }

        if(bindingResult.hasErrors()){
            return "register";
        }

        this.userService.create(user);

        return "redirect:/auth/login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "access-denied";
    }
}
