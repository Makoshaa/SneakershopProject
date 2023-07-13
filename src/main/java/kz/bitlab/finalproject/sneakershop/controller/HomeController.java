package kz.bitlab.finalproject.sneakershop.controller;

import kz.bitlab.finalproject.sneakershop.model.User;
import kz.bitlab.finalproject.sneakershop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(value = "/sign-in-page")
    public String signinPage() {
        return "signin";
    }

    @GetMapping(value = "/sign-up-page")
    public String signupPage() {
        return "signup";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profilePage() {
        return "profile";
    }

    @PostMapping(value = "/to-sign-up")
    public String toSignUp(@RequestParam(name = "user_email") String email,
                           @RequestParam(name = "user_password") String password,
                           @RequestParam(name = "user_repeat_password") String repeatPassword,
                           @RequestParam(name = "user_full_name") String fullName) {

        if(password.equals(repeatPassword)){
            User user=new User();
            user.setEmail(email);
            user.setFullName(fullName);
            user.setPassword(password);
            User newUser=userService.addUser(user);
            if (newUser!=null){
                return "redirect:/sign-up-page?success";
            }else {
                return "rerect:/sign-up-page?emailerror";
            }
        }else {
            return "redirect:/sign-up-page?passworderror";
        }
    }

    @PostMapping(value = "/to-update-password")
    public String toUpdatePassword(@RequestParam(name = "user_old_password") String oldPassword,
                           @RequestParam(name = "user_new_password") String newPassword,
                           @RequestParam(name = "user_repeat_new_password") String repeatNewPassword) {
       if (newPassword.equals(repeatNewPassword)){

        User user=userService.updatePassword(newPassword, oldPassword);
        if (user!=null){
            return "redirect:/profile?success";
        }else {
            return "redicrect:/profile?oldpassworderror";
        }
       }else {
           return "redirect:/profile?passwordmismatch";
       }
    }
}
