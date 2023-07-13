package kz.bitlab.finalproject.sneakershop.controller;


import kz.bitlab.finalproject.sneakershop.model.Permission;
import kz.bitlab.finalproject.sneakershop.model.User;
import kz.bitlab.finalproject.sneakershop.repository.PermissionRepository;
import kz.bitlab.finalproject.sneakershop.repository.UserRepository;
import kz.bitlab.finalproject.sneakershop.services.SneakersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired PermissionRepository permissionRepository;

    @Autowired
    SneakersService sneakersService;


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin-panel")
    public String adminPanel(Model model){
        List <User> user=userRepository.findAll();
        model.addAttribute("adamdar",user);
        return "admin";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/add-sneaker")
    public String addSneakerPage(){return "addsneakerpage";}


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin-panel/user-details")
    public String userDetails(@RequestParam("userId") Long userId, Model model) {
        List<Permission> permissions = permissionRepository.findAll();
        User user = userRepository.findById(userId).orElse(null);
            model.addAttribute("user", user);
            model.addAttribute("permissions", permissions);
        return "userEdit";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/admin-panel/save-user-details")
    public String saveUserDetails(@RequestParam("userId") Long userId,
                                  @RequestParam("fullName") String fullName,
                                  @RequestParam("role") String role,
                                  RedirectAttributes redirectAttributes) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setFullName(fullName);

            Permission permission = permissionRepository.findByRole(role);
            if (permission != null) {
                List<Permission> permissions = new ArrayList<>();
                permissions.add(permission);
                user.setPermissions(permissions);
            }
            userRepository.save(user);
            redirectAttributes.addAttribute("success", "");
        }
        else {
            redirectAttributes.addAttribute("error", ""); // Добавляем параметр "error" в URL
        }
        return "redirect:/admin-panel/user-details?userId=" + userId;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/admin-panel/delete-user")
    public String deleteUser(@RequestParam("userId") Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/admin-panel";
    }






}
