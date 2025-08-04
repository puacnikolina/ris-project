package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

import model.Role;
import model.User;

@Controller
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserRepository ur;
    
    @Autowired
    private RoleRepository rr;

    @GetMapping("getUsersOverview")
    public String getUsersOverview(Model model) {
        List<User> users = ur.findAll();
        model.addAttribute("users", users);
        return "admin/usersOverview";
    }
    
    @PostMapping("changeRole")
    public String changeUserRole(@RequestParam int id) {
        User user = ur.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole().getName().equals("USER")) {
            Role adminRole = rr.findByName("ADMIN");
            user.setRole(adminRole);
        } else {
            Role userRole = rr.findByName("USER");
            user.setRole(userRole);
        }

        ur.save(user);
        return "redirect:/users/getUsersOverview";
    }

}
