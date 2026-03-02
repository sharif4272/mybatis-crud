package com.revo.mybatis.controller;

import com.revo.mybatis.model.User;
import com.revo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user,
                           RedirectAttributes redirectAttributes) {
        try {
            if (userService.isEmailExists(user.getEmail())) {
                redirectAttributes.addFlashAttribute("error",
                        "This mail already exists!");
                return "redirect:/users/add";
            }

            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("success",
                    "User has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Error in saving user!");
        }
        return "redirect:/users/home";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "edit-user";
        }
        return "redirect:/users/home";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user,
                             RedirectAttributes redirectAttributes) {
        try {
            userService.updateUser(user);
            redirectAttributes.addFlashAttribute("success",
                    "User has been updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Failed to update user!");
        }
        return "redirect:/users/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id,
                             RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("success",
                    "User has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Failed to delete user!");
        }
        return "redirect:/users/home";
    }
}