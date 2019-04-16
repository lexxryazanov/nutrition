package com.nexing.nutrition.controller;

import com.nexing.nutrition.database.entity.Role;
import com.nexing.nutrition.database.entity.User;
import com.nexing.nutrition.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@RequestParam(name = "username") String name, @RequestParam(name = "password") String password , Map<String, Object> model) {
        User user = userRepository.findByUsername(name);
        if(user != null){
            model.put("message", "User already exists");
            return "registration";
        } else {
            user  = new User();
            user.setName(name);
            user.setPassword(password);
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
            return "redirect:/login";
        }
    }

}
