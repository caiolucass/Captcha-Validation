package com.example.captchavalidation.controller;

import com.example.captchavalidation.model.User;
import com.example.captchavalidation.service.IUserService;
import com.example.captchavalidation.model.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.apiclub.captcha.Captcha;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("/register")
    public String registerUser(Model model){
        User user = new User();
        user.getCaptcha();
        model.addAttribute("user", user);
        return "registerUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, Model model){
        if(user.getCaptcha().equals(user.getHiddenCaptcha())){
            service.createUser(user);
            model.addAttribute("message", "Usuario Registrado com sucesso!");
            return "redirect:allUsers";
        }else{
            model.addAttribute("message", "Captch invalido!");
            user.getCaptcha();
            model.addAttribute("user", user);
        }
        return "registerUser";
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model){
        List<User> userList = service.getAllUsers();
        model.addAttribute("userList", userList);
        return "listUsers";
    }

    @GetMapping("/{id}")
    public String getAllUsers(User user, Model model){
        if(user.getId() != null){
            service.getOneUser(user.getId());
            model.addAttribute("user", user);
            return "redirect:user";
        }else{
            model.addAttribute("message", "Usuario nao encontrado ou nao existe");
        }
        return "registerUser";
    }

    private void getCaptcha(User user){
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        user.setHiddenCaptcha(captcha.getAnswer());
        user.setCaptcha(""); //valor interado pelo usuario
        user.setRealCaptcha(CaptchaUtil.encoceCaptcha(captcha));
    }
}
