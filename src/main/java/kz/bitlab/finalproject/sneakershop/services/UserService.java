package kz.bitlab.finalproject.sneakershop.services;

import kz.bitlab.finalproject.sneakershop.model.Permission;
import kz.bitlab.finalproject.sneakershop.model.User;
import kz.bitlab.finalproject.sneakershop.repository.PermissionRepository;
import kz.bitlab.finalproject.sneakershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user!=null){
            return user;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User addUser(User user){
        User checkUser=userRepository.findByEmail(user.getEmail());
        if (checkUser==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Permission clientPermission = permissionRepository.findByRole("ROLE_CLIENT");
            if (clientPermission==null){
                return null;
            }
            List<Permission> permissions = new ArrayList<>();
            permissions.add(clientPermission);
            user.setPermissions(permissions);
            return userRepository.save(user);
        }
        return null;
    }

    public User updatePassword (String newPassword, String oldPassword){
        User currentUser=getCurrentSessionUser();
        if (passwordEncoder.matches(oldPassword, currentUser.getPassword())){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
           return userRepository.save(currentUser);
        }
        return null;
    }
    public User getCurrentSessionUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User user=(User) authentication.getPrincipal();
            if (user!=null){
                return user;
            }
        }
        return null;
    }
}
